package source;

public class GrafoPonderado extends GrafoMutavel {
    //#region Contrutor

    public GrafoPonderado(String nome) {
        super(nome);
    }
    //#endregion

    //#region boolean addAresta
    @Override
    public boolean addAresta(int origem, int destino) {
        return false;
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo.
     * Não verifica se os vértices pertencem ao grafo.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     */
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
    //#endregion

    //#region subgrafo

    @Override
    public GrafoPonderado subGrafo(Lista<Vertice> vertices) {
        return null;
    }
    //#endregion
}
