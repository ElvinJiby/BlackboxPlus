package computations;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testboard {
    @Test
    void testpath(){
        board boardp=new board();
        node [] e=new node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                node n=new node(i+j+1,6);
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
        boardp.linkboard();
        ints= boardp.iterate(44);
        int u=ints.get(ints.size()-1);
        assertEquals(21, u);

        assertEquals(21, ints.getLast());
    }
    @Test
    void testoneatomdeflection(){
        board boardp=new board();
        node [] e=new node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                node n=new node(i+j+1,6);
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
        boardp.getnode(3,4).setatom(true);
        boardp.linkboard();
        ints= boardp.iterate(44);
        int u=ints.get(ints.size()-1);
        assertEquals(32, u);
        ints= boardp.iterate(32);
        assertEquals(44, ints.getLast());
    }
    @Test
    void testtwoatomdeflection(){
        board boardp=new board();
        node [] e=new node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                node n=new node(i+j+1,6);
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
        boardp.getnode(2,1).setatom(true);
        boardp.getnode(4,2).setatom(true);
        boardp.linkboard();
        ints= boardp.iterate(8);
        int u=ints.get(ints.size()-1);
        assertEquals(8, u);

    }
}

