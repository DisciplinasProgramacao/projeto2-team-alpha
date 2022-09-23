package source.grafo;

import source.Lista;

public class GrafoNaoPonderado extends GrafoMutavel {
    //#region Contrutor

    public GrafoNaoPonderado(String nome) {
        super(nome);
    }
    //#endregion

    //#region boolean addAresta

    @Override
    public boolean addAresta(int origem, int destino) {
        return false;
    }
    //#endregion

    //#region subgrafo

    @Override
    public GrafoNaoPonderado subGrafo(Lista<Vertice> vertices) {
        return null;
    }
    //#endregion
}
