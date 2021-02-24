package se.osbe.emoreactor.brain.emotions;

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
    RELIEF("Relieved", "RELV"),
    ;

    private String _description;
    private String _mnmonic;

    private EmotionType(String description, String mnmonic) {
        _description = description;
        _mnmonic = mnmonic;
    }

    public String description() {
        return _description;
    }

    @Override
    public String toString() {
        return _mnmonic;
    }
}