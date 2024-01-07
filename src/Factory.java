import java.security.Provider;
import java.util.ArrayList;

public class Factory {



    public interface FactorySubClass {

    }


    public static class Estado implements FactorySubClass {
        private boolean[][] data;

        private Estado(boolean[][] data) {
            this.data = data;
        }

        public static Estado fromNextGen(boolean[][] nextGen) {
            return new Estado(nextGen);
        }

        public boolean getData(int row, int column) {
            return data[row][column];
        };

        public static Estado setseed(boolean[][] data) {
            return new Estado(data);
        }

        public int getRowLen() {
            return data.length;
        }

        public int getColumnLen() {
            return data[0].length;
        }
    }
    public static class Servicio implements FactorySubClass  {
        private Estado estado;
        private boolean[][] nextGen;

        private static class SorroundingsCountCommand {
            int vivas = 0, muertas = 0;

            public void execute(boolean data) {
                if (data) {
                    vivas++;
                }
                if (!data) {
                    muertas++;
                }
            }
        }

        private static class CheckLimitComand {
            public static int limite;


            public static boolean execute(int row, int columna) {
                return row < limite && columna < limite && row >= 0 && columna >= 0;
            }
        }

        private Servicio(Estado estado, boolean[][] nextGen) {
            this.estado = estado;
            this.nextGen = nextGen;
        }

        private static Servicio createFromSeed(Estado estado, boolean[][] seed) {
            int rowI = seed.length;
            int columnI = seed[0].length;
            boolean[][] nextGen = new boolean[rowI][columnI];
            CheckLimitComand.limite = rowI;
            return new Servicio(estado, nextGen);
        }


        private void checkSorrundinghelper(int row, int columna, SorroundingsCountCommand conteo) {
            if (CheckLimitComand.execute(row, columna)) {
                boolean casilla = estado.getData(row, columna);
                conteo.execute(casilla);
            }

        }

        private void checkSorroundings(int row, int columna, SorroundingsCountCommand conteo) {
            checkSorrundinghelper(row - 1, columna, conteo);
            checkSorrundinghelper(row + 1, columna, conteo);
            checkSorrundinghelper(row, columna - 1, conteo);
            checkSorrundinghelper(row, columna + 1, conteo);

            checkSorrundinghelper(row + 1, columna + 1, conteo);
            checkSorrundinghelper(row - 1, columna + 1, conteo);
            checkSorrundinghelper(row - 1, columna - 1, conteo);
            checkSorrundinghelper(row + 1, columna - 1, conteo);
        }

        public boolean overpoplacion(int row, int columna) {
            SorroundingsCountCommand conteo = new SorroundingsCountCommand();
            checkSorroundings(row, columna, conteo);
            return conteo.vivas < 2 && estado.getData(row, columna);
        };


        public boolean hiperpoblacion(int row, int columna) {
            SorroundingsCountCommand conteo = new SorroundingsCountCommand();
            checkSorroundings(row, columna, conteo);
            return conteo.vivas > 3 && estado.getData(row, columna);
        };

        private boolean revivir(int row, int columna) {
            SorroundingsCountCommand conteo = new SorroundingsCountCommand();
            checkSorroundings(row, columna, conteo);
            return conteo.vivas == 3 && !estado.getData(row, columna);
        }

        public Estado tick() {
            for (int row = 0; row < estado.getRowLen(); row ++) {
                for (int columna = 0; columna < estado.getColumnLen(); columna++) {
                    nextGen[row][columna] = subtick(row, columna);
                }
            }
            return Estado.fromNextGen(nextGen);
        }

        public boolean subtick(int row, int columna) {
            boolean overcheck, hipercheck, revivirCheck;
            overcheck = overpoplacion(row, columna);
            hipercheck = hiperpoblacion(row, columna);
            revivirCheck = revivir(row, columna);

            if (overcheck || hipercheck) {
                return false;
            }

            else if (revivirCheck) {
                return true;
            }

            else {
                return estado.getData(row, columna);
            }
        }


        ;
    }

    private class Grafica implements FactorySubClass {

    }
    private class Juego implements FactorySubClass {

    }


    public static Servicio createServiceWithState(boolean[][] seed ) {
        Estado estado = Estado.setseed(seed);
        return Servicio.createFromSeed(estado, seed);
    }
}
