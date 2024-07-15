public class Factory {


    private Estado estado_;
    private ServicioI servicio_;

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
            servicio_ = GraficaDecorator.decorateService(servicioCheckForCellLiveOrDie);
            withGrafic = true;
        } else {
            servicio_ = GraficaDecorator.decorateService((ServicioCheckForCellLiveOrDie) servicio_);
            withGrafic = true;
        }
        return this;
    };

    public ServicioI build() {
        return servicio_;
    }

    public static ServicioI createServiceWithState(boolean[][] seed ) {
        Estado estado = Estado.setseed(seed);
        return ServicioCheckForCellLiveOrDie.createFromSeed(estado, seed);
    }
}
