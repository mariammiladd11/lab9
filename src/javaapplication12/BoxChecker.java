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

public class BoxChecker implements Runnable {
    private final SudokuBoard board;
    private final int boxIndex;
    private final DuplicateReport report;

    public BoxChecker(SudokuBoard board, int boxIndex, DuplicateReport report) {
        this.board = board;
        this.boxIndex = boxIndex;
        this.report = report;
    }

    @Override
    public void run() {
        Set<Integer> seen = new HashSet<>();

        int startRow = (boxIndex / 3) * 3;
        int startCol = (boxIndex % 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                int value = board.get(r, c);

                if (seen.contains(value)) {
                    report.addIssue("Duplicate value " + value +
                            " in BOX " + boxIndex +
                            " at position (" + r + "," + c + ")");
                }

                seen.add(value);
            }
        }
    }
}
