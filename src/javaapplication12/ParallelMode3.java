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

public class ParallelMode3 extends SudukoChecker {
    
    public ParallelMode3(SudokuBoard board) {
        super(board);
    }

    @Override
    public VerificationResult validate() {
        VerificationResult result = new VerificationResult();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Thread for rows
        executor.execute(() -> {
            for (int r = 0; r < 9; r++) {
                int[] row = board.getRow(r);
                var duplicates = DuplicateFinder.findDuplicates(row, r, 0);
                result.addRowDuplicates(r, duplicates);
            }
        });

        // Thread for columns
        executor.execute(() -> {
            for (int c = 0; c < 9; c++) {
                int[] col = board.getCol(c);
                var duplicates = DuplicateFinder.findDuplicates(col, 0, c);
                result.addColDuplicates(c, duplicates);
            }
        });

        // Thread for boxes
        executor.execute(() -> {
            for (int b = 0; b < 9; b++) {
                int startRow = (b / 3) * 3;
                int startCol = (b % 3) * 3;
                int[] box = board.getBox(b);
                var duplicates = DuplicateFinder.findDuplicates(box, startRow, startCol);
                result.addBoxDuplicates(b, duplicates);
            }
        });

        executor.shutdown();
        while (!executor.isTerminated()) {}

        result.setValid(result.hasNoDuplicates());
        return result;
    }

}
