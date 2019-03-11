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
      // getting the information on row
      File read = new File(filename);
      Scanner toRow = new Scanner(read);
      row = 0;
      col = 0;
      while (toRow.hasNextLine()){
        row++;
        toRow.nextLine();
      }

      // getting the information on col
      Scanner toCol = new Scanner(read);
      String s = toCol.nextLine();
      for (int i = 0; i < s.length(); i++){
        col++;
      }

      // checking if there is only exactly one S and one E
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
    int r = 0;
    int c = 0;
    for (int x = 0; x < r; x++){
      for (int y = 0; y < c; y++){
        if (maze[x][y] == 'S'){
          r = x;
          c = y;
        }
      }
    }
    solve(r,c);
    return count();
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

    // if we are at the end of the maze, we have found a solution
    if (maze[row][col] == 'E'){
      return 1;
    }

    // if we are not a valid location (walls or visited spots), return zero because it is invalid
    if (maze[row][col] == '#' || maze[row][col] == '@' || maze[row][col] == '.'){
      return 0;
    }

    // empty spaces are valid
    if (maze[row][col] == ' '){
      // mark the location as a visited spot
      maze[r][c] == '@';
      count++;
      for (int i = 0; i < moves.length; i = i + 2){
        int x = solve(row + moves[i], c + moves[i + 1]);
        if (x > 0){
          return x;
        }
      }
    }
    maze[row][col] = '.';
    return -1; // when maze has no solution
  }

  public int count(){
    int toReturn = 0;
    for (int x = 0; y < maze.length; x++){
      for (int y = 0; y < maze[0].length; y++){
        if (maze[x][y] == '@'){
          toReturn++;
        }
      }
    }
    return toReturn;
  }
}
