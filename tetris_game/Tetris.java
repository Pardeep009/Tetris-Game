
  import java.util.*;
  import shapes.*;

  class ShapeStructure
  {
    int version;
    char shape;
    int x;
    int y;
  }

  public class Tetris
  {
     int hash[] = new int[19];
     Stack<Character> undo_stack = new Stack<Character>();
     Stack<Character> redo_stack = new Stack<Character>();
     Stack<Integer> line_stack = new Stack<Integer>();
     Stack<ShapeStructure> boardstack = new Stack<ShapeStructure>();

    public void check_hash(Shape obj,Board board)
    {
      for(int i=0;i<obj.pos.length;i++ )
      {
        if(hash[obj.pos[i][0]] == 18)
        {
          undo_stack.push('-');
          line_stack.push(obj.pos[i][0]);
          for(int y = 1 ; y < 19 ; y++)
          {
            for(int x = obj.pos[i][0]; x>=2 ; x--)
            {
              board.arr[x][y] = board.arr[x-1][y];
            }
          }
          for(int x=obj.pos[i][0]; x>=1 ; x--)
          {
            hash[x] = hash[x-1];
          }
        }
      }
    }

    public void generate_previous_shape(Board board)
    {
      if(boardstack.size()!=0)
      {
        ShapeStructure obj = boardstack.peek();
        boardstack.pop();
        if(obj.shape == 'l')
        {
          Shape shape = new Line(obj.x,obj.y,obj.version,board);
          for(int i=0;i<shape.pos.length;i++)
          {
            if(hash[shape.pos[i][0]]>0)
            hash[shape.pos[i][0]]--;
          }
          Shape('l',shape,board);
        }
        else if(obj.shape == 'T')
        {
          Shape shape = new Tshape(obj.x,obj.y,obj.version,board);
          for(int i=0;i<shape.pos.length;i++)
          {
            if(hash[shape.pos[i][0]]>0)
            hash[shape.pos[i][0]]--;
          }
          Shape('T',shape,board);
        }
        else if(obj.shape == 'L')
        {
          Shape shape = new Lshape(obj.x,obj.y,obj.version,board);
          for(int i=0;i<shape.pos.length;i++)
          {
            if(hash[shape.pos[i][0]]>0)
            hash[shape.pos[i][0]]--;
          }
          Shape('L',shape,board);
        }
        else if(obj.shape == 'S')
        {
          Shape shape = new Square(obj.x,obj.y,obj.version,board);
          for(int i=0;i<shape.pos.length;i++)
          {
            if(hash[shape.pos[i][0]]>0)
            hash[shape.pos[i][0]]--;
          }
          Shape('S',shape,board);
        }
        else
        {
          Shape shape = new Plus(obj.x,obj.y,obj.version,board);
          for(int i=0;i<shape.pos.length;i++)
          {
            if(hash[shape.pos[i][0]]>0)
            hash[shape.pos[i][0]]--;
          }
          Shape('+',shape,board);
        }
      }
    }

    public void remove_shape(Shape shape,Board board)
    {
      for(int i=0;i<shape.pos.length;i++)
      {
        board.arr[shape.pos[i][0]][shape.pos[i][1]] = ' ';
      }
    }

    public void print_hash()
    {
      for(int i=1;i<19;i++)
      {
        System.out.print(hash[i]+" ");
      }
    }

    public void generate_line(Board board)
    {
      char ch;
      do
      {
          int x = line_stack.peek();
          line_stack.pop();
          for(int i=2; i<x; i++)
          {
            for(int j=1;j<=18;j++)
            {
              board.arr[i][j] = board.arr[i+1][j];
            }
          }

          for(int i=1;i<=18;i++)
          {
            board.arr[x][i] = '#';
          }

          for(int i=1; i<x ; i++)
          {
            hash[i] = hash[i+1];
          }

          hash[x] = 18;

        ch = undo_stack.peek();
        undo_stack.pop();
        redo_stack.push(ch);

      }while(ch == '-');

    }

    public void Shape(char shape_type,Shape shape,Board board)
    {
      Scanner sc = new Scanner(System.in);
      char order;
      while(true)
      {
           board.put_Shape_on_board(shape);
           board.displayBoard();
           print_hash();
           order = sc.next().charAt(0);
           if(order == 'z')
           {
             if(undo_stack.size()!=0)
             {
                 char ch = undo_stack.peek();
                 undo_stack.pop();
                 redo_stack.push(ch);
                 if(ch == 'w' || ch == 'W')
                 {
                   shape.changeClockwise(board);
                 }
                 else if(ch == 'e' || ch == 'E')
                 {
                   shape.changeAntiClockwise(board);
                 }
                 else if(ch == 'd' || ch == 'D')
                 {
                   shape.shiftLeft(board,shape);
                 }
                 else if(ch == 'a' || ch == 'A')
                 {
                   shape.shiftRight(board,shape);
                 }
                 else if(ch == 's' || ch == 'S')
                 {
                   shape.shiftUp(board);
                 }
                 else if(ch == '%')
                 {
                   System.out.println("--------------Previous Shape FOUND---------------");
                   remove_shape(shape,board);
                   generate_previous_shape(board);
                 }
                 else if(ch == '-')
                 {
                   System.out.println("--------------LINE FOUND---------------");
                   remove_shape(shape,board);
                   generate_line(board);
                   generate_previous_shape(board);
                   // board.displayBoard();
                   // System.exit(0);
                   // generate_previous_shape(board);
                 }
             }
           }
           else if(order == 'x')
           {
               if(redo_stack.size()!=0)
               {
                  char ch = redo_stack.peek();
                  redo_stack.pop();
                  undo_stack.push(ch);
                  if(ch == 'e' || ch == 'E')
                  {
                    shape.changeClockwise(board);
                  }
                  else if(ch == 'w' || ch == 'W')
                  {
                    shape.changeAntiClockwise(board);
                  }
                  else if(ch == 'a' || ch == 'A')
                  {
                    shape.shiftLeft(board,shape);
                  }
                  else if(ch == 'd' || ch == 'D')
                  {
                    shape.shiftRight(board,shape);
                  }
                  else if(ch == 's' || ch == 'S')
                  {
                    shape.shiftDown(board,shape);
                  }
                  else if(ch == '%')
                  {
                    board.put_Shape_on_board(shape);
                    push_shape(shape,shape_type);
                    break;
                  }
               }
           }
           else
           {
             undo_stack.push(order);
             if(order == 'e' || order == 'E')
             {
               shape.changeClockwise(board);
             }
             else if(order == 'w' || order == 'W')
             {
               shape.changeAntiClockwise(board);
             }
             else if(order == 'd' || order == 'D')
             {
               shape.shiftRight(board,shape);
             }
             else if(order == 'a' || order == 'A')
             {
               shape.shiftLeft(board,shape);
             }
             else if(order == 's' || order == 'S')
             {
               if(shape.shiftDown(board,shape)==1)
               {
                 undo_stack.push('%');
                 board.put_Shape_on_board(shape);
                 push_shape(shape,shape_type);
                 break;
               }
             }
           }
      }

      for(int i=0;i<shape.pos.length;i++)
      {
        hash[shape.pos[i][0]]++;
      }

      check_hash(shape,board);

    }

    public void push_shape(Shape shape,char shape_type)
    {
      ShapeStructure sobj = new ShapeStructure();
      sobj.shape = shape_type;
      sobj.version = shape.version;
      sobj.x = shape.pos[0][0];
      sobj.y = shape.pos[0][1];
      boardstack.push(sobj);
    }

    public static void main(String[] args)
    {
      Tetris tetris = new Tetris();
      Board board = new Board();
      Shape obj;
      ShapeStructure sobj;
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         obj = new Line();
         tetris.Shape('l',obj,board);

         obj = new Tshape();
         tetris.Shape('T',obj,board);

         obj = new Lshape();
         tetris.Shape('L',obj,board);

         obj = new Square();
         tetris.Shape('S',obj,board);

         obj = new Plus();
         tetris.Shape('+',obj,board);

      }
    }
  }
