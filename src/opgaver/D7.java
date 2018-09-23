/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opgaver;

/**
 *
 * @author rodsur
 */
public class D7 {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        int[][] screen1 = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        
        int[][] screen2 = new int[16][33];
        
        int turn;
        int liveCells;
        
        System.out.println("brættet er " + screen1.length + " høj og "
                + screen1[0].length + " bred");
        
        for (turn=0; turn<100; turn++) {
            //Vælg hvilket array der er skal vises og hvilket der er buffer
            int[][] currentScreen = (turn%2==0 ? screen1 : screen2);
            int[][] currentBuffer = (turn%2==1 ? screen1 : screen2);
            
            //fyld buffer med 0er
            clearBuffer(currentBuffer);
            
            //print det nuværende array
            printBuffer(currentScreen,turn);
            
            //Arbejd med det nuværende stadie og gem ændringer i bufferen
            for (int y = 0; y<currentScreen.length; y++){
                
                //System.out.println(currentScreen[y].length);
                for (int x = 0; x<currentScreen[y].length; x++){
                    
                    //Check de omkring liggende celler
                    liveCells = checkCells(currentScreen,x,y);
                    
                    //Regler
                    enforceRules(currentScreen,currentBuffer,x,y,liveCells);  
                }
            }
        //pause loop
        for (long i=0 ; i<1000000000 ; i++) {}
        }
        System.out.println("Done");
    }
    
    public static void clearBuffer(int[][] buffer) {
        for (int y = 0; y<buffer.length; y++){
            for (int x = 0; x<buffer[y].length; x++){
                buffer[y][x] = 0;
            }
        }
    }
    
    public static void printBuffer(int[][] buffer,int turn) {
        System.out.println("generation " + turn + "---------------------");
        for (int y = 0; y<buffer.length; y++){
            for (int x = 0; x<buffer[y].length; x++){
                System.out.print(buffer[y][x]);
            }
            System.out.println("");
        }
    }
    
    public static void enforceRules(int[][] screen,int[][] buffer,int x,int y,int liveCells) {
        if (screen[y][x] == 1) {
            if (liveCells < 2 || liveCells > 3) {
                buffer[y][x] = 0;
            } else {
                buffer[y][x] = 1;
            }
        } else if (screen[y][x] == 0 && liveCells == 3){
            buffer[y][x] = 1;
        }
    }
    
    public static int checkCells(int[][] screen,int x,int y) {
        int liveCells = 0;
        if (y != 0) {
            if (screen[y-1][x] == 1) {
                liveCells++;
            }
            if (x != 0) {
                if (screen[y-1][x-1] == 1) {
                    liveCells++;
                }
            }
            if (x != screen[y].length - 1) {
                if (screen[y-1][x+1] == 1) {
                    liveCells++;
                }
            }
        }

        if (y != screen.length - 1) {
            if (screen[y+1][x] == 1) {
                liveCells++;    
            }
            if (x != 0) {
                if (screen[y+1][x-1] == 1) {
                    liveCells++;
                }
            }
            if (x != screen[y].length - 1) {
                if (screen[y+1][x+1] == 1) {
                    liveCells++;
                }
            }
        }

        if (x != 0) {
            if (screen[y][x-1] == 1) {
                liveCells++;
            }
        }

        if (x != screen[y].length - 1) {
            if (screen[y][x+1] == 1) {
            liveCells++;
            }
        }
        return liveCells;
    } 
}
