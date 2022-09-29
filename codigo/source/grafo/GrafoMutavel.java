package source.grafo;

import java.io.IOException;

public abstract class GrafoMutavel extends Grafo {
    // #region Contrutor

    public GrafoMutavel(String nome) {
        super(nome);
    }
    // #endregion

    // #region boolean Aresta

    public abstract boolean addAresta(int origem, int destino);

    public boolean delAresta(int origem, int destino) {
        return false;
    }
    // #endregion

    // #region Manipulação de Arquivo

    public abstract void carregar(String nomeArquivo) throws IOException;

    public abstract void salvar(String nomeArquivo) throws Exception;
    // #endregion
}
