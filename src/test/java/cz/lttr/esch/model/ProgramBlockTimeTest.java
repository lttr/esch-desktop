package cz.lttr.esch.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by Lukas Trumm on 20.09.2016
 */
public class ProgramBlockTimeTest {

    @Test
    public void programBlockShouldProvideStartAndEndTime() {
        LocalTime start = LocalTime.of(10, 15);
        Duration duration = Duration.ofMinutes(90);
        BasicTimeMilestones milestone = BasicTimeMilestones.FIFTEEN_MINUTES;
        ProgramBlock pb = new ProgramBlock("pb", start, duration, new Settings(milestone));

        LocalTime expectedEnd = LocalTime.of(11, 45);

        Assert.assertEquals("Start time does not match", start, pb.getStartTime());
        Assert.assertEquals("End time does not match", expectedEnd, pb.getEndTime());
    }

    @Test
    public void programBlockDurationCanBeChanged() {
        LocalTime startTime = LocalTime.of(16, 10);
        Duration duration = Duration.ofMinutes(50);
        BasicTimeMilestones milestone = BasicTimeMilestones.TEN_MINUTES;
        ProgramBlock pb = new ProgramBlock("pb", startTime, duration, new Settings(milestone));

        Duration offset = Duration.ofMinutes(10);
        LocalTime expectedMinusEndTime = LocalTime.of(16, 50);
        LocalTime expectedPlusEndTime = LocalTime.of(17, 10);

        pb.minusDuration(offset);
        Assert.assertEquals("Minus duration does not work", expectedMinusEndTime, pb.getEndTime());

        ProgramBlock pb2 = new ProgramBlock("pb", startTime, duration, new Settings(milestone));
        pb2.plusDuration(offset);
        Assert.assertEquals("Plus duration does not work", expectedPlusEndTime, pb2.getEndTime());
    }

    @Test
    public void programBlockStartTimeCanBeShifted() {
        LocalTime startTime = LocalTime.of(12, 30);
        Duration duration = Duration.ofMinutes(30);
        BasicTimeMilestones milestone = BasicTimeMilestones.TEN_MINUTES;
        ProgramBlock pb = new ProgramBlock("pb", startTime, duration, new Settings(milestone));

        pb.setStartTime(startTime.minusMinutes(20));
        LocalTime expectedEnd = LocalTime.of(12, 40);

        Assert.assertEquals("Plus duration does not work", expectedEnd, pb.getEndTime());
    }

    @Test
    public void programBlockDurationCannotBeLessThenZero() {
        LocalTime startTime = LocalTime.of(10, 0);
        Duration duration = Duration.ofMinutes(60);
        BasicTimeMilestones milestone = BasicTimeMilestones.TEN_MINUTES;
        ProgramBlock pb = new ProgramBlock("pb", startTime, duration, new Settings(milestone));

        pb.minusDuration(Duration.ofMinutes(80));

        Assert.assertEquals("Duration can't be less then zero", startTime, pb.getEndTime());
    }

    @Test
    public void programBlockEndTimeCanBeAtMidnight() {
        LocalTime startTime = LocalTime.of(22, 30);
        Duration duration = Duration.ofMinutes(90);
        BasicTimeMilestones milestone = BasicTimeMilestones.TEN_MINUTES;
        ProgramBlock pb = new ProgramBlock("pb", startTime, duration, new Settings(milestone));

        LocalTime expectedEnd = LocalTime.of(0, 0);

        Assert.assertEquals(startTime, pb.getStartTime());
        Assert.assertEquals(expectedEnd, pb.getEndTime());
    }

    @Test
    public void programBlockEndTimeCannotExceedMidnight() {
        LocalTime startTime = LocalTime.of(22, 30);
        Duration duration = Duration.ofMinutes(100);
        BasicTimeMilestones milestone = BasicTimeMilestones.TEN_MINUTES;
        ProgramBlock pb = new ProgramBlock("pb", startTime, duration, new Settings(milestone));

        LocalTime expectedStart = LocalTime.of(22, 20);
        LocalTime expectedEnd = LocalTime.of(0, 0);

        Assert.assertEquals("End time can't be after midnight", expectedStart, pb.getStartTime());
        Assert.assertEquals("End time can't be after midnight", expectedEnd, pb.getEndTime());
    }
}
