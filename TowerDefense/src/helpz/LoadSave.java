package helpz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {

        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  img;
    }

    //txt file
    public static void CreateFile() {

        File txtFile = new File("res/testFile.txt");

        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateLevel(String name, int[] idArray) {

        File newlevel = new File("res/" + name + ".txt");

        if(newlevel.exists()) {
            System.out.println("File " + name + " already exists");
            return;
        } else  {
            try {
                newlevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WriteToFile(newlevel, idArray);
        }
    }

    private static void WriteToFile(File f, int[] idArr) {

        try {
            PrintWriter pw = new PrintWriter(f);
            for (Integer i : idArr) {
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void SaveLevel(String name, int[][] idArr){
        File levelFile = new File("res/" + name + ".txt");

        if(levelFile.exists()) {
            WriteToFile(levelFile, Utilz.TwoDto1DintArr(idArr) );
            System.out.println("Level-file saved.");
        }else {
            System.out.println("File doesn't exist!");
            return;
        }
    }

    private static ArrayList<Integer> ReadFromFile(File file){
        ArrayList<Integer> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                list.add(Integer.parseInt(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int[][] GetLevelData(String name) {

        File lvlFile = new File("res/" + name + ".txt");

        if(lvlFile.exists()) {
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            return Utilz.ArrayListTo2Dint(list, 20, 20);
        } else {
            System.out.println("File: "+ name + " doesn't exist!");
            return null;
        }
    }

    //save 2d int array to file

    //load int array from file

    //Create a new lvl with default values

}
