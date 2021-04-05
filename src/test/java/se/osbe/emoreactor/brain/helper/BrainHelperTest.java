package se.osbe.emoreactor.brain.helper;

import org.junit.Assert;
import org.junit.Test;
import se.osbe.emoreactor.brain.feelings.Emotion;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.helper.BrainHelper;

import java.util.regex.Pattern;

public class BrainHelperTest {

    @Test
    public void getEmotionForPatternTest() throws Exception {
        Emotion emotion = Emotion.builder()
                .emotionType(EmotionType.HAPPY)
                .durationTime(3600000)
                .amplitudePeak(12)
                .amplitudeSustain(6)
                .attack(9)
                .decay(11)
                .sustain(50)
                .release(30)
                .build();

        Assert.assertFalse(BrainHelper.getEmotionForPattern("").isPresent());
        Assert.assertFalse(BrainHelper.getEmotionForPattern("hap").isPresent());
        Assert.assertFalse(BrainHelper.getEmotionForPattern("happy").isPresent());
        Assert.assertFalse(BrainHelper.getEmotionForPattern("HAPPY").isPresent());

        Assert.assertEquals(emotion, BrainHelper.getEmotionForPattern("hap-t3600000-h12-l6-a9-d11-s50-r30").get());
        Assert.assertEquals(emotion, BrainHelper.getEmotionForPattern("HAP-T3600000-H12-L6-A9-D11-S50-R30").get());
        Assert.assertEquals(emotion, BrainHelper.getEmotionForPattern("HAPPY-t3600000-H12-L6-A9-D11-S50-R30").get());
        Assert.assertEquals(emotion, BrainHelper.getEmotionForPattern("happy-T3600000-H12-L6-A9-D11-S50-R30").get());
        Assert.assertEquals(emotion, BrainHelper.getEmotionForPattern("HaP-T3600000-H12-L6-A9-D11-S50-R30").get());
        Assert.assertEquals(emotion, BrainHelper.getEmotionForPattern("HAP-3600000-12-6-9-11-50-30").get());
        Assert.assertEquals(emotion, BrainHelper.getEmotionForPattern("HAPPY-3600000-12-6-9-11-50-30").get());
    }

    @Test
    public void brainHelperTest() throws Exception {
        Assert.assertEquals(EmotionType.AGONY.getEmotionName(), BrainHelper.getEmotionEnumForPattern("AGY").get().getEmotionName());
        Assert.assertEquals(EmotionType.ANGER.getEmotionName(), BrainHelper.getEmotionEnumForPattern("AGR").get().getEmotionName());

        Assert.assertEquals(EmotionType.DEPRESSED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("DEP").get().getEmotionName());
        Assert.assertEquals(EmotionType.CONFUSED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("CON").get().getEmotionName());

        Assert.assertEquals(EmotionType.HELPLESS.getEmotionName(), BrainHelper.getEmotionEnumForPattern("HLP").get().getEmotionName());
        Assert.assertEquals(EmotionType.INDIFFERENT.getEmotionName(), BrainHelper.getEmotionEnumForPattern("IDF").get().getEmotionName());

        Assert.assertEquals(EmotionType.AFRAID.getEmotionName(), BrainHelper.getEmotionEnumForPattern("AFR").get().getEmotionName());
        Assert.assertEquals(EmotionType.HURT.getEmotionName(), BrainHelper.getEmotionEnumForPattern("HRT").get().getEmotionName());

        Assert.assertEquals(EmotionType.SADNESS.getEmotionName(), BrainHelper.getEmotionEnumForPattern("SAD").get().getEmotionName());
        Assert.assertEquals(EmotionType.JUDGEMENTAL.getEmotionName(), BrainHelper.getEmotionEnumForPattern("JUD").get().getEmotionName());

        Assert.assertEquals(EmotionType.OPEN.getEmotionName(), BrainHelper.getEmotionEnumForPattern("OPE").get().getEmotionName());
        Assert.assertEquals(EmotionType.LOVING.getEmotionName(), BrainHelper.getEmotionEnumForPattern("LOV").get().getEmotionName());

        Assert.assertEquals(EmotionType.HAPPY.getEmotionName(), BrainHelper.getEmotionEnumForPattern("HAP").get().getEmotionName());
        Assert.assertEquals(EmotionType.INTERESTED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("INT").get().getEmotionName());

        Assert.assertEquals(EmotionType.ALIVE.getEmotionName(), BrainHelper.getEmotionEnumForPattern("ALV").get().getEmotionName());
        Assert.assertEquals(EmotionType.POSITIVE.getEmotionName(), BrainHelper.getEmotionEnumForPattern("POS").get().getEmotionName());

        Assert.assertEquals(EmotionType.PEACEFUL.getEmotionName(), BrainHelper.getEmotionEnumForPattern("PFL").get().getEmotionName());
        Assert.assertEquals(EmotionType.STRONG.getEmotionName(), BrainHelper.getEmotionEnumForPattern("STR").get().getEmotionName());

        Assert.assertEquals(EmotionType.RELAXED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("RLX").get().getEmotionName());
        Assert.assertEquals(EmotionType.RELIEF.getEmotionName(), BrainHelper.getEmotionEnumForPattern("RLF").get().getEmotionName());
    }
}
