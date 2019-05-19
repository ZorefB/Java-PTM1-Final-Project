package server_side;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;


//this algrithm finds the shortest path from initial state to goal state
public class BestFirstSearch<T> extends CommonSearcher<T> {


    @Override
    public LinkedList<T> search(Searchable<T> s) {

        addToOpenList(s.getInitialState());//initializing
        HashSet<State<T>> closedSet = new HashSet<>();
        HashSet<T> closedSetAid=new HashSet<>();//meant to represent the states in the closedSet
        State<T> temp;
        Stack<State<T>> successors;

        while (!openList.isEmpty()) {
            State<T> n = popOpenList();//pop the state with the least cost using the priority queue
            closedSet.add(n);//put it in the close set
            closedSetAid.add(n.getState());//simultaneously put in the aid set

            if (n.equals(s.getGoalState())) {
                return backTrace(n, s.getInitialState());
            }

           successors = s.getAllPossibleStates(n);//save the successors of n in a stack
            for (State<T> state : successors) {
                if (!closedSetAid.contains(state.getState()) && !containsState(state) ){//if the state is not in the closed set and not in the openlist
                        state.setCameFrom(n);
                        addToOpenList(state);
                    }

                    else if(!closedSetAid.contains(state.getState())){//if the state in the openList

                    LinkedList<State<T>> assist=new LinkedList<>();//an assist linked list to save the open list elements

                    while(true){
                      temp=popOpenList();
                      if(temp.getState().equals(state.getState())){//if there is a match between the two states
                          if(state.getCost()<temp.getCost())//if the new state has better cost then the old state, replace them in the open list
                              addToOpenList(state);
                          else addToOpenList(temp);
                          break;
                      }
                        assist.add(temp);//if the temp state is not the same state, add it to assist so it wont go to lost
                    }
                        openList.addAll(assist);//add the leftovers back to openList
                }
            }
        }
        return null;
    }

}

