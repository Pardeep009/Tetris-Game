
  package shapes;
  abstract public class Shape
  {
    public int pos[][] = new int[5][2];
    public int version=1;
    public Shape()
    {
        version = 1;
    }

    public void shiftUp(Board board)
    {
      if(pos[0][0] == 2)
      return;
      for(int i=0;i<pos.length;i++)
      {
        board.arr[pos[i][0]][pos[i][1]] = ' ';
        pos[i][0]-=1;
      }
    }

    public abstract void changeClockwise(Board board);
    public abstract void changeAntiClockwise(Board board);

    public void shiftLeft(Board board,Shape shape)
    {
        int dummy_arr[][] = new int[shape.pos.length][shape.pos[0].length];
        for(int i=0;i<shape.pos.length;i++)
        {
          board.arr[shape.pos[i][0]][shape.pos[i][1]] = ' ';
          dummy_arr[i][0] = shape.pos[i][0];
          dummy_arr[i][1] = shape.pos[i][1] - 1;
        }

        for(int i=0;i<shape.pos.length;i++)
        {
          if(board.arr[dummy_arr[i][0]][dummy_arr[i][1]] != ' ')
          return;
        }

        for(int i=0;i<shape.pos.length;i++)
        {
          shape.pos[i][1] -= 1;
        }
    }

    public void shiftRight(Board board,Shape shape)
    {
        int dummy_arr[][] = new int[shape.pos.length][shape.pos[0].length];
        for(int i=0;i<shape.pos.length;i++)
        {
          board.arr[shape.pos[i][0]][shape.pos[i][1]] = ' ';
          dummy_arr[i][0] = shape.pos[i][0];
          dummy_arr[i][1] = shape.pos[i][1] + 1;
        }

        for(int i=0;i<shape.pos.length;i++)
        {
          if(board.arr[dummy_arr[i][0]][dummy_arr[i][1]] != ' ')
          return;
        }

        for(int i=0;i<shape.pos.length;i++)
        {
          shape.pos[i][1] += 1;
        }
    }

    public int shiftDown(Board board,Shape shape)
    {
      int dummy_arr[][] = new int[shape.pos.length][shape.pos[0].length];
      for(int i=0;i<shape.pos.length;i++)
      {
        board.arr[shape.pos[i][0]][shape.pos[i][1]] = ' ';
        dummy_arr[i][0] = shape.pos[i][0] + 1;
        dummy_arr[i][1] = shape.pos[i][1];
      }

      for(int i=0;i<shape.pos.length;i++)
      {
        if(board.arr[dummy_arr[i][0]][dummy_arr[i][1]] != ' ')
        return 1;
      }

      for(int i=0;i<shape.pos.length;i++)
      {
        shape.pos[i][0] += 1;
      }

      return 0;
    }

  }
