import classes.*;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        for (Constellation constellation : Constellation.values()) {
            System.out.println(constellation);
        }
    }
}