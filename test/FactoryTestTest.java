import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

class TestTest {
    @Test
    void testSimultaneoManipulado() {
        boolean[][] seed = new boolean[10][10];
        seed[0][0] = true;
        seed[0][1] = true;
        seed[1][0] = true;

        Factory factory = Factory.createFactoryObject();
        GraficaDecorator servicio = factory.setSeed(seed).createGrafic().build();
        Estado estado = servicio.tick();

        assert estado.getData(0,0);
        assert estado.getData(0,1);
        assert estado.getData(1,0);
        assert estado.getData(1,1);
    }
}