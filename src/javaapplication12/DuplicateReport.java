/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DuplicateReport {
    private final List<String> issues = Collections.synchronizedList(new ArrayList<>());
    private final Map<Integer, Map<Integer, List<int[]>>> rowDuplicates = new HashMap<>();
    private final Map<Integer, Map<Integer, List<int[]>>> colDuplicates = new HashMap<>();
    private final Map<Integer, Map<Integer, List<int[]>>> boxDuplicates = new HashMap<>();
    
    
    public void addIssue(String issue) {
        issues.add(issue);
    }

    public List<String> getIssues() {
        return issues;
    }

    public boolean hasIssues() {
        return !issues.isEmpty();
    }
    
   

    public void addRowDuplicate(int row, Map<Integer, List<int[]>> duplicates) {
        if (!duplicates.isEmpty()) rowDuplicates.put(row, duplicates);
    }

    public void addColDuplicate(int col, Map<Integer, List<int[]>> duplicates) {
        if (!duplicates.isEmpty()) colDuplicates.put(col, duplicates);
    }

    public void addBoxDuplicate(int box, Map<Integer, List<int[]>> duplicates) {
        if (!duplicates.isEmpty()) boxDuplicates.put(box, duplicates);
    }

    public boolean hasStructuredIssues() {
        return !rowDuplicates.isEmpty() || !colDuplicates.isEmpty() || !boxDuplicates.isEmpty();
    }

    
    public VerificationResult toVerificationResult() {
        VerificationResult result = new VerificationResult();

        rowDuplicates.forEach(result::addRowDuplicates);
        colDuplicates.forEach(result::addColDuplicates);
        boxDuplicates.forEach(result::addBoxDuplicates);

        result.setValid(result.hasNoDuplicates());
        return result;
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
} 
    

