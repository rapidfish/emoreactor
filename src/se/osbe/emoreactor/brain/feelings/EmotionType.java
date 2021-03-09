package se.osbe.emoreactor.brain.feelings;

public enum EmotionType {
    AGONY("Agony", "AGNY"),
    ANGER("Anger", "ANGR"),
    DEPRESSED("Depressed", "DEPR"),
    CONFUSED("Confused", "CONF"),
    HELPLESS("Helpless", "HELP"),
    INDIFFERENT("Indifferent", "INDI"),
    AFRAID("Afraid", "AFRD"),
    HURT("Hurt", "HURT"),
    SADNESS("Sadness", "SADN"),
    JUDGEMENTAL("Judgemental", "JUGM"),
    OPEN("Open", "OPEN"),
    LOVING("Loving", "LOVG"),
    HAPPY("Happy", "HAPY"),
    INTERESTED("Interested", "INTR"),
    ALIVE("Alive", "ALIV"),
    POSITIVE("Positive", "PSTV"),
    PEACEFUL("Peaceful", "PCFL"),
    STRONG("Strong", "STRO"),
    RELAXED("Relaxed", "RELX"),
    RELIEF("Relieved", "RELV");

    private String emotionName;
    private String mnmonic;

    EmotionType(String emotionName, String mnmonic) {
        this.emotionName = emotionName;
        this.mnmonic = mnmonic;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public String getMnmonic() {
        return mnmonic;
    }
}
