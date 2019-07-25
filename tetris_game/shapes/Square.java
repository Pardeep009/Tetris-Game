
  package shapes;
  public class Square extends Shape
  {
      public Square()
      {
          super();
          pos = new int[4][2];
          int y=1;
          for(int i=0;i<pos.length/2;i++)
          {
              pos[i][0] = i+y+1;
              pos[i][1] = Board.size/2;
          }
          for(int i=pos.length/2;i<pos.length;i++)
          {
              pos[i][0] = i;
              pos[i][1] = (Board.size/2)+1;
          }
      }

      public Square(int x,int y,int ver,Board board)
      {
          pos = new int[4][2];
          pos[0][0] = x;
          pos[0][1] = y;
          pos[1][0] = x+1;
          pos[1][1] = y;
          pos[2][0] = x;
          pos[2][1] = y+1;
          pos[3][0] = x+1;
          pos[3][1] = y+1;

          for(int i=0;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
          }
          version = ver;
      }

      public void changeClockwise(Board board){}

      public void changeAntiClockwise(Board board){}

  }
