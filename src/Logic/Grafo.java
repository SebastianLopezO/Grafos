package Logic;
import Bean.Nodo;
import Utility.ControlGraphviz;

import java.util.ArrayList;

public class Grafo {

    public Nodo[] Vect;
    public int[][] M;

    public Grafo(Nodo[] Vect, int[][] M) {
        this.Vect = Vect;
        this.M = M;
    }

    public Grafo() {
        Vect = null;
        M = null;
    }

    public Grafo(int Tam) {
        Vect = new Nodo[Tam];
    }

    public Nodo[] getVect() {
        return Vect;
    }

    public void setVect(Nodo[] Vect) {
        this.Vect = Vect;
    }

    public int[][] getM() {
        return M;
    }

    public void setM(int[][] M) {
        this.M = M;
    }

    public void MatAdj(String[] V, String[] L) {
        int[][] M = new int[V.length][V.length];

        for (int i = 0; i < L.length; i += 3) {
            int F = 0, C = 0, j = 0;

            while (j < V.length) {
                if (V[j].equals(L[i])) {
                    F = j;
                }
                j++;
            }
            j = 0;
            while (j < V.length) {
                if (V[j].equals(L[i + 1])) {
                    C = j;
                }
                j++;
            }

            M[F][C] = Integer.parseInt(L[i + 2]);
            M[C][F] = Integer.parseInt(L[i + 2]);
        }

        setM(M);
    }

    public void MostrarMatrizAdy() {
        for (int j = 0; j < M.length; j++) {
            for (int i = 0; i < M[0].length; i++) {
                System.out.print(M[j][i]);
            }
            System.out.println();
        }
    }

