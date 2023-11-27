public class MatrizServicio {
    private MatrizState state;

    private static class SorroundignView implements ServicioView  {
        private int x;
        private int y;

        public void setReferences(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int[] getReferences() {
            return new int[]{x, y};
        }

        @Override
        public void log() {

        }

        @Override
        public boolean[] getCopyForIter() {
            return new boolean[0];
        }

        @Override
        public boolean[] getCopyData() {
            return new boolean[0];
        }

        @Override
        public boolean setFromServicio(boolean[][] data) {
            return false;
        }
    }

    private static class NextGenView implements ServicioView  {
        private boolean[][] data;
        private boolean[] dataForIter;
        private static final TickDefaultFuntion defaultF = TickDefaultFuntion.createToNextGenView();

        public void aplicateDeafault() {

        }

        public static MatrizState castToState(NextGenView nextGenView) {
            return null;
        }

        @Override
        public void log() {

        }

        @Override
        public boolean[] getCopyForIter() {
            return new boolean[0];
        }

        @Override
        public boolean[] getCopyData() {
            return new boolean[0];
        }

        @Override
        public boolean setFromServicio(boolean[][] data) {
            return false;
        }
    }

    private static class TerminalView implements ServicioView  {
        private StringBuffer dataForLog;
        private boolean[][] stateData;


        private void createTerminalView() {

        }

        private void print() {

        }

        @Override
        public void log() {
            createTerminalView();
            print();
        }

        @Override
        public boolean[] getCopyForIter() {
            return new boolean[0];
        }

        @Override
        public boolean[] getCopyData() {
            return new boolean[0];
        }

        @Override
        public boolean setFromServicio(boolean[][] data) {
            return false;
        }

    }

    public static SorroundignView getSorroundings()  {
        return new SorroundignView();
    };

    public static NextGenView getNextGenView()  {
        return new NextGenView();
    };
    public static TerminalView getTerminalView()  {
        return new TerminalView();

    };

    public MatrizState newStateFromNext(NextGenView nextGenView) {
        return null;
    }


    public static MatrizServicio setMatrizState(MatrizState matrizState) {
        return createWithState(matrizState);
    }

    private static MatrizServicio createWithState(MatrizState matrizState) {
        MatrizServicio ma = new MatrizServicio();
        ma.state = matrizState;
        return ma;
    }


}
