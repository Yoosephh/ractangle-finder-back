package com.rectanglefinder.api.controllers;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rectanglefinder.api.errors.InvalidMatrixContentError;
import com.rectanglefinder.api.errors.InvalidMatrixError;
import com.rectanglefinder.api.errors.UnknownBodyError;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rectangle")
public class MainController {
  
  @PostMapping
  public ResponseEntity<Integer> postAreaFinder(@RequestBody String[][] matrix) {
    validateMatrix(matrix);

    int rowLength = matrix[0].length;
    int[] left = new int[rowLength]; //left = [0,0,0,0,0]
    int[] right = new int[rowLength];// right = [0,0,0,0,0]
    int[] height = new int[rowLength];// height = [0,0,0,0,0]

    Arrays.fill(right, rowLength); // right = [5,5,5,5,5]

    int maxArea = 0;

    for (String[] row : matrix) {
      // [
      //   ["1","0","1","0","0"],
      //   ["1","0","1","1","1"],
      //   ["1","1","1","1","1"],
      //   ["1","0","0","1","0"]
      // ]

      for (int j = 0; j < rowLength; j++) { 
        height[j] = (row[j].equals("1")) ? height[j] + 1 : 0;
        // [1, 0, 1, 0, 0]
        // [2, 0, 2, 1, 1]
      }

      int currLeft = 0;
      for (int j = 0; j < rowLength; j++) { 
        if (row[j].equals("1")) {
          left[j] = Math.max(left[j], currLeft);
        } else {
          left[j] = 0;
          currLeft = j + 1;
        }
        // [0, 0, 2, 0, 0]
        // [0, 0, 2, 2, 2]
      }

      int currRight = rowLength;
      for (int j = rowLength - 1; j >= 0; j--) {
        if (row[j].equals("1")) {
          right[j] = Math.min(right[j], currRight);
        } else {
          right[j] = rowLength;
          currRight = j;
        }
        // [5, 5, 3, 5, 5]
        // [5, 5, 3, 3, 3]
      }

      for (int j = 0; j < rowLength; j++) {
        maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
      }
    }

    return ResponseEntity.status(HttpStatus.OK).body(maxArea);
  }

  private void validateMatrix(String[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      throw new UnknownBodyError("Please, provide a matrix.");
    }
    int rowLength = matrix[0].length;
    for (int i = 1; i < matrix.length; i++) {
      if (matrix[i].length != rowLength) {
        throw new InvalidMatrixError(
            "Please, provide a matrix in the MxN format. Check if the lenghts for all the rows are equal.");
      }
    }
    for (String[] row : matrix) {
      for (int j = 0; j < row.length; j++) {
        if (!row[j].equals("0") && !row[j].equals("1")) {
          throw new InvalidMatrixContentError("Please, provide a matrix with only '0' and/or '1' chars");
        }
      }
    }
  }
}
