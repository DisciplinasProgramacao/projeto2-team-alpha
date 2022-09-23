package app;

import source.grafo.*;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Grafo não ponderado:");

        GrafoNaoPonderado grafoNaoPonderado = new GrafoNaoPonderado("grafoNaoPonderado");
        for(int i = 0; i <= 40; i++) {
            grafoNaoPonderado.addVertice(i);
        }

        int dec = 40;
        Random aleatorio = new Random();

        for (int i = 1; i <= 40; i++) {
            int aleatorioInt = aleatorio.nextInt(((i - 1) - 0) + 1) + 0;
            int aleatorioInt2 = aleatorio.nextInt(((i - 1) - 0) + 1) + 0;
            grafoNaoPonderado.addAresta(i, dec);
            grafoNaoPonderado.addAresta(i, aleatorioInt);
            grafoNaoPonderado.addAresta(aleatorioInt2, dec--);
        }

        System.out.println("Ordem do grafo não ponderado: " + grafoNaoPonderado.ordem());
        System.out.println("Tamanho do grafo não ponderado: " + grafoNaoPonderado.tamanho());

        grafoNaoPonderado.salvar();


        GrafoNaoPonderado grafoNaoPonderado2 = new GrafoNaoPonderado("grafoNaoPonderado2");
        grafoNaoPonderado2.carregar("grafoNaoPonderado");

        System.out.println("Ordem do grafo não ponderado 2: " + grafoNaoPonderado2.ordem());
        System.out.println("Tamanho do grafo não ponderado 2: " + grafoNaoPonderado2.tamanho());



        System.out.println("\n====================================================================\n");



        System.out.println("Grafo ponderado:");

        GrafoPonderado grafoPonderado = new GrafoPonderado("grafoPonderado");
        for(int i = 0; i <= 40; i++) {
            grafoPonderado.addVertice(i);
        }

        dec = 40;
        for (int i = 1; i <= 40; i++) {
            int aleatorioInt = aleatorio.nextInt(((i - 1) - 0) + 1) + 0;
            int aleatorioInt2 = aleatorio.nextInt(((i - 1) - 0) + 1) + 0;
            grafoPonderado.addAresta(i, dec, aleatorioInt + aleatorioInt2);
            grafoPonderado.addAresta(i, aleatorioInt, aleatorioInt2);
            grafoPonderado.addAresta(aleatorioInt2, dec--, aleatorioInt);
        }

        System.out.println("Ordem do grafo ponderado: " + grafoPonderado.ordem());
        System.out.println("Tamanho do grafo ponderado: " + grafoPonderado.tamanho());
        grafoPonderado.salvar();


        GrafoPonderado grafoPonderado2 = new GrafoPonderado("grafoPonderado2");
        grafoPonderado2.carregar("grafoPonderado");

        System.out.println();
        System.out.println("Ordem do grafo ponderado 2: " + grafoPonderado2.ordem());
        System.out.println("Tamanho do grafo ponderado 2: " + grafoPonderado2.tamanho());

        // System.out.println("\nTeste do gráfico completo:");
        // GrafoCompleto completo = new GrafoCompleto("completo", 30);
        // System.out.println("Tamanho do grafo: " + completo.tamanho());
        // System.out.println("O Grafo é completo? " + completo.eCompleto());
    }
}