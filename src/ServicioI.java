public interface ServicioI {
    static ServicioI createServiceWithState(boolean[][] seed) {
        Estado estado = Estado.setseed(seed);
        return Servicio.createFromSeed(estado, seed);
    }

    Estado tick();

    boolean subtick(int row, int columna);
}
