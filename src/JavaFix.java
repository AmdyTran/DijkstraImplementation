import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class JavaFix {
    public static void main(String args[]) throws FileNotFoundException {
        File output = new File("smallGFixed.txt");
        File input = new File("smallG.txt");
        Scanner s = new Scanner(input);
        PrintStream out = new PrintStream(output);
        for(int i = 0; i < 19801; i++){
            Scanner k = new Scanner(s.nextLine());
            if(i!= 0){
                int p = k.nextInt() -1;
                int j = k.nextInt() -1;
                out.println(p + " " + j + " " + k.nextInt());
            }
            
        }
    }
}
