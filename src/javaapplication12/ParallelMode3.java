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

public class ParallelMode3 {
    public static DuplicateReport run(SudokuBoard board) {

        DuplicateReport report = new DuplicateReport();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(() -> {
            for (int r = 0; r < 9; r++) {
                new RowChecker(board, r, report).run();
            }
        });

        executor.execute(() -> {
            for (int c = 0; c < 9; c++) {
                new ColumnChecker(board, c, report).run();
            }
        });

        executor.execute(() -> {
            for (int b = 0; b < 9; b++) {
                new BoxChecker(board, b, report).run();
            }
        });

        executor.shutdown();
        while (!executor.isTerminated()) {}

        return report;
    }
}
