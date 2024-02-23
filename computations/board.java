package computations;

import java.util.ArrayList;

public class board {
    private node[][] board=new node[9][9];
    private node[] exit=new node[53];
    public node[][]getboard(){
        return board;
    }

    public void setboard(node[][] board) {
        this.board = board;
    }
    public void setBoardindex(int i,int j,node k){
        board[i][j]=k;
    }
    public String toString(){//overriding the toString() method
        String c="";
        int a=5;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){
                c=c+board[i][j].getValue()+" ";
            }
            if(a<9){
                a++;
            }
            else{
                a--;
            }
            c = c + "\n";
        }
        return c;
    }
    public node getnode(int i,int j){
        return this.board[i][j];
    }
    public void linkboard(){
        int a=5;
        int ass=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){
                if(j!=0 && j!=a-1 &&i!=0 && i<4){
                    this.board[i][j].setSides(0,board[i-1][j-1]);
                    this.board[i][j].setSides(1,board[i-1][j]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j]);
                    this.board[i][j].setSides(5,board[i][j-1]);
                } else if (j!=0 && j!=a-1   && i==4 ) {
                    this.board[i][j].setSides(0,board[i-1][j-1]);
                    this.board[i][j].setSides(1,board[i-1][j]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j]);
                    this.board[i][j].setSides(4,board[i+1][j-1]);
                    this.board[i][j].setSides(5,board[i][j-1]);
                }else if (j!=0 && j!=a-1 && i!=8  && i>4 ) {
                    this.board[i][j].setSides(0,board[i-1][j]);
                    this.board[i][j].setSides(1,board[i-1][j+1]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j]);
                    this.board[i][j].setSides(4,board[i+1][j-1]);
                    this.board[i][j].setSides(5,board[i][j-1]);
                } else if (i==0 && j==0) {
                    this.board[i][j].setSides(0,exit[0]);
                    this.board[i][j].setSides(1,exit[53]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j]);
                    this.board[i][j].setSides(5,exit[1]);

                }
            }
            if(a<9){
                a++;
            }
            else{
                a--;
            }
        }
    }
}
