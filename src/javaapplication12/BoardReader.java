package javaapplication12;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sarahkhaled
 */
public class BoardReader {
     public static SudokuBoard readFromCsv(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        int[][] board = new int[9][9];
        int row = 0;
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue; 
            String[] tokens = line.split("\\s*,\\s*|\\s+");
            if (tokens.length < 9) {
                throw new IllegalArgumentException("Expected at least 9 values on line " + (row + 1)
                        + ", got " + tokens.length + " -- line: \"" + line + "\"");
            }
            for (int c = 0; c < 9; c++) {
                try {
                    int v = Integer.parseInt(tokens[c]);
                    if (v < 1 || v > 9) {
                        throw new IllegalArgumentException("Value out of range 1..9 at row " + (row+1) + ",col " + (c+1));
                    }
                    board[row][c] = v;
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Invalid integer at row " + (row+1) + ", col " + (c+1) + ": \"" + tokens[c] + "\"");
                }
            }
            row++;
            if (row==9)
                break;
        }
        if (row!=9) throw new IllegalArgumentException("Expected 9 non-empty rows, got " + row);

        return new SudokuBoard(board);
    }

    public static void main(String[] args) {
        if (args.length!= 1) {
            System.err.println("Usage: java BoardReader <path-to-csv>");
            System.exit(1);
        }
        try {
            SudokuBoard b = readFromCsv(args[0]);
            System.out.println("Read board:");
            System.out.println(b);
        } catch (Exception e) {
            System.err.println("Error reading board: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
