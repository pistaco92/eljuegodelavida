public class MatrizState {

    private boolean data;
    private boolean setAlready;
    public static MatrizState setFirstTime(boolean[][] seed) {
        return new MatrizState();
    }

    public static MatrizState setForNext(boolean[][] seed) {
        return new MatrizState();
    }

    public boolean[] getCopy() {
        return null;
    }
    public static boolean[] getForNext() {
        return null;
    }

    public static boolean[] getForService() {
        return null;
    }

    public static boolean[] getForIter() {
        return null;
    }
}
