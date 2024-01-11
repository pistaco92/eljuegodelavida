public class Estado {
    private boolean[][] data;

    private Estado(boolean[][] data) {
        this.data = data;
    }

    public static Estado fromNextGen(boolean[][] nextGen) {
        return new Estado(nextGen);
    }

    public boolean getData(int row, int column) {
        return data[row][column];
    };

    public static Estado setseed(boolean[][] data) {
        return new Estado(data);
    }

    public int getRowLen() {
        return data.length;
    }

    public int getColumnLen() {
        return data[0].length;
    }
}
