package toor.barrettbot.environment.sniper.sniper.main;

public class DropDate {

    private String day;
    private String month;
    private String year;
    private String hour;
    private String minute;
    private String second;
    private String formatted;

    public DropDate() {

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public void setFormat(String str) {
        this.formatted = str;
    }

    public String format() {
        return formatted;
    }
}
