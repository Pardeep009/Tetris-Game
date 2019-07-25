
  package shapes;
  public class Plus extends Shape
  {
      public Plus()
      {
        super();
        int y=1;
        pos[2][0] = 3;
        pos[2][1] = Board.size/2;
        for(int i=0;i<pos.length/2;i++)
        {
          pos[i][0] = i+y+1;
          pos[i][1] = pos[2][1]-1;
        }
        for(int i=(pos.length/2)+1;i<pos.length;i++)
        {
          pos[i][0] = i;
          pos[i][1] = pos[2][1]+1;
        }
      }

      public Plus(int x,int y,int ver,Board board)
      {
        pos[0][0] = x;
        pos[0][1] = y;
        if(ver == 1)
        {
          pos[1][0] = x+1;
          pos[1][1] = y;
          pos[2][0] = x+1;
          pos[2][1] = y+1;
          for(int i=(pos.length/2)+1;i<pos.length;i++)
          {
            pos[i][0] = x+i-2;
            pos[i][1] = y+2;
          }
        }
        else
        {
          pos[1][0] = x;
          pos[1][1] = y-1;
          pos[2][0] = x+1;
          pos[2][1] = y-1;
          for(int i=(pos.length/2)+1;i<pos.length;i++)
          {
            pos[i][0] = x+2;
            pos[i][1] = y-(i-2);
          }
        }

        for(int i=0;i<pos.length;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
        }
        version = ver;
      }

      boolean check_changeC2(Board board)
      {
        int dummy_arr[][] = new int[5][2];
        for (int i=0;i<pos.length;i++) {
          dummy_arr[i][0] = pos[i][0];
          dummy_arr[i][1] = pos[i][1];
          board.arr[dummy_arr[i][0]][dummy_arr[i][1]] = ' ';
        }
        dummy_arr[0][1]+=2;
        dummy_arr[dummy_arr.length-1][1]-=2;
        dummy_arr[1][0]-=1;
        dummy_arr[dummy_arr.length-2][0]+=1;
        dummy_arr[1][1] = dummy_arr[dummy_arr.length-2][1] = dummy_arr[dummy_arr.length/2][1];
        for(int i=0;i<dummy_arr.length;i++)
        {
          if(board.arr[dummy_arr[i][0]][dummy_arr[i][1]]!=' ')
          return false;
        }
        return true;
      }

      void changeC2(int var,Board board)
      {
        if(check_changeC2(board))
        {
          for(int i=0;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
          }
          pos[0][1]+=2;
          pos[pos.length-1][1]-=2;
          pos[1][0]-=1;
          pos[pos.length-2][0]+=1;
          pos[1][1] = pos[pos.length-2][1] = pos[pos.length/2][1];
          version = var;
        }
      }

      boolean check_changeC1(Board board)
      {
        int dummy_arr[][] = new int[5][2];
        for (int i=0;i<pos.length;i++) {
          dummy_arr[i][0] = pos[i][0];
          dummy_arr[i][1] = pos[i][1];
          board.arr[dummy_arr[i][0]][dummy_arr[i][1]] = ' ';
        }
        dummy_arr[0][1]-=2;
        dummy_arr[dummy_arr.length-1][1]+=2;
        dummy_arr[1][1]-=1;
        dummy_arr[dummy_arr.length-2][1]+=1;
        dummy_arr[1][0] = dummy_arr[dummy_arr.length-2][0] = dummy_arr[dummy_arr.length/2][0];
        for(int i=0;i<dummy_arr.length;i++)
        {
          if(board.arr[dummy_arr[i][0]][dummy_arr[i][1]]!=' ')
          return false;
        }
        return true;
      }

      void changeC1(int var,Board board)
      {
        if(check_changeC1(board))
        {
          for(int i=0;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
          }
          pos[0][1]-=2;
          pos[pos.length-1][1]+=2;
          pos[1][1]-=1;
          pos[pos.length-2][1]+=1;
          pos[1][0] = pos[pos.length-2][0] = pos[pos.length/2][0];
          version = var;
        }
      }

      public void changeClockwise(Board board)
      {
          if(version == 1)
          {
            changeC2(2,board);
          }
          else
          {
            changeC1(1,board);
          }
      }

      public void changeAntiClockwise(Board board)
      {
          changeClockwise(board);
      }

  }
