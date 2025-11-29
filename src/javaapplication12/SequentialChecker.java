/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author Linae
 */
public class SequentialChecker {
    public VerificationResult validate(SudokuBoard board) {

        VerificationResult result = new VerificationResult();

        // -------------------------
        // 1. Check all rows
        // -------------------------
        for (int r = 0; r < 9; r++) {
            int[] row = board.getRow(r);
            var duplicates = DuplicateFinder.findDuplicates(row, r, 0);
            result.addRowDuplicates(r, duplicates);
        }

        // -------------------------
        // 2. Check all columns
        // -------------------------
        for (int c = 0; c < 9; c++) {
            int[] col = board.getColumn(c);
            var duplicates = DuplicateFinder.findDuplicates(col, 0, c);
            result.addColDuplicates(c, duplicates);
        }

        // -------------------------
        // 3. Check all boxes (0–8)
        // -------------------------
        for (int b = 0; b < 9; b++) {
            int startRow = (b / 3) * 3;
            int startCol = (b % 3) * 3;

            int[] box = board.getBox(b);
            var duplicates = DuplicateFinder.findDuplicates(box, startRow, startCol);
            result.addBoxDuplicates(b, duplicates);
        }

        // Final validity flag
        result.setValid(result.hasNoDuplicates());

        return result;
    }
    
}
