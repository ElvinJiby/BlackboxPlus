package computations;

import java.util.ArrayList;

public class Board {
    private Node[][] board = new Node[9][9];//the boxes in the hexagonal board
    private Node[] exit = new Node[54];//the numbers through which we pass the ray

    public Node[][] getboard() {
        return board;
    }//get method for the board

    public void setboard(Node[][] board) {
        this.board = board;
    }//set method for board

    public void setBoardindex(int i, int j, Node k) {
        board[i][j] = k;
    }//sets node k in board index i and j

    public void setexit(Node[] a) {
        this.exit = a;
    }//sets node a as exit

    public String toString() {//overriding the toString() method
        StringBuilder c = new StringBuilder();
        int a = 5;
        int t = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < a; j++) {
                c.append(board[i][j].getValue()).append(" ");
            }
            if (a < 9 && t == 0) {
                a++;

            } else {
                a--;
                t = 1;
            }
            c.append("\n");
        }
        return c.toString();
    }//to string method to print the whole board

    public Node getnode(int i, int j) {
        return this.board[i][j];
    }//returns the node at index i and j

    public void linkboard() {//links each node with all its surrounding 6 nodes or exit nodes

        int a = 5;//this variable used as the end value of j increases in the 2d array as the value of i increases until middle and starts to decrease as soon as it reaches the mid
        int t = 0;//used as a bollean value which is set to 1 as soon as it reaches the mid indicating that now we need to decrease the value of a if this is set to 1
        int ao = 2;//this is inisilazed to 2 in the begning but changes for all the external hexagonal box at j=0 with above the middle index
        int aon = 11;//this is inisilazed to 11 in the begning but changes for all the external hexagonal box at j=0 with below the middle index
        int atw = 52;//this is inisilazed to 52 in the begning but changes forall the external hexagonal box at i=0
        int ath = 43;//this is inisilazed to 43 in the begning but changes for all the external hexagonal box at j=a-1 which is  above the middle index
        int afo = 34;//this is inisilazed to 34 in the begning but changes for all the external hexagonal box at j=a-1 which is  below the middle index
        int afi = 20;//this is inisilazed to 20 in the begning but changes for all the external hexagonal box at i=8
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < a; j++) {
                if (j != 0 && j != a - 1 && i != 0 && i < 4) {//for all the hexagonal boxes which are inner boxes and are above the middle index it sets the 6 sides to point to other hexagonal box
                    this.board[i][j].setSides(0, board[i - 1][j - 1]);
                    this.board[i][j].setSides(1, board[i - 1][j]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j + 1]);
                    this.board[i][j].setSides(4, board[i + 1][j]);
                    this.board[i][j].setSides(5, board[i][j - 1]);
                } else if (j != 0 && j != a - 1 && i == 4) {//for all the hexagonal boxes which are inner boxes and are above on the middle index it sets the 6 sides to point to other hexagonal box
                    this.board[i][j].setSides(0, board[i - 1][j - 1]);
                    this.board[i][j].setSides(1, board[i - 1][j]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j]);
                    this.board[i][j].setSides(4, board[i + 1][j - 1]);
                    this.board[i][j].setSides(5, board[i][j - 1]);
                } else if (j != 0 && j != a - 1 && i != 8 && i > 4) {////for all the hexagonal boxes which are inner boxes and are below the middle index it sets the 6 sides to point to other hexagonal box
                    this.board[i][j].setSides(0, board[i - 1][j]);
                    this.board[i][j].setSides(1, board[i - 1][j + 1]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j]);
                    this.board[i][j].setSides(4, board[i + 1][j - 1]);
                    this.board[i][j].setSides(5, board[i][j - 1]);
                } else if (i == 0 && j == 0) {//sets all sides for the position (0,0)
                    this.board[i][j].setSides(0, exit[0]);
                    exit[0].setnext(3);//this sets the next value to number 3 so when the ray passes through this exit 4 we can say that it needs to go through 3 as its iterating.
                    exit[0].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(1, exit[53]);
                    exit[53].setnext(4);//this sets the next value to number 4 so when the ray passes through this exit 54 we can say that it needs to go through 4 as its iterating.
                    exit[53].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j + 1]);
                    this.board[i][j].setSides(4, board[i + 1][j]);
                    this.board[i][j].setSides(5, exit[1]);
                    exit[1].setnext(2);//this sets the next value to number 2 so when the ray passes through this exit 2 we can say that it needs to go through 2 as its iterating.
                    exit[1].setSides(0, this.board[i][j]);

                } else if (i == 4 && j == 0) {//sets all sides for the position (4,0)
                    this.board[i][j].setSides(0, exit[8]);
                    exit[8].setnext(3);//this sets the next value to number 3 so when the ray passes through this exit 9 we can say that it needs to go through 3 as its iterating.
                    exit[8].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(1, board[i - 1][j]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j]);
                    this.board[i][j].setSides(4, exit[10]);
                    exit[10].setnext(1);//this sets the next value to number 1 so when the ray passes through this exit 11 we can say that it needs to go through 1 as its iterating.
                    exit[10].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(5, exit[9]);
                    exit[9].setnext(2);//this sets the next value to number 2 so when the ray passes through this exit 10 we can say that it needs to go through 2 as its iterating.
                    exit[9].setSides(0, this.board[i][j]);

                } else if (i == 8 && j == 0) {////sets all sides for the position (8,0)
                    this.board[i][j].setSides(0, board[i - 1][j]);
                    this.board[i][j].setSides(1, board[i - 1][j + 1]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, exit[19]);
                    exit[19].setnext(0);//this sets the next value to number 0 so when the ray passes through this exit 20 we can say that it needs to go through 0 as its iterating.
                    exit[19].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(4, exit[18]);
                    exit[18].setnext(1);//this sets the next value to number 1 so when the ray passes through this exit 19 we can say that it needs to go through 1 as its iterating.
                    exit[18].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(5, exit[17]);
                    exit[17].setnext(2);//this sets the next value to number 2 so when the ray passes through this exit 18 we can say that it needs to go through 2 as its iterating.
                    exit[17].setSides(0, this.board[i][j]);

                } else if (i == 8 && j == a - 1) {////sets all sides for the position (8,a-1) where a is used to set the horizontal end index.
                    this.board[i][j].setSides(0, board[i - 1][j]);
                    this.board[i][j].setSides(1, board[i - 1][j + 1]);
                    this.board[i][j].setSides(2, exit[28]);
                    exit[28].setnext(5);//this sets the next value to number 5 so when the ray passes through this exit 29 we can say that it needs to go through 5 as its iterating.
                    exit[28].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(3, exit[27]);
                    exit[27].setnext(0);//this sets the next value to number 0 so when the ray passes through this exit 28 we can say that it needs to go through 0 as its iterating.
                    exit[27].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(4, exit[26]);
                    exit[26].setnext(1);//this sets the next value to number 1 so when the ray passes through this exit 27 we can say that it needs to go through 1 as its iterating.
                    exit[26].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(5, board[i][j - 1]);

                } else if (i == 4 && j == a - 1) {////sets all sides for the position (4,a-1) where a is used to set the horizontal end index.
                    this.board[i][j].setSides(0, board[i - 1][j - 1]);
                    this.board[i][j].setSides(1, exit[37]);
                    exit[37].setnext(4);//this sets the next value to number 4 so when the ray passes through this exit 38 we can say that it needs to go through 4 as its iterating.
                    exit[37].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(2, exit[36]);
                    exit[36].setnext(5);////this sets the next value to number 5 so when the ray passes through this exit 37 we can say that it needs to go through 5 as its iterating.
                    exit[36].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(3, exit[35]);
                    exit[35].setnext(0);//this sets the next value to number 0 so when the ray passes through this exit 35 we can say that it needs to go through 0 as its iterating.
                    exit[35].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(4, board[i + 1][j - 1]);
                    this.board[i][j].setSides(5, board[i][j - 1]);

                } else if (i == 0 && j == a - 1) {//sets all sides for the position (0,a-1) where a is used to set the horizontal end index.
                    this.board[i][j].setSides(0, exit[46]);
                    exit[46].setnext(3);//this sets the next value to number 3 so when the ray passes through this exit 47 we can say that it needs to go through 3 as its iterating.
                    exit[46].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(1, exit[45]);
                    exit[45].setnext(4);//this sets the next value to number 4 so when the ray passes through this exit 46 we can say that it needs to go through 4 as its iterating.
                    exit[45].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(2, exit[44]);
                    exit[44].setnext(5);//this sets the next value to number 5 so when the ray passes through this exit 45 we can say that it needs to go through 5 as its iterating.
                    exit[44].setSides(0, this.board[i][j]);
                    this.board[i][j].setSides(3, board[i + 1][j + 1]);
                    this.board[i][j].setSides(4, board[i + 1][j]);
                    this.board[i][j].setSides(5, board[i][j - 1]);

                }
                else if (i < 4 && j == 0) {//all the external hexagonal box at j=0 with above the middle index
                    this.board[i][j].setSides(0, exit[ao]);
                    exit[ao].setnext(3);
                    exit[ao].setSides(0, this.board[i][j]);
                    ao = ao + 1;//increases the value of ao therfore ao points to the other exit number
                    this.board[i][j].setSides(1, board[i - 1][j]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j + 1]);
                    this.board[i][j].setSides(4, board[i + 1][j]);
                    this.board[i][j].setSides(5, exit[ao]);
                    exit[ao].setnext(2);
                    exit[ao].setSides(0, this.board[i][j]);
                    ao = ao + 1;//increases the value of ao therfore ao points to the other exit number
                } else if (i > 4 && j == 0) {////all the external hexagonal box at j=0 with below the middle index
                    this.board[i][j].setSides(0, board[i - 1][j]);

                    this.board[i][j].setSides(1, board[i - 1][j + 1]);
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j]);
                    this.board[i][j].setSides(5, exit[aon]);
                    exit[aon].setnext(2);
                    exit[aon].setSides(0, this.board[i][j]);
                    aon++;//increases the value of aon therfore a0 points to the other exit number
                    this.board[i][j].setSides(4, exit[aon]);
                    exit[aon].setnext(1);
                    exit[aon].setSides(0, this.board[i][j]);
                    aon++;
                } else if (i == 0) {////all the external hexagonal box at i=0
                    this.board[i][j].setSides(0, exit[atw]);
                    exit[atw].setnext(3);
                    exit[atw].setSides(0, this.board[i][j]);
                    atw = atw - 1;//decreases the value of atw therfore atw points to the other exit number
                    this.board[i][j].setSides(1, exit[atw]);
                    exit[atw].setnext(4);
                    exit[atw].setSides(0, this.board[i][j]);
                    atw = atw - 1;//decreases the value of atw therfore atw points to the other exit number
                    this.board[i][j].setSides(2, board[i][j + 1]);
                    this.board[i][j].setSides(3, board[i + 1][j + 1]);
                    this.board[i][j].setSides(4, board[i + 1][j]);
                    this.board[i][j].setSides(5, board[i][j - 1]);
                } else if (i < 4 && j == a - 1) {////all the external hexagonal box at j=a-1 which is  above the middle index
                    this.board[i][j].setSides(0, board[i - 1][j - 1]);

                    this.board[i][j].setSides(1, exit[ath]);
                    exit[ath].setnext(4);
                    exit[ath].setSides(0, this.board[i][j]);
                    ath = ath - 1;//decreases the value of ath therfore ath points to the other exit number
                    this.board[i][j].setSides(2, exit[ath]);
                    exit[ath].setnext(5);
                    exit[ath].setSides(0, this.board[i][j]);
                    ath = ath - 1;//decreases the value of ath therfore ath points to the other exit number
                    this.board[i][j].setSides(3, board[i + 1][j + 1]);
                    this.board[i][j].setSides(4, board[i + 1][j]);
                    this.board[i][j].setSides(5, board[i][j - 1]);
                } else if (i > 4 && j == a - 1) {//all the external hexagonal box at j=a-1 which is  below the middle index
                    this.board[i][j].setSides(0, board[i - 1][j]);

                    this.board[i][j].setSides(2, exit[afo]);
                    exit[afo].setnext(5);
                    exit[afo].setSides(0, this.board[i][j]);
                    afo = afo - 1;//decreases the value of afo therfore afo points to the other exit number
                    this.board[i][j].setSides(3, exit[afo]);
                    exit[afo].setnext(0);
                    exit[afo].setSides(0, this.board[i][j]);
                    afo = afo - 1;//decreases the value of afo therfore afo points to the other exit number

                    this.board[i][j].setSides(1, board[i - 1][j + 1]);
                    this.board[i][j].setSides(4, board[i + 1][j - 1]);
                    this.board[i][j].setSides(5, board[i][j - 1]);
                } else if (i == 8) {//all the external hexagonal box at i=8

                    this.board[i][j].setSides(0, board[i - 1][j]);

                    this.board[i][j].setSides(1, board[i - 1][j + 1]);

                    this.board[i][j].setSides(2, board[i][j + 1]);

                    this.board[i][j].setSides(4, exit[afi]);
                    exit[afi].setnext(1);
                    exit[afi].setSides(0, this.board[i][j]);
                    afi = afi + 1;//increases the value of afi therfore afi points to the other exit number
                    this.board[i][j].setSides(3, exit[afi]);
                    exit[afi].setnext(0);
                    exit[afi].setSides(0, this.board[i][j]);
                    afi = afi + 1;//increases the value of afi therfore afi points to the other exit number
                    this.board[i][j].setSides(5, board[i][j - 1]);
                }
            }
            if (a < 9 && t == 0) {//increase a until it reaches the mid
                a++;

            } else {//start deacreasing a as it has now reached the mid and also set t to 1 therfore it doesn't go to the above condition
                a--;
                t = 1;
            }
        }

    }

    public ArrayList<Integer> iterate(int a) {
        ArrayList<Integer> ints = new ArrayList<>();
        Node head = exit[a - 1];//sets the head as the exit
        Node next = head.getSides(0);//gets the node pointed by the head
        int n = head.getnext();//this points to the next value in the path which is changed continuesly in the code to cause deflections or reflections.
        ints.add(head.getValue());//adds the head to the array list of the path
       // System.out.print(head.getValue() + "->");
        if (next.hasatom()) {//ray is obsorbed
           // System.out.print("Ray absorbed");
            ints.add(next.getValue());//adds the value to the array list
            ints.add(-1);//also adds -1 indicating the ray has been absorbrd
            return ints;//returns the path and stops the function
        }

        while (!next.isexit()) {//runs until it reaches another exit node
            ArrayList<Integer> atomp = new ArrayList<>();// array list is used to get the nodes with the atoms in it
            for (int i = 0; i < 6; i++) {
                if (next.getSides(i).hasatom()) {//if the side has an atom stores the index of the side in the array list
                    atomp.add(i);

                }
            }
            if (atomp.size() == 1 && next.getSides(n).hasatom()) {//condition where there is only one atom in the next corresponding sides and ray gets absorbed.
                //System.out.print("Ray absorbed");
                ints.add(next.getValue());//adds the value to the array list
                ints.add(next.getSides(n).getValue());//adds the value of the node with the atom to the array list
                ints.add(-1);//indicates the ray is absorbed
                return ints;
            } else if (atomp.size() == 1) {//condition where there is only one atom, but it needs to deflect
                if ((atomp.getFirst() == (n + 1) % 6)) {//decreases the value of the next side it needs to go to by one to deflect the ray
                    n = (n - 1 + 6) % 6;
                } else if ((atomp.getFirst() == (n - 1 + 6) % 6)) {//increases the value of the next side it needs to go to by one to deflect the ray
                    n = (n + 1) % 6;
                } else if ((atomp.getFirst() == (n + 2) % 6) || (atomp.getFirst() == (n - 2 + 6) % 6)) {//causes the ray to be reflected back
                    n = (n + 3) % 6;
                }
            } else if (atomp.size() == 2) {//condition checks for two atoms
                if ((((n - 2 + 6) % 6) == atomp.get(0) //checks if the two element in the atomp array is in those positions and if it is then reflect completely
                        || ((n - 2 + 6) % 6) == atomp.get(1))
                        || (((n + 2) % 6) == atomp.get(0)
                        || ((n + 2) % 6) == atomp.get(1))) {
                    n = (n + 3) % 6;//checks if one of the atoms causes the atoms to get completely reflected
                } else if ((((n + 1) % 6) == atomp.get(0) || ((n + 1) % 6) == atomp.get(1)) //checks if the atoms are in the positions and decreases n to deflect the ray
                        && (((n) % 6) == atomp.get(0) || ((n) % 6) == atomp.get(1))) {
                    n = (n - 2 + 6) % 6;//decreases the value if n by 2 causing the ray to get deflected after bouncing through the 2 atoms
                } else if ((((n - 1 + 6) % 6) == atomp.get(0) || ((n - 1 + 6) % 6) == atomp.get(1)) //checks if the two atoms are in those positions to increase the value of n to cause deflection.
                        && (((n) % 6) == atomp.get(0) || ((n) % 6) == atomp.get(1))) {
                    n = (n + 2) % 6;//increases the value of n by 2 causing the ray to get deflected after bouncing through the 2 atoms.
                } else if ((((n - 1 + 6) % 6) == atomp.get(0) || ((n - 1 + 6) % 6) == atomp.get(1))
                        && (((n + 1) % 6) == atomp.get(0) || ((n + 1) % 6) == atomp.get(1))) {//checks if the atoms are in those positions to cause complete reflection
                    n = (n + 3) % 6;//checks if the ray should bounce in a way to cause complete reflection
                }
            } else if (atomp.size() > 3) {//if there are 3 or more atoms it deflects completely.
                n = (n + 3) % 6;
            }
            //System.out.print(next.getValue() + "->");
            ints.add(next.getValue());//adds the next to the list of paths

            next = next.getSides(n);//change the value of next therfore getting the value after getting deflected or reflected


        }
        //System.out.print(next.getValue());
        //System.out.println();
        ints.add(next.getValue());//adds next value to the array list

        return ints;//returns the array list of the whole path
    }

    public void setrandom(Board boardp, int r) {//function is used to make the hexagon with value r set atom to true.
        int a = 5;//a is the same as before which gives the end value of j
        int t = 0;//this is also used as a boolean increasing the value of a when its 0 and decreasing the value of a when its 1

        for (int i = 0; i < 9; i++) {//goes through the whole board to search for entry r
            for (int j = 0; j < a; j++) {
                if (boardp.getnode(i, j).getValue() == r) {
                    boardp.getnode(i, j).setatom(true);//sets the node to true when it finds r
                }


            }
            if (a < 9 && t == 0) {
                a++;

            } else {
                a--;
                t = 1;
            }
        }
    }
}
