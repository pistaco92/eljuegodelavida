public class ServicioCheckForCellLiveOrDie implements ServicioI {

    private Estado estado;
    private boolean[][] nextGen;


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

    public static ServicioI createFromEstado(Estado estado) {
        int rowI = estado.getColumnLen();
        int columnI = estado.getColumnLen();
        boolean[][] nextGen = new boolean[rowI][columnI];
        CheckLimitComand.limiteSuperior = rowI;
        return new ServicioCheckForCellLiveOrDie(estado, nextGen);

    };
    static ServicioI createFromSeed(Estado estado, boolean[][] seed) {
        int rowI = seed.length;
        int columnI = seed[0].length;
        boolean[][] nextGen = new boolean[rowI][columnI];
        ServicioCheckForCellLiveOrDie.CheckLimitComand.limiteSuperior = rowI;
        return new ServicioCheckForCellLiveOrDie(estado, nextGen);
    }


    private void checkSorrundinghelper(int row, int columna, ServicioCheckForCellLiveOrDie.SorroundingsCountCommand conteo) {
        if (ServicioCheckForCellLiveOrDie.CheckLimitComand.execute(row, columna)) {
            boolean casilla = estado.getData(row, columna);
            conteo.execute(casilla);
        }

    }

    private void checkSorroundings(int row, int columna, ServicioCheckForCellLiveOrDie.SorroundingsCountCommand conteo) {
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

    @Override
    public Estado tick() {
        for (int row = 0; row < estado.getRowLen(); row ++) {
            for (int columna = 0; columna < estado.getColumnLen(); columna++) {
                nextGen[row][columna] = subtick(row, columna);
            }
        }
        return Estado.fromNextGen(nextGen);
    }

    @Override
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

