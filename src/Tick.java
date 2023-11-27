public class Tick {

    public interface FuncionTick {

        public boolean setState();

        public void ForEachCell(boolean cell);


        public void run();
        public boolean[][] getResults();

    }

    public static class FuncionTickDefault implements FuncionTick {

        @Override
        public boolean setState() {
            return false;
        }

        @Override
        public void ForEachCell(boolean cell) {

        }

        @Override
        public void run() {

        }

        @Override
        public boolean[][] getResults() {
            return new boolean[0][];
        }
    }

    private MatrizServicio servicio;
    private FuncionTick funcion;
    private ServicioView nextState;

    public static void setWithFuncion(FuncionTick funcion) {

    }

    public static Tick setWithDefault(MatrizServicio servicio) {
        return null;
    };

    public static void goNextState() {

    }
    public static void aplicateNextState() {

    }
    public static void run() {

    }
}
