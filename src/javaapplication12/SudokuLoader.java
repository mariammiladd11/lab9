/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author MALAK
 */
public class SudokuLoader {
    public static SudokuBoard loadFromFile(String path) throws Exception {
        int[][] grid = new int[9][9];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (int r = 0; r < 9; r++) {
                String line = br.readLine();
                if (line == null) throw new Exception("Not enough rows.");

                String[] parts = line.trim().split("\\s+");
                if (parts.length != 9) throw new Exception("Invalid row: " + line);

                for (int c = 0; c < 9; c++) {
                    grid[r][c] = Integer.parseInt(parts[c]);
                }
            }
        }

        return new SudokuBoard(grid);
    }
}
