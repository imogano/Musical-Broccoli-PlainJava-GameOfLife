package com.gameoflife;

import java.util.Arrays;
import java.util.Scanner;

public class PatternDisplayTask implements Runnable{
    private Turtle bob;
    private int[][] seed;
    private int ssize = 20;

    public PatternDisplayTask(int[][] seed){
        this.seed = seed;

        this.bob = new Turtle();
        this.bob.up();
        this.bob.hide();
        this.bob.speed(0);
        this.bob.shape("square");
        Turtle.setCanvasSize(500, 500);
        Turtle.zoom(-500.0, -500.0, 500.0, 500.0);
    }


    private void drawResult(int[][] objectTpDraw){
        for(int i=0;i<objectTpDraw.length;i++){
            for(int j=0;j<objectTpDraw[0].length;j++){

                // 25 columns x 25 rows
                // 20 gap size
                // 20 x 25 = 500
                // 0, 20, 40, ..., 500

                int blockSize = 500/objectTpDraw.length;
                // ssize = blockSize;
                this.bob.shapeSize(blockSize,blockSize);
                int x = -500 + (j*blockSize);
                int y = 500 - (i*blockSize);

                if(objectTpDraw[i][j]==1){
                    this.bob.setPosition(x, y);
                    this.bob.stamp();
                }
            }
        }
    }


    @Override
    public void run() {
        int[][] state = this.seed;
        System.out.println("Starting display. Enter \"stop\" to halt pattern display.");
        while(!Thread.interrupted()){
            try {
                this.bob.clear();
                drawResult(state);
                Thread.sleep(300);
                state = GOFGameLogic.advanceGeneration(state);
                if(!Arrays.deepToString(state).contains("1")){
                    break;
                }
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Session interrupted. Taking you out...");
        Turtle.exit();
    }
    
}
