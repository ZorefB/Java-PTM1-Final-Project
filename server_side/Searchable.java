package server_side;

import java.util.Stack;

/*
 * Searchable has three methods that define it,
 * and make it a searchable problem
 */

public interface Searchable<T> {

   public State<T> getInitialState();
   public State<T> getGoalState();
   public Stack<State<T>> getAllPossibleStates(State<T> s);

}
