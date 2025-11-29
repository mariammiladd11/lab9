/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author CYBER-TECH
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMode27 extends SudukoChecker {
     public ParallelMode27(SudokuBoard board) {
        super(board);
    }
    public VerificationResult validate(SudokuBoard board) {
        DuplicateReport report = new DuplicateReport();

        ExecutorService executor = Executors.newFixedThreadPool(27);

        // Rows
        for (int r = 0; r < 9; r++) {
            final int row = r;
            executor.execute(() -> {
                report.addRowDuplicate(row, DuplicateFinder.findDuplicates(board.getRow(row), row, 0));
            });
        }

        // Columns
        for (int c = 0; c < 9; c++) {
            final int col = c;
            executor.execute(() -> {
                report.addColDuplicate(col, DuplicateFinder.findDuplicates(board.getCol(col), 0, col));
            });
        }

        // Boxes
        for (int b = 0; b < 9; b++) {
            final int box = b;
            executor.execute(() -> {
                int startRow = (box / 3) * 3;
                int startCol = (box % 3) * 3;
                report.addBoxDuplicate(box, DuplicateFinder.findDuplicates(board.getBox(box), startRow, startCol));
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        return report.toVerificationResult();
    }

    @Override
    public VerificationResult validate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

