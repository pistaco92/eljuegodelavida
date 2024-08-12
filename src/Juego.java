public class Juego {

    private static int tiempoDePausa = 1000;

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
            Thread.sleep(tiempoDePausa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