    public void ListAdj(int[][] M, String[] V) {
        Nodo P = null;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] != 0) {
                    Nodo X = new Nodo();
                    X.setDato(V[j]);
                    if (Vect[i] != null) {
                        P = Vect[i];
                        while (P.getLiga() != null) {
                            P = P.getLiga();
                        }
                        P.setLiga(X);
                    } else {
                        Vect[i] = X;
                    }
                }
            }
        }
    }

    public void MostrarListaAdy(String[] V) {
        Nodo P = null;

        for (int i = 0; i < Vect.length; i++) {
            P = Vect[i];
            int j = 0;
            String S = "";
            while (j < V.length) {
                if (j == i) {
                    S = V[j];
                }
                j++;
            }
            System.out.print("[" + S + "]" + "-->");
            while (P != null) {
                System.out.print(P.getDato() + "-->");
                P = P.getLiga();
            }
            System.out.print("/");
            System.out.println();
        }
    }

    public void MatInc(String[] L, String[] V){
        int[][] MI = new int[V.length][L.length/3];
        int NumDato1 = 0, NumDato2  = 0;

        for (int i = 0; i < L.length; i+=3) {
            int j=0;
            while (j < V.length){
                if (V[j].equals(L[i])){
                    NumDato1 = j;
                }
                j++;
            }
            j = 0;
            while (j < V.length){
                if (V[j].equals(L[i+1])){
                    NumDato2 = j;
                }
                j++;
            }

            if (NumDato1 == NumDato2){
                MI[i][i] = 3;
            }else{
                MI[NumDato1][i/3] = 1;
                MI[NumDato2][i/3] = 2;
            }
        }
        MostrarMatrizInc(MI);
    }

    public void MostrarMatrizInc(int[][] MI){
        for (int j = 0; j < MI.length; j++) {
            for (int i = 0; i < MI[0].length; i++) {
                System.out.print(MI[j][i]);
            }
            System.out.println();
        }
    }

    public void MinDistance(int min, int inicio, int fin){
        int[][] dm = new int[M.length][M.length];
        ArrayList<Integer> vis = new ArrayList<>();
        int i =0, k =0, d,d1;
        while(k < dm.length){
            for (int j = 0; j < M.length; j++) {
                if(M[i][j] != 0 && !content(j, vis))
                    dm[j][k] = M[i][j] + dm[i][k];
            }
            vis.add(i);
            d = min;
            for (int j = 0; j < M.length; j++) {
                if(d > dm[j][k] && dm[j][k] != 0 && j != vis.get(k)){
                    d = dm[j][k];
                    i = j;
                }
            }
            if(k == 0){
                dm[i][k+1] = d;
            }else{
                for (int j = 0; j < k; j++) {
                    if(d > dm[i][j] && dm[i][j] != 0 )
                        d = dm[i][j];
                }
                if (k < M.length-1)
                    dm[i][k+1] = d;
            }
            k++;
        }
        if(inicio == 0){
            d = 0;
            d1 = min;
        }else{
            d = min;
            d1 = min;
        }
        for (int j = 0; j < M.length; j++) {
            if(d > dm[inicio][j] && dm[inicio][j] != 0)
                d = dm[inicio][j];
            if(d1 > dm[fin][j] && dm[fin][j] != 0)
                d1 = dm[fin][j];
        }
        System.out.println("Distancia minima:" + (d1 - d));
    }

    private boolean content(int j, ArrayList<Integer> vis){
        boolean ban = false;
        for (Integer vi : vis) {
            if (vi.intValue() == j) {
                ban = true;
                break;
            }
        }
        return ban;
    }

    public void DFS(String S, int[] Visitado, String[] Ve) {
        Nodo P;
        int V = 0, j = 0;
        String W;
        while (j < Ve.length) {
            if (Ve[j].equals(S)) {
                V = j;
            }
            j++;
        }

        Visitado[V] = 1;
        System.out.print(S + " ");
        P = Vect[V];

        while (P != null) {
            W = P.getDato();
            j = 0;
            while (j < Ve.length) {
                if (Ve[j].equals(W)) {
                    V = j;
                }
                j++;
            }
            if (Visitado[V] == 0) {
                DFS(W, Visitado, Ve);
            }
            P = P.getLiga();
        }
        System.out.println();
    }

    public void BFS(String S, int[] Visitado, String[] Ve, String[] Cola) {
        int Primero = -1, Ultimo = -1, j = 0, V = 0;
        String W;
        Nodo P;

        StringBuilder ConfigGraphviz = new StringBuilder("digraph G {\n");

        while (j < Ve.length) {
            if (Ve[j].equals(S)) {
                V = j;
            }
            j++;
        }
        Visitado[V] = 1;
        Ultimo++;
        Cola[Ultimo] = S;

        while (Primero != Ultimo) {
            Primero++;
            j = 0;
            while (j < Ve.length) {
                if (Ve[j].equals(Cola[Primero])) {
                    V = j;
                }
                j++;
            }
            System.out.print(Cola[Primero] + " ");
            P = Vect[V];

            while (P != null) {
                W = P.getDato();
                j = 0;
                while (j < Ve.length) {
                    if (Ve[j].equals(W)) {
                        V = j;
                    }
                    j++;
                }
                if (Visitado[V] == 0) {
                    ConfigGraphviz.append(String.format("  %s -> %s [label=\"%d\", color=\"blue\"];\n", Cola[Primero], W, M[V][Primero]));

                    Visitado[V] = 1;
                    Ultimo++;
                    Cola[Ultimo] = W;
                }
                P = P.getLiga();
            }
        }
        System.out.println();
        ConfigGraphviz.append("}\n");
        ControlGraphviz.Generate(ConfigGraphviz.toString());
    }

    public void ShowGraph(String[] V) {
        int[][] MA = M;
        StringBuilder ConfigGraphviz = new StringBuilder();

        for (int i = 0; i < MA.length; i++) {
            int j = i + 1;
            while (j < MA[0].length) {
                if (MA[i][j] != 0) {
                    ConfigGraphviz.append(String.format("%s--%s [label=\"%d\"];\n", V[i], V[j], MA[i][j]));
                }
                j++;
            }
        }

        ControlGraphviz.Generate(ConfigGraphviz.toString());

    }
}