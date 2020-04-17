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
            topNeighbor = grid[yPos - 1][xPos];
        }
        if(yPos < grid.length){
            bottomNeighbor = grid[yPos + 1][xPos];
        }
        if(xPos > 0){
            leftNeighbor = grid[yPos][xPos -1];
        }
        if(xPos < grid[0].length){
            rightNeighbor = grid[yPos][xPos + 1];
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
