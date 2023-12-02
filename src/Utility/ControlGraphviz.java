package Utility;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControlGraphviz {
    ControlGraphviz(){}

    public static void Generate(String st){
        CrearArchivo(st);
        CrearIMG();
        AbrirIMG();
    }

    public static void AbrirIMG() {
        String root = System.getProperty("user.dir");
        String img = root + "\\src\\View\\Graph.jpg";
        System.out.println("Abriendo Imagen.... (5seg)");

        File imageFile = new File(img);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (imageFile.exists()) {
            try {
                Desktop.getDesktop().open(imageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("La imagen no existe en la ruta especificada.");
        }
    }

    public static void CrearArchivo(String Statemant) {
        try {
            String root = System.getProperty("user.dir");
            FileWriter a = new FileWriter(root+"\\src\\Utility\\ConfigGraphviz.txt");
            BufferedWriter escribir = new BufferedWriter(a);
            escribir.write(Statemant);
            escribir.close();
        } catch (Exception ex) {
            System.out.println("No se ha podido generar la imagen: "+ex.getMessage());
        }
    }

    public static void CrearIMG() {
        try {
            String root = System.getProperty("user.dir");
            String dotPath = root+"\\libraries\\Graphviz\\dot.exe";
            String fileInputPath = root+"\\src\\Utility\\ConfigGraphviz.txt";
            String fileOutputPath = root+"\\src\\View\\Graph.jpg";

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);
        } catch (IOException e) {
            System.out.println("No se ha podido generar la imagen: "+e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("No se ha podido generar la imagen: "+e.getMessage());
        } catch (SecurityException e) {
            System.out.println("No se ha podido generar la imagen: "+e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No se ha podido generar la imagen: "+e.getMessage());
        } catch (UnsupportedOperationException e) {
            System.out.println("No se ha podido generar la imagen: "+e.getMessage());
        } catch (Exception e) {
            System.out.println("No se ha podido generar la imagen: "+e.getMessage());
        }
    }
}
