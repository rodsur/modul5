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
public class D8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Opgave D.8:
        Skriv en metode der givet et 9x9 array af heltal bestemmer
        (via en boolsk returværdi) om der er tale om en gyldig Sudoku løsning,
        og skriv et program der benytter denne.
        */
        /*
        int[][] sudoku = {
            {1,2,3,4,5,6,7,8,9},
            {2,3,4,5,6,7,8,9,1},
            {3,4,5,6,7,8,9,1,2},
            {1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1}
        };
        */
        int[][] sudoku = {
            {1,2,3,4,5,6,7,8,9},
            {4,5,6,7,8,9,1,2,3},
            {7,8,9,1,2,3,4,5,6},
            {2,3,4,5,6,7,8,9,1},
            {5,6,7,8,9,1,2,3,4},
            {8,9,1,2,3,4,5,6,7},
            {3,4,5,6,7,8,9,1,2},
            {6,7,8,9,1,2,3,4,5},
            {9,1,2,3,4,5,6,7,8}
        };
        
        
        if (checkValidity(sudoku) == true) {
            System.out.println("Denne sudoku er gyldig");
        } else {
            System.out.println("Denne sudoku er ikke gyldig");
        }
    }
    
    public static boolean checkValidity(int[][] sudoku) {
        
        for (int y = 0; y<9;y++) {
            checkRow(sudoku,y);
            if (checkRow(sudoku,y) == false) {
                return false;
            }
        }
        
        for (int x = 0; x<9; x++) {
            checkColumn(sudoku,x);
            if (checkColumn(sudoku,x) == false) {
                return false;
            }
        }
        for (int y = 0; y<9; y+=3) {
            for (int x = 0; x<9; x+=3) {
                if (checkBox(sudoku,x,y) == false) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean checkRow(int[][] sudoku, int y) {
        int[] row = new int[9];
        for (int i = 0; i<9; i++) {
            row[i] = sudoku[y][i];
        }
        return checkArray(row);
    }
    
    public static boolean checkColumn(int[][] sudoku, int x) {
        int[] column = new int[9];
        for (int i = 0; i<9; i++) {
            column[i] = sudoku[i][x];
        }
        return checkArray(column);
    }
    
    public static boolean checkBox(int[][] sudoku, int xPos, int yPos) {
        int[] box = new int[9];
        int position = 0;
        for (int y = yPos; y<=yPos+2; y++) {
            for (int x = xPos; x<=xPos+2; x++) {
                box[position++] = sudoku[y][x];
            }
        }
        return checkArray(box);
    }
    
    public static boolean checkArray(int[] array) {
        for (int i = 0; i<9; i++) {
            for (int j = i+1; j<9; j++) {
                if (array[i] == array[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
