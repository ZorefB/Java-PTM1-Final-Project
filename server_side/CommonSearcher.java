package server_side;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher <T> {

    protected PriorityQueue <State<T>> openList;
    private int evaluatedNodes;


    public abstract LinkedList<T> search(Searchable<T> s);//declaring the search method(just for visual)


    public CommonSearcher() {
        //setting comparator that compares by cost, so the priority queue organize by the lowest cost
        Comparator<State<T>> comp=(State<T> st1,State<T> st2)->(int)(st1.getCost()-st2.getCost());
        openList=new PriorityQueue<>(comp);
        evaluatedNodes=0;
    }

    protected void addToOpenList(State<T> state) {
        openList.add(state);
    }

    protected State<T> popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }

    //returns list of the state's description path from the initial state to the goal state
    protected LinkedList<T> backTrace(State<T> goalState, State<T> initialState) {

        LinkedList<T> result=new LinkedList<>();

        State<T> currentState=goalState;//points the current state to the goal state
        //in this loop we add all state's description to the linkedlist in the correct order(from initial to goal)
        while(currentState.getState()!=initialState.getState()){
            result.addFirst(currentState.getState());
            currentState=currentState.getCameFrom();
        }
        result.addFirst(initialState.getState());//we add finally the initial state
        return result;
    }

    //this function returns true if the open list contains a certain state
    protected boolean containsState(State<T> st)
    {
       PriorityQueue <State<T>> copyOpenList=new PriorityQueue<>(openList);
       while(!copyOpenList.isEmpty())
           if((copyOpenList.poll()).getState().equals(st.getState()))//first we remove a state, then we check if its equal to the given state
               return true;
       return false;

    }
}
