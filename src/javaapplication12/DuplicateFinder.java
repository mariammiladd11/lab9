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
    
    public static Map<Integer, List<int[]>> findDuplicates(int[] values, int rowOffset, int colOffset) {

        Map<Integer, List<Integer>> occurrences = new HashMap<>();
        Map<Integer, List<int[]>> duplicates = new HashMap<>();

        
        for (int i = 0; i < 9; i++) {
            int num = values[i];

            
            occurrences.putIfAbsent(num, new ArrayList<>());
            occurrences.get(num).add(i);
        }

       
        for (Map.Entry<Integer, List<Integer>> entry : occurrences.entrySet()) {
            int number = entry.getKey();
            List<Integer> positions = entry.getValue();

            if (positions.size() > 1) {
                List<int[]> realCoords = new ArrayList<>();

                for (int pos : positions) {
                    int realRow;
                    int realCol;

                    
                    if (rowOffset != 0 && colOffset == 0) {
                        realRow = rowOffset;
                        realCol = pos;
                    }
                    
                    else if (rowOffset == 0 && colOffset != 0) {
                        realRow = pos;
                        realCol = colOffset;
                    }
                    
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
