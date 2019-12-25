import java.util.*;
public class OODAssign2OLD{
    public static void main(String[]args){
        CheckersBoard game1 = new CheckersBoard();
        //	System.out.println(game1.move(5,7,3,1));
        System.out.println(game1.move(0,2,4,6));
        //	System.out.println(game1.move(0,2,4,6));
        game1.display();
        System.out.println(game1.count(1));
    }
}

class CheckersBoard{
    private int[][] board = {{2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 0, 0, 2, 0, 2},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1}};
    private static final int BLACK = 1;
    private static final int RED = 2;
    private static final int EMPTY = 0;
    public boolean finished = false;

    public int get(int y, int x){
        System.out.println(x+", "+y+" check");
        if(x < 0 || x >7 || y < 0 || y > 7)
            return -1;
        else
            return board[y][x];
    }

    public boolean move(int x1, int y1, int x2, int y2){
        if (y2<=7 && x2<=7 && y1>=0 && x2>=0){
            //basic move:
            if(Math.abs(y2-y1)==1 && Math.abs(x2-x1)==1){
                if (board[y1][x1]==RED && board[y2][x2]==EMPTY){
                    board[y1][x1]=EMPTY;
                    board[y2][x2]=RED;
                    return true;
                }
                else if (board[y1][x1]==BLACK && board[y2][x2]==EMPTY){
                    board[y1][x1]=EMPTY;
                    board[y2][x2]=BLACK;
                    return true;
                }
            }
            else if(board[y1][x1]!=EMPTY && board[y2][x2]==EMPTY && Math.abs(x2-x1)%2==0 && Math.abs(y2-y1)%2==0){
                return capture(x1,y1,x2,y2);
            }
        }
        return false;
    }

    public boolean capture(int x1, int y1, int x2, int y2){
        int startPiece = board[y1][x1];
        int capturedPiece = board[y1][x1]==RED? BLACK:RED;
        int vertical = board[y1][x1]==RED ? 1:-1;
        vertical=1;
        boolean left=false;
        boolean right=false;
        System.out.println(x1+2+", "+(y1+vertical*2));
        if(x1==x2 && y1==y2){
            System.out.println(true);
            return true;
        }
        System.out.println(get(y1+(vertical*2),x1-2));
        if(get(y1+vertical,x1-1)==capturedPiece && get(y1+(vertical*2),x1-2)==EMPTY){
            left = capture(x1-2, y1+(vertical*2), x2, y2);
            System.out.println("left");
            if(left){
                board[y1+vertical][x1-1]=EMPTY;
                board[y1][x1]=EMPTY;
                board[y2][x2]=startPiece;
                return true;
            }
        }
        System.out.println("fail");
        if(get(y1+vertical,x1+1)==capturedPiece && get(y1+(vertical*2),x1+2)==EMPTY){
            right = capture(x1+2, y1+(vertical*2), x2, y2);


            System.out.println("right");
            if(right){
                board[y1+vertical][x1+1]=EMPTY;
                board[y1][x1]=EMPTY;
                board[y2][x2]=startPiece;
                return true;
            }
        }
        return false;
    }

    public int count(int colour){
        int count = 0;
        for (int[]row : board){
            for (int piece :row){
                if (piece == colour){
                    count++;
                }
            }
        }
        return count;
    }

    public void display(){
        String ans="---------------------------------\n";
        for(int[]col : board){
            for(int piece : col){
                ans= ans + "|";
                if (piece == 0){
                    ans = ans + ("   ");
                }
                else if (piece == BLACK){
                    ans = ans + (" B ");
                }
                else{
                    ans = ans + (" R ");
                }
            }
            ans = ans +"|\n---------------------------------\n";
        }
        System.out.println(ans);
    }
}