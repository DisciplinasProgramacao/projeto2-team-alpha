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

            for (int j = 0; j < (i-1); j++) {
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
    public boolean completo() {
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

    @Override
    public boolean euleriano() {
        return false;
    }
    //#endregion

    //#region Métodos Aux

    /**
     * Método que cria um subgrafo completo a partir de uma lista de vertices parametrizada
     * @param listaVertice Lista de vétices  do subgrafo
     * @throws Exception
     */
    @Override
    public GrafoCompleto subGrafo(Lista<Vertice> listaVertice) throws Exception {
        
        Vertice[] arrayVertices= new Vertice[this.vertices.size()];
        arrayVertices = listaVertice.allElements(arrayVertices);
            GrafoCompleto novoSubGrafo = new GrafoCompleto("subGrafo",0);
            for(int i=0; i<arrayVertices.length && arrayVertices[i]!=null;i++){
                novoSubGrafo.addVertice(arrayVertices[i].getId());
                for(int j=0; j<novoSubGrafo.ordem()-1;j++){
                    if(this.existeAresta(arrayVertices[i].getId(), arrayVertices[j].getId())!=null){
                        novoSubGrafo.addAresta(arrayVertices[i].getId(), arrayVertices[j].getId(), 0);
                    }
                }
            }
            if(novoSubGrafo.completo()){
                return novoSubGrafo;
            }else{
                throw new Exception("Esses vertices não formam um grafo completo");
            }
    }
    //#endregion
}
