package source.grafo;

import source.ABB;
import source.Lista;
import java.util.*;

/**
 * Classe básica para um Grafo simples
 */
public abstract class Grafo implements Cloneable {
    //#region Atributos

    public final String nome;
    protected ABB<Vertice> vertices;
    //#endregion

    //#region Construtor

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(String nome) {
        this.nome = nome;
        this.vertices = new ABB<>();
    }
    //#endregion

    //#region Getters

    public int tamanho() {
        return this.ordem() + (this.getAllArestas().length() / 2);
    }

    public int ordem() {
        return this.vertices.size();
    }

    public Vertice getVertice(int id) {
        return vertices.find(id);
    }

    public Vertice[] getAllVertices() {
        Vertice[] allVertices = new Vertice[vertices.size()];
        allVertices = vertices.allElements(allVertices);
        return allVertices;
    }

    public Lista<Aresta> getAllArestas() {
        Lista<Aresta> arestas = new Lista<>();

        for (Vertice vertice : this.getAllVertices()) {
            for(Aresta aresta : vertice.getAllArestas()){
                arestas.add(aresta);
            }
        }

        return arestas;
    }
    //#endregion

    //#region Métodos booleanos

    public boolean completo() {
        Vertice[] vertices = getAllVertices();

        for (Vertice vertice : vertices) {
            if (!vertice.foiVisitado()) {
                for (Vertice destino : vertices) {
                    if ((vertice != destino) && (vertice.existeAresta(destino.getId()) != null)) {
                        return false;
                    }
                }
                vertice.visitar();
            }
            vertice.limparVisita();
        }

        return true;
    }

    public boolean euleriano() {
        for(Vertice vertice : this.getAllVertices()) {
            if(vertice.getGrau() % 2 != 0) {
                return false;
            }
        }

        return true;
    }
    //#endregion

    //#region Métodos de Vértices

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o
     * próximo id disponível.
     */
    public Vertice addVertice(int id) {
        Vertice novo = new Vertice(id);
        this.vertices.add(id, novo);

        return novo;
    }

    public Vertice[] listaAdjacencia(int id) {
        Vertice[] listaAdjacencia = new Vertice[this.ordem()];
        listaAdjacencia = vertices.find(id).getListaAdjacencia().allElements(listaAdjacencia);

        return listaAdjacencia;
    }

    public Vertice existeVertice(int idVertice) {
        return this.vertices.find(idVertice);
    }
    //#endregion

    //#region Métodos de Arestas

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
        return vertices.find(verticeA).existeAresta(verticeB);
    }
    //#endregion

    //#region Métodos Principais
    public Vertice[] encontrarCaminho(int verticeInicial, int verticeDestino, Vertice[] visitados) {
        Vertice[] listaAdjacencia = listaAdjacencia(verticeInicial);
        Vertice inicial = this.existeVertice(verticeInicial);
        Vertice destino = this.existeVertice(verticeDestino);
        int indice = 0;

        if (inicial.existeAresta(verticeDestino) != null) {
            visitados[indice++] = inicial;
        } else {
            for (int j = 0; j < listaAdjacencia.length; j++) {
                if (listaAdjacencia[j] != null) {
                    if (listaAdjacencia[j].existeAresta(destino.getId()) != null) {
                        encontrarCaminho(listaAdjacencia[j].getId(), verticeDestino, visitados);
                    }
                }
            }
        }

        return visitados;
    }

    public abstract Grafo subGrafo(Lista<Vertice> vertices) throws Exception;

    public Lista<Vertice> caminhoEuleriano() throws IllegalStateException {
        if(!this.euleriano()) {
            throw new IllegalStateException("Euler properties infringed");
        }

        Lista<Lista<Integer>> adj = new Lista<>();

        for(Vertice vertice : getAllVertices()) {
            Lista<Integer> vertices = new Lista<>();
            vertices.add(vertice.getId());

            for(Vertice adjacencia : listaAdjacencia(vertice.getId())) {
                if(adjacencia != null) {
                    vertices.add(adjacencia.getId());
                }
            }

            adj.add(vertices);
        }

        Lista<Vertice> caminho = printEulerianCircuit(adj);

        return caminho;
    }

    private Lista<Vertice> printEulerianCircuit(Lista<Lista<Integer>> adj) {
        Lista<Vertice> caminho = new Lista<>();
        Map<Integer, Integer> arestas = new HashMap<>();

        for (int i = 0; i < adj.length(); i++) {
            arestas.put(i, adj.find(i).length());
        }

        Lista<Integer> currentPath = new Lista<>();
        Lista<Vertice> currentCaminho = new Lista<>();
        currentPath.push(0);
        int currentVertexNumber = 0;

        while (!currentPath.isEmpty()) {
            if (adj.find(currentVertexNumber).length() > 0) {

                currentPath.push(currentVertexNumber);
                currentVertexNumber = adj.find(currentVertexNumber).pop();
            } else {
                Vertice vertice = getVertice(currentVertexNumber);

                if(!vertice.foiVisitado()) {
                    currentCaminho.add(vertice);
                    vertice.visitar();
                }

                currentVertexNumber = currentPath.pop();
            }
        }

        currentCaminho.add(getVertice(currentVertexNumber));

        for(int i = currentCaminho.length(); i > 0; i--) {
            caminho.add(currentCaminho.pop());
        }

        return caminho;
    }

    private Lista<Vertice> buscaEmProfundidadeUtil(int id, Lista<Vertice> visitados) {
        Vertice vertice = this.existeVertice(id);
        if (!vertice.foiVisitado()) {
            visitados.add(vertice);
        }

        Vertice[] listaAdjacencia = this.listaAdjacencia(vertice.getId());

        for (Vertice verticeAdjacente : listaAdjacencia) {
            if (verticeAdjacente != null) {
                if (!verticeAdjacente.foiVisitado()) {
                    verticeAdjacente.visitar();
                    buscaEmProfundidadeUtil(verticeAdjacente.getId(), visitados);
                }
            }
        }

        return visitados;
    }
    //#endregion
}
