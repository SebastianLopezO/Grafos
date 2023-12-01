package View;

import java.util.Scanner;
import Logic.Grafo;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Ver, Lad, Opc, OpcRoute;
        
        Boolean App=true, Route;

        System.out.println("Ingrese los vertices separadas por comas: ");
        Ver = sc.next();
        System.out.println("Ingrese los lados y su distancia separadas por comas: ");
        Lad = sc.next();

        String[] V = Ver.split(",");
        String[] L = Lad.split(",");

        while (L.length % 3 != 0) {
            System.out.println("Hubo un problema con el ingreso de los lados.: ");
            System.out.println("Ingrese de nuevo los lados y su distancia: ");
            Lad = sc.next();
            L = Lad.split(",");
        }

        Grafo G = new Grafo(V.length);
        G.CrearMatrizAdy(V, L);
        G.CrearListaAdy(G.getM(), V);

        while(App) {
            Opc = Menu();

            switch (Opc) {
                case "Mostrar Matriz Adyacente":
                    System.out.println("Matriz Adyacencia");
                    G.MostrarMatrizAdy();
                    break;

                case "Mostrar Lista Adyacente":
                    System.out.println("Lista de Adyacencia");
                    G.MostrarListaAdy(V);
                    break;

                case "Mostrar Grafo":
                    G.Mostrar_Grafo(V);
                    break;
                    
                case "Matriz de Incidencia":
                    System.out.println("Matriz Incidencia");
                    G.CrearMatrizInc(L, V);
                    break;

                case "Distancia Minima":
                    int Min = 0,
                            inicio = 0,
                            fin = 0;
                    boolean ban = true;
                    for (int i = 2; i < L.length; i += 3) {
                        Min += Integer.parseInt(L[i]);
                    }
                    String in,
                            fi;
                    System.out.println("Ingresa el vertice inicial");
                    in = sc.next();
                    System.out.println("Ingresa el vertice final");
                    fi = sc.next();
                    for (int i = 0; i < V.length; i++) {
                        if (in.equalsIgnoreCase(V[i])) {
                            inicio = i;
                        }
                        if (fi.equalsIgnoreCase(V[i])) {
                            fin = i;
                        }
                    }
                    G.MinDistance(Min, inicio, fin);
                    break;

                case "Recorridos":
                    Route=true;
                    while(Route) {
                        System.out.println("");
                        System.out.println("MENU RECORRIDOS");
                        System.out.println("1. DFS.\n"
                                + "2. BFS.\n"
                                + "3. Volver.");
                        OpcRoute = MenuRoute();

                        switch (OpcRoute) {
                            case "DFS":
                                System.out.println("Ingrese el dato desde el cual desea hacer el recorrido: ");
                                String SD = sc.next();

                                int[] Visitado = new int[V.length];
                                G.DFS(SD, Visitado, V);
                                break;

                            case "BFS":
                                System.out.println("Ingrese el dato desde el cual desea hacer el recorrido: ");
                                String SB = sc.next();

                                String[] Cola = new String[V.length];
                                int[] Visitado2 = new int[V.length];
                                G.BFS(SB, Visitado2, V, Cola);
                                break;

                            case "Volver":
                                Route=false;
                                break;

                            default:
                                System.out.println("Opcion incorrecta.");
                        }
                    }
                    break;

                case "Salir":
                    App=false;
                    break;
            }
        }
    }

    public static String Menu() {
        String[] Options = {
                "Mostrar Matriz Adyacente",
                "Mostrar Lista Adyacente",
                "Mostrar Grafo",
                "Matriz de Incidencia",
                "Distancia Minima",
                "Recorridos",
                "Salir"
        };

        String Option = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una Opcion: ",
                "Menu de Grafos",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
        return Option;
    }

    public static String MenuRoute(){
        String[] Options = {
                "DFS",
                "BFS",
                "Volver"
        };

        String Option = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una Opcion: ",
                "Menu de Recorrido de grafo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
        return Option;
    }

}