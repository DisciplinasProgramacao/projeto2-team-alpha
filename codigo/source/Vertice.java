package source;

public class Vertice {
    private ABB<Aresta> arestas;    
    private final int id;
    private boolean visitado;

    /**
     * Construtor para criação de vértice identificado
     * @param id Número/id do vértice a ser criado (atributo final).
    */
    public Vertice(int id){
        this.id = id;
        this.arestas = new ABB<Aresta>();
        this.visitado = false;
    }
    
    /**
     * Adiciona uma aresta neste vértice para um destino
     * @param peso Peso da aresta (1 para grafos não ponderados)
     * @param destino Vértice de destino
    */
    public boolean addAresta(int destino, int peso){
        return this.arestas.add(destino, new Aresta(peso, destino));
    }

    /**
     * Verifica se já existe aresta entre este vértice e um destino. Método privado
     * @param dest Vértice de destino
     * @return TRUE se existe aresta, FALSE se não
    */
    public boolean existeAresta(int destino){
        return (this.arestas.find(destino) != null);
    }

    public Aresta arestaConectadaCom(int destino){
        if(existeAresta(destino)){
            return this.arestas.find(destino);
        }

        return null;
    }

    public Aresta[] getAllArestas() {
        Aresta[] arestaArray = new Aresta[this.getGrau()];
        arestaArray = arestas.allElements(arestaArray);

        return arestaArray;
    }
    
    /**
     * Retorna o grau do vértice
     * @return Grau (inteiro não negativo)
    */
    public int getGrau(){
        return this.arestas.size();
    }

    public void visitar(){
        this.visitado = true;
    }
    
    public void limparVisita(){
        this.visitado = false;
    }
    
    public boolean foiVisitado(){
        return this.visitado;
    }

    public int getId() {
        return this.id;
    }
}
