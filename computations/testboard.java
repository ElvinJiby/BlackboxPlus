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

        assertEquals(34, u);
    }
}

