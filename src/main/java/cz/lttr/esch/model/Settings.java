package cz.lttr.esch.model;


/**
 * Created by Lukas Trumm on 13.09.2016
 */
public class Settings {

    private static final BasicTimeMilestones DEFAULT_BASIC_TIME_MILESTONE = BasicTimeMilestones.FIFTEEN_MINUTES;

    /**
     * The basic atomic time milestone in minutes.
     */
    private BasicTimeMilestones basicTimeMilestone;


    public Settings() {
        basicTimeMilestone = DEFAULT_BASIC_TIME_MILESTONE;
    }

    public Settings(BasicTimeMilestones basicTimeMilestone) {
        this.basicTimeMilestone = basicTimeMilestone;
    }

    public BasicTimeMilestones getBasicTimeMilestone() {
        return basicTimeMilestone;
    }

    public int getBasicTimeMilestoneInMinutes() {
        return basicTimeMilestone.getMinutes();
    }
}
