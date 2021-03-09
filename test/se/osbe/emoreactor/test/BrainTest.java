package se.osbe.emoreactor.test;

//public class BrainTest {
//
//    private Brain brain; // null = default configuration
//    private Feeling feeling;
//    private DiceHelper dice; // use help from randomizer to produce input, until "real" input values can be fed into the process
//
//    //@Before
//    public void before() throws Exception {
//        // Starting EmoReactor for John Doe, with default personalityBaseline
//        PersonalityCharacteristics personalityBaseline = new PersonalityCharacteristics(); // no arg constructor gives default where all personalityBaseline param pairs is in perfect balance (50%/50%)
//        PersonalityCharacteristics baseline = new PersonalityCharacteristics();
//        brain = new Brain("Average Joe", baseline); // default, if set to null
//        brain.setAwarenessPercentage(10);
//        dice = brain.getDiceHelper();
//    }
//
//    //@Test
//    public void brainSmokeTest() throws Exception {
//        Map<Emotion, Float> emotionNow;
//
//        int intensity = 5;
//        int duration = 10; //demo.dice.getRandomDoubleBetween(5d, 350d).intValue(); // 2s - 5min
//        brain.setAwarenessPercentage(100); // awareness 100%
//
//        Feeling feeling = Feeling.builder().build();
////                . ("anger=" + intensity + "," + duration + "s;")
////                .build();
//
//        Assert.assertTrue(brain.offerInboundFeeling(feeling));
//        emotionNow = brain.nextTurn();
//
//        Assert.assertNotNull(emotionNow);
//        brain.nextTurn();
//
//        Assert.assertEquals(Reactor.InclinationType.POSITIVE, brain.readReactorInclinationForEmotion(Emotion.EmotionType.ANGER));
//    }
//}
