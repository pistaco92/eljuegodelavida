public class GraficaDecorator implements ServicioI {

    private final Servicio service;

    private static class StringHandler {

        private String text = "";

        public void addRow(int element) {
            text += element;
        }
        public void addElement(boolean element) {
            text += " " + element;
        }

        public void addRowSeparator() {
            text += "\n";
        }

        public void print() {
            System.out.println(text);
        }
    }
    private GraficaDecorator(Servicio servicio) {
        this.service = servicio;
    }
    public static ServicioI decorateService(Servicio servicio) {
        return new GraficaDecorator(servicio);
    };

    private void graficar(Estado estado) {
        StringHandler stringHandler = new StringHandler();
        for (int row = 0; row < estado.getRowLen(); row++) {
            stringHandler.addRow(row);
            for (int columna = 0; columna < estado.getColumnLen(); columna++) {
                stringHandler.addElement(estado.getData(row, columna));
            }
            stringHandler.addRowSeparator();
        }
        stringHandler.print();
    }
    @Override
    public Estado tick() {
        Estado estado = service.tick();
        graficar(estado);
        return estado;
    }
    @Override
    public boolean subtick(int row, int columna) {
        Estado estado = service.tick();
        graficar(estado);
        return service.subtick(row, columna);
    }
}
