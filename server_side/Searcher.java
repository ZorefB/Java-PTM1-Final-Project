package server_side;

import java.util.LinkedList;

public interface Searcher<T> {

    public LinkedList<T> search(Searchable<T> s);
}

