package computations;

public class Lists {
    public Board createboard() {//creates a board for the game
        Board board = new Board();
        Node[] e = new Node[54];
        int a = 5;//set to 5 for the first set of horizontal hexagons and increases until 9 when it reaches to the middle then starts to decrease
        int t = 0;//used as a boolean value which specifies if an increases or decreases
        int c = 0;//used for each hexagonal nodes value
        for (int i = 0; i < 9; i++) {//iterates to create 9 rows
            for (int j = 0; j < a; j++) {//iterates to create 5 - 9 columns and then again to 9-5 columns
                c++;
                Node n = new Node(c, 6);//creates a hexagonal box with 6 sides
                n.setexit(false);
                n.setnext(7);
                board.setBoardindex(i, j, n);//puts node n in position (i,j)in the 2d array


            }
            if (a < 9 && t == 0) {//increases an until it reaches middle
                a++;

            } else {//decreases j after it reaches the middle
                a--;
                t = 1;
            }
        }
        for (int i = 0; i < 54; i++) {//sets the 54 exit numbers
            e[i] = new Node(i + 1, 1);
            e[i].setNodeValue(i + 1);
            e[i].setexit(true);//this helps us know that this node is an exit node
        }
        board.setexit(e);


        board.linkboard();//calls the link board function so all the boards are linked to each-other
        return board;
    }
}
