package se.osbe.emoreactor.brain.feelings;

import java.util.*;

public class Feeling implements Cloneable {


    private final String uuid;
    private final long initialTimeStamp;
    private final List<Emotion> emotions;

    private Feeling() {
        // closed
        this.uuid = UUID.randomUUID().toString().split("-")[0];
        this.initialTimeStamp = new Date().getTime();
        this.emotions = Collections.emptyList();
    }

    public Feeling(List<Emotion> emotions) {
        this.uuid = UUID.randomUUID().toString().split("-")[0];
        this.initialTimeStamp = new Date().getTime();
        this.emotions = Objects.nonNull(emotions) ? emotions : new LinkedList<>();
    }

    public Feeling(String name, List<Emotion> emotions) {
        this.uuid = UUID.randomUUID().toString().split("-")[0];
        this.initialTimeStamp = new Date().getTime();
        this.emotions = Objects.nonNull(emotions) ? emotions : new LinkedList<>();
    }

    public static FeelingBuilder builder() {
        return new FeelingBuilder();
    }

    public long getInitialTimeStamp() {
        return initialTimeStamp;
    }

    public List<Emotion> getEmotions() {
        return emotions;
    }

    public String getUID() {
        return uuid;
    }

    public String toString() {
        return "Feeling [" + this.uuid + "] -> " + this.getEmotions() + "]";
    }

    public boolean isExpired() {
        return emotions.size() == 0 || emotions.size() == emotions.stream().filter(e -> e.isExpired()).count();
    }

    public static class FeelingBuilder {
        private List<Emotion> emotions;

        FeelingBuilder() {
            emotions = new ArrayList<>();
        }

        public FeelingBuilder addEmotion(Emotion emotion) {
            this.emotions.add(emotion);
            return this;
        }

        public FeelingBuilder addEmotions(List<Emotion> emotions) {
            this.emotions.addAll(emotions);
            return this;
        }

        public Feeling build() {
            return new Feeling(emotions);
        }
    }
}
