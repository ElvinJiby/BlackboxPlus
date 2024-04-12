package computations;

import java.util.ArrayList;

public class Board {
    private Node[][] board=new Node[9][9];
    private Node[] exit=new Node[54];
    public Node[][]getboard(){
        return board;
    }

    public void setboard(Node[][] board) {
        this.board = board;
    }
    public void setBoardindex(int i, int j, Node k){
        board[i][j]=k;
    }
    public void setexit(Node[] a) {
        this.exit = a;
    }

    public String toString(){//overriding the toString() method
        String c="";
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){
                c=c+board[i][j].getValue()+" ";
            }
            if(a<9 && t==0){
                a++;

            }
            else{
                a--;
                t=1;
            }
            c = c + "\n";
        }
        return c;
    }
    public Node getnode(int i, int j){
        return this.board[i][j];
    }
    public void linkboard(){

        int a=5;
        int t=0;
        int ao=2;
        int aon=11;
        int atw=52;
        int ath=43;
        int afo=34;
        int afi=20;
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
                    exit[0].setnext(3);
                    exit[0].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(1,exit[53]);
                    exit[53].setnext(4);
                    exit[53].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j]);
                    this.board[i][j].setSides(5,exit[1]);
                    exit[1].setnext(2);
                    exit[1].setSides(0,this.board[i][j]);

                }else if (i==4 && j==0) {
                    this.board[i][j].setSides(0,exit[8]);
                    exit[8].setnext(3);
                    exit[8].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(1,board[i-1][j]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j]);
                    this.board[i][j].setSides(4,exit[10]);
                    exit[10].setnext(1);
                    exit[10].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(5,exit[9]);
                    exit[9].setnext(2);
                    exit[9].setSides(0,this.board[i][j]);

                }else if (i==8 && j==0) {
                    this.board[i][j].setSides(0,board[i-1][j]);
                    this.board[i][j].setSides(1,board[i-1][j+1]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,exit[19]);
                    exit[19].setnext(0);
                    exit[19].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(4,exit[18]);
                    exit[18].setnext(1);
                    exit[18].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(5,exit[17]);
                    exit[17].setnext(2);
                    exit[17].setSides(0,this.board[i][j]);

                }
                else if (i==8 && j==a-1) {
                    this.board[i][j].setSides(0,board[i-1][j]);
                    this.board[i][j].setSides(1,board[i-1][j+1]);
                    this.board[i][j].setSides(2,exit[28]);
                    exit[28].setnext(5);
                    exit[28].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(3,exit[27]);
                    exit[27].setnext(0);
                    exit[27].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(4,exit[26]);
                    exit[26].setnext(1);
                    exit[26].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(5,board[i][j-1]);

                }else if (i==4 && j==a-1) {
                    this.board[i][j].setSides(0,board[i-1][j-1]);
                    this.board[i][j].setSides(1,exit[37]);
                    exit[37].setnext(4);
                    exit[37].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(2,exit[36]);
                    exit[36].setnext(5);
                    exit[36].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(3,exit[35]);
                    exit[35].setnext(0);
                    exit[35].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(4,board[i+1][j-1]);
                    this.board[i][j].setSides(5,board[i][j-1]);

                }
                else if (i==0 && j==a-1) {
                    this.board[i][j].setSides(0,exit[46]);
                    exit[46].setnext(3);
                    exit[46].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(1,exit[45]);
                    exit[45].setnext(4);
                    exit[45].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(2,exit[44]);
                    exit[44].setnext(5);
                    exit[44].setSides(0,this.board[i][j]);
                    this.board[i][j].setSides(3,board[i+1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j]);
                    this.board[i][j].setSides(5,board[i][j-1]);

                }
                else if(i<4 && j==0){
                    this.board[i][j].setSides(0,exit[ao]);
                    exit[ao].setnext(3);
                    exit[ao].setSides(0,this.board[i][j]);
                    ao=ao+1;
                    this.board[i][j].setSides(1,board[i-1][j]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j]);
                    this.board[i][j].setSides(5,exit[ao]);
                    exit[ao].setnext(2);
                    exit[ao].setSides(0,this.board[i][j]);
                    ao=ao+1;
                }
                else if(i>4 && j==0){
                    this.board[i][j].setSides(0,board[i-1][j]);

                    this.board[i][j].setSides(1,board[i-1][j+1]);
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j]);
                    this.board[i][j].setSides(5,exit[aon]);
                    exit[aon].setnext(2);
                    exit[aon].setSides(0,this.board[i][j]);
                    aon++;
                    this.board[i][j].setSides(4,exit[aon]);
                    exit[aon].setnext(1);
                    exit[aon].setSides(0,this.board[i][j]);
                    aon++;
                } else if (i==0) {
                    this.board[i][j].setSides(0,exit[atw]);
                    exit[atw].setnext(3);
                    exit[atw].setSides(0,this.board[i][j]);
                    atw=atw-1;
                    this.board[i][j].setSides(1,exit[atw]);
                    exit[atw].setnext(4);
                    exit[atw].setSides(0,this.board[i][j]);
                    atw=atw-1;
                    this.board[i][j].setSides(2,board[i][j+1]);
                    this.board[i][j].setSides(3,board[i+1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j]);
                    this.board[i][j].setSides(5,board[i][j-1]);
                }
                else if(i<4 && j==a-1){
                    this.board[i][j].setSides(0,board[i-1][j-1]);

                    this.board[i][j].setSides(1,exit[ath]);
                    exit[ath].setnext(4);
                    exit[ath].setSides(0,this.board[i][j]);
                    ath=ath-1;
                    this.board[i][j].setSides(2,exit[ath]);
                    exit[ath].setnext(5);
                    exit[ath].setSides(0,this.board[i][j]);
                    ath=ath-1;
                    this.board[i][j].setSides(3,board[i+1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j]);
                    this.board[i][j].setSides(5,board[i][j-1]);
                }
                else if(i>4 && j==a-1){
                    this.board[i][j].setSides(0,board[i-1][j]);

                    this.board[i][j].setSides(2,exit[afo]);
                    exit[afo].setnext(5);
                    exit[afo].setSides(0,this.board[i][j]);
                    afo=afo-1;
                    this.board[i][j].setSides(3,exit[afo]);
                    exit[afo].setnext(0);
                    exit[afo].setSides(0,this.board[i][j]);
                    afo=afo-1;

                    this.board[i][j].setSides(1,board[i-1][j+1]);
                    this.board[i][j].setSides(4,board[i+1][j-1]);
                    this.board[i][j].setSides(5,board[i][j-1]);
                }
                else if(i==8){

                    this.board[i][j].setSides(0,board[i-1][j]);

                    this.board[i][j].setSides(1,board[i-1][j+1]);

                    this.board[i][j].setSides(2,board[i][j+1]);

                    this.board[i][j].setSides(4,exit[afi]);
                    exit[afi].setnext(1);
                    exit[afi].setSides(0,this.board[i][j]);
                    afi=afi+1;
                    this.board[i][j].setSides(3,exit[afi]);
                    exit[afi].setnext(0);
                    exit[afi].setSides(0,this.board[i][j]);
                    afi=afi+1;
                    this.board[i][j].setSides(5,board[i][j-1]);
                }
            }
            if(a<9 && t==0){
                a++;

            }
            else{
                a--;
                t=1;
            }
        }

    }
    public ArrayList<Integer> iterate(int a){
        ArrayList<Integer> ints=new ArrayList<>();
        Node head=exit[a-1];
        Node next=head.getSides(0);
        int n=head.getnext();
        ints.add(head.getValue());
        System.out.print(head.getValue()+"->");
        if(next.hasatom()){
            System.out.print("Ray absorbed");
            ints.add(next.getValue());
            ints.add(-1);
            return ints;
        }

        while (next.isexit()!=true ){
            ArrayList<Integer> atomp= new ArrayList<Integer>();
           for(int i = 0; i<6; i++){
               if(next.getSides(i).hasatom()==true){
                   atomp.add(i);
                   System.out.println(atomp);

                }
               }
            if(atomp.size()==1 && next.getSides(n).hasatom()){
                System.out.print("Ray absorbed");
                ints.add(next.getValue());
                ints.add(next.getSides(n).getValue());
                ints.add(-1);
                return ints;
            } else if (atomp.size()==1) {
                if((atomp.getFirst()==(n+1)%6) ){
                    n=(n-1+6)%6;
                } else if ((atomp.getFirst()==(n-1+6)%6) ) {
                    n=(n+1)%6;
                } else if ((atomp.getFirst()==(n+2)%6) || (atomp.getFirst()==(n-2+6)%6)) {
                    n=(n+3)%6;
                }
            } else if (atomp.size()==2) {
                if((((n + 1) % 6) == atomp.get(0) || ((n + 1) % 6) ==atomp.get(1)) && (((n ) % 6) == atomp.get(0) || ((n ) % 6) ==atomp.get(1))){
                    n=(n-2+6)%6;
                } else if ((((n - 1+6) % 6) == atomp.get(0) || ((n - 1+6) % 6) ==atomp.get(1)) && (((n ) % 6) == atomp.get(0) || ((n ) % 6) ==atomp.get(1))) {
                    n=(n+2)%6;
                }else if ((((n - 1+6) % 6) == atomp.get(0) || ((n - 1+6) % 6) ==atomp.get(1)) && (((n + 1) % 6) == atomp.get(0) || ((n + 1) % 6) ==atomp.get(1))) {
                    n=(n+3)%6;
                }
            } else if (atomp.size()==3) {
                n=(n+3)%6;
            }
            System.out.print(next.getValue()+"->");
            ints.add(next.getValue());

            next=next.getSides(n);



            }
        System.out.print(next.getValue());
        System.out.println();
        ints.add(next.getValue());

    return ints;
    }
    public void setrandom(Board boardp,int r){
        int a=5;
        int t=0;

        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){
                if(boardp.getnode(i,j).getValue()==r){
                    boardp.getnode(i,j).setatom(true);
                }


            }
            if(a<9 && t==0){
                a++;

            }
            else{
                a--;
                t=1;
            }
        }
    }
}
