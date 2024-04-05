package computations;

import java.util.ArrayList;

public class Lists {
    public Board createboard(){
        Board boardp=new Board();
        ArrayList<Integer> ints=new ArrayList<>();
        Node[] e=new Node[54];
        int a=5;
        int t=0;
        int c=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){
                c++;
                Node n=new Node(c,6);
                n.setexit(false);
                n.setnext(7);
                boardp.setBoardindex(i,j,n);


            }
            if(a<9 && t==0){
                a++;

            }
            else{
                a--;
                t=1;
            }
        }
        for(int i=0;i<54;i++){
            e[i]=new Node(i+1,1);
            e[i].setValue(i+1);
            e[i].setexit(true);
        }
        boardp.setexit(e);
        System.out.println(boardp);


        boardp.linkboard();
        return boardp;
    }
   public static void main(String[] args) {
       Board boardp=new Board();
       ArrayList<Integer> ints=new ArrayList<>();
       Lists c=new Lists();
       boardp=c.createboard();
       boardp.getnode(4,3).setatom(true);
       boardp.getnode(4,2).setatom(true);



        ints= boardp.iterate(17);



    }
}
