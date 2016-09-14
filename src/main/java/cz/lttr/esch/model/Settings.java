package cz.lttr.esch.model;


/**
 * Created by Lukas Trumm on 13.09.2016
 */
public class Settings {

    private static final int DEFAULT_BASIC_TIME_INTERVAL = 15;

    /**
     * The basic atomic time interval in minutes.
     */
    private int basicTimeInterval;


    public Settings() {
        basicTimeInterval = DEFAULT_BASIC_TIME_INTERVAL;
    }

    public Settings(int basicTimeInterval) {
        this.basicTimeInterval = basicTimeInterval;
    }

    public int getBasicTimeInterval() {
        return basicTimeInterval;
    }

}
