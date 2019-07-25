
  package shapes;
  public class Lshape extends Shape
  {
    public Lshape()
    {
      super();
      int y = 1;
      for(int i=0;i<=pos.length/2;i++)
      {
        pos[i][0] = i+y+1;
        pos[i][1] = Board.size/2;
      }
      for(int i=pos.length/2+1;i<pos.length;i++)
      {
        pos[i][0] = pos[pos.length/2][0];
        pos[i][1] = pos[i-1][1]+1;
      }
    }

    public Lshape(int x,int y,int ver,Board board)
    {
        pos[0][0] = x;
        pos[0][1] = y;
        if(ver == 1)
        {
          for(int i=1;i<=pos.length/2;i++)
          {
            pos[i][0] = pos[i-1][0] + 1;
            pos[i][1] = pos[i-1][1];
          }
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            pos[i][0] = pos[i-1][0];
            pos[i][1] = pos[i-1][1] + 1;
          }
        }
        else if(ver == 2)
        {
          for(int i=1;i<=pos.length/2;i++)
          {
            pos[i][0] = pos[i-1][0];
            pos[i][1] = pos[i-1][1] - 1;
          }
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            pos[i][0] = pos[i-1][0] + 1;
            pos[i][1] = pos[i-1][1];
          }
        }
        else if(ver == 3)
        {
          for(int i=1;i<=pos.length/2;i++)
          {
            pos[i][0] = pos[i-1][0] - 1;
            pos[i][1] = pos[i-1][1];
          }
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            pos[i][0] = pos[i-1][0];
            pos[i][1] = pos[i-1][1] - 1;
          }
        }
        else
        {
          for(int i=1;i<=pos.length/2;i++)
          {
            pos[i][0] = pos[i-1][0];
            pos[i][1] = pos[i-1][1] + 1;
          }
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            pos[i][0] = pos[i-1][0] - 1;
            pos[i][1] = pos[i-1][1];
          }
        }

        for(int i=0;i<pos.length;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
        }

        version = ver;
    }


    int touch_bottomboundary()
    {
      return (((pos[0][0]+pos.length/2)>(Board.size-2))||((pos[pos.length-1][0]+pos.length/2)>(Board.size-2)))?1:0;
    }

    int touch_rightboundary()
    {
      return (((pos[0][1]+pos.length/2)>(Board.size-2))||((pos[pos.length-1][1]+pos.length/2)>(Board.size-2)))?1:0;
    }

    int touch_leftboundary()
    {
      return (((pos[0][1]-pos.length/2)<(1))||((pos[pos.length-1][1]-pos.length/2)<(1)))?1:0;
    }

    boolean check_changeC1(Board board)
    {
      int dummy_arr[][] = new int[5][2];
      for (int i=0;i<pos.length;i++) {
        dummy_arr[i][0] = pos[i][0];
        dummy_arr[i][1] = pos[i][1];
        board.arr[dummy_arr[i][0]][dummy_arr[i][1]] = ' ';
      }
      for (int i=0;i<pos.length/2;i++)
      {
        dummy_arr[i][0] = dummy_arr[dummy_arr.length/2][0];
        dummy_arr[i][1] += (dummy_arr.length/2)-i;
        dummy_arr[dummy_arr.length-i-1][0] += (dummy_arr.length/2)-i;
        dummy_arr[dummy_arr.length-i-1][1] = dummy_arr[dummy_arr.length/2][1];
      }
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
        for(int i=0;i<pos.length/2;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
          board.arr[pos[pos.length-(i+1)][0]][pos[pos.length-(i+1)][1]] = ' ';
          pos[i][0] = pos[pos.length/2][0];
          pos[i][1] += (pos.length/2)-i;
          pos[pos.length-i-1][0] += (pos.length/2)-i;
          pos[pos.length-i-1][1] = pos[pos.length/2][1];
        }
        version = var;
      }
    }

    boolean check_changeC2(Board board)
    {
      int dummy_arr[][] = new int[5][2];
      for (int i=0;i<pos.length;i++) {
        dummy_arr[i][0] = pos[i][0];
        dummy_arr[i][1] = pos[i][1];
        board.arr[dummy_arr[i][0]][dummy_arr[i][1]] = ' ';
      }
      for (int i=0;i<pos.length/2;i++)
      {
        dummy_arr[i][0] += (dummy_arr.length/2) - i;
        dummy_arr[i][1] = dummy_arr[dummy_arr.length/2][1];
        dummy_arr[dummy_arr.length-i-1][0] = dummy_arr[dummy_arr.length/2][0];
        dummy_arr[dummy_arr.length-i-1][1] -= (dummy_arr.length/2) - i;
      }
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
        for(int i=0;i<pos.length/2;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
          board.arr[pos[pos.length-(i+1)][0]][pos[pos.length-(i+1)][1]] = ' ';
          pos[i][0] += (pos.length/2) - i;
          pos[i][1] = pos[pos.length/2][1];
          pos[pos.length-i-1][0] = pos[pos.length/2][0];
          pos[pos.length-i-1][1] -= (pos.length/2) - i;
        }
        version = var;
      }
    }

    boolean check_changeC3(Board board)
    {
      int dummy_arr[][] = new int[5][2];
      for (int i=0;i<pos.length;i++) {
        dummy_arr[i][0] = pos[i][0];
        dummy_arr[i][1] = pos[i][1];
        board.arr[dummy_arr[i][0]][dummy_arr[i][1]] = ' ';
      }
      for (int i=0;i<pos.length/2;i++)
      {
        dummy_arr[i][0] = dummy_arr[dummy_arr.length/2][0];
        dummy_arr[i][1] -= (dummy_arr.length/2) - i;
        dummy_arr[dummy_arr.length-i-1][0] -= (dummy_arr.length/2) - i;
        dummy_arr[dummy_arr.length-i-1][1] = dummy_arr[dummy_arr.length/2][1];
      }
      for(int i=0;i<dummy_arr.length;i++)
      {
        if(board.arr[dummy_arr[i][0]][dummy_arr[i][1]]!=' ')
        return false;
      }
      return true;
    }

    void changeC3(int var,Board board)
    {
      if(check_changeC3(board))
      {
        for(int i=0;i<pos.length/2;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
          board.arr[pos[pos.length-(i+1)][0]][pos[pos.length-(i+1)][1]] = ' ';
          pos[i][0] = pos[pos.length/2][0];
          pos[i][1] -= (pos.length/2) - i;
          pos[pos.length-i-1][0] -= (pos.length/2) - i;
          pos[pos.length-i-1][1] = pos[pos.length/2][1];
        }
        version = var;
      }
    }

    boolean check_changeC4(Board board)
    {
      int dummy_arr[][] = new int[5][2];
      for (int i=0;i<pos.length;i++) {
        dummy_arr[i][0] = pos[i][0];
        dummy_arr[i][1] = pos[i][1];
        board.arr[dummy_arr[i][0]][dummy_arr[i][1]] = ' ';
      }
      for (int i=0;i<pos.length/2;i++)
      {
        dummy_arr[i][0] -= (dummy_arr.length/2)-i;
        dummy_arr[i][1] = dummy_arr[dummy_arr.length/2][1];
        dummy_arr[dummy_arr.length-i-1][0] = dummy_arr[dummy_arr.length/2][0];
        dummy_arr[dummy_arr.length-i-1][1] += (dummy_arr.length/2)-i;
      }
      for(int i=0;i<dummy_arr.length;i++)
      {
        if(board.arr[dummy_arr[i][0]][dummy_arr[i][1]]!=' ')
        return false;
      }
      return true;
    }

    void changeC4(int var,Board board)
    {
      if(check_changeC4(board))
      {
        for(int i=0;i<pos.length/2;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
          board.arr[pos[pos.length-(i+1)][0]][pos[pos.length-(i+1)][1]] = ' ';
          pos[i][0] -= (pos.length/2)-i;
          pos[i][1] = pos[pos.length/2][1];
          pos[pos.length-i-1][0] = pos[pos.length/2][0];
          pos[pos.length-i-1][1] += (pos.length/2)-i;
        }
        version = var;
      }
    }

    public void changeClockwise(Board board)
    {
      if(version == 1)
      {
        if(touch_bottomboundary()==1)
        return;
        changeC1(2,board);
      }
      else if(version == 2)
      {
        if(touch_leftboundary()==1)
        return;
        changeC2(3,board);
      }
      else if(version == 3)
      {
        changeC3(4,board);
      }
      else
      {
        if(touch_rightboundary()==1)
        return;
        changeC4(1,board);
      }
    }

    public void changeAntiClockwise(Board board)
    {
        if(version == 1)
        {
          if(touch_leftboundary()==1)
          return;
          changeC3(4,board);
        }
        else if(version == 2)
        {
          changeC4(1,board);
        }
        else if(version == 3)
        {
          if(touch_rightboundary()==1)
          return;
          changeC1(2,board);
        }
        else
        {
          if(touch_bottomboundary()==1)
          return;
          changeC2(3,board);
        }
    }

  }

  // System.out.println(pos[i][0]);
  // System.out.println(pos[i][1]);
  // System.out.println(pos[pos.length-i-1][0]);
  // System.out.println(pos[pos.length-i-1][1]);

  // System.out.println(pos[i][0]);
  // System.out.println(pos[i][1]);
  // System.out.println(pos[pos.length-i-1][0]);
  // System.out.println(pos[pos.length-i-1][1]);
