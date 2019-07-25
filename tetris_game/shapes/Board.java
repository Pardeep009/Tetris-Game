
  package shapes;
  public class Board
  {
    public char arr[][];
    public static int size=20;
    public Board()
    {
      arr = new char[size][size];
      for(int i=0;i<size;i++)
      {
        for(int j=0;j<size;j++)
        {
          if((i==0)||(i==size-1)||(j==0)||(j==size-1))
          {
            arr[i][j] = '*';
          }
          else
          {
            arr[i][j] = ' ';
          }
        }
      }
    }

    public void put_Shape_on_board(Shape shape)
    {
      for(int i=0;i<shape.pos.length;i++)
      {
        arr[shape.pos[i][0]][shape.pos[i][1]] = '#';
      }
    }

   public void displayBoard()
    {
      System.out.println("\033\143");
      for(int i=0;i<size;i++)
      {
        for(int j=0;j<size;j++)
        {
          System.out.print(arr[i][j]);
        }
        System.out.println();
      }
      // System.out.println("Left -> A");
      // System.out.println("Right -> D");
      // System.out.println("Down -> S");
      // System.out.println("Clockwise -> E");
      // System.out.println("AntiClockwise -> W");
    }
  }
