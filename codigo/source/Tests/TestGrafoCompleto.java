package source.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import source.grafo.GrafoCompleto;

public class TestGrafoCompleto {


    @Test
    void testECompletoPosito() {
        GrafoCompleto grafo1 = new GrafoCompleto("testaGrafoCompleto", 2);
        grafo1.addVertice(1);
        grafo1.addVertice(2);
        grafo1.addVertice(3);
        grafo1.addAresta(1,2,1);
        grafo1.addAresta(2,3,1);
        grafo1.addAresta(1,3,1);
        assertTrue(grafo1.completo(),"Testa se o respectivo grafo é completo");
        
        
    }

    @Test
    void testECompletoNegativo() {
        GrafoCompleto grafo1 = new GrafoCompleto("testaGrafoCompleto", 2);
        grafo1.addVertice(1);
        grafo1.addVertice(2);
        grafo1.addVertice(3);
        grafo1.addAresta(1,2,1);
        grafo1.addAresta(2,3,1);
        assertFalse(grafo1.completo(),"Testa se o respectivo grafo é imcompleto");
    }


    @Test
    void testGetOrdem() {
        GrafoCompleto grafo1 = new GrafoCompleto("testaGrafoCompleto", 2);
        grafo1.addVertice(1);
        grafo1.addVertice(2);
        grafo1.addVertice(3);
        grafo1.addAresta(1,2,1);
        grafo1.addAresta(2,3,1);
        grafo1.addAresta(1,3,1);
        assertEquals(2, grafo1.ordem(),"Testa ordem do respectivo gravo");
    }


    @Test
    void testSubGrafo() {
        GrafoCompleto grafo1 = new GrafoCompleto("testaSubGrafo", 2);
        grafo1.addVertice(1);
        grafo1.addVertice(2);
        grafo1.addVertice(3);
        grafo1.addAresta(1,2,1);
        grafo1.addAresta(2,3,1);
        grafo1.addAresta(1,3,1);
    }
}
