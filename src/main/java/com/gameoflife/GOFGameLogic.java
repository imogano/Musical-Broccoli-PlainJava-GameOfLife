package com.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class GOFGameLogic {
    public static int[][] advanceGeneration(int[][] cells){
        int[][] nextCells = new int[cells.length][cells[0].length];
        int num = 0;
        String array = "";
        String neigh = "";
        String position = "";
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[0].length;j++){
                num = getNumberOfNeighbours(i, j, cells.length, cells);
                neigh+=num+",";
                array+= cells[i][j]+",";
                position+="["+i+"]["+j+"],";

                if(!liveOrDie(num, cells[i][j])){
                    nextCells[i][j]=1;
                }
                else if(liveOrDie(num, cells[i][j])){
                    nextCells[i][j]=0;
                }
                else{
                    nextCells[i][j] = cells[i][j];
                }
            }
            array+="\n";
            neigh+="\n";
            position+="\n";
        }
        cells = nextCells;
        return cells;
       
    }

    public static boolean liveOrDie(int num, int state){
        if(state!=0){
            if(num >1 && num < 4){
                return false;
            }
        }
        else if (state!=1){
            if(num==3){
                return false;
            }
        }
        return true;
    }

    public static int getNumberOfNeighbours(int x, int y, int worldSize, int[][] cells){
        int neighboursAlive = 0;
        
        /*
        (x-1,y+1)|(x,y+1)|(x+1,y+1)
        (x-1,y)  |(x,y)  |(x+1,y)
        (x-1,y-1)|(x,y-1)|(x+1,y-1)
        */

        if(x-1>=0 && y+1<worldSize && cells[x-1][y+1] == 1)
            neighboursAlive +=1;
        if (y+1<worldSize &&cells[x][y+1] == 1)
            neighboursAlive +=1;    
        if (x+1<worldSize && y+1<worldSize && cells[x+1][y+1] == 1)
            neighboursAlive +=1; 
        if (x-1>=0 && cells[x-1][y] == 1)
            neighboursAlive +=1; 
        if (x+1<worldSize && cells[x+1][y] == 1)
            neighboursAlive +=1;  
        if (x-1>=0 && y-1>=0 && cells[x-1][y-1] == 1)
            neighboursAlive +=1; 
        if (y-1>=0 && cells[x][y-1] == 1)
            neighboursAlive +=1; 
        if (x+1<worldSize && y-1>=0 && cells[x+1][y-1] == 1)
            neighboursAlive +=1; 

        return neighboursAlive;
    }

    public static int[][] convertStringToArray(String stateString){
        int[][] returnArray = null;
        String stateTemp = stateString;
        List<List<Integer>> newList = new ArrayList();
        if(stateString.contains("[") && stateString.contains("]") && stateString.contains(",")){
            
            String[] sre = stateTemp.replace("[", "").trim().split("]");
            for(String opt:sre){
                List<Integer> tre = new ArrayList();
                String[] op = opt.trim().split(",");    
                for(String optId: op){
                    optId = optId.trim();
                    if(optId.equals("1") || optId.equals("0")){
                        // System.out.println(optId);                        
                        try{
                            int number = Integer.parseInt(optId);
                            tre.add(number);
                        }
                        catch (NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    }
                }
                newList.add(tre);
            }
            int rows = newList.size();
            int columns = newList.get(0).size();
            int[][] trw = new int [rows][columns]; 
            for (int i = 0; i<rows; i++){
                for(int j = 0; j<columns; j ++){
                    trw[i][j] = newList.get(i).get(j);
                }
            }
            returnArray = trw;
        }         
        return returnArray;
    }

}
