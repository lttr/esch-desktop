package cz.lttr.esch.model;


/**
 * Created by Lukas Trumm on 13.09.2016
 */
public class Settings {

    private static final int DEFAULT_BASIC_TIME_MILESTONE = 15;

    /**
     * The basic atomic time milestone in minutes.
     */
    private int basicTimeMilestone;


    public Settings() {
        basicTimeMilestone = DEFAULT_BASIC_TIME_MILESTONE;
    }

    public Settings(int basicTimeMilestone) {
        this.basicTimeMilestone = basicTimeMilestone;
    }

    public int getBasicTimeMilestone() {
        return basicTimeMilestone;
    }

}
