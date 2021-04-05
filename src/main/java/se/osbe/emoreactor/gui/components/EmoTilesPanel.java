package se.osbe.emoreactor.gui.components;

import lombok.Data;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.helper.DiceHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class EmoTilesPanel extends JPanel {
    private static final Color COLOR_BABY_BLUE = new Color(0xBFD7ED);
    private static final Color COLOR_BLUE_GREEN = new Color(0x75E6DA);

    private Map<EmotionType, EmoTilePanel> emoTiles = new HashMap<>(20);

    public EmoTilesPanel(Map<EmotionType, Float> emotionRegistry, Map<EmotionType, Float> inclinationRegistry) {
        DiceHelper dice = new DiceHelper();
        this.setLayout(new GridLayout(5, 4));
        this.setPreferredSize(new Dimension(250, 250));
        Arrays.stream(EmotionType.values()).forEach(
                e -> {
                    var panel = new EmoTilePanel(e,
                            Optional.ofNullable(emotionRegistry.get(e)).orElseGet(() -> 0f),
                            Optional.ofNullable(inclinationRegistry.get(e)).orElseGet(() -> 0f)
                    );
                    emoTiles.put(e, panel);
                    this.add(panel);
                }
        );
    }

    public void upDateEmoTiles(Map<EmotionType, Float> emotionRegistry, Map<EmotionType, Float> inclinationRegistry) {
        Arrays.stream(EmotionType.values()).forEach(et -> {
            var emoTilePanel = emoTiles.get(et);
            var emoAmp = Math.round(Optional.ofNullable(emotionRegistry.get(et)).orElseGet(() -> 0f));
            if (emoAmp >= 70) {
                emoTilePanel.setBackground(Color.PINK);
            } else if(emoAmp > 0) {
                emoTilePanel.setBackground(COLOR_BLUE_GREEN);
            } else {
                emoTilePanel.setBackground(COLOR_BABY_BLUE);
            }
            emoTilePanel.setEmoAmplitudeText(String.valueOf(emoAmp));
            emoTilePanel.setEmoInclinationText(String.valueOf(Math.round(Optional.ofNullable(inclinationRegistry.get(et)).orElseGet(() -> 0f))));
        });
    }

    public static class Demo extends JFrame {
        private final DiceHelper DICE = new DiceHelper();
        private Map<EmotionType, Float> emotionRegistry;
        private Map<EmotionType, Float> inclinationRegistry;
        private EmoTilesPanel emoTilesPanel;

        public Demo() {
            emotionRegistry = Arrays.stream(EmotionType.values())
                    .collect(Collectors.toMap(Function.identity(), (e) -> DICE.getRandomFloatBetween(0f, 100f)));
            inclinationRegistry = Arrays.stream(EmotionType.values())
                    .collect(Collectors.toMap(Function.identity(), (e) -> (5.0f - DICE.getRandomFloatBetween(0f, 10f))));
            emoTilesPanel = new EmoTilesPanel(emotionRegistry, inclinationRegistry);
            this.add(emoTilesPanel);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setPreferredSize(new Dimension(250, 250));
            this.setVisible(true);
        }

        public static void main(String[] args) throws InterruptedException {
            final DiceHelper dice = new DiceHelper();
            EmoTilesPanel.Demo demo = new EmoTilesPanel.Demo();
            while (true) {
                Map<EmotionType, Float> emotionRegistry = Arrays.stream(EmotionType.values())
                        .collect(Collectors.toMap(Function.identity(), (e) -> dice.getRandomFloatBetween(0f, 100f)));
                Map<EmotionType, Float> inclinationRegistry = Arrays.stream(EmotionType.values())
                        .collect(Collectors.toMap(Function.identity(), (e) -> (5.0f - dice.getRandomFloatBetween(0f, 10f))));
                demo.getEmoTilesPanel().upDateEmoTiles(emotionRegistry, inclinationRegistry);
                Thread.sleep(1000);
            }
        }

        public EmoTilesPanel getEmoTilesPanel() {
            return emoTilesPanel;
        }
    }
}
