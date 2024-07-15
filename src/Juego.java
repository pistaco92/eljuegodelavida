public class Juego {

    public static void run(boolean[][] seed) {
        Factory factory = Factory.createFactoryObject();
        GraficaDecorator servicio = factory.setSeed(seed).createGrafic().build();
        Estado estado = servicio.tick();
        sleep();
        runInfinito(estado);
    }

    private static void runInfinito(Estado estadoNextGen) {
        Factory factory = Factory.createFactoryObject();
        GraficaDecorator servicio = factory.setSeedFromNextGeneration(estadoNextGen).createGrafic().build();
        Estado estado = servicio.tick();
        sleep();
        runInfinito(estado);
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
