/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author CYBER-TECH
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuFileReader {

    public static SudokuBoard readBoard(String filePath) {
        int[][] board = new int[9][9];

        try {
            Scanner scanner = new Scanner(new File(filePath));
            for (int i = 0; i < 9; i++) {
                if (!scanner.hasNextLine()) {
                    throw new IllegalArgumentException("File must have 9 rows.");
                }
                String line = scanner.nextLine().trim();
                String[] nums = line.split("\\s+");
                if (nums.length != 9) {
                    throw new IllegalArgumentException("Each row must have 9 numbers.");
                }
                for (int j = 0; j < 9; j++) {
                    board[i][j] = Integer.parseInt(nums[j]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new SudokuBoard(board);
    }
}

