package server_side;


/*
 * This class represents a position in a matrix
 */

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object p)
    {
        Position p1=(Position)p;
        return (this.x==p1.x && this.y==p1.y);
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    @Override
    public String toString()
    {
        return ""+x+" "+y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
