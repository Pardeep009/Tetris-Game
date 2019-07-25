
  package shapes;
  public class Tshape extends Shape
  {
      public Tshape()
      {
        super();
        pos[pos.length/2][0] = 2;
        pos[pos.length/2][1] = Board.size/2;
        pos[0][0] = 2;
        pos[0][1] = pos[2][1] - 1;
        pos[1][0] = 2;
        pos[1][1] = pos[2][1] + 1;
        for(int i=pos.length/2 + 1;i<pos.length;i++)
        {
            pos[i][0] = pos[i-1][0]+1;
            pos[i][1] = pos[pos.length/2][1];
        }
      }

      public Tshape(int x,int y,int ver,Board board)
      {
          pos[0][0] = x;
          pos[0][1] = y;
          if(ver == 1)
          {
            pos[1][0] = x;
            pos[1][1] = y+2;
            pos[pos.length/2][0] = x;
            pos[pos.length/2][1] = y+1;
            for(int i=pos.length/2 + 1;i<pos.length;i++)
            {
              pos[i][0] = pos[i-1][0] + 1;
              pos[i][1] = y+1;
            }
          }
          else if(ver == 2)
          {
            pos[1][0] = x+2;
            pos[1][1] = y;
            pos[pos.length/2][0] = x+1;
            pos[pos.length/2][1] = y;
            for(int i=pos.length/2 + 1;i<pos.length;i++)
            {
              pos[i][0] = x+1;
              pos[i][1] = pos[i-1][1] - 1;
            }
          }
          else if(ver == 3)
          {
            pos[1][0] = x;
            pos[1][1] = y-2;
            pos[pos.length/2][0] = x;
            pos[pos.length/2][1] = y-1;
            for(int i=pos.length/2 + 1;i<pos.length;i++)
            {
              pos[i][0] = pos[i-1][0] - 1;
              pos[i][1] = y - 1;
            }
          }
          else
          {
            pos[1][0] = x-2;
            pos[1][1] = y;
            pos[pos.length/2][0] = x-1;
            pos[pos.length/2][1] = y;
            for(int i=pos.length/2 + 1;i<pos.length;i++)
            {
              pos[i][0] = x-1;
              pos[i][1] = pos[i-1][1] + 1;
            }
          }
          for(int i=0;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
          }
          version = ver;
      }

      int touch_bottomboundary(int index)
      {
        if(index == 5)
        return (pos[pos.length-1][0]+pos.length/2)>(Board.size-2)?1:0;
        else
        return (pos[index-1][0]+pos.length/2-1)>(Board.size-2)?1:0;
      }

      int touch_rightboundary(int index)
      {
        if(index == 5)
        return (pos[pos.length-1][1]+pos.length/2)>(Board.size-2)?1:0;
        else
        return (pos[index-1][1]+pos.length/2-1)>(Board.size-2)?1:0;
      }

      int touch_leftboundary(int index)
      {
        System.out.println(index);
        if(index == 5)
        return (pos[pos.length-1][1]-pos.length/2)<1?1:0;
        else
        return (pos[index-1][1]-1)<1?1:0;
      }

      boolean check_changeC1(Board board)
      {
        int dummy_arr[][] = new int[5][2];
        for (int i=0;i<pos.length;i++) {
          dummy_arr[i][0] = pos[i][0];
          dummy_arr[i][1] = pos[i][1];
          board.arr[dummy_arr[i][0]][dummy_arr[i][1]] = ' ';
        }
        dummy_arr[0][0] = dummy_arr[dummy_arr.length/2][0]-1;
        dummy_arr[1][0] = dummy_arr[dummy_arr.length/2][0]+1;
        dummy_arr[0][1]=dummy_arr[1][1]=dummy_arr[dummy_arr.length/2][1];
        for(int i=dummy_arr.length/2+1;i<dummy_arr.length;i++)
        {
          dummy_arr[i][0] = dummy_arr[dummy_arr.length/2][0];
          dummy_arr[i][1] = dummy_arr[i-1][1] - 1;
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
          board.arr[pos[0][0]][pos[0][1]] = ' ';
          board.arr[pos[1][0]][pos[1][1]] = ' ';
          pos[0][0] = pos[pos.length/2][0]-1;
          pos[1][0] = pos[pos.length/2][0]+1;
          pos[0][1]=pos[1][1]=pos[pos.length/2][1];
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
              pos[i][0] = pos[pos.length/2][0];
              pos[i][1] = pos[i-1][1] - 1;
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
        dummy_arr[0][0]=dummy_arr[1][0]=dummy_arr[dummy_arr.length/2][0];
        dummy_arr[0][1] = dummy_arr[dummy_arr.length/2][1]+1;
        dummy_arr[1][1] = dummy_arr[dummy_arr.length/2][1]-1;
        for(int i=dummy_arr.length/2+1;i<dummy_arr.length;i++)
        {
          dummy_arr[i][0] = dummy_arr[i-1][0] - 1;
          dummy_arr[i][1] = dummy_arr[dummy_arr.length/2][1];
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
          board.arr[pos[0][0]][pos[0][1]] = ' ';
          board.arr[pos[1][0]][pos[1][1]] = ' ';
          pos[0][0]=pos[1][0]=pos[pos.length/2][0];
          pos[0][1] = pos[pos.length/2][1]+1;
          pos[1][1] = pos[pos.length/2][1]-1;
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
            pos[i][0] = pos[i-1][0] - 1;
            pos[i][1] = pos[pos.length/2][1];
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
        dummy_arr[0][0] = dummy_arr[dummy_arr.length/2][0]+1;
        dummy_arr[1][0] = dummy_arr[dummy_arr.length/2][0]-1;
        dummy_arr[0][1]=dummy_arr[1][1]=dummy_arr[dummy_arr.length/2][1];
        for(int i=dummy_arr.length/2+1;i<dummy_arr.length;i++)
        {
            dummy_arr[i][0] = dummy_arr[dummy_arr.length/2][0];
            dummy_arr[i][1] = dummy_arr[i-1][1] + 1;
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
          board.arr[pos[0][0]][pos[0][1]] = ' ';
          board.arr[pos[1][0]][pos[1][1]] = ' ';
          pos[0][0] = pos[pos.length/2][0]+1;
          pos[1][0] = pos[pos.length/2][0]-1;
          pos[0][1]=pos[1][1]=pos[pos.length/2][1];
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
              pos[i][0] = pos[pos.length/2][0];
              pos[i][1] = pos[i-1][1] + 1;
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
        dummy_arr[0][0]=dummy_arr[1][0]=dummy_arr[dummy_arr.length/2][0];
        dummy_arr[0][1] = dummy_arr[dummy_arr.length/2][1]-1;
        dummy_arr[1][1] = dummy_arr[dummy_arr.length/2][1]+1;
        for(int i=dummy_arr.length/2+1;i<dummy_arr.length;i++)
        {
            dummy_arr[i][0] = dummy_arr[i-1][0] + 1;
            dummy_arr[i][1] = dummy_arr[dummy_arr.length/2][1];
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
          board.arr[pos[0][0]][pos[0][1]] = ' ';
          board.arr[pos[1][0]][pos[1][1]] = ' ';
          pos[0][0]=pos[1][0]=pos[pos.length/2][0];
          pos[0][1] = pos[pos.length/2][1]-1;
          pos[1][1] = pos[pos.length/2][1]+1;
          for(int i=pos.length/2+1;i<pos.length;i++)
          {
            board.arr[pos[i][0]][pos[i][1]] = ' ';
              pos[i][0] = pos[i-1][0] + 1;
              pos[i][1] = pos[pos.length/2][1];
          }
          version = var;
        }
      }

      public void changeClockwise(Board board)
      {
          if(version == 1)
          {
            // 5 ki left
            if(touch_leftboundary(5)==1)
            return;
            changeC1(2,board);
          }
          else if(version == 2)
          {
            // 1 ki right
            if(touch_rightboundary(1)==1)
            return;
            changeC2(3,board);
          }
          else if(version == 3)
          {
            // 5 ki right
            // 1 ki bottom
            if(touch_rightboundary(5)==1)
            return;
            if(touch_bottomboundary(1)==1)
            return;
            changeC3(4,board);
          }
          else
          {
            // 1 ki left
            // 5 ki bottom
            if(touch_leftboundary(1)==1)
            return;
            if(touch_bottomboundary(5)==1)
            return;
            changeC4(1,board);
          }
      }

      public void changeAntiClockwise(Board board)
      {
          if(version == 1)
          {
            // 5 ki right
            if(touch_rightboundary(5)==1)
            return;
              changeC3(4,board);
          }
          else if(version == 2)
          {
            // 2 ki right
            // 5 ka bottom
            if(touch_rightboundary(2)==1)
            return;
            if(touch_bottomboundary(5)==1)
            return;
              changeC4(1,board);
          }
          else if(version == 3)
          {
            // 2 ki bottom
            // 5 ki left
            if(touch_leftboundary(5)==1)
            return;
            if(touch_bottomboundary(2)==1)
            return;
              changeC1(2,board);
          }
          else
          {
            // 2 ka left
            if(touch_leftboundary(2)==1)
            return;
              changeC2(3,board);
          }
      }

  }
