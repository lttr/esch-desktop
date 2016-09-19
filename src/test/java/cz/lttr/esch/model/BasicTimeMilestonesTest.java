package cz.lttr.esch.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lukas Trumm on 19.09.2016
 */
@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class BasicTimeMilestonesTest {

    @Parameterized.Parameters
    public static Collection data() {
        List<Integer> possibleMilestonesInMinutes = new ArrayList<>();
        for (BasicTimeMilestones btm : BasicTimeMilestones.values()) {
            possibleMilestonesInMinutes.add(btm.getMinutes());
        }
        return possibleMilestonesInMinutes;
    }

    @Parameterized.Parameter
    public int milestone;

    @Test
    public void milestonesShouldDevide60() {
        String message = milestone + " does not devide 60 without remainder!";
        Assert.assertTrue(message, 60 % milestone == 0);
    }
}
