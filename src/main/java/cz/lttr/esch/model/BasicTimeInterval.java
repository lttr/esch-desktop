package cz.lttr.esch.model;

/**
 * Created by Lukas Trumm on 13.09.2016
 */
public enum BasicTimeInterval {
    ONE_MINUTE(1),
    FIVE_MINUTES(5),
    TEN_MINUTES(10),
    FIFTEEN_MINUTES(15),
    TWENTY_MINUTES(20),
    THIRTY_MINUTES(30),
    SIXTY_MINUTES(60);

    private int minutes;

    BasicTimeInterval(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
