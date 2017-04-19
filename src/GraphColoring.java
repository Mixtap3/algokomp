import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
/**
 * Created by Leonard on 2017-04-11.
 */
public class GraphColoring {

    Scanner sc;
    int v;
    int e;
    int col;
    StringBuilder sb;
    boolean[] isInScene;
    int[] transList;
    ArrayList<int[]> sceneList;

    private void edges(){
        // Check isolated vertices and store edges
        sceneList = new ArrayList<>();
        while (sc.hasNext()){
            int e1 = sc.nextInt();
            int e2 = sc.nextInt();
            isInScene[e1-1] = true;
            isInScene[e2-1] = true;
            int[] pair = {e1, e2};
            sceneList.add(pair);
        }
        setNewV();
    }

    private void checkIsolated(){
        isInScene = new boolean[v];
        for (int i = 0; i < v; i++) {
            isInScene[i] = false;
        }
        edges();
    }

    private void setNewV(){
        // Set new v to non-isolated vertices
        int count = 0;
        for (boolean b : isInScene){
            if (!b){
                count++;
            }
        }
        v = v - count;
    }

    private void makeTrans(){
        transList = new int[isInScene.length];
        int count = 0;
        for (int i = 0; i < isInScene.length; i++) {
            if (isInScene[i]){
                count++;
                transList[i] = count;
            }
        }
    }

    GraphColoring(){
        // init variables and arrays
        sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();
        col = sc.nextInt() + 1;
//        edgeList = new ArrayList<>();

        checkIsolated();
        makeTrans();
        // Cast actors for roles
        sb = new StringBuilder();
        String[] rollList = new String[v];
        for (int i = 0; i < v; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < col-1; j++) {
                sb.append((j+1) +" ");
            }
            sb.append(col);
            rollList[i] = col + " "+ sb.toString();
        }

        // print first three integers
        System.out.println(v+"\n"+e+"\n"+col);


        // print casted actors in roles
        for (String s : rollList){
            System.out.println(s);
        }

        // print scenes
        for (int[] s : sceneList){
//            System.out.println(transList[s[0]-1] + ""+ transList[s[1]-1]);
            String row = 2 + " " + transList[s[0] - 1] + " "+ transList[s[1] - 1];
            System.out.println(row);
        }
    }


    public static void main(String[] args) throws IOException{
//        File file = new File("test2.txt");
//        System.setIn(new FileInputStream(file));

        GraphColoring gc = new GraphColoring();
    }

}
