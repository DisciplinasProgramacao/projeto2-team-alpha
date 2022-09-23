package source;

import java.io.IOException;

public abstract class GrafoMutavel extends Grafo{
    //#region Contrutor

    public GrafoMutavel(String nome) {
        super(nome);
    }
    //#endregion

    //#region boolean Aresta
    
    public abstract boolean addAresta(int origem, int destino);

    public boolean delAresta(int origem, int destino) {
        return false;
    }
    //#endregion

    //#region boolean Vertice

    public boolean addVertice(Vertice novo) {
        return false;
    }

    public boolean delVertice(int idVertice) {
        return false;
    }
    //#endregion

    //#region Manipulação de Arquivo

    public void carregar(String nomeArquivo) throws IOException {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "read");
       
        while (arq.ready()) {
            String line = arq.readLine();
            line = line.replaceAll("\\n", "");
            String vertices[] = line.split(";");

            int verticeOrigem = Integer.parseInt(vertices[0]);
            this.addVertice(verticeOrigem);

            for (String vertice : vertices) {
                int verticeInt = Integer.parseInt(vertice);

                if (verticeInt != verticeOrigem){
                    this.addAresta(verticeOrigem, verticeInt);
                }
            }
        }
  
        arq.close();
    }

    public void salvar(String nomeArquivo) throws Exception {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "save");

        for (Vertice vertice : getAllVertices()) {
            arq.write(vertice.getId());

            for (Aresta aresta : vertice.getAllArestas()) {
                arq.write(";" + aresta.getDestino());
            }

            arq.write("\n");
        }
    
        arq.close();
    }
    //#endregion
}
