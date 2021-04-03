package se.osbe.emoreactor.brain.config;

import se.osbe.emoreactor.brain.feelings.EmotionType;

import javax.swing.*;
import java.awt.*;

public class EmoTilePanel extends JPanel {
    private static final Font EMO_TYPE_FONT = new Font("Sans serif", Font.PLAIN, 12);
    private static final Font EMO_AMP_FONT = new Font("Sans serif", Font.BOLD, 24);
    private static final Font EMO_INCL_FONT = new Font("Sans serif", Font.ITALIC, 10);
    private EmotionType emoType;

    // presented data
    private JLabel emoName;
    private JLabel emoAmplitude;
    private JLabel emoInclination;

    public EmoTilePanel(EmotionType emoType, Float amplitude, Float inclination) {
        emoType = emoType;
        setName(emoType.getEmotionName());
        setLayout(new GridLayout(3, 1, 0, 0));
        setBackground(new Color(214, 223, 238));
        setPreferredSize(new Dimension(70, 70));
        setBorder(BorderFactory.createLineBorder(new Color(128, 128, 128), 2));

        emoName = new JLabel(emoType.getEmotionName());
        emoName.setFont(EMO_TYPE_FONT);
        emoName.setForeground(new Color(48, 48, 48));
        emoName.setVerticalAlignment(SwingConstants.TOP);
        emoName.setHorizontalAlignment(SwingConstants.CENTER);

        emoAmplitude = new JLabel("" + Math.round(amplitude));
        emoAmplitude.setFont(EMO_AMP_FONT);
        emoAmplitude.setForeground(new Color(33, 122, 196));
        emoAmplitude.setPreferredSize(new Dimension(10, 10));
        emoAmplitude.setVerticalAlignment(SwingConstants.CENTER);
        emoAmplitude.setHorizontalAlignment(SwingConstants.CENTER);

        emoInclination = new JLabel("" + inclination);
        emoInclination.setFont(EMO_INCL_FONT);
        emoInclination.setVerticalAlignment(SwingConstants.BOTTOM);
        emoInclination.setHorizontalAlignment(SwingConstants.CENTER);

        add(emoName);
        add(emoAmplitude);
        add(emoInclination);
    }

    public EmotionType getEmoType() {
        return emoType;
    }

    public JLabel getEmoAmplitude() {
        return this.emoAmplitude;
    }

    public void setEmoAmplitudeText(String str) {
        this.emoAmplitude.setText(str);
    }

    public JLabel getEmoInclination() {
        return emoInclination;
    }

    public void setEmoInclinationText(String str) {
        emoInclination.setText(str);
    }

    public static class Demo extends JFrame {
        private EmoTilePanel emoTilePanel;
        public Demo() {
            emoTilePanel = new EmoTilePanel(EmotionType.CONFUSED, 50f, -1f);
            this.add(emoTilePanel);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.pack();
            this.setVisible(true);
        }

        public EmoTilePanel getEmoTilePanel() {
            return emoTilePanel;
        }

        public static void main(String[] args) throws InterruptedException {
            EmoTilePanel.Demo emoTilePanel = new EmoTilePanel.Demo();
            emoTilePanel.getEmoTilePanel().setEmoAmplitudeText("999");
        }
    }
}
