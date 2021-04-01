package se.osbe.emoreactor.brain.feelings;

public enum EmotionType {
    AFRAID("Afraid", "AFR"),
    AGONY("Agony", "AGY"),
    ALIVE("Alive", "ALV"),
    ANGER("Anger", "AGR"),
    CONFUSED("Confused", "CON"),
    DEPRESSED("Depressed", "DEP"),
    HAPPY("Happy", "HAP"),
    HELPLESS("Helpless", "HLP"),
    HURT("Hurt", "HRT"),
    INDIFFERENT("Indifferent", "IDF"),
    INTERESTED("Interested", "INT"),
    JUDGEMENTAL("Judgemental", "JUD"),
    LOVING("Loving", "LOV"),
    OPEN("Open", "OPE"),
    PEACEFUL("Peaceful", "PFL"),
    POSITIVE("Positive", "POS"),
    RELAXED("Relaxed", "RLX"),
    RELIEF("Relieved", "RLF"),
    SADNESS("Sadness", "SAD"),
    STRONG("Strong", "STR");


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
