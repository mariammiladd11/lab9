/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author CYBER-TECH
 */
public class SudokuVerifierFileTest {

   public static void main(String[] args) {
        // List of Sudoku files to test
        String[] files = {"valid_board.txt", "invalid_board.txt"};

        for (String file : files) {
            System.out.println("\n=== Testing file: " + file + " ===");

            try {
                // Load the board from file
                SudokuBoard board = SudokuLoader.loadFromFile(file);

                // List of modes to test
                String[] modes = {"sequential", "3-thread", "27-thread"};

                for (String mode : modes) {
                    System.out.println("\n--- Mode: " + mode + " ---");

                    // Create checker using factory
                    SudukoChecker checker = CheckerFactory.createChecker(mode, board);

                    // Validate the board
                    VerificationResult result = checker.validate();

                    // Print result
                    System.out.println("Valid: " + result.isValid());

                    // Print duplicates if invalid
                    if (!result.isValid()) {
                        printDuplicates(result);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error loading board from file: " + file);
                e.printStackTrace();
            }
        }
    }

    // Helper method to print duplicates
    private static void printDuplicates(VerificationResult result) {
        System.out.println("--------- ROW DUPLICATES ---------");
        result.getRowDuplicates().forEach((row, duplicates) -> {
            duplicates.forEach((value, positions) -> {
                System.out.println("ROW " + (row + 1) + ", #" + value + ", " + positions);
            });
        });
        System.out.println("----------------------------------");

        System.out.println("--------- COLUMN DUPLICATES ---------");
        result.getColDuplicates().forEach((col, duplicates) -> {
            duplicates.forEach((value, positions) -> {
                System.out.println("COL " + (col + 1) + ", #" + value + ", " + positions);
            });
        });
        System.out.println("----------------------------------");

        System.out.println("--------- BOX DUPLICATES ---------");
        result.getBoxDuplicates().forEach((box, duplicates) -> {
            duplicates.forEach((value, positions) -> {
                System.out.println("BOX " + (box + 1) + ", #" + value + ", " + positions);
            });
        });
        System.out.println("----------------------------------");
    }
}

