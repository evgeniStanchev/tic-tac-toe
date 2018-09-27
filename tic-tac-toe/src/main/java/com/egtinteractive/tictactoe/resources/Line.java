package com.egtinteractive.tictactoe.resources;

public enum Line {
    /**
     * 3 types of lines: rows, columns and diagonals. This enum can be useful
     * for getting an array of the positions for each of these 3 types of lines
     * and their length
     */
    ROWS(new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } }), //
    COLS(new int[][] { { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 } }), //
    DIAGONALS(new int[][] { { 0, 4, 8 }, { 2, 4, 6 } });

    private int[][] line;
    private final int linesCount;

    Line(final int[][] type) {
	this.line = type;
	this.linesCount = this.line.length;
    }

    public int[] getLine(final int count) {
	return this.line[count];
    }

    public int getLinesCount() {
	return this.linesCount;
    }
}