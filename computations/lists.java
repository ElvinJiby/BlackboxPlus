package computations;
import javax.swing.*;
import java.util.Iterator;
public class lists {
    public static void main(String[] args) {
        board boardp=new board();
        int a=5;
        for(int i=0;i<9;i++){
            for(int j=0;j<a;j++){

                node n=new node(i+j);
                boardp.setBoardindex(i,j,n);
                
            }
            if(a<9){
                a++;
            }
            else{
                a--;
            }
        }

            System.out.println(boardp);

    }
}
