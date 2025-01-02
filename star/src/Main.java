import classes.*;

public class Main {
    public static void main(String[] args) {
        try {
            Star star = new Star("ASA1234", new Declination(70, 40, 50.50F),
                    new RightAscension(22, 40, 50), 10.40F, 100, "Apus", 5000, 0.5F);
            //Star.StarSerializer(star);
            //Star deserializedStar = Star.StarDeserializer("ABC1234");
            //deserializedStar.show();
            //Star.ShowAllStars();
            //Star.DeleteStar("beta Apus");
            Star.ShowStarsFromConstellation("Apus");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}