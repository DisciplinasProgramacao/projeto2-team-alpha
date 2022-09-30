package source.grafo;

import source.ABB;
import source.Lista;

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
        Aresta[] aresta = new Aresta[this.ordem()];
        aresta = this.getArestas().allElements(aresta);
        return this.ordem() + (aresta.length  / 2);
    }

    public int ordem() {
        return this.vertices.size();
    }
    //#endregion

    //#region Métodos booleanos

    public boolean completo() {
        return false;
    }

    public boolean euleriano() {
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

    public Vertice existeVertice(int idVertice) {
        return this.vertices.find(idVertice);
    }

    public Vertice[] getAllVertices() {
        Vertice[] verticesArray = new Vertice[this.ordem()];
        verticesArray = vertices.allElements(verticesArray);

        return verticesArray;
    }

    public Vertice[] listaAdjacencia(Vertice vertice) {
        return vertice.getListaAdjacencia();
    }

    public Vertice[] listaAdjacencia(int id) {
        return vertices.find(id).getListaAdjacencia();
    }


    public Vertice[] buscaEmProfundidade(int id, Vertice[] visitados) {
        Vertice[] listaAdjacencia = this.listaAdjacencia(id);

        int i = 1;
        for (Vertice verticeAdjacente : listaAdjacencia) {
            if (verticeAdjacente != null) {
                if (!verticeAdjacente.foiVisitado()) {
                    verticeAdjacente.visitar();
                    visitados[i++] = verticeAdjacente;
                    buscaEmProfundidade(verticeAdjacente.getId(), visitados);
                }
            }
        }
        visitados[0] = getVertice(id);
        return visitados;
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

    public boolean removerAresta(int origem, int destino) {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if (saida != null && chegada != null) {
            saida.removerAresta(destino);
            chegada.removerAresta(origem);
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

    public static int DFS(Lista<Vertice> visited, Vertice from) {
        int count = 1;
        visited.add(from);
        Vertice[] adjacentes = from.getListaAdjacencia();
        for (Vertice to : adjacentes) {
            if (visited.contains(to)) {
                count += DFS(visited, to);
            }
        }
        return count;
    }

    public boolean ePonte(Vertice from, Vertice to) throws CloneNotSupportedException {
        if (from.getListaAdjacencia().length == 1) {
            return false;
        }
        Grafo grafoAux = (Grafo) this.clone();

        int ponteCount = DFS(new Lista<Vertice>(), to);
        grafoAux.removerAresta(from.getId(), to.getId());
        int nonBridgeCount = DFS(new Lista<Vertice>(), to);
        grafoAux.addAresta(from.getId(), to.getId(), 0);

        return nonBridgeCount < ponteCount;
    }

    //#endregion
    public Vertice[] encontrarCaminho(int verticeInicial, int verticeDestino, Vertice[] visitados) {
        Vertice[] listaAdjacencia = listaDeAdjacencia(verticeInicial);
        Vertice inicial = this.existeVertice(verticeInicial);
        Vertice destino = this.existeVertice(verticeDestino);
        int indice = 0;

        if (inicial.existeAresta(verticeDestino)) {
            visitados[indice++] = inicial;
        } else {
            for (int j = 0; j < listaAdjacencia.length; j++) {
                if (listaAdjacencia[j] != null) {
                    if (listaAdjacencia[j].existeAresta(destino.getId())) {
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

    public abstract Grafo subGrafo(Lista<Vertice> vertices) throws Exception;
    // #endregion

    //#region Métodos Aux

    private void caminhoEuleriano(Grafo grafo, Lista<Vertice> caminho, Vertice from) throws CloneNotSupportedException {
        Vertice[] listaAdjacencia = from.getListaAdjacencia();
        for (Vertice to : listaAdjacencia) {
            if (!ePonte(from, to)) {
                caminho.add(to);
                grafo.removerAresta(from.getId(), to.getId());
                caminhoEuleriano(grafo, caminho, to);
                break;
            }
        }
    }

    public Lista<Vertice> caminhoEuleriano() throws Exception {
        Grafo grafoAux = (Grafo) this.clone();
        Lista<Vertice> caminho = new Lista<>();

        if(!this.euleriano()){
            caminho.add(getVerticeComGrauImpar());
            caminhoEuleriano(grafoAux, caminho, caminho.getElement(0));
        }
        // if (oddCount == 0) {
        //     caminho.add(verticeInicial);
        //     caminhoEuleriano(grafoAux, caminho, caminho.getElement(0));
        // // } else if (oddCount == 2) {
        // //     caminho.add(getVerticeComGrauImpar());
        // //     caminhoEuleriano(grafoAux, caminho, caminho.getElement(0));
        // // } 
        // }else {
        //     throw new Exception("Euler properties infringed.");
        // }

        return caminho;
    }

    private int quantidadeVerticesComGrauImpar() {
        int quant = 0;
        Vertice[] listaVertices = this.getAllVertices();

        for(Vertice vertice : listaVertices) {
            if((vertice.getGrau() % 2) != 0) {
                quant++;
            }
        }

        return quant;
    }

    private Vertice getVerticeComGrauImpar() {
        Vertice[] listaVertices = this.getAllVertices();

        for(Vertice vertice : listaVertices) {
            if((vertice.getGrau() % 2) != 0) {
                return vertice;
            }
        }

        return null;
    }

    public Vertice getVertice(int id) {
        return vertices.find(id);
    }

    public Lista<Aresta> getArestas() {
        Lista<Aresta> arestas = new Lista<>();
        Vertice[] verticesArray = getAllVertices();

        for (Vertice vertice : verticesArray) {
            for(Aresta aresta : vertice.getAllArestas()){
                arestas.add(aresta);
            }
        }

        return arestas;
    }
    // #endregion
}
