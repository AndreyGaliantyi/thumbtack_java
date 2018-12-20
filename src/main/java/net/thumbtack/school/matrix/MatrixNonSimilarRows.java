package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {
    private ArrayList<int[]> matrixNonSimilarRows;

    public MatrixNonSimilarRows(int[][] matrix) {
        ArrayList<int[]> listMatrix = new ArrayList<>(Arrays.asList(matrix));
        Collections.reverse(listMatrix);
        for (int i = 0; i < listMatrix.size(); i++) {
            HashSet<Integer> lineSet = new HashSet<>();
            for (int element : listMatrix.get(i)) {
                lineSet.add(element);
            }
            for (int j = i + 1; j < listMatrix.size(); j++) {
                HashSet<Integer> otherLineSet = new HashSet<>();
                for (int element : listMatrix.get(j)) {
                    otherLineSet.add(element);
                }
                if (lineSet.equals(otherLineSet)) {
                    listMatrix.remove(j);
                    j--;
                }
            }
        }
        matrixNonSimilarRows = listMatrix;
    }

    public List<int[]> getNonSimilarRows() {
        return matrixNonSimilarRows;
    }
}
