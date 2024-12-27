package classes;

public class Declination {
    private int deg;
    private int min;
    private float sec;

    public int get_deg() {
        return deg;
    }

    public int get_min() {
        return min;
    }

    public double get_sec() {
        return sec;
    }

    private void set_deg(int deg) {
        if (deg > 90 || deg < -90)
            throw new IllegalArgumentException("deg out of range");
        this.deg = deg;
    }

    private void set_min(int min) {
        if (min > 60 || min < 0)
            throw new IllegalArgumentException("min out of range");
        this.min = min;
    }

    private void set_sec(float sec) {
        if (sec > 60.0 || sec < 0.0)
            throw new IllegalArgumentException("sec out of range");
        this.sec = sec;
    }

    public Declination(int deg, int min, float sec) throws IllegalArgumentException {
        set_deg(deg);
        set_min(min);
        set_sec(sec);
    }
}
