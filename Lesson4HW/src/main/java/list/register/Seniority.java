package list.register;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Seniority {
    private final int AVG_COUNT_DAYS_IN_MONTH = 30;
    private final int COUNT_MONTHS_IN_YEAR = 12;

    private long numberOfDays;
    private boolean toPresentTime;
    private LocalDate dateRecordUpdated;

    public Seniority(long numberOfDaysNow, boolean toPresentTime) {
        this.numberOfDays = numberOfDaysNow;
        if (toPresentTime) {
            this.toPresentTime = true;
            dateRecordUpdated = LocalDateTime.now().toLocalDate();
        }
    }

    public Seniority() {
        numberOfDays = 0;
    }

    void addPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(endDate))
            numberOfDays += ChronoUnit.DAYS.between(startDate, endDate);
        else
            numberOfDays += ChronoUnit.DAYS.between(endDate, startDate);
    }

    void addCurrentWork(LocalDate startDate) {
        if (!toPresentTime) {
            toPresentTime = true;
            dateRecordUpdated = LocalDateTime.now().toLocalDate();
            numberOfDays += ChronoUnit.DAYS.between(startDate, dateRecordUpdated);
        }
    }

    int getCountMonths() {
        updateCurrentSeniority();
        return (int) (numberOfDays / AVG_COUNT_DAYS_IN_MONTH);
    }

    private void updateCurrentSeniority() {
        if (toPresentTime) {
            addPeriod(dateRecordUpdated, LocalDateTime.now().toLocalDate());
            dateRecordUpdated = LocalDateTime.now().toLocalDate();
        }
    }

    public int[] getSeniority() {
        int[] countYearMonth = new int[2];
        updateCurrentSeniority();
        if (numberOfDays != 0) {
            int numberOfMonths = getCountMonths();
            countYearMonth[0] = numberOfMonths / COUNT_MONTHS_IN_YEAR;
            countYearMonth[1] = numberOfMonths % COUNT_MONTHS_IN_YEAR;
        }
        return countYearMonth;
    }

    @Override
    public String toString() {
        int[] countYearMonth = getSeniority();
        if (countYearMonth[0] == 0 && countYearMonth[1] == 0) {
            return "0";
        }
        if (countYearMonth[0] == 0)
            return countYearMonth[1] + " мес.";
        return countYearMonth[0] + " лет " + countYearMonth[1] + " мес.";
    }

}
