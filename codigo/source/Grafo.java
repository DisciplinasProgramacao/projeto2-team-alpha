package source;

/**
 * Classe básica para um Grafo simples
 */
public abstract class Grafo {
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
    //#endregion

    //#region Métodos booleanos
    
    public boolean eCompleto() {
        return false;
    }

    public boolean eEuleriano() {
        return false;
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

    //#region Busca em Profundidade
    public void buscaEmProfundidade(){
        int tempo=0;
        Vertice[] verticesArray = getAllVertices();

        for (Vertice vertice : verticesArray) {
            vertice.setTempoDescoberta(0);
            vertice.setTempoTermino(0);
            vertice.setPai(null);
        }

        for(Vertice vertice : verticesArray){
            if(vertice.getTempoDescoberta()==0){
                buscaEmProfundidade(vertice,tempo);
            }
        }
    }

    public void buscaEmProfundidade(Vertice v, int tempo){
        tempo++;
        v.setTempoDescoberta(tempo);
        Vertice[] verticesArray = getAllVertices();
        Vertice[] listaAdjacencia = new Vertice[this.ordem()];
        int i=0;
        for(Vertice vertice : verticesArray){
            if(vertice.existeAresta(v.getId())){
                listaAdjacencia[i] = vertice;
                i++;
            }
        }

        for(Vertice vertice : listaAdjacencia){
            if(vertice.getTempoDescoberta() == 0){
                
            }
        }
    }
    //#endregion

    //#endregion

    //#region Métodos de Arestas

    public Aresta existeAresta(int verticeA, int verticeB) {
        Aresta aresta = vertices.find(verticeA).arestaConectadaCom(verticeB);
        if (aresta != null) {
            return aresta;
        }

        return null;
    }

    public abstract Grafo subGrafo(Lista<Vertice> vertices);
    //#endregion

    //#region Métodos Aux

    public Lista<Vertice> caminhoEuleriano() {
        return null;
    }
    //#endregion
}
