package cz.lttr.esch.model;

/**
 * Basic time milestones is a set of numbers which represent a basic time interval
 * for time related operations.
 *
 * The number (of minutes) has to devide 60 (an hour) without a remainder.
 *
 * Created by Lukas Trumm on 13.09.2016
 */
enum BasicTimeMilestones {
    ONE_MINUTE(1),
    FIVE_MINUTES(5),
    TEN_MINUTES(10),
    FIFTEEN_MINUTES(15),
    TWENTY_MINUTES(20),
    THIRTY_MINUTES(30),
    SIXTY_MINUTES(60);

    private int minutes;

    BasicTimeMilestones(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
