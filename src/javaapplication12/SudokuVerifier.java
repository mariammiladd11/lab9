/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author MALAK
 */
public class SudokuVerifier {
    public static void main(String[] args) {

        int[][] validBoard = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
        };

        int[][] invalidBoard = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,5} // duplicate 5 in last row
        };

        testBoard(validBoard, "VALID BOARD");
        testBoard(invalidBoard, "INVALID BOARD");
    }

    private static void testBoard(int[][] boardArray, String boardName) {
        System.out.println("\n--- Testing " + boardName + " ---");

        SudokuBoard board = new SudokuBoard(boardArray);

        String[] modes = {"sequential", "3-thread", "27-thread"};

        for (String mode : modes) {
            SudukoChecker checker = CheckerFactory.createChecker(mode, board);
            VerificationResult result = checker.validate();

            System.out.println("\nMode: " + mode);
            System.out.println("Valid: " + result.isValid());

            if (!result.isValid()) {
                System.out.println("Duplicate rows: " + result.getRowDuplicates());
                System.out.println("Duplicate columns: " + result.getColDuplicates());
                System.out.println("Duplicate boxes: " + result.getBoxDuplicates());
            }
        }
    }
}
