package se.osbe.emoreactor.brain.demo;

import se.osbe.emoreactor.brain.Brain;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmoReactorGUIDemo extends JFrame {

    private static final String DELIMITER = ", ";
    private static final DiceHelper dice = new DiceHelper();

    private static final Font MAIN_FONT = new Font("Sans serif", Font.PLAIN, 14);
    private static final Font MINI_FONT = new Font("Sans serif", Font.PLAIN, 10);
    private JTextField feelingInputTextField = new JTextField("HAP(30000,20,5,10,20,50,20)");
    private JTextField awarenessTextfield = new JTextField();
    private JTextField timeSampleLabel = new JTextField();
    private JScrollPane textArea1scrollPane = null;
    private JScrollPane textArea2scrollPane = null;
    private JScrollPane textArea3scrollPane = null;
    private JTextArea textArea1 = new JTextArea(); // emo now
    private JTextArea textArea2 = new JTextArea(); // Personlaity
    private JTextArea textArea3 = new JTextArea(); // feelings in progress
    private JButton offerFeelingJButton = new JButton();
    private JPanel innerWindow = new JPanel();
    private Brain brain = new Brain(); // default everything!

    public EmoReactorGUIDemo() {
        super("Emo Reactor");

        brain.setAwarenessPercentage(100);

        innerWindow.setLayout(new GridLayout(1, 4, 2, 2));
        //feelingInputTextField.setPreferredSize(new Dimension(500, 30));
        feelingInputTextField.setBackground(Color.PINK);
        feelingInputTextField.setEditable(true);
        feelingInputTextField.addActionListener((e) -> {
                    String[] cmd = feelingInputTextField.getText().split("\\(");
                    String[] attr = cmd[1].split(",");
                    EmotionType emoType = BrainHelper.getEmotionEnumForPattern(cmd[0]);
                    brain.offerInboundFeeling(
                            Feeling.builder()
                                    .addEmotion(
                                            new Emotion(
                                                    emoType,
                                                    Long.valueOf(attr[0].trim()),
                                                    Float.valueOf(attr[1].trim()),
                                                    Float.valueOf(attr[2].trim()),
                                                    Integer.valueOf(attr[3].trim()),
                                                    Integer.valueOf(attr[4].trim()),
                                                    Integer.valueOf(attr[5].trim()),
                                                    Integer.valueOf(attr[6].replaceAll("\\D*", "").trim())
                                            )
                                    )
                                    .build()
                    );
                }
        );
        innerWindow.add(feelingInputTextField);

        awarenessTextfield.setBackground(Color.YELLOW);
        awarenessTextfield.addActionListener((e) -> {
            brain.setAwarenessPercentage(Integer.valueOf(awarenessTextfield.getText().replaceAll("\\D", "")));
        });
        awarenessTextfield.setText("Awareness: " + String.valueOf(Math.round(brain.getPerceptionAwareness())) + " %");
        innerWindow.add(awarenessTextfield);

        timeSampleLabel.setBackground(Color.GREEN);
        timeSampleLabel.setFont(MAIN_FONT);
        innerWindow.add(timeSampleLabel);

        innerWindow.setBackground(Color.WHITE);


        textArea1.setBackground(Color.WHITE);
        textArea1.setFont(MINI_FONT);
        textArea1.setLineWrap(true);
        textArea1scrollPane = new JScrollPane(textArea1);
        textArea1scrollPane.setPreferredSize(new Dimension(500, 200));

        textArea2.setBackground(Color.GRAY);
        textArea2.setFont(MINI_FONT);
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
        textArea2scrollPane.setPreferredSize(new Dimension(300, 200));

        textArea3.setBackground(Color.LIGHT_GRAY);
        textArea3.setFont(MINI_FONT);
        textArea3.setLineWrap(true);
        textArea3.setEditable(false);
        textArea3scrollPane = new JScrollPane(textArea3);
        textArea3scrollPane.setWheelScrollingEnabled(true);
       // textArea3scrollPane.setPreferredSize(new Dimension(200, 200));

        offerFeelingJButton.setText("Offer random feeling to brain");
        offerFeelingJButton.setFont(MAIN_FONT);
        offerFeelingJButton.addActionListener((e) -> {
            Feeling feeling = generateRandomFeeling();
            brain.offerInboundFeeling(feeling);
        });

        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().setLayout(new BorderLayout(1, 1));
        this.getContentPane().add("North", innerWindow);
        this.getContentPane().add("West", textArea2scrollPane);
        this.getContentPane().add("East", textArea1scrollPane);
        this.getContentPane().add("Center", textArea3scrollPane);
        this.getContentPane().add("South", offerFeelingJButton);
    }

    public static void main(String[] args) throws InterruptedException {
        EmoReactorGUIDemo window = new EmoReactorGUIDemo();
        window.setSize(1200, 700);
        window.setLocation(100, 100);
        window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        while (true) {
            window.setTimeSampleTextField(String.format("Time sample: %d", window.getBrain().getTurnCounter()));

            Map<EmotionType, Float> feelingNow = window.getBrain().nextTurn();
            Map<EmotionType, Float> inclinations = window.getBrain().getInclinations();

            window.appendTextArea1(
                    "Emo Reactor Now:\n\n" +
                            feelingNow.entrySet().stream()
                                    .filter(emo -> emo.getValue() >= 0f)
                                    .map(e -> {
                                        Float incl = inclinations.get(e.getKey());
                                        return e.getKey() + " [ " + Math.round(e.getValue()) + " ]    "
                                                + ((incl != null && incl.compareTo(0f) > 0) ? "+" : "") + incl;
                                    })
                                    .sorted()
                                    .collect(Collectors.joining("\n"))
            );

            window.setTextArea3(
                    // .filter(f -> !f.isExpired())
                    window.brain.getFeelings().stream().map(f -> {
                        long timeLeft = (f.getDuration() - (new Date().getTime() - f.getInitialTimeStamp()));
                        return new StringBuilder()
                                .append("Feeling-ID  : ").append(f.getUID()).append("\n")
                                .append("Created-TS  : ").append(f.getInitialTimeStamp()).append("\n")
                                .append("Duration: ").append(BrainHelper.getTimeAsString(timeLeft >= 0L ? timeLeft : 0))
                                .append(" / ").append(BrainHelper.getTimeAsString(f.getDuration())).append("\n")
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
                                                }).collect(Collectors.joining())
                                )
                                .append("\n")
                                .toString();
                    }).collect(Collectors.joining())
            );

            if (feelingNow.entrySet().stream().filter(e -> e.getValue() >= 100).count() > 0) {
                window.appendTextArea1(window.getBrain().getName() + " has passed out from emotional trauma!");
                window.appendTextArea1("He was overwelmed by the emotion(s): " + feelingNow.entrySet().stream().filter(e -> e.getValue() >= 90).collect(Collectors.toList()));
                break;
            }

//            if (feelingNow.entrySet().stream().filter(e -> e.getValue() > 0).count() == 0) {
//                window.appendTextArea1("\n" + window.getBrain().getName() + " is feeling nothing at the moment!");
//            }

            window.appendTextArea1("\n");
            Thread.sleep(1000);
            window.wipeTextArea1();
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

    public void wipeTextArea1() {
        this.textArea1.setText("");
        textArea1.setCaretPosition(textArea1.getText().length());
    }

    public void setTimeSampleTextField(String str) {
        timeSampleLabel.setText(str);
    }

    public void appendTextArea1(String str) {
        String before = this.textArea1.getText();
        this.textArea1.setText(new StringBuffer(before).append(str).append("\n").toString());
        textArea1.setCaretPosition(textArea1.getText().length());
    }

    public void setTextArea3(String str) {
        this.textArea3.setText(str);
        textArea3.setCaretPosition(textArea3.getText().length());
    }

    public Brain getBrain() {
        return brain;
    }
}
