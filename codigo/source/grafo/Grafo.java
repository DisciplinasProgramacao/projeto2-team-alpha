package source.grafo;

import source.ABB;
import source.Lista;

/**
 * Classe básica para um Grafo simples
 */
public abstract class Grafo {
    // #region Atributos

    public final String nome;
    protected ABB<Vertice> vertices;
    // #endregion

    // #region Construtor

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(String nome) {
        this.nome = nome;
        this.vertices = new ABB<>();
    }
    // #endregion

    // #region Getters

    public int tamanho() {
        int qtdArestas = 0;

        Vertice[] verticesArray = getAllVertices();

        for (Vertice vertice : verticesArray) {
            qtdArestas += vertice.getGrau();
        }

        return this.ordem() + (qtdArestas / 2);
    }

    public int ordem() {
        return this.vertices.size();
    }
    // #endregion

    // #region Métodos booleanos

    public boolean eCompleto() {
        return false;
    }

    public boolean eEuleriano() {
        return false;
    }
    // #endregion

    // #region Métodos de Vértices

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o
     * próximo id disponível.
     */
    public Vertice addVertice(int id) {
        Vertice novo = new Vertice(id);
        this.vertices.add(id, novo);

        return novo;
    }

    public Vertice existeVertice(int idVertice) {
        return this.vertices.find(idVertice);
    }

    public Vertice[] getAllVertices() {
        Vertice[] verticesArray = new Vertice[this.ordem()];
        verticesArray = vertices.allElements(verticesArray);

        return verticesArray;
    }

    public Vertice[] buscaEmProfundidade(int id, Vertice[] visitados) {
        Vertice[] verticesArray = getAllVertices();

        Vertice[] listaAdjacencia = new Vertice[verticesArray.length];
        for (int i = 0; i < verticesArray.length; i++) {
            if (verticesArray[i].existeAresta(id)) {
                listaAdjacencia[i] = verticesArray[i];
            }
        }

        for (int j = 0; j < listaAdjacencia.length; j++) {
            if (listaAdjacencia[j] != null) {
                if (!listaAdjacencia[j].foiVisitado()) {
                    listaAdjacencia[j].visitar();
                    visitados[j] = listaAdjacencia[j];
                    buscaEmProfundidade(listaAdjacencia[j].getId(), visitados);
                }
            }
        }

        return visitados;
    }

    
    // #endregion

    // #region Métodos de Arestas

    public boolean addAresta(int origem, int destino, int peso) {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if (saida != null && chegada != null) {
            saida.addAresta(destino, peso);
            chegada.addAresta(origem, peso);
            return true;
        }

        return false;
    }

    public Aresta existeAresta(int verticeA, int verticeB) {
        Aresta aresta = vertices.find(verticeA).arestaConectadaCom(verticeB);
        if (aresta != null) {
            return aresta;
        }

        return null;
    }

    public abstract Grafo subGrafo(Lista<Vertice> vertices) throws Exception;
    // #endregion

    // #region Métodos Aux

   
    // #endregion
}
