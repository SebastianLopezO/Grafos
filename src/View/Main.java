package View;

import java.util.Scanner;
import Logic.Grafo;
public class Main {

    public static void main(String[] args) {
        Scanner Leer = new Scanner(System.in);
        String Ver, Lad;
        int Opc;

        System.out.println("Ingrese los vertices separadas por comas: ");
        Ver = Leer.next();
        System.out.println("Ingrese los lados y su distancia separadas por comas: ");
        Lad = Leer.next();

        String[] V = Ver.split(",");
        String[] L = Lad.split(",");

        while (L.length % 3 != 0) {
            System.out.println("Hubo un problema con el ingreso de los lados.: ");
            System.out.println("Ingrese de nuevo los lados y su distancia: ");
            Lad = Leer.next();
            L = Lad.split(",");
        }

        Grafo G = new Grafo(V.length);
        G.CrearMatrizAdy(V, L);
        G.CrearListaAdy(G.getM(), V);

        do {
            System.out.println("");
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Mostrar Matriz Adyacente.\n"
                    + "2. Mostrar Lista Adyacente.\n"
                    + "3. Mostrar Grafo. \n"
                    + "4. Matriz de Incidencia. \n"
                    + "5. Distancia Minima. \n"
                    + "6. Recorridos. \n"
                    + "0. Salir.");
            Opc = Leer.nextInt();

            switch (Opc) {
                case 1:
                    System.out.println("Matriz Adyacencia");
                    G.MostrarMatrizAdy();
                    break;

                case 2:
                    System.out.println("Lista de Adyacencia");
                    G.MostrarListaAdy(V);
                    break;

                case 3:
                    G.Mostrar_Grafo(V);
                    break;

                case 4:
                    System.out.println("Matriz Incidencia");
                    G.CrearMatrizInc(L, V);
                    break;

                case 5:
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
                    in = Leer.next();
                    System.out.println("Ingresa el vertice final");
                    fi = Leer.next();
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

                case 6:
                    do {
                        System.out.println("");
                        System.out.println("MENU RECORRIDOS");
                        System.out.println("1. DFS.\n"
                                + "2. BFS.\n"
                                + "3. Volver.");
                        Opc = Leer.nextInt();

                        switch (Opc) {
                            case 1:
                                System.out.println("Ingrese el dato desde el cual desea hacer el recorrido: ");
                                String SD = Leer.next();

                                int[] Visitado = new int[V.length];
                                G.DFS(SD, Visitado, V);
                                break;

                            case 2:
                                System.out.println("Ingrese el dato desde el cual desea hacer el recorrido: ");
                                String SB = Leer.next();

                                String[] Cola = new String[V.length];
                                int[] Visitado2 = new int[V.length];
                                G.BFS(SB, Visitado2, V, Cola);
                                break;

                            case 3:
                                break;

                            default:
                                System.out.println("Opcion incorrecta.");
                        }
                    } while (Opc != 3);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (Opc != 0);
    }

}