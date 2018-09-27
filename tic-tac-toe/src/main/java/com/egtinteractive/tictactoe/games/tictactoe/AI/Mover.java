package com.egtinteractive.tictactoe.games.tictactoe.AI;

import static com.egtinteractive.tictactoe.resources.Positions.*;

import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.tictactoe.games.tictactoe.Field;
import com.egtinteractive.tictactoe.games.tictactoe.TicTacToe;

public final class Mover {

    private final Field field;
    private final Checker check;
    private final char botSign;
    private final char playerSign;

    Mover(final Field field, final char botSign, final char playerSign) {
	this.botSign = botSign;
	this.playerSign = playerSign;
	this.field = field;
	this.check = new Checker(this.botSign, this.playerSign, this.field);
    }

    int setCenter(final char sign) {
	final char centerElement = this.field.getElement(getCenterPosition());
	if (centerElement == Character.MIN_VALUE) {
	    this.field.setElement(getCenterPosition(), sign);
	    return CENTER.getPosition(0);
	}
	return -1;
    }

    void setNearMiddle(final char sign, final int firstIndex) {
	if (check.isPossibleToWin()) {
	    return;
	}
	final int[] nearMiddle = this.field.getNearMiddlesIndexes(firstIndex);
	for (int index = 0; index < nearMiddle.length; index++) {
	    if (this.field.getElement(nearMiddle[index]) == Character.MIN_VALUE) {
		this.field.setElement(nearMiddle[index], sign);
		return;
	    }
	}
    }

    void setMiddle(final char sign) {
	if (check.isPossibleToWin()) {
	    return;
	}
	for (int index = 0; index < MIDDLE.getLength(); index++) {
	    if (this.field.getElement(MIDDLE.getPosition(index)) == Character.MIN_VALUE) {
		this.field.setElement(MIDDLE.getPosition(index), sign);
		return;
	    }
	}
    }

    boolean setReverseCorner(final char sign, final int cornerIndex) {
	if (check.isPossibleToWin()) {
	    return true;
	}
	for (int index = 0; index < CORNER.getLength(); index++) {
	    if (cornerIndex == CORNER.getPosition(index)) {
		this.field.setElement(REVERSE_CORNER.getPosition(index), sign);
		return true;
	    }
	}
	return false;
    }

    int setRandomCorner(final char sign) {
	if (check.isPossibleToWin()) {
	    return -1;
	}
	final int index = ThreadLocalRandom.current().nextInt(CORNER.getLength());
	final char randomCornerElement = this.field.getElement(CORNER.getPosition(index));
	if (randomCornerElement == Character.MIN_VALUE) {
	    this.field.setElement(CORNER.getPosition(index), sign);
	    return CORNER.getPosition(index);
	}
	return setAvailableCorner();
    }

    int setAvailableCorner() {
	if (check.isPossibleToWin()) {
	    return -1;
	}
	for (int index = 0; index < CORNER.getLength(); index++) {
	    final char cornerElement = this.field.getElement(CORNER.getPosition(index));
	    if (cornerElement == Character.MIN_VALUE) {
		this.field.setElement(CORNER.getPosition(index), botSign);
		return CORNER.getPosition(index);
	    }
	}
	return -1;
    }

    boolean setRandomly() {
	for (int index = 0; index < this.field.getMaxPositionCount(); index++) {
	    if (check.isEmpty(index)) {
		this.field.setElement(index, botSign);
		return true;
	    }
	}
	return false;
    }

    void setSmartly() {
	if (check.isPossibleToWin()) {
	    return;
	}
	if (defend()) {
	    TicTacToe.botIsDefending();
	    return;
	}
	setRandomly();
    }

    boolean setRoundedCorner() {
	for (int index = 0; index < CORNER.getLength(); index++) {
	    final int[] nearMiddle = this.field.getNearMiddlesIndexes(CORNER.getPosition(index));
	    if (this.field.getElement(nearMiddle[0]) == playerSign
		    && this.field.getElement(nearMiddle[1]) == playerSign) {
		final int position = CORNER.getPosition(index);
		if (this.field.getElement(position) == Character.MIN_VALUE) {
		    this.field.setElement(position, botSign);
		}
		return true;
	    }
	}
	return false;
    }

    private boolean defend() {
	return check.isDefendRequired();
    }
}