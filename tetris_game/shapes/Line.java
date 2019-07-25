
  package shapes;

  public class Line extends Shape
  {
    public Line()
    {
      super();
      pos[0][0] = 2;
      pos[0][1] = Board.size/2;
      for(int i=1;i<pos.length;i++)
      {
          pos[i][0] =  pos[i-1][0]+1;
          pos[i][1] = Board.size/2;
      }
    }

    public Line(int x,int y,int ver,Board board)
    {
        board.arr[x][y] = ' ';
        pos[0][0] = x;
        pos[0][1] = y;
        if(ver == 1)
        {
          for(int i=1;i<pos.length;i++)
          {
            pos[i][0] = pos[i-1][0] + 1;
            pos[i][1] = y;
            board.arr[pos[i][0]][pos[i][1]] = ' ';
          }
        }
        else
        {
          for(int i=1;i<pos.length;i++)
          {
            pos[i][0] = x;
            pos[i][1] = pos[i-1][1] - 1;
            board.arr[pos[i][0]][pos[i][1]] = ' ';
          }
        }
        version = ver;
    }

    int touch_bottomboundary()
    {
      return (pos[pos.length-1][0]+pos.length/2)>(Board.size-2)?1:0;
    }
    int touch_rightboundary()
    {
      return (pos[0][1]+pos.length/2)>(Board.size-2)?1:0;
    }

    int touch_leftboundary()
    {
      return (pos[pos.length-1][1]-pos.length/2)<(1)?1:0;
    }

    boolean check_changeC1(int pos[][],Board board)
    {
      int x1,y1;
      for(int i=0;i<pos.length/2;i++)
      {
        x1 = pos[i][0] - (pos.length/2)-i;
        y1 = pos[pos.length/2][1];
        if(board.arr[x1][y1]!=' ')
        return false;
        x1 = pos[pos.length-(i+1)][0] + (pos.length/2)-i;
        y1 = pos[pos.length/2][1];
        if(board.arr[x1][y1]!=' ')
        return false;
      }
      return true;
    }

    void changeC1(int var,Board board)
    {
      if(check_changeC1(pos,board))
      {
        for(int i=0;i<pos.length/2;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
          board.arr[pos[pos.length-(i+1)][0]][pos[pos.length-(i+1)][1]] = ' ';
          pos[i][0] -= (pos.length/2)-i;
          pos[i][1] = pos[pos.length/2][1];
          pos[pos.length-(i+1)][0] += (pos.length/2)-i;
          pos[pos.length-(i+1)][1] = pos[pos.length/2][1];;
        }
        version = var;
      }
    }

    boolean check_changeC2(int pos[][],Board board)
    {
      int x1,y1;
      for(int i=0;i<pos.length/2;i++)
      {
        x1 = pos[pos.length/2][0];
        y1 = pos[i][1] + (pos.length/2)-i;
        if(board.arr[x1][y1]!=' ')
        return false;
        x1 = pos[pos.length/2][0];
        y1 = pos[pos.length-(i+1)][1] - (pos.length/2)-i;
        if(board.arr[x1][y1]!=' ')
        return false;
      }
      return true;
    }

    void changeC2(int var,Board board)
    {
      if(check_changeC2(pos,board))
      {
        for(int i=0;i<pos.length/2;i++)
        {
          board.arr[pos[i][0]][pos[i][1]] = ' ';
          board.arr[pos[pos.length-(i+1)][0]][pos[pos.length-(i+1)][1]] = ' ';
            pos[i][0] = pos[pos.length/2][0];
            pos[i][1] += (pos.length/2)-i;
            pos[pos.length-(i+1)][0] = pos[pos.length/2][0];
            pos[pos.length-(i+1)][1] -= ((pos.length/2)-i);
        }
        version = var;
      }
    }

    public void changeClockwise(Board board)
    {
      if(version == 1)
      {
          if((touch_leftboundary()==1)||(touch_rightboundary()==1))
          return;
          changeC2(2,board);
      }
      else
      {
          if(touch_bottomboundary()==1)
          return;
            changeC1(1,board);
      }
    }

    public void changeAntiClockwise(Board board){
      changeClockwise(board);
    }

  }
