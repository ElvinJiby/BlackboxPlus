package computations;

public class Node {
    private int value;
    private int next;
    private Node[] sides;
    private boolean isexit;
    private boolean atom;
    public Node(int value, int s){
        this.value=value;
        this.sides=new Node[s];
        this.atom=false;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
    public void setSides(int i, Node v){
        this.sides[i]=v;
    }
    public Node getSides(int i) {
        return this.sides[i];
    }
    public void setexit(boolean b){
        this.isexit=b;
    }
    public boolean isexit(){
        return this.isexit;
    }
    public void setnext(int n){
        this.next=n;
    }
    public int getnext(){
       return this.next;
    }
    public void setatom(boolean b){
        this.atom=b;
    }
    public boolean hasatom(){
        return this.atom;
    }


}
