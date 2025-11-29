/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Linae
 */
public class DuplicateFinder {
    /**
     * Detect duplicates inside a row, column, or box.
     *
     * @param values      int[9] segment (row/column/box)
     * @param rowOffset   starting row index on the board
     * @param colOffset   starting column index on the board
     * @return Map<value, List<int[]>> -> positions of each duplicate value
     */
    public static Map<Integer, List<int[]>> findDuplicates(int[] values, int rowOffset, int colOffset) {

        Map<Integer, List<Integer>> occurrences = new HashMap<>();
        Map<Integer, List<int[]>> duplicates = new HashMap<>();

        // Record positions of each value
        for (int i = 0; i < 9; i++) {
            int num = values[i];

            // Add position index
            occurrences.putIfAbsent(num, new ArrayList<>());
            occurrences.get(num).add(i);
        }

        // Now find numbers that appear more than once
        for (Map.Entry<Integer, List<Integer>> entry : occurrences.entrySet()) {
            int number = entry.getKey();
            List<Integer> positions = entry.getValue();

            if (positions.size() > 1) {
                List<int[]> realCoords = new ArrayList<>();

                for (int pos : positions) {
                    int realRow;
                    int realCol;

                    // ROW
                    if (rowOffset != 0 && colOffset == 0) {
                        realRow = rowOffset;
                        realCol = pos;
                    }
                    // COLUMN
                    else if (rowOffset == 0 && colOffset != 0) {
                        realRow = pos;
                        realCol = colOffset;
                    }
                    // BOX (3×3)
                    else {
                        realRow = rowOffset + (pos / 3);
                        realCol = colOffset + (pos % 3);
                    }

                    realCoords.add(new int[]{realRow, realCol});
                }

                duplicates.put(number, realCoords);
            }
        }

        return duplicates;
    } 
    
}
