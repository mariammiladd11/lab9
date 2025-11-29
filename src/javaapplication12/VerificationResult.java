/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Linae
 */
public class VerificationResult {
    private boolean valid = true;

    // Maps: row → { value → list of coords }
    private Map<Integer, Map<Integer, List<int[]>>> rowDuplicates = new HashMap<>();
    private Map<Integer, Map<Integer, List<int[]>>> colDuplicates = new HashMap<>();
    private Map<Integer, Map<Integer, List<int[]>>> boxDuplicates = new HashMap<>();


    // -----------------------------
    // Add Duplicate Results
    // -----------------------------

    public void addRowDuplicates(int rowIndex, Map<Integer, List<int[]>> duplicates) {
        if (!duplicates.isEmpty()) valid = false;
        rowDuplicates.put(rowIndex, duplicates);
    }

    public void addColDuplicates(int colIndex, Map<Integer, List<int[]>> duplicates) {
        if (!duplicates.isEmpty()) valid = false;
        colDuplicates.put(colIndex, duplicates);
    }

    public void addBoxDuplicates(int boxIndex, Map<Integer, List<int[]>> duplicates) {
        if (!duplicates.isEmpty()) valid = false;
        boxDuplicates.put(boxIndex, duplicates);
    }


    // -----------------------------
    // Getters
    // -----------------------------

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean v) {
        this.valid = v;
    }

    public Map<Integer, Map<Integer, List<int[]>>> getRowDuplicates() {
        return rowDuplicates;
    }

    public Map<Integer, Map<Integer, List<int[]>>> getColDuplicates() {
        return colDuplicates;
    }

    public Map<Integer, Map<Integer, List<int[]>>> getBoxDuplicates() {
        return boxDuplicates;
    }



    // -----------------------------
    // Helper
    // -----------------------------
    public boolean hasNoDuplicates() {
        return rowDuplicates.values().stream().allMatch(Map::isEmpty)
                && colDuplicates.values().stream().allMatch(Map::isEmpty)
                && boxDuplicates.values().stream().allMatch(Map::isEmpty);
    }
    
}
