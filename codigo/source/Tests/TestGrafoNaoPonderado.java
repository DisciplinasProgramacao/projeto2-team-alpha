package source.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import source.grafo.GrafoNaoPonderado;

public class TestGrafoNaoPonderado {

    @Test
    void testAddAresta() {

        GrafoNaoPonderado grafoNaoPonderado = new GrafoNaoPonderado("grafoNaoPonderado");

        grafoNaoPonderado.addVertice(1);
        grafoNaoPonderado.addVertice(2);
        grafoNaoPonderado.addVertice(3);
        grafoNaoPonderado.addVertice(4);
        grafoNaoPonderado.addVertice(5);
        grafoNaoPonderado.addAresta(1, 2);
        grafoNaoPonderado.addAresta(2, 5);
        grafoNaoPonderado.addAresta(5, 3);
        grafoNaoPonderado.addAresta(5, 4);
        grafoNaoPonderado.addAresta(4, 3);
        
    }

    @Test
    void testSubGrafo() {

    }
}
