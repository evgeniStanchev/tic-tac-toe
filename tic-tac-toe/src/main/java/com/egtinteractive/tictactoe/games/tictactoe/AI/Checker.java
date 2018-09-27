
package com.egtinteractive.tictactoe.games.tictactoe.AI;

import com.egtinteractive.tictactoe.games.tictactoe.Field;
import static com.egtinteractive.tictactoe.resources.Line.*;

public final class Checker {

    private final char botSign;
    private final char playerSign;
    private final Field field;

    Checker(final char botSign, final char playerSign, final Field field) {
	this.field = field;
	this.botSign = botSign;
	this.playerSign = playerSign;
    }

    boolean isEmpty(final int index) {
	return this.field.getElement(index) == Character.MIN_VALUE;
    }

    boolean isDefendRequired() {
	return theColumns(playerSign) || theRows(playerSign) || theDiagonals(playerSign, botSign);
    }

    boolean isPossibleToWin() {
	return theColumns(botSign) || theRows(botSign) || theDiagonals(botSign, botSign);
    }

    /**
     * This method check all of the rows.
     * 
     * 
     * @param sign
     *            - if 2 positions in one row are marked with this sign, that
     *            method mark the 3rd position with the botSign
     * @return true if this method mark a position
     */
    private boolean theRows(final char sign) {
	for (int row = 0, count = 0; row < ROWS.getLinesCount(); count = 0, row++) {
	    for (int index = 0; index < ROWS.getLine(row).length; index++) {
		if (this.field.getElement(ROWS.getLine(row)[index]) == sign) {
		    count++;
		}
	    }
	    if (count == 2) {
		for (int currentCol = 0; currentCol < COLS.getLinesCount(); currentCol++) {
		    final int index = COLS.getLine(currentCol)[row];
		    if (isEmpty(index)) {
			this.field.setElement(index, botSign);
			return true;
		    }
		}
	    }
	}
	return false;
    }

    /**
     * This method check all of the columns.
     * 
     * 
     * @param sign
     *            - if 2 positions in one column are marked with this sign, that
     *            method mark the 3rd position with the botSign
     * @return true if this method mark a position
     */
    private boolean theColumns(final char sign) {
	for (int col = 0, count = 0; col < COLS.getLinesCount(); count = 0, col++) {
	    for (int colLine = 0; colLine < COLS.getLine(col).length; colLine++) {
		final int index = COLS.getLine(col)[colLine];
		if (this.field.getElement(index) == sign) {
		    count++;
		}
	    }
	    if (count == 2) {
		for (int currentRow = 0; currentRow < ROWS.getLinesCount(); currentRow++) {
		    final int index = ROWS.getLine(currentRow)[col];
		    if (isEmpty(index)) {
			this.field.setElement(index, botSign);
			return true;
		    }
		}
	    }
	}
	return false;
    }

    /**
     * This method check the two diagonals. Mark a diagonal's position with the
     * markSign if the other 2 positions in that diagonal are marked with the
     * checkSign
     * 
     * @param checkSign
     * @param markSign
     * @return true if it mark a position
     */
    private boolean theDiagonals(final char checkSign, final char markSign) {
	for (int diagonal = 0, count = 0; diagonal < DIAGONALS.getLinesCount(); count = 0, diagonal++) {
	    final int[] diagonalLine = DIAGONALS.getLine(diagonal);
	    for (int currentIndex = 0; currentIndex < diagonalLine.length; currentIndex++) {
		final int index = diagonalLine[currentIndex];
		if (this.field.getElement(index) == checkSign) {
		    count++;
		}
	    }
	    if (count == 2) {
		for (int currentIndex = 0; currentIndex < diagonalLine.length; currentIndex++) {
		    final int index = DIAGONALS.getLine(diagonal)[currentIndex];
		    if (this.field.getElement(index) == Character.MIN_VALUE) {
			this.field.setElement(index, markSign);
			return true;
		    }
		}

	    }
	}
	return false;
    }

    /**
     * This method checks if the two near middles are set
     * 
     * @param firstIndex
     * @return true if at least one of them is set
     */
    boolean isNearMiddleSet(final int firstIndex) {
	final int[] middle = this.field.getNearMiddlesIndexes(firstIndex);
	for (int index = 0; index < middle.length; index++) {
	    if (field.getElement(middle[index]) != Character.MIN_VALUE) {
		return true;
	    }
	}
	return false;
    }

    /**
     * This method checks if the two distant middles are set
     * 
     * @param firstIndex
     * @return true if at least one of them is set
     */
    boolean isDistantMiddleSet(final int firstIndex) {
	final int reverseIndex = field.getReverseCornerIndex(firstIndex);
	final int[] distantMiddle = field.getNearMiddlesIndexes(reverseIndex);
	for (int index = 0; index < distantMiddle.length; index++) {
	    if (field.getElement(distantMiddle[index]) != Character.MIN_VALUE) {
		return true;
	    }
	}
	return false;
    }
}