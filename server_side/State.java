package server_side;

import java.util.Objects;

/*
 * This class represents a specific state of a searchable
 */

public class State<T> {

    private T state; // T=Represented type
    private double cost;
    private State<T> cameFrom;

    public State(T state){
        this.state=state;
    }

    //Compares between two state
    public boolean equals(State<T> s) {
        return state.equals(s.state);
    }

    //Hashing a specific state
    @Override
    public int hashCode()
    {
        return Objects.hash(state.toString());
    }

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }
}
