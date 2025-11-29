package javaapplication12;


import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sarahkhaled
 */
public class SudokuBoard {
     private final int[][] board; 
     
    public SudokuBoard(int[][] src) {
        if (src==null) throw new IllegalArgumentException("Source board cannot be null");
        if (src.length!= 9) throw new IllegalArgumentException("Board must have 9 rows");
        this.board =new int[9][9];
        for (int r= 0; r< 9; r++) {
            if (src[r] == null || src[r].length != 9)
                throw new IllegalArgumentException("Each row must have 9 columns (row " + (r+1) + ")");
            System.arraycopy(src[r],0,this.board[r], 0, 9);
        }
    }

    public int get(int row, int col) {
        checkBounds(row, col);
        return board[row][col];
    }

    private void checkBounds(int row, int col) {
        if (row <0 || row>= 9 || col< 0 || col>= 9)
            throw new IndexOutOfBoundsException("Row and column must be in range 0..8");
    }

    public int[] getRow(int row) {
        if (row< 0 || row >= 9) throw new IndexOutOfBoundsException("row must be 0..8");
        return Arrays.copyOf(board[row], 9);
    }

    public int[] getCol(int col) {
        if (col < 0 || col >= 9) throw new IndexOutOfBoundsException("col must be 0..8");
        int[] out = new int[9];
        for (int r = 0; r < 9; r++) out[r] = board[r][col];
        return out;
    }
    public int[] getBox(int boxIndex) {
        if (boxIndex < 0 || boxIndex >= 9) throw new IndexOutOfBoundsException("boxIndex must be 0..8");
        int br = (boxIndex / 3) * 3;
        int bc = (boxIndex % 3) * 3;
        int[] out = new int[9];
        int pos = 0;
        for (int r = br; r < br + 3; r++) {
            for (int c = bc; c < bc + 3; c++) {
                out[pos++] = board[r][c];
            }
        }
        return out;
    }

    public boolean isValidShapeAndRange() {
        for (int r = 0; r< 9;r++) {
            for (int c = 0; c < 9; c++) {
                int v = board[r][c];
                if (v < 1 || v > 9) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            for (int c= 0; c < 9; c++) {
                sb.append(board[r][c]);
                if (c< 8) sb.append(' ');
            }
            if (r < 8) sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
