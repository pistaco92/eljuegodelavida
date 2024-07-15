import javax.management.ObjectName;

public class Factory {


    private Estado estado_;
    private ServicioCheckForCellLiveOrDie servicio_;
    private GraficaDecorator servicioWithGrafic;

    private boolean withGrafic;
    private boolean seedSet;
    private boolean serviceCreated;
    public static Factory createFactoryObject() {
        return new Factory();
    }


    public Factory setSeed(boolean[][] seed) {
        if (!seedSet) {
            estado_ = Estado.setseed(seed);
            seedSet = true;
        }
        return this;
    }
    public Factory setSeedFromNextGeneration(Estado estadoNextGen) {
        if (!seedSet) {
            estado_ = estadoNextGen;
            seedSet = true;
        }
        return this;
    };

    public Factory createGrafic() {
        if (!serviceCreated) {
            ServicioCheckForCellLiveOrDie servicioCheckForCellLiveOrDie = (ServicioCheckForCellLiveOrDie) ServicioCheckForCellLiveOrDie.createFromEstado(estado_);
            serviceCreated = true;
            servicioWithGrafic = GraficaDecorator.decorateService(servicioCheckForCellLiveOrDie);
            withGrafic = true;
        } else {
            servicioWithGrafic = GraficaDecorator.decorateService((ServicioCheckForCellLiveOrDie) servicio_);
            withGrafic = true;
        }
        return this;
    };

    public <T> T build() {
        if (withGrafic) {
            return (T) servicioWithGrafic;
        }
        return (T) servicio_;
    }

    public static <T> T createServiceWithState(boolean[][] seed ) {
        Estado estado = Estado.setseed(seed);
        return (T) ServicioCheckForCellLiveOrDie.createFromSeed(estado, seed);
    }
}
