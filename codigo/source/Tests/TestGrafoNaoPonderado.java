package source.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import source.Lista;
import source.grafo.Aresta;
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
        assertEquals(6,subGrafo.tamanho(),"Testa tamanho do respectivo grafo");
        
    }
    
    @Test
    void testBuscaEmProfundidade() {
        GrafoNaoPonderado grafo = new GrafoNaoPonderado("GrafoDaBuscaEmProfundidade");
        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addVertice(3);
        grafo.addVertice(4);
        grafo.addVertice(5);
        grafo.addAresta(1, 3);
        grafo.addAresta(1, 2);
        grafo.addAresta(3, 5);
        Lista<Vertice> visitados = grafo.buscaEmProfundidade(1);
        Vertice[] visitadosArray = new Vertice[4];
        //criar array com vertices escolhidos (???)
        assertEquals(visitados.allElements(visitadosArray),visitadosArray,"Testa vertives visitados na busca");
    }
}
