import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

class FactoryTestTest {

    static class JsonUtilices {
        public static JsonNode cargarJsonNode() {
            try {
                ObjectMapper object = new ObjectMapper();
                JsonNode jsonSeed = object.readTree(new File("/home/pistaco/IdeaProjects/ElJuegoDeLaVida/src/seedJson.json"));
                return jsonSeed;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public static boolean[][] cargarJsonBoolean(String value) {
            try {
                ObjectMapper object = new ObjectMapper();
                JsonNode jsonSeed = object.readTree(new File("/home/pistaco/IdeaProjects/ElJuegoDeLaVida/src/seedJson.json"));
                String valorString = jsonSeed.get(value).toString();
                return object.readValue(valorString, boolean[][].class);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Test
    void tick() {
        boolean[][] sedeo = JsonUtilices.cargarJsonBoolean("sedeo");
        boolean[][] resultado = JsonUtilices.cargarJsonBoolean("resultado");

        Factory.Servicio servicio = Factory.createServiceWithState(sedeo);
        Factory.Estado estado = servicio.tick();


        for (int row = 0; row < estado.getRowLen(); row++) {
            for (int column = 0; column < estado.getColumnLen(); column++) {
                assert resultado != null;
                boolean estadoCasilla = estado.getData(row, column);
                boolean resultadoCasilla = resultado[row][column];
                assert estadoCasilla == resultadoCasilla;


            }
        }


    }



    @Test
    void subtickMedio() {
        boolean[][] sedeo = JsonUtilices.cargarJsonBoolean("sedeo");
        Factory.Servicio servicio = Factory.createServiceWithState(sedeo);
        assert servicio.subtick(1, 1);
    }

    @Test
    void subtick() {
        int row = 2;
        int columna = 1;

        boolean[][] sedeo = JsonUtilices.cargarJsonBoolean("sedeo");
        Factory.Servicio servicio = Factory.createServiceWithState(sedeo);
        assert servicio.subtick(1, 1);
    }

    @Test
    void testDataEstado() {
        boolean[][] sedeo = JsonUtilices.cargarJsonBoolean("sedeo");
        Factory.Estado estado = Factory.Estado.setseed(sedeo);


        assert !estado.getData(0, 0);
        assert estado.getData(1, 0);
        assert estado.getData(2, 0);

        assert estado.getRowLen() == 3;
        assert estado.getColumnLen() == 3;


    }


    @Test
    void TestGrafica() {
    }

    @BeforeEach
    void before() {

    }

    @AfterEach
    void after() {
    }
}