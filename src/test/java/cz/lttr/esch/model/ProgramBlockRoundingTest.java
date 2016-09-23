package cz.lttr.esch.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lukas Trumm on 13.09.2016
 */
@RunWith(Parameterized.class)
public class ProgramBlockRoundingTest {

    @Parameterized.Parameters
    public static Collection data() {
        List<BasicTimeMilestones> possibleMilestonesInMinutes = new ArrayList<>();
        for (BasicTimeMilestones btm : BasicTimeMilestones.values()) {
            possibleMilestonesInMinutes.add(btm);
        }
        return possibleMilestonesInMinutes;
    }

    @Parameterized.Parameter
    public BasicTimeMilestones milestone;

    /**
     * For every possible basic time milestone the end time of a one hour block will never be rounded.
     */
    @Test
    public void hourLongBlockShouldEndAfter60Minutes() {
        LocalTime end = LocalTime.of(11, 0);
        ProgramBlock pb = getProgramBlock60(new Settings(milestone));
        Assert.assertEquals(end, pb.getEndTime());
    }

    /**
     * For every possible basic time milestone the end time of a program block
     * will allways be rounded so that the minutes part will be a multiple
     * of the basic time milestone.
     */
    @Test
    public void blockShouldBeRoundedToMultipleOfBasicMilestone() {
        ProgramBlock pb60 = getProgramBlock60(new Settings(milestone));
        Assert.assertEquals("60 minutes block", 0, pb60.getEndTime().getMinute() % milestone.getMinutes());

        ProgramBlock pb56 = getProgramBlock56(new Settings(milestone));
        Assert.assertEquals("56 minutes block", 0, pb56.getEndTime().getMinute() % milestone.getMinutes());

        ProgramBlock pb24 = getProgramBlock24(new Settings(milestone));
        Assert.assertEquals("24 minutes block", 0, pb24.getEndTime().getMinute() % milestone.getMinutes());

        ProgramBlock pb3 = getProgramBlock3(new Settings(milestone));
        Assert.assertEquals("3 minutes block", 0, pb3.getEndTime().getMinute() % milestone.getMinutes());
    }

    private ProgramBlock getProgramBlock60(Settings settings) {
        LocalTime start = LocalTime.of(10, 0);
        Duration duration = Duration.ofMinutes(60);
        return new ProgramBlock("ProgramBlock60", start, duration, settings);
    }

    private ProgramBlock getProgramBlock56(Settings settings) {
        LocalTime start = LocalTime.of(10, 0);
        Duration duration = Duration.ofMinutes(56);
        return new ProgramBlock("ProgramBlock56", start, duration, settings);
    }

    private ProgramBlock getProgramBlock24(Settings settings) {
        LocalTime start = LocalTime.of(10, 0);
        Duration duration = Duration.ofMinutes(24);
        return new ProgramBlock("ProgramBlock24", start, duration, settings);
    }

    private ProgramBlock getProgramBlock3(Settings settings) {
        LocalTime start = LocalTime.of(10, 0);
        Duration duration = Duration.ofMinutes(3);
        return new ProgramBlock("ProgramBlock3", start, duration, settings);
    }
}