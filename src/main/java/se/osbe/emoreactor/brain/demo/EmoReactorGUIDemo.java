package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.Personality;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.brain.feelings.Feeling;
import se.osbe.emoreactor.gui.components.EmoTilesPanel;
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

    private static final String LOGO_IMG_PATH = "src/main/resources/brain-2.jpg";
    // private static final String LOGO_IMG_PATH = "src/main/resources/brain-rotate.gif";
    private static final String DELIMITER = ", ";
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
    private static final Color UPPER_ROW_BACKGROUND_COLOR = new Color(0xFFFFFF);

    private static final Font MAIN_FONT = new Font("Sans serif", Font.PLAIN, 14);
    private static final Font MINI_FONT = new Font("Sans serif", Font.PLAIN, 10);
    private static final String FEELING_EXAMPLE_STR = "HAP-D60000-H12-L6-A9-D11-S50-R30";
    private final JTextField feelingInputTextField = new JTextField(FEELING_EXAMPLE_STR);
    private final JTextField awarenessTextfield = new JTextField();
    private final JTextField timeSampleLabel = new JTextField();
    private final JTextArea textArea2 = new JTextArea(); // Personlaity
    private final JTextArea textArea3 = new JTextArea(); // feelings in progress
    private final JButton offerFeelingJButton = new JButton();
    private final JPanel upperRowPanel = new JPanel();
    private final Brain brain;
    EmoTilesPanel currentFeelingPanel = null;
    //private JScrollPane textArea2scrollPane = null;
    private JScrollPane textArea3scrollPane = null;

    public EmoReactorGUIDemo() {
        super("Emo Reactor");
        brain = new Brain("John Doe",
                new Personality(25,75,75,25,40,60,70,30));
        brain.setAwarenessPercentage(100);

        JLabel logo = new JLabel(new ImageIcon(LOGO_IMG_PATH));
        logo.setPreferredSize(new Dimension(10, 50));
        logo.setBounds(0, 2000, 440, 20);
        timeSampleLabel.setBackground(TIME_SAMPLE_COLOR);
        timeSampleLabel.setFont(MAIN_FONT);
        feelingInputTextField.setBackground(INPUT_FEELING_COLOR);
        feelingInputTextField.setEditable(true);
        feelingInputTextField.addActionListener((e) -> {
                    Optional<Emotion> emoOpt = BrainHelper.getEmotionForPattern(feelingInputTextField.getText());
                    if (emoOpt.isPresent()) {
                        brain.offerInboundFeeling(new Feeling(Arrays.asList(emoOpt.get())));
                    }
                }
        );
        awarenessTextfield.setBackground(AWARENESS_COLOR);
        awarenessTextfield.addActionListener((e) -> {
            brain.setAwarenessPercentage(Integer.valueOf(awarenessTextfield.getText().replaceAll("\\D", "")));
        });
        awarenessTextfield.setText("Awareness: " + Math.round(brain.getPerceptionAwareness()) + " %  --  " + brain.getName());

        upperRowPanel.setLayout(new GridLayout(2, 2, 0, 0));
        upperRowPanel.setBackground(UPPER_ROW_BACKGROUND_COLOR);
        upperRowPanel.add(logo);
        upperRowPanel.add(timeSampleLabel);
        upperRowPanel.add(feelingInputTextField);
        upperRowPanel.add(awarenessTextfield);

        // Introvert, Extrovert, Intuition, Densing, Feeling, Thinking, Percieving, Judging
        JLabel jLabel1 = new JLabel("Introvert vs Extrovert");
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setFont(MINI_FONT);
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jLabel1.setVerticalAlignment(JLabel.BOTTOM);
        JSlider jSlider1 = new JSlider(JSlider.HORIZONTAL, 0, 100,
                Float.valueOf(brain.getPersonalityBaseline().getIntrovert()).intValue());
        jSlider1.setForeground(Color.WHITE);
        jSlider1.setMajorTickSpacing(50);
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true);
        jSlider1.setValue(Float.valueOf(brain.getPersonalityBaseline().getIntrovert()).intValue());

        JLabel jLabel2 = new JLabel("Intuition vs Sensing");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(MINI_FONT);
        jLabel2.setHorizontalAlignment(JLabel.CENTER);
        jLabel2.setVerticalAlignment(JLabel.BOTTOM);
        JSlider jSlider2 = new JSlider(JSlider.HORIZONTAL, 0, 100,
                Float.valueOf(brain.getPersonalityBaseline().getIntuition()).intValue());
        jSlider2.setForeground(Color.WHITE);
        jSlider2.setMajorTickSpacing(50);
        jSlider2.setPaintTicks(true);
        jSlider2.setPaintLabels(true);

        JLabel jLabel3 = new JLabel("Feeling vs Thinking");
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setFont(MINI_FONT);
        jLabel3.setHorizontalAlignment(JLabel.CENTER);
        jLabel3.setVerticalAlignment(JLabel.BOTTOM);
        JSlider jSlider3 = new JSlider(JSlider.HORIZONTAL, 0, 100,
                Float.valueOf(brain.getPersonalityBaseline().getFeeling()).intValue());
        jSlider3.setForeground(Color.WHITE);
        jSlider3.setMajorTickSpacing(50);
        jSlider3.setPaintTicks(true);
        jSlider3.setPaintLabels(true);

        JLabel jLabel4 = new JLabel("Percieving vs Judging");
        jLabel4.setForeground(Color.WHITE);
        jLabel4.setFont(MINI_FONT);
        jLabel4.setHorizontalAlignment(JLabel.CENTER);
        jLabel4.setVerticalAlignment(JLabel.BOTTOM);
        JSlider jSlider4 = new JSlider(JSlider.HORIZONTAL, 0, 100,
                Float.valueOf(brain.getPersonalityBaseline().getPercieving()).intValue());
        jSlider4.setForeground(Color.WHITE);
        jSlider4.setMajorTickSpacing(50);
        jSlider4.setPaintTicks(true);
        jSlider4.setPaintLabels(true);

        JPanel personalityPane = new JPanel(new GridLayout(8, 1, 0, 0));
        personalityPane.setBackground(COLOR_NAVY_BLUE);

        personalityPane.add(jLabel1);
        personalityPane.add(jSlider1);
        personalityPane.add(jLabel2);
        personalityPane.add(jSlider2);
        personalityPane.add(jLabel3);
        personalityPane.add(jSlider3);
        personalityPane.add(jLabel4);
        personalityPane.add(jSlider4);
        brain.getPersonalityBaseline();

        textArea3.setBackground(COLOR_BLUE_GROTTO);
        textArea3.setFont(MINI_FONT);
        textArea3.setLineWrap(true);
        textArea3.setEditable(false);
        textArea3scrollPane = new JScrollPane(textArea3);
        textArea3scrollPane.setWheelScrollingEnabled(true);
        textArea3scrollPane.setPreferredSize(new Dimension(300, 400));

        currentFeelingPanel = new EmoTilesPanel(brain.getCurrentFeeling(), brain.getInclinations());

        offerFeelingJButton.setText("Add Randomized Feeling");
        offerFeelingJButton.setFont(MAIN_FONT);
        offerFeelingJButton.setPreferredSize(new Dimension(100, 30));
        offerFeelingJButton.addActionListener((e) -> {
            Feeling feeling = generateRandomFeeling();
            brain.offerInboundFeeling(feeling);
        });

        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().setLayout(new BorderLayout(1, 1));
        this.getContentPane().add("North", upperRowPanel);
        this.getContentPane().add("West", personalityPane);
        this.getContentPane().add("East", currentFeelingPanel); // textArea1scrollPane
        this.getContentPane().add("Center", textArea3scrollPane);
        this.getContentPane().add("South", offerFeelingJButton);

        offerFeelingJButton.setFocusPainted(true);
    }

    public static void main(String[] args) throws InterruptedException {
        EmoReactorGUIDemo window = new EmoReactorGUIDemo();
        window.setPreferredSize(new Dimension(850, 500));
        window.setLocation(300, 150);
        // window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        new SplashWindow("src/main/resources/brain.gif", window);
        while (true) {
            window.setTimeSampleTextField(String.format("Time sample: %d", window.getBrain().getTurnCounter()));

            Map<EmotionType, Float> currentFeeling = window.getBrain().nextTurn();
            Map<EmotionType, Float> currentInclinations = window.getBrain().getInclinations();
            window.updateCurrentFeelingPanel(currentFeeling, currentInclinations);

            window.setTextArea3(
                    window.brain.getFeelings().stream().map(f -> {
                        long timeLeft = (f.getDuration() - (new Date().getTime() - f.getInitialTimeStamp()));
                        return new StringBuilder()
                                .append("Feeling-ID  : ").append(f.getUID()).append("\n")
                                .append("Created-TS  : ").append(f.getInitialTimeStamp()).append("\n")
                                .append("Time left   : ").append(BrainHelper.getTimeAsString(timeLeft >= 0L ? timeLeft : 0)).append("\n")
                                .append("Duration  : ").append(BrainHelper.getTimeAsString(f.getDuration())).append("\n")
                                .append(
                                        f.getEmotions().stream()
                                                .filter(e -> !e.isExpired())
                                                .map(e -> {
                                                    return new StringBuilder()
                                                            .append("  " + e.getEmotionType().getEmotionName()).append(" ")
                                                            .append(e.getDurationTime()).append("-H")
                                                            .append(Math.round(e.getAmplitudePeak())).append("-L")
                                                            .append(Math.round(e.getAmplitudeSustain())).append(" -- A")
                                                            .append(Math.round(e.getAttackPercent())).append("-D")
                                                            .append(Math.round(e.getDecayPercent())).append("-S")
                                                            .append(Math.round(e.getSustainPercent())).append("-R")
                                                            .append(Math.round(e.getReleasePercent())).append("")
                                                            .append("\n")
                                                            .toString();
                                                })
                                                .collect(Collectors.joining())
                                )
                                .append("\n")
                                .toString();
                    }).collect(Collectors.joining())
            );

            if (
                    currentFeeling.entrySet().stream().filter(e -> e.getValue() >= 100).count() > 0
            ) {
                JOptionPane.showMessageDialog(null, window.getBrain().getName() + " is dead!");
                System.exit(1);
            }

            Thread.sleep(1000);
        }
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

    public void setTimeSampleTextField(String str) {
        timeSampleLabel.setText(str);
    }

    public void updateCurrentFeelingPanel(Map<EmotionType, Float> feelingNow, Map<EmotionType, Float> inclinationNow) {
        this.currentFeelingPanel.upDateEmoTiles(feelingNow, inclinationNow);
    }

    public void setTextArea3(String str) {
        this.textArea3.setText(str);
        textArea3.setCaretPosition(textArea3.getText().length());
    }

    public Brain getBrain() {
        return brain;
    }
}
