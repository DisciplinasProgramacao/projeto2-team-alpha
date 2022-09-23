package source.grafo;

import source.ABB;
import source.Lista;

public class GrafoCompleto extends Grafo {
    //#region Atributo

    private int ordem;
    //#endregion

    //#region Construtor

    public GrafoCompleto(String nome, int ordem) {
        super(nome);
        this.setOrdem(ordem);

        this.vertices = new ABB<>();
        Vertice[] arrayVertices = new Vertice[ordem];

        int i = 0;
        for (Vertice vertice : arrayVertices) {
            Vertice adicionado = this.addVertice(i++);
            vertice = (adicionado);

            for (int j = 0; j < i; j++) {
                this.addAresta(j, vertice.getId(), 0);
            }
        }
    }
    //#endregion

    //#region Getters

    public int getOrdem() {
        return this.ordem;
    }
    //#endregion

    //#region Setters

    public void setOrdem(int ordem) {
        if(ordem > 0)
            this.ordem = ordem;
    }
    //#endregion

    //#region Booleanos

    /**
     * Verifica se este é um grafo completo.
     * 
     * @return TRUE para grafo completo, FALSE caso contrário
     */
    @Override
    public boolean eCompleto() {
        Vertice[] vertices = getAllVertices();

        for (Vertice vertice : vertices) {
            if (!vertice.foiVisitado()) {
                for (Vertice destino : vertices) {
                    if (vertice != destino && !vertice.existeAresta(destino.getId()))
                        return false;
                }
                vertice.visitar();
            }
            vertice.limparVisita();
        }

        return true;
    }
    //#endregion

    //#region Métodos Aux

    /**
     * Método que cria um subgrafo completo a partir de uma lista de vertices parametrizada
     * @param vertices Lista de vétices  do subgrafo
     */
    @Override
    public GrafoCompleto subGrafo(Lista<Vertice> vertices) {
        Vertice[] verticesArray = new Vertice[this.getOrdem()];
        verticesArray = vertices.allElements(verticesArray);

        GrafoCompleto subgrafo = new GrafoCompleto("Subgrafo de " + this.nome, verticesArray.length);

        return subgrafo;
    }
    //#endregion
}
