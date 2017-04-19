import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
/**
 * Created by Leonard Halling & Filip Hildebrandt on 2017-04-11.
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
        v++;
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

    private void baseCase(){
        System.out.println(3);
        System.out.println(2);
        System.out.println(3);
        System.out.println(3 + " " + 1 + " " + 2 + " " + 3);
        System.out.println(3 + " " + 1 + " " + 2 + " " + 3);
        System.out.println(3 + " " + 1 + " " + 2 + " " + 3);
        System.out.println(2 + " " + 1 + " " + 3 );
        System.out.println(2 + " " + 2 + " " + 3 );
    }

    GraphColoring(){
        // init variables and arrays
        sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();
        col = sc.nextInt() + 1;

        /*if (e == (v*(v-1)/2)){

            System.out.println(3);
            System.out.println(2);
            System.out.println(3);
            System.out.println(3 + " " + 1 + " " + 2 + " " + 3);
            System.out.println(3 + " " + 1 + " " + 2 + " " + 3);
            System.out.println(3 + " " + 1 + " " + 2 + " " + 3);
            System.out.println(2 + " " + 1 + " " + 3 );
            System.out.println(2 + " " + 2 + " " + 3 );
            return;
        }*/

        checkIsolated();
        makeTrans();
        if (col > v){
            col = v;
        }
        // Cast actors for roles
        /*if (v < 3){
            v = 3;
        }*/
        if (e == 0){
            baseCase();
            return;
        }



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

        System.out.println(v+"\n"+(e+1)+"\n"+col);


        // print casted actors in roles
        for (String s : rollList){
            System.out.println(s);
        }

        // print scenes
        int test = 1;
        for (int[] s : sceneList){
            String row = 2 + " " + transList[s[0] - 1] + " "+ transList[s[1] - 1];
            System.out.println(row);
            test = transList[s[1] - 1];
        }
        System.out.println(2 + " " + test + " "+ v);
    }


    public static void main(String[] args) throws IOException{
//        File file = new File("test2.txt");
//        System.setIn(new FileInputStream(file));

        GraphColoring gc = new GraphColoring();
    }
}
