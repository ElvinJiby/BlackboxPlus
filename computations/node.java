package computations;

public class node{
    private int value;
    private node[] sides;
    public node(int value){
        this.value=value;
        this.sides=new node[6];
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
    public void setSides(int i,node v){
        this.sides[i]=v;
    }

}
