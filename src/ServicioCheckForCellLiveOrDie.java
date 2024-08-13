public class ServicioCheckForCellLiveOrDie {

    private Estado estado;
    private boolean[][] nextGen;

    private static int rowLen;
    private static int columnaLen;


    private static int umbralOverPoblacion = 2, umbralHiperPoblacion = 3, umbralRevivir = 3;

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
        public static int limiteSuperior, limiteInferior = 0;


        public static boolean execute(int row, int columna) {
            return row < limiteSuperior && columna < limiteSuperior && row >= limiteInferior && columna >= limiteInferior;
        }
    }

    private ServicioCheckForCellLiveOrDie(Estado estado, boolean[][] nextGen) {
        this.estado = estado;
        this.nextGen = nextGen;
    }

    public static ServicioCheckForCellLiveOrDie createFromEstado(Estado estado) {
        rowLen = estado.getColumnLen();
        columnaLen = estado.getColumnLen();
        boolean[][] nextGen = new boolean[rowLen][columnaLen];
        CheckLimitComand.limiteSuperior = rowLen;
        return new ServicioCheckForCellLiveOrDie(estado, nextGen);

    };
    static ServicioCheckForCellLiveOrDie createFromSeed(Estado estado, boolean[][] seed) {
        rowLen = seed.length;
        columnaLen = seed[0].length;
        boolean[][] nextGen = new boolean[rowLen][columnaLen];
        return new ServicioCheckForCellLiveOrDie(estado, nextGen);
    }


    private void checkSorroundings(int row, int columna, ServicioCheckForCellLiveOrDie.SorroundingsCountCommand conteo) {

        // nombres


        int rowAnterior = row - 1;
        int rowDespues = row + 1;
        int columnaAnterior = columna - 1;
        int columnaDespues = columna + 1;

        for (int rowIndex = rowAnterior; rowIndex <= rowDespues ; rowIndex++) {
            for (int columnaIndex = columnaAnterior; columnaIndex <= columnaDespues ; columnaIndex++) {
                if (ServicioCheckForCellLiveOrDie.CheckLimitComand.execute(row, columna)) {
                    boolean casilla = estado.getData(row, columna);
                    conteo.execute(casilla);
                }
            }
        }
    }

    public boolean overpoplacion(int row, int columna) {
        SorroundingsCountCommand conteo = new SorroundingsCountCommand();
        checkSorroundings(row, columna, conteo);
        return conteo.vivas < umbralOverPoblacion && estado.getData(row, columna);
    };


    public boolean hiperpoblacion(int row, int columna) {
        SorroundingsCountCommand conteo = new SorroundingsCountCommand();
        checkSorroundings(row, columna, conteo);
        return conteo.vivas > umbralHiperPoblacion && estado.getData(row, columna);
    };

    private boolean revivir(int row, int columna) {
        SorroundingsCountCommand conteo = new SorroundingsCountCommand();
        checkSorroundings(row, columna, conteo);
        return conteo.vivas == umbralRevivir && !estado.getData(row, columna);
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


}

