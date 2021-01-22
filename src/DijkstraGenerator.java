import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DijkstraGenerator {
    public static void main(String args[]) throws FileNotFoundException {
        int V = 20000;
        int E = 100000; 
        int weightMax = 1000;
        PrintStream out = new PrintStream(new File("dijkstraGenerated.txt"));
        //We ensure every vertex has at least out degree > 1
        out.println(V + " " + E);
        for(int i = 0; i < V; i++){
           out.println(i + " " + (int) (Math.random()*V) + " " + (int) (Math.random()*weightMax)+1);
        }

        for(int i = 0; i < E-V; i++){
            out.println((int) (Math.random()*V) + " " + (int) (Math.random()*V) + " " + (int) (Math.random()*weightMax)+1);
        }
    }
}
