package source.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import source.Lista;
import source.grafo.GrafoPonderado;
import source.grafo.Vertice;

public class TestGrafoPonderado {
    @Test
    void testAddAresta() {
        GrafoPonderado grafoPonderado = new GrafoPonderado("grafoNaoPonderado");
        grafoPonderado.addVertice(1);
        grafoPonderado.addVertice(2);
       
        grafoPonderado.addAresta(1,2,2);
        grafoPonderado.addAresta(2,3,3);
        grafoPonderado.addAresta(1,3,1);
        //Test de arestas (????)
        assertEquals(5,grafoPonderado.existeAresta(5,4),"Testa se aresta existe");
        
    }


    @Test
    void testSubGrafo() {
        
            GrafoPonderado grafoPonderado = new GrafoPonderado("grafoNaoPonderado");
            grafoPonderado.addVertice(1);
            grafoPonderado.addVertice(2);
            grafoPonderado.addVertice(3);
            grafoPonderado.addVertice(4);
            grafoPonderado.addVertice(5);
            grafoPonderado.addAresta(1,2,2);
            grafoPonderado.addAresta(2,3,3);
            grafoPonderado.addAresta(1,3,1);
            grafoPonderado.addAresta(2,4,2);
            grafoPonderado.addAresta(1,4,2);
    
            Lista<Vertice> listaDeVertices = new Lista<Vertice>();
            listaDeVertices.add(new Vertice(1));
            listaDeVertices.add(new Vertice(2));
            listaDeVertices.add(new Vertice(4));
            GrafoPonderado subGrafo = grafoPonderado.subGrafo(listaDeVertices);
            
            assertEquals(3, subGrafo.ordem(),"Testa ordem do respectivo gravo");
            assertEquals(6,subGrafo.tamanho(),"Testa tamanho do respectivo grafo");
            
        
    }
}
