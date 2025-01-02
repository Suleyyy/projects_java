package classes;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Star implements Serializable {
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
    private final GreekLetters[] greekLetters = GreekLetters.values();

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

    public void setCatName() {
        Map<String, Integer> counter = new HashMap<>();
        File[] files = new File("src\\Stars\\").listFiles();
        if(files != null) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified)); //sorts files by last modification
            for (File file : files) {
                Star star = StarDeserializer(file.getName());
                if(star.getName().equals(this.name)) {
                    continue;
                }
                if(!counter.containsKey(star.getConstellation()))
                {
                    counter.put(star.getConstellation(), 0);
                }
                else {
                    counter.merge(star.getConstellation(), 1, Integer::sum);
                }
                star.catName = greekLetters[counter.get(star.getConstellation())].toString() + " " + this.constellation;
                Star.StarSerializer(star);
            }
            if (counter.containsKey(this.getConstellation())) {
                this.catName = greekLetters[counter.get(this.getConstellation()) + 1].toString() + " " + this.constellation;
            }
            else
                this.catName = greekLetters[0].toString() + " " + this.constellation;
            Star.StarSerializer(this);
        }
        else {
            this.catName = greekLetters[0].toString() + " " + this.constellation;
            Star.StarSerializer(this);
        }
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

    public void setAbsoluteMagnitude() {
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

    public void setHemisphere() {
        if (this.declination.get_deg() > 0)
            this.hemisphere = "PN";
        else if (this.declination.get_deg() < 0)
            this.hemisphere = "PD";
        else
            throw new IllegalArgumentException("Incorrect hemisphere");
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) throws IllegalArgumentException {
        if (temperature >= 2000)
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
    public Star(String name, Declination declination, RightAscension rightAscension, float apparentMagnitude,
                float lightYears, String constellation, float temperature, float mass) throws IllegalArgumentException
    {
        setName(name);
        setDeclination(declination);
        setRightAscension(rightAscension);
        setApparentMagnitude(apparentMagnitude);
        setLightYears(lightYears);
        setConstellation(constellation);
        setTemperature(temperature);
        setMass(mass);
        setHemisphere();
        setAbsoluteMagnitude();
        setCatName();
    }
    public void show()
    {
        System.out.println("------------------");
        System.out.print("Name: " + getName() + "\nCatName: " + getCatName() + "\nDeclination: " + getDeclination().get_deg() + "\nRightAscension: " +
                        getRightAscension().get_hours() + "\nApparentMagnitude: " + getApparentMagnitude()
                        + "\nAbsoluteMagnitude: " + getAbsoluteMagnitude() + "\nLightYears: " + getLightYears() +
                        "\nConstellation: " + getConstellation() + "\nTemperature: " + getTemperature() +
                        "\nMass: " + getMass() + "\nHemisphere: " + getHemisphere() + "\n");
        System.out.println("------------------");
    }

    public static void StarSerializer(Star star){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\Stars\\" + star.getName() + ".obj"));
            oos.writeObject(star);
            oos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Star StarDeserializer(String starName){
        try
        {
            if(starName.endsWith(".obj")) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\Stars\\" + starName));
                Star star = (Star) ois.readObject();
                ois.close();
                return star;
            }
            else {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\Stars\\" + starName + ".obj"));
                Star star = (Star) ois.readObject();
                ois.close();
                return star;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void ShowAllStars(){
        File[] files = new File("src\\Stars\\").listFiles();
        for (File file : files) {
            Star star = StarDeserializer(file.getName());
            star.show();
        }
    }
    public static void DeleteStar(String catName) {
        try
        {
            File[] files = new File("src\\Stars\\").listFiles();
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        for (int i = 0; i < files.length; i++) {
            Star star = StarDeserializer(files[i].getName());
            if (catName.equals(star.getCatName())) {
                Files.delete(files[i].toPath());
            }
            if(i == files.length - 1)
                star.setCatName();
        }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void ShowStarsFromConstellation(String coName){
        File[] files = new File("src\\Stars\\").listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        for (File file : files) {
            Star star = StarDeserializer(file.getName());
            if(coName.equals(star.getConstellation())) {
                star.show();
            }
        }
    }
    public static void ShowStarsParsecFromEarth(float distance){
        File[] files = new File("src\\Stars\\").listFiles();
        for (File file : files) {
            Star star = StarDeserializer(file.getName());
            if(star.getLightYears() / 3.26 == distance) {
                star.show();
            }
        }
    }
    public static void ShowByTemp(float num, float num2){
        File[] files = new File("src\\Stars\\").listFiles();
        for (File file : files) {
            Star star = StarDeserializer(file.getName());
            if(star.getTemperature() > num && star.getTemperature() < num2) {
                star.show();
            }
        }
    }
    public static void ShowAbsoluteMagnitude(float num, float num2){
        File[] files = new File("src\\Stars\\").listFiles();
        for (File file : files) {
            Star star = StarDeserializer(file.getName());
            if(star.getAbsoluteMagnitude() > num && star.getTemperature() < num2) {
                star.show();
            }
        }
    }
    public static void ShowByHemisphere(String hem){
        File[] files = new File("src\\Stars\\").listFiles();
        for (File file : files) {
            Star star = StarDeserializer(file.getName());
            if(star.getHemisphere().equals(hem)) {
                star.show();
            }
        }
    }
    public static void ShowPotSupernova(){
        File[] files = new File("src\\Stars\\").listFiles();
        for (File file : files) {
            Star star = StarDeserializer(file.getName());
            if(star.getMass() > 1.44) {
                star.show();
            }
        }
    }
}
