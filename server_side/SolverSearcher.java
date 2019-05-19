package server_side;

import java.util.LinkedList;

/*
 * This class implements the Object Adapter design pattern.
 * The adapting is between a solver and a searcher,
 * which means in a given searcher, the solving method will be
 * implemented using the search method of the searcher. 
 */

public class SolverSearcher<T>  implements Solver<Searchable<T>,LinkedList<T>> {

    Searcher<T> s;

    public SolverSearcher(Searcher<T> s) {
        this.s = s;
    }

    @Override
    public LinkedList<T> solve(Searchable<T> p) {
        return s.search(p);
    }


}
