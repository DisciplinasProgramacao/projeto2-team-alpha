public class App {

      
    public static void main(String[] args) throws Exception {
       Grafo grafo = new Grafo("teste");
       grafo.addVertice(1);
       grafo.addVertice(2);
       grafo.addVertice(3);
       grafo.addAresta(1, 2);
       System.out.println("Tamanho do grafo: "+ grafo.tamanho());
    }
}
