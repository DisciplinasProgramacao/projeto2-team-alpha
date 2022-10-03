package source.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.Lista;
import source.grafo.GrafoCompleto;
import source.grafo.GrafoNaoPonderado;
import source.grafo.Vertice;

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
        assertTrue(grafo1.completo(),"Testa se o respectivo grafo é imcompleto");
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
        assertEquals(4, grafo1.ordem(),"Testa ordem do respectivo gravo");
    }


    @Test
    void testSubGrafo() {
        GrafoNaoPonderado grafoNaoPonderado = new GrafoNaoPonderado("grafoNaoPonderado");
        grafoNaoPonderado.addVertice(1);
        grafoNaoPonderado.addVertice(2);
        grafoNaoPonderado.addVertice(3);
        grafoNaoPonderado.addVertice(4);
        grafoNaoPonderado.addVertice(5);
        grafoNaoPonderado.addAresta(1,2);
        grafoNaoPonderado.addAresta(2,3);
        grafoNaoPonderado.addAresta(1,3);
        grafoNaoPonderado.addAresta(2,4);
        grafoNaoPonderado.addAresta(1,4);
        
        Lista<Vertice> listaDeVertices = new Lista<Vertice>();
        listaDeVertices.add(new Vertice(1));
        listaDeVertices.add(new Vertice(2));
        listaDeVertices.add(new Vertice(4));
        GrafoNaoPonderado subGrafo = grafoNaoPonderado.subGrafo(listaDeVertices);
        
        assertEquals(4, subGrafo.ordem(),"Testa ordem do respectivo gravo");
        
    }
}
