import java.util.*;


class TicTacToemain{
	static char[][] board;
	
	public TicTacToemain() {
		board = new char[3][3];
        initBoard();
	}
	
	void initBoard( ) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	static void dispBoard() {
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j] + " | ");
			}
            System.out.println();
			System.out.println("-------------");
		}
	}

    static void placeMark(int row, int col, char mark){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            board[row][col]=mark;
        }
        else{
            System.out.println("Please enter value between 0 - 2");
        }
    }
    
    static boolean checkcolwin(){
        for(int j=0;j<=2;j++){
            if(board[0][j] != ' ' && board[0][j]==board[1][j] && board[0][j]==board[1][j]){
                return true;
            }
        }
        return false;
    }

    static boolean checkrowwin(){
        for(int i=0;i<=2;i++){
            if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]){
                return true;
            }
        }
        return false;
    }

    static boolean checkdigwin(){
        if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]){
            return true;
        }
        return false;
        
    }

    static boolean checkdraw(){
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }

}

abstract class player{
    String name;
    char mark;

    abstract void makemove();

    boolean ismovevalid(int row, int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(TicTacToemain.board[row][col]==' '){
                return true;
            }
        }
        return false;
    }
}

class HumanPlayer extends player{

    HumanPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }

    void makemove(){
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do{
            System.out.println("Enter the row and col: ");
            row = scan.nextInt();
            col = scan.nextInt();
        }while(!ismovevalid(row, col));
        TicTacToemain.placeMark(row, col, mark);
    }
    
}

class AIPlayer extends player{

    AIPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }

    void makemove(){
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do{
            Random r = new Random();
            row=r.nextInt(3);
            col=r.nextInt(3);
        }while(!ismovevalid(row, col));
        TicTacToemain.placeMark(row, col, mark);
    }
    
}

class TicTacToe{
    public static void main(String[] args){
        TicTacToemain t = new TicTacToemain();
        HumanPlayer p1 = new HumanPlayer("Bob", 'X');
        AIPlayer p2 = new AIPlayer("AI", 'O');

        player cp;
        cp=p1;
        while(true){
            System.out.println(cp.name+" turn");
            cp.makemove();
            TicTacToemain.dispBoard();
            if(TicTacToemain.checkrowwin() || TicTacToemain.checkcolwin() || TicTacToemain.checkdigwin()){
                System.out.println(cp.name +" has won");
                break;
            }
            else if(TicTacToemain.checkdraw()){
                System.out.println("Game is draw");
                break;
            }
            else{
                if(cp==p1){
                cp=p2;
                }
                else{
                cp=p1;
                }
            }
        }
    }
}