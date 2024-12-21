import java.util.*;

public class Main {

    public static class Ship {
        int[][] position; //tablica wsplorzednych x y z


        public Ship(int[][] positions) {
            this.position = positions;

        }

        // tworzenie statków, 1 dla kazdego gracza,zwracanie talicy z nimi
        public static Ship[] shipPlacement() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Gracz 1: Podaj 4 koordynaty twojego statku, kolejno x y z naciskając Enter po każdym");
            int[][] p1Cords = new int[4][3];
            int counter = 0;

            while (counter < 4) {
                System.out.println("Podaj koordynaty punktu " + (counter + 1) + " (x y z):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int z = scanner.nextInt();

                if (counter == 0 || isConnected(p1Cords, counter, x, y, z)  && notOnBorder(x,y,z)&& notSameCords(p1Cords, counter, x, y, z)) {
                    p1Cords[counter][0] = x;
                    p1Cords[counter][1] = y;
                    p1Cords[counter][2] = z;
                    counter++;
                } else {
                    System.out.println("Koordynaty muszą być połączone z poprzednimi! Spróbuj ponownie.");
                }
            }

            counter = 0;
            System.out.println("Gracz 2: Podaj 4 koordynaty twojego statku, kolejno x y z naciskając Enter po każdym");
            int[][] p2Cords = new int[4][3];

            while (counter < 4) {
                System.out.println("Podaj koordynaty punktu " + (counter + 1) + " (x y z):");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int z = scanner.nextInt();

                if (counter == 0 || isConnected(p2Cords, counter, x, y, z) && notOnBorder(x,y,z)&& notSameCords(p2Cords, counter, x, y, z)) {
                    p2Cords[counter][0] = x;
                    p2Cords[counter][1] = y;
                    p2Cords[counter][2] = z;
                    counter++;
                } else {
                    System.out.println("Koordynaty muszą być połączone z poprzednimi! Spróbuj ponownie.");
                }
            }

            scanner.close();
            Ship player1Ship = new Ship(p1Cords);
            Ship player2Ship = new Ship(p2Cords);

            return new Ship[]{player1Ship, player2Ship};
        }

        private static boolean isConnected(int[][] cords, int counter, int x, int y, int z) {
            int prevx = cords[counter - 1][0];
            int prevy = cords[counter - 1][1];
            int prevz = cords[counter - 1][2];

            return prevx - x <= 1 && prevy - y <= 1 && prevz - z <= 1;
        }

        private static boolean notOnBorder(int x, int y, int z) {
            return x >= 1 && x <= 8 && y >= 1 && y <= 8 && z >= 1 && z <= 8;
        }
        private static boolean notSameCords(int[][] cords, int counter, int x, int y, int z) {
            for (int i = 0; i < counter; i++) {
                if (cords[i][0] == x && cords[i][1] == y && cords[i][2] == z) {
                    return false;
                }
            }
            return true;
        }
        // metoda sprawdza czy kazy otaczajacy element zostal trafiony
        public static boolean isShipPointDestroyed(String[][][] space, int x, int y, int z) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {

                        int newX = x + i;
                        int newY = y + j;
                        int newZ = z + k;

                        if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10 && newZ >= 0 && newZ < 10) {
                            if (space[newX][newY][newZ] != "~"  || space[newX][newY][newZ] != "#" ) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }


        //sprawdza czy kazda czesc jest ostrzelana i powinna konczyc gre jesli tak
        public void checkShipStatus(String[][][] space) {
            int counter = 0;
            for (int i = 0; i < position.length; i++) {
                int x=position[i][0];
                int y=position[i][1];
                int z=position[i][2];
                if(isShipPointDestroyed(space,x,y,z)==true) {
                    counter++;
                }
                if(counter == 4) {
                    System.out.println("UWAGAAA NABIERAMY WODY WYSŁAĆ SOS!!!!");
                }
            }
        }

        //metoda do strzelania, zwraca cordy ostrzelane
        public static int[] Big380mmCannonShoot(){
            int[] cords = new int[3];
            System.out.println("Podaj koordynaty do strzału(0,10) ! kolejno x y z");
            Scanner scanner = new Scanner(System.in);
            cords[0] = scanner.nextInt();
            cords[1] = scanner.nextInt();
            cords[2] = scanner.nextInt();
            for(int s : cords){

                if (s <0 || s > 10){
                    System.out.println("Kapitanie, złe koordynaty!!! , ostrzal punktu 0 0 0");
                    break;

                }
                System.out.println("Namierzanie!");
                System.out.println("OGNIAAAA!");
                return cords;
            }

            return new int[]{0, 0, 0};

        }
        //sprawdza czy podany statek jest blisko (wpisac statek oponenta)
        public static boolean isShipNear(Ship ship, int x, int y, int z) {

            for (int i = 0; i < ship.position.length; i++) {
                int shipx = ship.position[i][0];
                int shipy = ship.position[i][1];
                int shipz = ship.position[i][2];

                for (int plusx = -1; plusx <= 1; plusx++) {
                    for (int plusy = -1; plusy <= 1; plusy++) {
                        for (int plusz = -1; plusz <= 1; plusz++) {
                            int checkx = x + plusx;
                            int checky = y + plusy;
                            int checkz = z + plusz;

                            if (checkx == shipx && checky == shipy && checkz == shipz) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }



    }
    public static void main(String[] args) {
        String[][][] space = new String[10][10][10]; // x y z
            for (int i = 0; i < space.length; i++) {
                for (int j = 0; j < space.length; j++) {
                    for (int k = 0; k < space.length; k++) {
                        space[i][j][k] = "-";
                    }
                }
            }




    }
}
