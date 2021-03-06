import java.util.*;
import java.io.*;
public class Maze{
  private char[][]maze;
  private boolean animate; //false by default
  private int row;
  private int col;

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
      for (int y = 0; y < maze[x].length; y++){
        aMazing = aMazing + maze[x][y];
      }
    }
    return aMazing;
  }

  public int solve(){
    int r = 0;
    int c = 0;
    for (int x = 0; x < maze.length; x++){
      for (int y = 0; y < maze[0].length; y++){
        if (maze[x][y] == 'S'){
          r = x;
          c = y;
          maze[x][y] = ' ';
        }
      }
    }
    solve(r,c);
    return count();
  }

  private int solve(int row, int col){ //you can add more parameters since this is private
    int[] moves = {1, 0, -1, 0, 0, 1, 0, -1};
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
      maze[row][col] = '@';
      for (int i = 0; i < moves.length; i = i + 2){
        int x = solve(row + moves[i], col + moves[i + 1]);
        if (x > 0){
          return x;
        }
      }
      maze[row][col] = '.';
    }
    return -1; // when maze has no solution
  }

  public int count(){
    int toReturn = 0;
    for (int x = 0; x < maze.length; x++){
      for (int y = 0; y < maze[0].length; y++){
        if (maze[x][y] == '@'){
          toReturn++;
        }
      }
    }
    return toReturn;
  }
}
