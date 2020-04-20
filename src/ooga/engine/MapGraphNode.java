package ooga.engine;

import java.util.ArrayList;

public class MapGraphNode {

    private int xPos;
    private int yPos;
    private MapGraphNode topNeighbor;
    private MapGraphNode bottomNeighbor;
    private MapGraphNode rightNeighbor;
    private MapGraphNode leftNeighbor;


    public MapGraphNode(int i, int row){
        xPos = i;
        yPos = row;
    }

    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public void addNeighbor(MapGraphNode[][] grid){ //todo: make this more accurate
        if(yPos > 0 ){
            topNeighbor = grid[xPos][yPos - 1];

        }
        if(yPos < grid.length){
            bottomNeighbor = grid[xPos][yPos + 1];

        }
        if(xPos > 0){
            leftNeighbor = grid[xPos - 1][yPos];

        }
        if(xPos < grid[0].length){
            rightNeighbor = grid[xPos + 1][yPos];

        }
    }

    public MapGraphNode getTopNeighbor(){
        return topNeighbor;
    }

    public MapGraphNode getBottomNeighbor(){
        return bottomNeighbor;
    }

    public MapGraphNode getLeftNeighbor(){
        return leftNeighbor;
    }

    public MapGraphNode getRightNeighbor(){
        return rightNeighbor;
    }
}
