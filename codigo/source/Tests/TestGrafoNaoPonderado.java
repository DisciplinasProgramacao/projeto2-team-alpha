package source.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.Lista;
import source.grafo.GrafoNaoPonderado;
import source.grafo.Vertice;

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
        //Test de arestas (????)
        assertEquals(5,grafoNaoPonderado.existeAresta(5,4),"Testa se aresta existe");
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
        
        assertEquals(3, subGrafo.ordem(),"Testa ordem do respectivo gravo");
        
    }
}
