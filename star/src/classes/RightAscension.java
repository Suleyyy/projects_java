package classes;

public class RightAscension {
    private int hrs;
    private int min;
    private int sec;

    public int get_hours() {
        return hrs;
    }

    public int get_min() {
        return min;
    }

    public double get_sec() {
        return sec;
    }

    private void set_hours(int hrs) {
        if (hrs < 0 || hrs > 24)
            throw new IllegalArgumentException("hrs out of range");
        this.hrs = hrs;
    }

    private void set_min(int min) {
        if (min > 60 || min < 0)
            throw new IllegalArgumentException("min out of range");
        this.min = min;
    }

    private void set_sec(int sec) {
        if (sec > 60.0 || sec < 0.0)
            throw new IllegalArgumentException("sec out of range");
        this.sec = sec;
    }

    public RightAscension(int hrs, int min, int sec) throws IllegalArgumentException {
        set_hours(hrs);
        set_min(min);
        set_sec(sec);
    }
}
