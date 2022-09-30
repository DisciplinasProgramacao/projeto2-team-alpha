package source.grafo;

import source.Lista;
import source.Arquivo;
import java.io.IOException;
public class GrafoPonderado extends GrafoMutavel {
    //#region Contrutor

    public GrafoPonderado(String nome) {
        super(nome);
    }

    //#endregion

    //#region boolean addAresta
    @Override
    public boolean addAresta(int origem, int destino) {
        return addAresta(origem, destino, 0);
    }
    //#endregion

    //#region subgrafo

    @Override
    public GrafoPonderado subGrafo(Lista<Vertice> vertices) {
        return null;
    }
    //#endregion

    //#region Manipular Arquivo

    @Override
    public void carregar(String nomeArquivo) throws IOException {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "read");
       
        while (arq.ready()) {
            String line = arq.readLine();
            line = line.replaceAll("\\n", "");
            String verticesPesos[] = line.split(";");
            int verticeOrigem = Integer.parseInt(verticesPesos[0]);

            for (String verticePeso : verticesPesos) {
                int vertice = Integer.parseInt(verticePeso.split("-")[0]);
                this.addVertice(vertice);
                
                if (vertice != verticeOrigem) {
                    int peso = Integer.parseInt(verticePeso.split("-")[1]);
                    this.addAresta(verticeOrigem, vertice, peso);
                }
            }
        }
  
        arq.close();
    }

    @Override
    public void salvar(String nomeArquivo) throws Exception {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "save");

        for (Vertice vertice : this.getAllVertices()) {
            arq.write(vertice.getId());

            for (Aresta aresta : vertice.getAllArestas()) {
                arq.write(";" + aresta.getDestino() + "-" + aresta.getPeso());
            }

            arq.write("\n");
        }
    
        arq.close();
    }

    public void salvar() throws Exception {
        salvar(this.nome);
    }
    //#endregion
}
