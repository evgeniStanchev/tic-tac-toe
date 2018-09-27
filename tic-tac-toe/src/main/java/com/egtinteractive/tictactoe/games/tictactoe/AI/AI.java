package com.egtinteractive.tictactoe.games.tictactoe.AI;

import com.egtinteractive.tictactoe.games.tictactoe.Field;

public final class AI {

    private final Checker check;
    private final Mover mover;
    private int firstIndex;
    private Tactic tactic;

    private final Difficulty difficulty;
    private final Field field;
    private final char botSign;
    private final char playerSign;
    private boolean isPlayerFirst;

    public AI(final boolean isPlayerFirst, final Field field, final Difficulty difficulty, final char playerSign,
	    final char botSign) {
	this.difficulty = difficulty;
	this.isPlayerFirst = isPlayerFirst;
	this.field = field;
	this.botSign = botSign;
	this.playerSign = playerSign;
	this.mover = new Mover(this.field, this.botSign, this.playerSign);

	this.tactic = this.difficulty.getFirstTacticState(this.isPlayerFirst);
	this.check = new Checker(this.botSign, this.playerSign, this.field);
    }

    public void makeMove() {
	tactic.makeMove(this);
    }

    public char getPlayerSign() {
	return this.playerSign;
    }

    public Difficulty getDifficulty() {
	return this.difficulty;
    }

    Checker getChecker() {
	return check;
    }

    Field getField() {
	return this.field;
    }

    Mover getMover() {
	return mover;
    }

    char getSign() {
	return botSign;
    }

    int getFirstIndex() {
	return firstIndex;
    }

    void setTactic(final Tactic tactic) {
	this.tactic = tactic;
    }

    void setFirstIndex(final int index) {
	this.firstIndex = index;
    }
}