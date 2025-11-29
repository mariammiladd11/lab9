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
import java.util.concurrent.TimeUnit;

public class ParallelMode3 extends SudukoChecker {

    public ParallelMode3(SudokuBoard board) {
        super(board);
    }

    @Override
    public VerificationResult validate() {
        DuplicateReport report = new DuplicateReport();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Rows
        executor.execute(() -> {
            for (int r = 0; r < 9; r++) {
                report.addRowDuplicate(r, DuplicateFinder.findDuplicates(board.getRow(r), r, 0));
            }
        });

        // Columns
        executor.execute(() -> {
            for (int c = 0; c < 9; c++) {
                report.addColDuplicate(c, DuplicateFinder.findDuplicates(board.getCol(c), 0, c));
            }
        });

        // Boxes
        executor.execute(() -> {
            for (int b = 0; b < 9; b++) {
                int startRow = (b / 3) * 3;
                int startCol = (b % 3) * 3;
                report.addBoxDuplicate(b, DuplicateFinder.findDuplicates(board.getBox(b), startRow, startCol));
            }
        });

        // Wait for threads to finish
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return report.toVerificationResult();
    }
}
