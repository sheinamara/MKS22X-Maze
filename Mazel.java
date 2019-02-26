import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mazel{
  public static void main(String args[]){
    try{
      File text = new File("Mazel.txt");
      Scanner input = new Scanner(text);
      String line = input.nextLine();
      while (input.hasNextLine()){
        line += "\n" + input.nextLine();
      }
      System.out.println(line);
    }
    catch (FileNotFoundException e){
      System.out.println("Wrong file!");
    }
  }
}
