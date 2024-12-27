package classes;
import java.util.regex.*;

public class Star {
    private String name;
    private String catName;
    private int declination; //new class here
    private int rightAscension; //new class here
    private float apparentMagnitude;
    private float absoluteMagnitude;
    private float lightYears;
    private String constellation; //new class here
    private String hemisphere;
    private float temperature;
    private float mass;



    public Star(String name, String catName, int declination, int rightAscension, float apparentMagnitude, float absoluteMagnitude,
                float lightYears, String constellation, String hemisphere, float temperature, float mass)
    {
        this.name = name;
        this.catName = catName;
        this.declination = declination;
        this.rightAscension = rightAscension;
        this.apparentMagnitude = apparentMagnitude;
        this.absoluteMagnitude = absoluteMagnitude;
        this.lightYears = lightYears;
        this.constellation = constellation;
        this.hemisphere = hemisphere;
        this.temperature = temperature;
        this.mass = mass;
    }
}
