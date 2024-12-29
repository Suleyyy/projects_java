import classes.*;

public class Main {
    public static void main(String[] args) {
        try {
            Star star = new Star("ABC1234", "RandomName", new Declination(70, 40, 50.50F),
                    new RightAscension(22, 40, 50), 10.40F, 100, "Apus", 5000, 0.5F);
            star.show();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}