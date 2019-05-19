package server_side;


import java.util.Stack;

/*
 * This class is a specific implementation of a searchable
 *  related to the matrix problem 
 * 
 * */

public class MatrixSearchable implements  Searchable<Position> {

    private double[][] matrixSearchable;
    private int rows;
    private int columns;

    private State<Position> initialState;
    private State<Position> goalState;


    public MatrixSearchable(double[][] matrixSearchable, Position initialPosition, Position goalPosition) {
        this.matrixSearchable = matrixSearchable;
        rows=matrixSearchable.length;
        columns=matrixSearchable[0].length;

        initialState=new State<>(initialPosition);
        initialState.setCost(matrixSearchable[initialPosition.getX()][initialPosition.getY()]);

        goalState=new State<>(goalPosition);

    }

    private Stack<Position> getNeighbors(Position p) {
       Stack<Position> s=new Stack<>();
        Position up;
        Position down;
        Position right;
        Position left;

        if(p.getX()>0) {
            up = new Position(p.getX() - 1, p.getY());
            s.push(up);
        }
        if(p.getX()<rows-1){
            down=new Position(p.getX()+1, p.getY());
            s.push(down);
        }

        if(p.getY()>0) {
            left = new Position(p.getX(), p.getY()-1);
            s.push(left);
        }
        if(p.getY()<columns-1){
            right=new Position(p.getX(), p.getY()+1);
            s.push(right);
        }
        return s;

    }

    @Override
    public State<Position> getInitialState() {
       return initialState;
    }

    @Override
    public State<Position> getGoalState() {
        return goalState;
    }

    @Override
    public Stack<State<Position>> getAllPossibleStates(State<Position> s) {
       Stack<State<Position>> stateStack=new Stack<>();
       Stack<Position> PositionStack=getNeighbors(s.getState());
       Position pos;
       State<Position> st;
       while(!PositionStack.isEmpty())
       {
           pos=PositionStack.pop();
           st=new State<Position>(pos);
           st.setCost(s.getCost()+matrixSearchable[pos.getX()][pos.getY()]);
           st.setCameFrom(s);
           stateStack.push(st);
       }
       return stateStack;

    }


}

