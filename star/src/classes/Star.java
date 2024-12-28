package classes;
import java.util.regex.*;
import classes.*;

public class Star {
    private String name;
    private String catName;
    private Declination declination;
    private RightAscension rightAscension;
    private float apparentMagnitude;
    private float absoluteMagnitude;
    private float lightYears;
    private String constellation;
    private String hemisphere;
    private float temperature;
    private float mass;

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (Pattern.matches("^[A-Z]{3}[0-9]{4}$", name))
            this.name = name;
        else
            throw new IllegalArgumentException("Incorrect name");
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Declination getDeclination() {
        return declination;
    }

    public void setDeclination(Declination declination) throws IllegalArgumentException {
        this.declination = declination;
    }

    public RightAscension getRightAscension() {
        return rightAscension;
    }

    public void setRightAscension(RightAscension rightAscension) throws IllegalArgumentException {
        this.rightAscension = rightAscension;
    }

    public float getApparentMagnitude() {
        return apparentMagnitude;
    }

    public void setApparentMagnitude(float apparentMagnitude) throws IllegalArgumentException {
        if (absoluteMagnitude >= -26.74 && absoluteMagnitude <= 15.00)
            this.apparentMagnitude = apparentMagnitude;
        else
            throw new IllegalArgumentException("Incorrect absolute magnitude");
    }

    public float getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public void setAbsoluteMagnitude(float absoluteMagnitude) {
        this.absoluteMagnitude = (float) (getApparentMagnitude() - (5 * Math.log10(getLightYears() * 3.26)));
    }

    public float getLightYears() {
        return lightYears;
    }

    public void setLightYears(float lightYears) {
        this.lightYears = lightYears;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) throws IllegalArgumentException {
        boolean check = false;
        for (Constellation constellation1 : Constellation.values()) {
            if(constellation1.name().equals(constellation)) {
                check = true;
                break;
            }
        }
        if(check)
            this.constellation = constellation;
        else
            throw new IllegalArgumentException("Incorrect constellation");
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) throws IllegalArgumentException {
        if (temperature <= 2000)
            this.temperature = temperature;
        else
            throw new IllegalArgumentException("Incorrect temperature");
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) throws IllegalArgumentException {
        if(mass >= 0.1 && mass <= 50.0)
            this.mass = mass;
        else
            throw new IllegalArgumentException("Incorrect mass");
    }

}
