package se.osbe.emoreactor.brain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonalityBaselineTest {

    @Test
    public void defaultPersonalityBaselineTest() {
        PersonalityCharacteristics baseline = new PersonalityCharacteristics();



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
        PersonalityCharacteristics baseline = new PersonalityCharacteristics(
                10f, 20f, 40f, 60f, 1f, 4f, 3f, 1f
        );

        assertEquals(Long.valueOf(33).longValue(), Float.valueOf(baseline.getIntrovert()).longValue());
        assertEquals(Long.valueOf(66).longValue(), Float.valueOf(baseline.getExtrovert()).longValue());

        assertEquals(Long.valueOf(40).longValue(), Float.valueOf(baseline.getIntuition()).longValue());
        assertEquals(Long.valueOf(60).longValue(), Float.valueOf(baseline.getSensing()).longValue());

        assertEquals(Long.valueOf(20).longValue(), Float.valueOf(baseline.getFeeling()).longValue());
        assertEquals(Long.valueOf(80).longValue(), Float.valueOf(baseline.getThinking()).longValue());

        assertEquals(Long.valueOf(75).longValue(), Float.valueOf(baseline.getPercieving()).longValue());
        assertEquals(Long.valueOf(25).longValue(), Float.valueOf(baseline.getJudging()).longValue());
    }
}