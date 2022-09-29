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

        Vertice[] listaAdjacencia = listaDeAdjacencia(id);

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

    public Vertice[] encontrarCaminho(int verticeInicial, int verticeDestino, Vertice[] visitados) {

        Vertice[] listaAdjacencia = listaDeAdjacencia(verticeInicial);

        Vertice inicial = this.existeVertice(verticeInicial);

        int indice = 0;

        if (inicial.existeAresta(verticeDestino)) {
            visitados[indice] = inicial;
            indice++;
        } else {
            for (int j = 0; j < listaAdjacencia.length; j++) {
                if (listaAdjacencia[j] != null) {
                    if (listaAdjacencia[j].existeAresta(inicial.getId())) {
                        encontrarCaminho(listaAdjacencia[j].getId(), verticeDestino, visitados);
                    }
                }
            }
        }

        return visitados;
    }

    public Vertice[] listaDeAdjacencia(int id) {

        Vertice[] verticesArray = getAllVertices();

        Vertice[] listaAdjacencia = new Vertice[verticesArray.length];
        for (int i = 0; i < verticesArray.length; i++) {
            if (verticesArray[i].existeAresta(id)) {
                listaAdjacencia[i] = verticesArray[i];
            }
        }

        return listaAdjacencia;
    }

    public abstract Grafo subGrafo(Lista<Vertice> vertices);
    // #endregion

    // #region Métodos Aux

    /*
     * public Lista<Vertice> caminhoEuleriano(Vertice verticess) {
     * Vertice[] vertices = this.getAllVertices();
     * Lista<Vertice> lista = new Lista<Vertice>();
     * 
     * for (Vertice vertice : vertices) {
     * if (this.existeAresta(vertice.getId(), verticess.getId()) != null) {
     * lista.add(vertice);
     * this.();
     * getEulerPath(this, path, vertice);
     * break;
     * }
     * }
     * }
     */
    // #endregion
}
