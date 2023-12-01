package View;

import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import Logic.Grafo;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Opc, OpcRoute;
        
        Boolean App=true, Route;

        String[] V = MenuCorners();
        String[] L = MenuEdges();

        while (L.length % 3 != 0) {
            System.out.println("Hubo un problema con el ingreso de los lados.: ");
            L = MenuEdges();
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
                    String[] ConfigMin = MenuMinDistance();
                    in= ConfigMin[0];
                    fi = ConfigMin[1];

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

        return (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una Opcion: ",
                "Menu de Grafos",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
    }

    public static String MenuRoute(){
        String[] Options = {
                "DFS",
                "BFS",
                "Volver"
        };

        return (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una Opcion: ",
                "Menu de Recorrido de grafo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
    }

    public static String[] MenuEdges(){
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JTextField corner1 = new JTextField();
        JTextField corner2 = new JTextField();
        JTextField distance = new JTextField();

        panel.add(new JLabel("Ingrese el primer vertice: "));
        panel.add(corner1);
        panel.add(new JLabel("Ingrese el segundo vertice: "));
        panel.add(corner2);
        panel.add(new JLabel("Ingrese la distancia: "));
        panel.add(distance);

        ArrayList<String> Edges = new ArrayList<>();

        while(true) {
            corner1.setText(""); corner2.setText(""); distance.setText("");
            int result = JOptionPane.showConfirmDialog(null, panel, "Ingreso de Aristas",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String Vconer1 = corner1.getText();
                String Vconer2 = corner2.getText();
                String Vdistance = distance.getText();

                if(!Vconer1.isEmpty() && !Vconer2.isEmpty() && !Vdistance.isEmpty()){
                    Edges.add(Vconer1); Edges.add(Vconer2); Edges.add(Vdistance);
                }else{
                    System.out.println("No puedes dejar valores vacios");
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Valores ingresados:\n" + Edges,
                        "Aristas Ingresada",
                        JOptionPane.QUESTION_MESSAGE
                );
                System.out.println("Aristas: "+ Edges);
                String[] res = new String[Edges.size()];
                Edges.toArray(res);
                return res;
            }
        }
    }

    public static String[] MenuCorners(){
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JTextField corner = new JTextField();

        panel.add(new JLabel("Ingrese el vertice: "));
        panel.add(corner);


        ArrayList<String> Corners = new ArrayList<>();

        while(true) {
            corner.setText("");
            int result = JOptionPane.showConfirmDialog(null, panel, "Ingreso de Vertices",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String Vcorner = corner.getText();
                if(!Vcorner.isEmpty()){
                    Corners.add(Vcorner);
                }else{
                    System.out.println("No puedes dejar valores vacios");
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Valores ingresados:\n" + "Vertices: " + Corners,
                        "Arista Ingresada",
                        JOptionPane.QUESTION_MESSAGE
                );
                System.out.println("Verices: "+ Corners);
                String[] res = new String[Corners.size()];
                Corners.toArray(res);
                return res;
            }
        }
    }

    public static String[] MenuMinDistance(){
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JTextField cornerStart = new JTextField();
        JTextField cornerEnd = new JTextField();

        panel.add(new JLabel("Ingresa el vertice inicial: "));
        panel.add(cornerStart);
        panel.add(new JLabel("Ingresa el vertice final: "));
        panel.add(cornerEnd);

        while(true) {
            cornerStart.setText(""); cornerEnd.setText("");
            int result = JOptionPane.showConfirmDialog(null, panel, "Configuracion Distancia Minima",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String VcornerStart = cornerStart.getText();
                String VcornerEnd= cornerEnd.getText();

                if(!VcornerStart.isEmpty() && !VcornerEnd.isEmpty()){
                    return new String[]{VcornerStart, VcornerEnd};

                }else{
                    System.out.println("No puedes dejar valores vacios");
                }
            } else {
                System.out.println("Debes ingresar valores validos");
            }
        }
    }
}