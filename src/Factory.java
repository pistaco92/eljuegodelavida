public class Factory {
    private boolean[][] seedForMatriz;
    private MatrizState matrizState;

    public void setSeed(boolean[][] seed) {
        seedForMatriz = seed;
        matrizState = MatrizState.setFirstTime(seed);
    };

    public MatrizServicio create() {

        return MatrizServicio.setMatrizState(matrizState);
    };


}
