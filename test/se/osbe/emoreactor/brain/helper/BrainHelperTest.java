package se.osbe.emoreactor.brain.helper;

import org.junit.Assert;
import org.junit.Test;
import se.osbe.emoreactor.brain.feelings.EmotionType;
import se.osbe.emoreactor.helper.BrainHelper;

import java.util.regex.Pattern;

public class BrainHelperTest {

    // @Before
    //    public void before() throws Exception {
    //        ;
    //    }

    @Test
    public void brainHelperTest() throws Exception {
        Assert.assertEquals(EmotionType.AGONY.getEmotionName(), BrainHelper.getEmotionEnumForPattern("AGY").getEmotionName());
        Assert.assertEquals(EmotionType.ANGER.getEmotionName(), BrainHelper.getEmotionEnumForPattern("AGR").getEmotionName());

        Assert.assertEquals(EmotionType.DEPRESSED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("DEP").getEmotionName());
        Assert.assertEquals(EmotionType.CONFUSED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("CON").getEmotionName());

        Assert.assertEquals(EmotionType.HELPLESS.getEmotionName(), BrainHelper.getEmotionEnumForPattern("HLP").getEmotionName());
        Assert.assertEquals(EmotionType.INDIFFERENT.getEmotionName(), BrainHelper.getEmotionEnumForPattern("IDF").getEmotionName());

        Assert.assertEquals(EmotionType.AFRAID.getEmotionName(), BrainHelper.getEmotionEnumForPattern("AFR").getEmotionName());
        Assert.assertEquals(EmotionType.HURT.getEmotionName(), BrainHelper.getEmotionEnumForPattern("HRT").getEmotionName());

        Assert.assertEquals(EmotionType.SADNESS.getEmotionName(), BrainHelper.getEmotionEnumForPattern("SAD").getEmotionName());
        Assert.assertEquals(EmotionType.JUDGEMENTAL.getEmotionName(), BrainHelper.getEmotionEnumForPattern("JUD").getEmotionName());

        Assert.assertEquals(EmotionType.OPEN.getEmotionName(), BrainHelper.getEmotionEnumForPattern("OPE").getEmotionName());
        Assert.assertEquals(EmotionType.LOVING.getEmotionName(), BrainHelper.getEmotionEnumForPattern("LOV").getEmotionName());

        Assert.assertEquals(EmotionType.HAPPY.getEmotionName(), BrainHelper.getEmotionEnumForPattern("HAP").getEmotionName());
        Assert.assertEquals(EmotionType.INTERESTED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("INT").getEmotionName());

        Assert.assertEquals(EmotionType.ALIVE.getEmotionName(), BrainHelper.getEmotionEnumForPattern("ALV").getEmotionName());
        Assert.assertEquals(EmotionType.POSITIVE.getEmotionName(), BrainHelper.getEmotionEnumForPattern("POS").getEmotionName());

        Assert.assertEquals(EmotionType.PEACEFUL.getEmotionName(), BrainHelper.getEmotionEnumForPattern("PFL").getEmotionName());
        Assert.assertEquals(EmotionType.STRONG.getEmotionName(), BrainHelper.getEmotionEnumForPattern("STR").getEmotionName());

        Assert.assertEquals(EmotionType.RELAXED.getEmotionName(), BrainHelper.getEmotionEnumForPattern("RLX").getEmotionName());
        Assert.assertEquals(EmotionType.RELIEF.getEmotionName(), BrainHelper.getEmotionEnumForPattern("RLF").getEmotionName());
    }
}
