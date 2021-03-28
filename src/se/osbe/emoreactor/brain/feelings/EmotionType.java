package se.osbe.emoreactor.brain.feelings;

public enum EmotionType {
    AGONY("Agony", "AGY"),
    ANGER("Anger", "AGR"),
    DEPRESSED("Depressed", "DEP"),
    CONFUSED("Confused", "CON"),
    HELPLESS("Helpless", "HLP"),
    INDIFFERENT("Indifferent", "IDF"),
    AFRAID("Afraid", "AFR"),
    HURT("Hurt", "HRT"),
    SADNESS("Sadness", "SAD"),
    JUDGEMENTAL("Judgemental", "JUD"),
    OPEN("Open", "OPE"),
    LOVING("Loving", "LOV"),
    HAPPY("Happy", "HAP"),
    INTERESTED("Interested", "INT"),
    ALIVE("Alive", "ALV"),
    POSITIVE("Positive", "POS"),
    PEACEFUL("Peaceful", "PFL"),
    STRONG("Strong", "STR"),
    RELAXED("Relaxed", "RLX"),
    RELIEF("Relieved", "RLF");

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
