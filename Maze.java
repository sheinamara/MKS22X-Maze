import java.util.*;
import java.io.*;
public class Maze{
  private char[][]maze;
  private boolean animate; //false by default
  private int row;
  private int col;
  private int[] moves;

  public Maze(String filename) throws FileNotFoundException, IllegalStateException{
    animate = false;
    int eCount = 0;
    int sCount = 0;

    try{
      File read = new File(filename);
      Scanner toRow = new Scanner(read);
      row = 0;
      col = 0;
      while (toRow.hasNextLine()){
        row++;
        toRow.nextLine();
      }

      Scanner toCol = new Scanner(read);
      String s = toCol.nextLine();
      for (int i = 0; i < s.length(); i++){
        col++;
      }

      Scanner toCheck = new Scanner(read);
      maze = new char[row][col];
      for (int a = 0; toCheck.hasNextLine(); a++){
        String line = toCheck.nextLine();
        for (int b = 0; b < line.length(); b++){
          if (line.charAt(b) == 'S'){
            sCount++;
          }
          if (line.charAt(b) == 'E'){
            eCount++;
          }
          maze[a][b] = line.charAt(b);
        }
      }
      if (sCount != 1 || eCount != 1){
        throw new IllegalStateException("You have more than one goal or one start!");
      }
    }

    catch (FileNotFoundException e){
      System.out.println("Wrong file!");
    }
  }

  private void wait(int millis){
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public void setAnimate(boolean b){
    animate = b;
  }

  public void clearTerminal(){
    System.out.println("\033[2J\033[1;1H");
  }

  public String toString(){
    String aMazing = "";
    for (int x = 0; x < maze.length; x++){
      aMazing = aMazing + "\n";
      for (int y = 0; y < maze[x].length; x++){
        aMazing = aMazing + maze[x][y];
      }
    }
    return aMazing;
  }



  //find the location of the S.


  //erase the S


  //and start solving at the location of the s.

  //return solve(???,???);
  public int solve(){
    return 1;
  }

  /*
    Recursive Solve function:

    A solved maze has a path marked with '@' from S to E.

    Returns the number of @ symbols from S to E when the maze is solved,
    Returns -1 when the maze has no solution.


    Postcondition:

      The S is replaced with '@' but the 'E' is not.

      All visited spots that were not part of the solution are changed to '.'

      All visited spots that are part of the solution are changed to '@'
  */
  private int solve(int row, int col){ //you can add more parameters since this is private
    if (animate){
      clearTerminal();
      System.out.println(this);
      wait(20);
    }

      //COMPLETE SOLVE

      return -1; //so it compiles
  }

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException

    */

      //COMPLETE CONSTRUCTOR



    /*Return the string that represents the maze.

     It should look like the text file with some characters replaced.

    */


    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */


}
