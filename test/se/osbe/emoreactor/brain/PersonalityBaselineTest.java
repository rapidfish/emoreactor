package se.osbe.emoreactor.brain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonalityBaselineTest {

    @Test
    public void defaultPersonalityBaselineTest() {
        Personality baseline = new Personality();



        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getIntrovert()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getExtrovert()).longValue());

        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getIntuition()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getSensing()).longValue());

        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getFeeling()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getThinking()).longValue());

        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getPercieving()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getJudging()).longValue());
    }

    @Test
    public void customPersonalityBaselineTest() {
        Personality baseline = new Personality(1f, 99f, 1f, 3f, 42f, 42f, 1f, 0f);

        assertEquals(Long.valueOf(1).longValue(), Float.valueOf(baseline.getIntrovert()).longValue());
        assertEquals(Long.valueOf(99).longValue(), Float.valueOf(baseline.getExtrovert()).longValue());

        assertEquals(Long.valueOf(25).longValue(), Float.valueOf(baseline.getIntuition()).longValue());
        assertEquals(Long.valueOf(75).longValue(), Float.valueOf(baseline.getSensing()).longValue());

        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getFeeling()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getThinking()).longValue());

        assertEquals(Long.valueOf(100).longValue(), Float.valueOf(baseline.getPercieving()).longValue());
        assertEquals(Long.valueOf(0).longValue(), Float.valueOf(baseline.getJudging()).longValue());
    }

    @Test
    public void customPersonalityBaselineTest2() {
        Personality baseline = new Personality(0f, 0f, 100f, 100f, 1024f, 1024f, 1024f, 4096f);

        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getIntrovert()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getExtrovert()).longValue());

        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getIntuition()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getSensing()).longValue());

        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getFeeling()).longValue());
        assertEquals(Long.valueOf(50).longValue(), Float.valueOf(baseline.getThinking()).longValue());

        assertEquals(Long.valueOf(20).longValue(), Float.valueOf(baseline.getPercieving()).longValue());
        assertEquals(Long.valueOf(80).longValue(), Float.valueOf(baseline.getJudging()).longValue());
    }

    @Test(expected = RuntimeException.class)
    public void customPersonalityBaselineTest3() {
        new Personality(0f, 0f, 0f, -1f, 0f, 0f, 0f, 0f);
    }
}