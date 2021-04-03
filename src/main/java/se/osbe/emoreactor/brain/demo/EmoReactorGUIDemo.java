package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.config.EmoTilesPanel;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmoReactorGUIDemo extends JFrame {

    private static final String LOGO_IMG_PATH = "src/main/resources/brain.jpg";
    private static final String DELIMITER = ", ";
    // private static final String LOGO_IMG_PATH = "src/main/resources/brain-rotate.gif";
    private static final DiceHelper dice = new DiceHelper();
    // https://www.canva.com/colors/color-palettes/speckled-eggs/
    private static final Color COLOR_NAVY_BLUE = new Color(0x003B73);
    private static final Color COLOR_ROYAL_BLUE = new Color(0x0074B7);
    private static final Color COLOR_BLUE_GROTTO = new Color(0x60A3D9);
    private static final Color COLOR_BABY_BLUE = new Color(0xBFD7ED);
    // https://www.canva.com/colors/color-palettes/summer-splash/
    private static final Color COLOR_BLUE_GREEN = new Color(0x75E6DA);
    // "...my own recipie, way more dangerous..."
    private static final Color AWARENESS_COLOR = new Color(241, 241, 191);
    private static final Color INPUT_FEELING_COLOR = new Color(117, 230, 218);
    private static final Color TIME_SAMPLE_COLOR = new Color(205, 225, 250);
    private static final Color UPPER_ROW_BACKGROUND_COLOR = Color.WHITE; //new Color(218, 233, 255);
    private static final Font MAIN_FONT = new Font("Sans serif", Font.PLAIN, 14);
    private static final Font MINI_FONT = new Font("Sans serif", Font.PLAIN, 10);
    private static String FEELING_EXAMPLE_STR = "HAP-D60000-H12-L6-A9-D11-S50-R30";
    //    private JScrollPane textArea1scrollPane = null;
    EmoTilesPanel currentFeelingPanel = null;
    private JTextField feelingInputTextField = new JTextField(FEELING_EXAMPLE_STR);
    private JTextField awarenessTextfield = new JTextField();
    private JTextField timeSampleLabel = new JTextField();
    private JScrollPane textArea2scrollPane = null;
    private JScrollPane textArea3scrollPane = null;
    private JTextArea textArea1 = new JTextArea(); // emo now
    private JTextArea textArea2 = new JTextArea(); // Personlaity
    private JTextArea textArea3 = new JTextArea(); // feelings in progress
    private JButton offerFeelingJButton = new JButton();
    private JPanel upperRowPanel = new JPanel();
    private Brain brain = new Brain(); // default everything!


    public EmoReactorGUIDemo() {
        super("Emo Reactor");

        brain.setAwarenessPercentage(100);

        JLabel logo = new JLabel(new ImageIcon(LOGO_IMG_PATH));
        logo.setPreferredSize(new Dimension(10, 50));
        logo.setBounds(0, 2000, 440, 20);
        upperRowPanel.setLayout(new GridLayout(2, 2, 2, 2));
        upperRowPanel.add(logo);

        timeSampleLabel.setBackground(TIME_SAMPLE_COLOR);
        timeSampleLabel.setFont(MAIN_FONT);
        upperRowPanel.add(timeSampleLabel);
        upperRowPanel.setBackground(UPPER_ROW_BACKGROUND_COLOR);

        // feelingInputTextField.setPreferredSize(new Dimension(500, 30));
        feelingInputTextField.setBackground(INPUT_FEELING_COLOR);
        feelingInputTextField.setEditable(true);
        feelingInputTextField.addActionListener((e) -> {
                    Optional<Emotion> emoOpt = BrainHelper.getEmotionForPattern(feelingInputTextField.getText());
                    if (emoOpt.isPresent()) {
                        brain.offerInboundFeeling(new Feeling(Arrays.asList(emoOpt.get())));
                    }
                }
        );
        upperRowPanel.add(feelingInputTextField);

        awarenessTextfield.setBackground(AWARENESS_COLOR);
        awarenessTextfield.addActionListener((e) -> {
            brain.setAwarenessPercentage(Integer.valueOf(awarenessTextfield.getText().replaceAll("\\D", "")));
        });
        awarenessTextfield.setText("Awareness: " + String.valueOf(Math.round(brain.getPerceptionAwareness())) + " %  --  " + brain.getName());
        upperRowPanel.add(awarenessTextfield);


//        textArea1.setBackground(COLOR_BABY_BLUE);
//        textArea1.setFont(MINI_FONT);
//        textArea1.setLineWrap(true);
//        textArea1scrollPane = new JScrollPane(textArea1);
//        textArea1scrollPane.setPreferredSize(new Dimension(300, 400));
        currentFeelingPanel = new EmoTilesPanel(brain.getCurrentFeeling(), brain.getInclinations());

        textArea2.setBackground(COLOR_ROYAL_BLUE);
        textArea2.setFont(MINI_FONT);
        textArea2.setForeground(Color.WHITE);
        textArea2.setLineWrap(true);
        textArea2.setText(
                new StringBuilder()
                        .append("Brain Facts (Id: ").append(brain.getUUID().toString().split("-")[0]).append(")\n\n")
                        .append("Name: ").append(brain.getName()).append("\n")
                        //.append("Awareness: ").append(brain.getPerceptionAwareness()).append(" %\n")
                        .append("\n")
                        .append("PERSONALITY:\n")
                        .append(brain.getPersonalityBaseline())
                        .toString()
        );
        textArea2.setEditable(false);
        textArea2scrollPane = new JScrollPane(textArea2);
        textArea2scrollPane.setWheelScrollingEnabled(true);
        textArea2scrollPane.setPreferredSize(new Dimension(300, 400));

        textArea3.setBackground(COLOR_BLUE_GROTTO);
        textArea3.setFont(MINI_FONT);
        textArea3.setLineWrap(true);
        textArea3.setEditable(false);
        textArea3scrollPane = new JScrollPane(textArea3);
        textArea3scrollPane.setWheelScrollingEnabled(true);
        textArea3scrollPane.setPreferredSize(new Dimension(300, 400));

        offerFeelingJButton.setText("Randomized Feeling");
        offerFeelingJButton.setFont(MAIN_FONT);
        offerFeelingJButton.setPreferredSize(new Dimension(100, 30));
        offerFeelingJButton.addActionListener((e) -> {
            Feeling feeling = generateRandomFeeling();
            brain.offerInboundFeeling(feeling);
        });

        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().setLayout(new BorderLayout(1, 1));
        this.getContentPane().add("North", upperRowPanel);
        this.getContentPane().add("West", textArea2scrollPane);
        this.getContentPane().add("East", currentFeelingPanel); // textArea1scrollPane
        this.getContentPane().add("Center", textArea3scrollPane);
        this.getContentPane().add("South", offerFeelingJButton);

        offerFeelingJButton.setFocusPainted(true);
    }

    public static void main(String[] args) throws InterruptedException {
        EmoReactorGUIDemo window = new EmoReactorGUIDemo();
        window.setSize(1200, 1000);
        window.setLocation(250, 150);
        // window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        // new SplashWindow("src/main/resources/brain.gif", window);
        while (true) {
            window.setTimeSampleTextField(String.format("Time sample: %d", window.getBrain().getTurnCounter()));

            Map<EmotionType, Float> currentFeeling = window.getBrain().nextTurn();
            Map<EmotionType, Float> currentInclinations = window.getBrain().getInclinations();

//            window.appendTextArea1(
//                            feelingNow.entrySet().stream()
//                                    .filter(emo -> emo.getValue() >= 0f)
//                                    .map(e -> {
//                                        Float incl = inclinations.get(e.getKey());
//                                        return e.getKey() + " [ " + Math.round(e.getValue()) + " ]    "
//                                                + ((incl != null && incl.compareTo(0f) > 0) ? "+" : "") + incl;
//                                    })
//                                    .sorted()
//                                    .collect(Collectors.joining("\n"))
//            );

            window.updateCurrentFeelingPanel(currentFeeling, currentInclinations);

            window.setTextArea3(
                    // .filter(f -> !f.isExpired())
                    window.brain.getFeelings().stream().map(f -> {
                        long timeLeft = (f.getDuration() - (new Date().getTime() - f.getInitialTimeStamp()));
                        long elapsedTime = f.getElapsedTime();
                        return new StringBuilder()
                                .append("Feeling-ID  : ").append(f.getUID()).append("\n")
                                .append("Created-TS  : ").append(f.getInitialTimeStamp()).append("\n")
                                .append("Time left   : ").append(BrainHelper.getTimeAsString(timeLeft >= 0L ? timeLeft : 0)).append("\n")
                                .append("Duration  : ").append(BrainHelper.getTimeAsString(f.getDuration())).append("\n")
                                .append(
                                        f.getEmotions().stream()
                                                .map(e -> {
                                                    return new StringBuilder()
                                                            .append("  " + e.getEmotionType().getEmotionName()).append(" [")
                                                            .append(BrainHelper.getTimeAsString(e.getDurationTime())).append(", ")
                                                            .append(Math.round(e.getAmplitudePeak())).append(", ")
                                                            .append(Math.round(e.getAmplitudeSustain())).append(", ")
                                                            .append(Math.round(e.getAttackPercent())).append(", ")
                                                            .append(Math.round(e.getDecayPercent())).append(", ")
                                                            .append(Math.round(e.getSustainPercent())).append(", ")
                                                            .append(Math.round(e.getReleasePercent())).append("]")
                                                            .append("\n")
                                                            .toString();
                                                })
                                                .collect(Collectors.joining())
                                )
                                .append("____________________________________")
                                .append("\n")
                                .toString();
                    }).collect(Collectors.joining())
            );

            if (currentFeeling.entrySet().stream().filter(e -> e.getValue() >= 100).count() > 0) {
//                window.appendTextArea1("\n\n" + window.getBrain().getName() + " has passed out from emotional trauma!");
//                window.appendTextArea1("Brain died due to emotional overload: " + currentFeeling.entrySet().stream().filter(e -> e.getValue() >= 90).collect(Collectors.toList()));
//                window.textArea1.setBackground(Color.PINK);
                break;
            }
            if (
                    currentFeeling.entrySet().stream()
                            .filter(e -> e.getKey() == EmotionType.HAPPY && e.getValue() <= 2)
                            .filter(e -> currentInclinations.get(e.getKey()) <= -1f)
                            .findAny()
                            .isPresent()
            ) {
                JOptionPane.showMessageDialog(null, window.getBrain().getName() + " is reacting to a feeling pattern of HAPPY <= 2 having inclination of -1 or less");
                System.exit(1);
            }


//            if (feelingNow.entrySet().stream().filter(e -> e.getValue() > 0).count() == 0) {
//                window.appendTextArea1("\n" + window.getBrain().getName() + " is feeling nothing!");
//            }

            Thread.sleep(1000);
            window.wipeTextArea1();
        }
        JOptionPane.showMessageDialog(null, window.getBrain().getName() + " is now dead due to emotional overload (R.I.P)");
        System.exit(1);
    }

    // brain demo stuff
    private static Feeling generateRandomFeeling() {
        int noOfEmo = dice.getRandomFloatBetween(1f, 10f).intValue();
        Feeling.FeelingBuilder feeling = Feeling.builder();
        IntStream.range(0, noOfEmo).forEach(i -> {
            EmotionType emotionType = dice.randomEmotionType();
            Integer amplitudePeak = dice.getRandomFibonacci(10);
            Integer amplitudeSustain = dice.getRandomFibonacci(10);
            Integer duration = Math.round(dice.getRandomFloatBetween(5000, 60000));
            feeling.addEmotions(Arrays.asList(
                    Emotion.builder()
                            .emotionType(emotionType)
                            .amplitudePeak(amplitudePeak).amplitudeSustain(amplitudeSustain)
                            .durationTime(duration)
                            .attack(10).decay(30).sustain(50).release(10)
                            .build()
                    )
            );
        });
        return feeling.build();
    }

    public void wipeTextArea1() {
        this.textArea1.setText("");
        textArea1.setCaretPosition(textArea1.getText().length());
    }

    public void setTimeSampleTextField(String str) {
        timeSampleLabel.setText(str);
    }

    public void updateCurrentFeelingPanel(Map<EmotionType, Float> feelingNow, Map<EmotionType, Float> inclinationNow) {
        this.currentFeelingPanel.upDateEmoTiles(feelingNow, inclinationNow);
    }
//    public void appendTextArea1(String str) {
//        String before = this.textArea1.getText();
//        this.textArea1.setText(new StringBuffer(before).append(str).append("\n").toString());
//        textArea1.setCaretPosition(textArea1.getText().length());
//    }

    public void setTextArea3(String str) {
        this.textArea3.setText(str);
        textArea3.setCaretPosition(textArea3.getText().length());
    }

    public Brain getBrain() {
        return brain;
    }
}
