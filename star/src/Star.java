public class Star {

    private String name;
    private String cat_name;
    private float declination;
    private float rectascence;
    private float observ_size;
    private float absolute_size;
    private float lightYear_distance;
    private String constellation;
    private String hemisphere;
    private float temperature;
    private float mass;

    public Star(String n, String cn, float dec, float rec, float obs, float abs, float lyd, String cons, String hemi, float temp, float ms) {
         name = n;
         cat_name = cn;
         declination = dec;
         rectascence = rec;
         observ_size = obs;
         absolute_size = abs;
         lightYear_distance = lyd;
         constellation = cons;
         hemisphere = hemi;
         temperature = temp;
         mass = ms;
    }

}
