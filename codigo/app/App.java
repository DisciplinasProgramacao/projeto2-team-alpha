package app;

import source.Grafo;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo("teste");
        grafo.addVertice(1);
        grafo.addVertice(2);
        grafo.addVertice(3);
        grafo.addAresta(1, 2, 1);
        grafo.addAresta(3, 2, 3);

        System.out.println("Tamanho do grafo: " + grafo.tamanho());
        System.out.println("O Grafo é completo? " + grafo.eCompleto());
        grafo.salvar("grafoTeste");
        grafo.carregar("grafoTeste");

        System.out.println("/nTeste do gráfico completo:");
        Grafo completo = new Grafo("completo", 30);
        System.out.println("Tamanho do grafo: " + completo.tamanho());
        System.out.println("O Grafo é completo? " + completo.eCompleto());
        completo.salvar("GrafoCompleto");

        Grafo semNome = new Grafo("Completo");

        semNome.carregar("GrafoCompleto");
        System.out.println("Tamanho do grafo: " + semNome.tamanho());
        System.out.println("O Grafo é completo? " + semNome.eCompleto());
    }
}