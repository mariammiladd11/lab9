/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

/**
 *
 * @author CYBER-TECH
 */
import java.util.HashSet;
import java.util.Set;

public class ColumnChecker implements Runnable {
    private final SudokuBoard board;
    private final int col;
    private final DuplicateReport report;

    public ColumnChecker(SudokuBoard board, int col, DuplicateReport report) {
        this.board = board;
        this.col = col;
        this.report = report;
    }

    @Override
    public void run() {
        Set<Integer> seen = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            int value = board.get(row, col);

            if (seen.contains(value)) {
                report.addIssue("Duplicate value " + value +
                        " found in COLUMN " + col + " at row " + row);
            }

            seen.add(value);
        }
    }
}
