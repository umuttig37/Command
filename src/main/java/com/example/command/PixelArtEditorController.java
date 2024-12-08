package com.example.command;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PixelArtEditorController {
    private static final int GRID_SIZE = 8;
    private static final int CELL_SIZE = 40;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private int cursorX = 0;
    private int cursorY = 0;

    private Rectangle[][] cells = new Rectangle[GRID_SIZE][GRID_SIZE];

    @FXML
    private GridPane gridPane;

    @FXML
    private Button generateCodeButton;

    @FXML
    public void initialize() {
        // Initialize the grid and cells
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                cell.setStroke(Color.BLACK);
                cells[row][col] = cell;
                gridPane.add(cell, col, row);
            }
        }

        updateCursor();

        // Bind the generate code button
        generateCodeButton.setOnAction(event -> generateCode());
    }

    public void moveCursor(int dx, int dy) {
        cursorX = Math.max(0, Math.min(GRID_SIZE - 1, cursorX + dx));
        cursorY = Math.max(0, Math.min(GRID_SIZE - 1, cursorY + dy));
        updateCursor();
    }

    public void togglePixel() {
        grid[cursorY][cursorX] = 1 - grid[cursorY][cursorX]; // Toggle between 0 and 1
        updateCell(cursorX, cursorY);
    }

    public void generateCode() {
        StringBuilder code = new StringBuilder("int[][] pixelArt = {\n");
        for (int row = 0; row < GRID_SIZE; row++) {
            code.append("    {");
            for (int col = 0; col < GRID_SIZE; col++) {
                code.append(grid[row][col]);
                if (col < GRID_SIZE - 1) {
                    code.append(", ");
                }
            }
            code.append("}");
            if (row < GRID_SIZE - 1) {
                code.append(",");
            }
            code.append("\n");
        }
        code.append("};");
        System.out.println(code);
    }

    private void updateCursor() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setStroke((row == cursorY && col == cursorX) ? Color.RED : Color.BLACK);
            }
        }
    }

    private void updateCell(int x, int y) {
        cells[y][x].setFill(grid[y][x] == 1 ? Color.BLACK : Color.WHITE);
    }
}
