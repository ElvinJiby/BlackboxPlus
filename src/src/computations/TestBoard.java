package computations;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard {
    @Test
    void testpath(){
        Board boardp=new Board();
        Node[] e=new Node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                Node n=new Node(i+j+1,6);
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
        boardp.linkboard();
        ints= boardp.iterate(44);
        int u=ints.get(ints.size()-1);
        assertEquals(21, u);

        assertEquals(21, ints.getLast());
    }
    @Test
    void testoneatomdeflection(){
        Board boardp=new Board();
        Node[] e=new Node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                Node n=new Node(i+j+1,6);
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
        Board boardp=new Board();
        Node[] e=new Node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                Node n=new Node(i+j+1,6);
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
        boardp.getnode(2,1).setatom(true);
        boardp.getnode(4,2).setatom(true);
        boardp.linkboard();
        ints= boardp.iterate(8);
        int u=ints.get(ints.size()-1);
        assertEquals(8, u);

    }
    @Test
    void testfig3(){
        Board boardp=new Board();
        Node[] e=new Node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                Node n=new Node(i+j+1,6);
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
        boardp.getnode(2,1).setatom(true);
        boardp.getnode(4,2).setatom(true);
        boardp.getnode(4,3).setatom(true);
        boardp.getnode(3,3).setatom(true);
        boardp.getnode(3,4).setatom(true);
        boardp.getnode(3,7).setatom(true);
        boardp.linkboard();
        ints= boardp.iterate(24);
        int u=ints.get(ints.size()-1);
        assertEquals(17, u);

        ints= boardp.iterate(8);
        u=ints.get(ints.size()-1);
        assertEquals(8, u);

        ints= boardp.iterate(28);
        u=ints.get(ints.size()-1);
        assertEquals(28, u);

        ints= boardp.iterate(44);
        u=ints.get(ints.size()-1);
        assertEquals(32, u);

        ints= boardp.iterate(41);
        u=ints.get(ints.size()-1);
        assertEquals(41, u);

    }
    @Test
    void testfig4(){
        Board boardp=new Board();
        Node[] e=new Node[54];
        ArrayList<Integer> ints=new ArrayList<>();
        int a=5;
        int t=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                Node n=new Node(i+j+1,6);
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
        boardp.getnode(2,2).setatom(true);
        boardp.getnode(2,4).setatom(true);
        boardp.getnode(4,5).setatom(true);
        boardp.getnode(4,6).setatom(true);
        boardp.getnode(7,1).setatom(true);
        boardp.getnode(8,3).setatom(true);
        boardp.linkboard();
        ints= boardp.iterate(14);
        int u=ints.get(ints.size()-1);
        assertEquals(14, u);

        ints= boardp.iterate(53);
        u=ints.get(ints.size()-1);
        assertEquals(48, u);

        ints= boardp.iterate(30);
        u=ints.get(ints.size()-1);
        assertEquals(-1, u);
    }
}

