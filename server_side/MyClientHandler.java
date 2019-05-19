package server_side;

import java.io.*;
import java.util.LinkedList;


/*
 * This class is a client handler that can handle the matrix problem
 * */

public class MyClientHandler implements ClientHandler{

    private Solver<Searchable<Position>, LinkedList<Position>> solver;
    private CacheManager<Searchable<Position>, String> cm;

    public MyClientHandler(CacheManager<Searchable<Position>, String> cm,  Solver<Searchable<Position>, LinkedList<Position>> solver) {
        this.cm=cm;
        this.solver=solver;
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {

        BufferedReader inputFromClient=new BufferedReader(new InputStreamReader(inFromClient));
        PrintWriter outputToClient=new PrintWriter(outToClient);

        LinkedList<String> stringList=new LinkedList<>();
        String initialByString;
        String goalByString;
        double[][] theMatrix;
        Position initialPosition;
        Position goalPosition;
        MatrixSearchable aSearchableMatrix;
        LinkedList<Position> solutionByList;
        String solutionByString;

        try {
            String line;
            while (!(line = inputFromClient.readLine()).equals("end")){
                stringList.add(line);;
            }
            initialByString=inputFromClient.readLine();
            goalByString=inputFromClient.readLine();

            theMatrix=stringsToMatrix(stringList);
            initialPosition=stringToPosition(initialByString);
            goalPosition=stringToPosition(goalByString);

            aSearchableMatrix=new MatrixSearchable(theMatrix,initialPosition, goalPosition);



            if(cm.isExist(aSearchableMatrix)) {
                System.out.println("Getting solution from the file");
                solutionByString=cm.getSolution(aSearchableMatrix);
            }
            else{
                solutionByList=solver.solve(aSearchableMatrix);
                solutionByString=SolutionByInstructions(solutionByList);
                cm.saveSolution(aSearchableMatrix, solutionByString);
            }
            outputToClient.println(solutionByString);
            outputToClient.flush();
        } catch (IOException e) {e.printStackTrace();}
    }

    private double[][] stringsToMatrix(LinkedList<String> list) {
        LinkedList<double[]> arraysList=new LinkedList<>();
        String arrayByString;
        String [] arrayOfStrings;
        double[] doubleArray;
        while(!list.isEmpty()) {
            arrayByString=list.remove();
            arrayOfStrings=arrayByString.split(",");
            doubleArray=new double[arrayOfStrings.length];
            for(int i=0; i<arrayOfStrings.length; i++)
                doubleArray[i]=Double.parseDouble(arrayOfStrings[i]);
            arraysList.add(doubleArray);
        }
        int m=arraysList.size();
        int n=arraysList.peek().length;
        double[][] finalMatrix=new double[m][n];
        for(int i=0; i<m; i++) {
            finalMatrix[i]=arraysList.remove();
        }
        return finalMatrix;

    }
    private Position stringToPosition(String s) {
        String [] arrayOfStrings=s.split(",");
        int [] arrayOfIntegers=new int[2];
        Position answerByPosition;
        for(int i=0; i<2; i++)
            arrayOfIntegers[i]=Integer.parseInt(arrayOfStrings[i]);
        answerByPosition=new Position(arrayOfIntegers[0], arrayOfIntegers[1]);
        return answerByPosition;
    }

    private String SolutionByInstructions (LinkedList<Position> solutionByList) {
        LinkedList<Position> list=new LinkedList<>(solutionByList);

        String instructions=new String("");

        Position cameFrom=list.remove();
        Position current;

        while(!list.isEmpty()){

            current=list.remove();

            if(current.getX()<cameFrom.getX())
                instructions+="Up";
            else if(current.getX()>cameFrom.getX())
                instructions+="Down";
            else if(current.getY()<cameFrom.getY())
                instructions+="Left";
            else if(current.getY()>cameFrom.getY())
                instructions+="Right";

            cameFrom=current;
            if(!list.isEmpty())
                instructions+=",";

        }
        return instructions;

    }

}
