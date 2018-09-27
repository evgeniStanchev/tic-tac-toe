package com.egtinteractive.tictactoe.games.tictactoe;

import static com.egtinteractive.tictactoe.resources.Positions.*;
import static com.egtinteractive.tictactoe.resources.BundleUtils.*;
import java.util.Objects;

import com.egtinteractive.tictactoe.IO.IO;

public final class Field {

    private static final int SIZE = 3;
    private final int maxPositionCount = SIZE * SIZE;
    private final char[][] matrix = new char[SIZE][SIZE];
    private final IO io;
    private int emptyPositionCount;
    private final char playerSign;
    private final char botSign;

    Field(final IO io) {
	this.io = io;
	this.playerSign = RESOURCE.getString("PLAYER_SIGN").charAt(0);
	this.botSign = RESOURCE.getString("AI_SIGN").charAt(0);
	this.emptyPositionCount = maxPositionCount;
    }

    public int getEmptyPositionCount() {
	return emptyPositionCount;
    }

    public int getMaxPositionCount() {
	return maxPositionCount;
    }

    int getRow(final int index) {
	return index / SIZE;
    }

    int getCol(final int index) {
	return index % SIZE;
    }

    public char[][] getMatrix() {
	return matrix;
    }

    public int getIndex(final int row, final int col) {
	return (row * SIZE) + col;
    }

    public int[] getNearMiddlesIndexes(final int firstIndex) {
	final int[] indexes = new int[2];
	for (int index = 0, count = 0; index < MIDDLE.getLength(); index++) {
	    final int row = getRow(MIDDLE.getPosition(index));
	    final int col = getCol(MIDDLE.getPosition(index));
	    if (getRow(firstIndex) == row || getCol(firstIndex) == col) {
		indexes[count] = getIndex(row, col);
		count++;
	    }
	}
	return indexes;
    }

    public char getElement(final int index) {
	return this.matrix[getRow(index)][getCol(index)];
    }

    public void setElement(final int index, final char sign) {
	this.matrix[getRow(index)][getCol(index)] = sign;
    }

    public int getCornerSum() {
	int count = 0;
	for (int index = 0; index < CORNER.getLength(); index++) {
	    if (getElement(CORNER.getPosition(index)) != Character.MIN_VALUE) {
		count++;
	    }
	}
	return count;
    }

    public boolean isCorner(final int thisIndex) {
	for (int index = 0; index < CORNER.getLength(); index++) {
	    if (thisIndex == CORNER.getPosition(index)) {
		return true;
	    }
	}
	return false;
    }

    public boolean isCenter(final int index) {
	if (index == CENTER.getPosition(0)) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * Checks if the opposite corner is empty
     * 
     * @param index
     * @return true if the opposite corner is empty and the specified index is
     *         not null
     */
    public boolean isEmptyOppoCorner(final int index) {
	final int row = getRow(index) == 0 ? 2 : 0;
	final int col = getCol(index) == 0 ? 2 : 0;
	if (this.matrix[row][col] == Character.MIN_VALUE) {
	    return true;
	}
	return false;
    }

    boolean decreasePositionCount() {
	if (this.emptyPositionCount != 0) {
	    this.emptyPositionCount--;
	    return true;
	}
	return false;
    }

    void printMatrix() {
	final StringBuilder sb = new StringBuilder();
	sb.append("PLAYER:");
	sb.append(this.playerSign);
	sb.append("      BOT:");
	sb.append(this.botSign);
	sb.append(System.lineSeparator());
	for (int row = 0; row < this.matrix.length; row++) {
	    sb.append(System.lineSeparator());
	    for (int col = 0; col < this.matrix.length; col++) {
		if (Objects.equals(this.matrix[row][col], Character.MIN_VALUE)) {
		    sb.append("     ");
		} else {
		    sb.append("  ");
		    sb.append(this.matrix[row][col]);
		    sb.append("  ");
		}
		if (col != 2) {
		    sb.append("|");
		}
	    }
	    sb.append(System.lineSeparator());
	    if (row != 2) {
		sb.append("-----------------");
	    }
	    sb.append(System.lineSeparator());
	}
	this.io.write(sb.toString());
    }

    /**
     * Indicates if the specified position is null, already taken or out of
     * bounds
     * 
     * @param position
     * @return true if it is not
     */
    boolean isCorrectNumber(final Integer position) {
	if (Objects.isNull(position)) {
	    this.io.writeErr(RESOURCE.getString("WRONG_POSITION_ERROR_MESSAGE"));
	    return false;
	}
	if (position < 0 || position > 8) {
	    this.io.writeErr(RESOURCE.getString("WRONG_POSITION_ERROR_MESSAGE"));
	    return false;
	}
	if (isPositionTaken(position)) {
	    this.io.write(RESOURCE.getString("POSITION_TAKEN_ERROR_MESSAGE"));
	    return false;
	}
	return true;
    }

    /**
     * Check if the position is already taken
     * 
     * @param index
     * @return true if the position is not empty
     */
    private boolean isPositionTaken(final int index) {
	return !Objects.equals(Character.MIN_VALUE, this.getElement(index));
    }

    /**
     * Returns the reverse corner of the specified corner
     * 
     * @param cornerIndex
     * @return
     */
    public int getReverseCornerIndex(final int cornerIndex) {
	for (int index = 0; index < CORNER.getLength(); index++) {
	    if (cornerIndex == CORNER.getPosition(index)) {
		return REVERSE_CORNER.getPosition(index);
	    }
	}
	return -1;
    }

    /**
     * This method searches for already marked position with the specified sign
     * in ascending order;
     * 
     * @param sign
     * @return the first occurrence or -1 if there is no such element;
     */
    public int getFirstOccurrenceOf(final char sign) {
	for (int index = 0; index < ALL_POSITIONS.getLength(); index++) {
	    if (this.getElement(ALL_POSITIONS.getPosition(index)) == sign) {
		return index;
	    }
	}
	return -1;
    }

    /**
     * This method searches for already marked corner with the specified sign in
     * order: - 0, 2, 6, 8;
     * 
     * @param sign
     * @return the first occurrence or -1 if there is no such element;
     */
    public int getMarkedCornerIndex(final char sign) {
	for (int index = 0; index < CORNER.getLength(); index++) {
	    if (this.matrix[getRow(CORNER.getPosition(index))][getCol(CORNER.getPosition(index))] == sign) {
		return CORNER.getPosition(index);
	    }
	}
	return -1;
    }
}