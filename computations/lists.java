package computations;

import java.util.ArrayList;

public class lists {
   public static void main(String[] args) {
       board boardp=new board();
       ArrayList<Integer> ints=new ArrayList<>();
       node [] e=new node[54];
       int a=5;
       int t=0;
       int c=0;
        for(int i=0;i<9;i++){
           for(int j=0;j<a;j++){
                c++;
               node n=new node(c,6);
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
            e[i]=new node(i+1,1);
            e[i].setValue(i+1);
            e[i].setexit(true);
        }
            boardp.setexit(e);
            System.out.println(boardp);
       boardp.getnode(4,3).setatom(true);
       boardp.getnode(4,2).setatom(true);

        boardp.linkboard();

        ints= boardp.iterate(17);



    }
}
