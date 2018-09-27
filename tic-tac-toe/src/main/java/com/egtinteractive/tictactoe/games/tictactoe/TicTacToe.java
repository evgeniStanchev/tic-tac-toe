package com.egtinteractive.tictactoe.games.tictactoe;

import static com.egtinteractive.tictactoe.resources.Line.*;
import static com.egtinteractive.tictactoe.resources.BundleUtils.*;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.tictactoe.IO.IO;
import com.egtinteractive.tictactoe.games.tictactoe.AI.AI;
import com.egtinteractive.tictactoe.games.tictactoe.AI.Difficulty;

public final class TicTacToe {

    private static final float MULTIPLIER_RATE = 1.25f;
    private static final float SCORE_FIRST_POINTS = 1.00f;
    private static final float BOT_IS_DEFENDING = 5.5f;

    private static float score;
    private static float multiplier;
    private boolean isPlayerWinner;
    private boolean isPlayerFirst;
    private boolean isGameEnded;
    private AI bot;
    private final Field field;
    private final char playerSign;
    private final char botSign;
    private final IO io;

    public TicTacToe(final TTTBuilder builder) {
	TicTacToe.score = builder.score;
	TicTacToe.multiplier = builder.multiplier;
	this.isGameEnded = builder.isGameEnded;
	this.io = builder.io;
	this.field = builder.field;
	this.isPlayerFirst = builder.isPlayerFirst;
	this.playerSign = builder.playerSign;
	this.botSign = builder.botSign;
    }

    public static class TTTBuilder {
	private final float score;
	private final float multiplier;
	private final char playerSign;
	private final char botSign;
	private final boolean isPlayerFirst;
	private final IO io;
	private final Field field;
	private final boolean isGameEnded;

	public TTTBuilder(final IO io) {
	    this.io = io;
	    this.multiplier = MULTIPLIER_RATE;
	    this.score = SCORE_FIRST_POINTS;
	    this.playerSign = RESOURCE.getString("PLAYER_SIGN").charAt(0);
	    this.botSign = RESOURCE.getString("AI_SIGN").charAt(0);
	    this.isPlayerFirst = ThreadLocalRandom.current().nextBoolean();
	    this.field = new Field(this.io);
	    this.isGameEnded = false;
	}
    }

    public boolean isGameEnded() {
	return this.isGameEnded;
    }

    public boolean isPlayerWinner() {
	return isPlayerWinner;
    }

    public Field getField() {
	return field;
    }

    public boolean isPlayerFirst() {
	return isPlayerFirst;
    }

    public int getScore() {
	return (int) score;
    }

    public void playGame() {
	this.field.printMatrix();
	if (!this.isPlayerFirst) {
	    botMakeMove(bot);
	}
	while (true) {
	    try {
		final Integer newPosition = this.io.nextInt();
		if (this.field.isCorrectNumber(newPosition)) {
		    playerMakeMove(newPosition);
		    if (weHaveAWinner()) {
			this.isGameEnded = true;
			announceTheWinner(this.playerSign);
			break;
		    }
		    botMakeMove(bot);
		    if (weHaveAWinner()) {
			this.isGameEnded = true;
			announceTheWinner(this.botSign);
			break;
		    }
		    if (this.field.getEmptyPositionCount() == 0 && !weHaveAWinner()) {
			this.isGameEnded = true;
			drawGame();
			break;
		    }
		}
		this.io.write(RESOURCE.getString("ENTER_A_POSSITION_MESSAGE"));
	    } catch (NoSuchElementException e) {
		break;
	    }
	}
    }

    public void chooseDifficulty() {
	int choice;
	final StringBuilder sb = new StringBuilder();
	sb.append(System.lineSeparator());
	sb.append(RESOURCE.getString("CHOOSE_A_DIFFICULTY_MESSAGE"));
	sb.append(System.lineSeparator());
	int count = 0;
	for (Difficulty diff : Difficulty.values()) {
	    sb.append(System.lineSeparator());
	    sb.append(++count);
	    sb.append(".");
	    sb.append(diff.toString());
	}
	while (true) {
	    this.io.write(sb.toString());
	    this.io.write(System.lineSeparator());
	    if (!io.hasNext()) {
		return;
	    }
	    try {
		choice = io.nextInt();
		if (choice > 0 && choice <= count) {
		    break;
		}
	    } catch (InputMismatchException e) {
		this.io.writeErr(RESOURCE.getString("WRONG_INPUT_ERROR_MESSAGE"));
		io.next();
	    }
	}
	count = 0;
	for (Difficulty diff : Difficulty.values()) {
	    if (choice == (++count)) {
		this.bot = new AI(this.isPlayerFirst, this.field, diff, this.playerSign, this.botSign);
		multiplier *= count;
		this.io.write(RESOURCE.getString("CHOOSE_A_DIFFICULTY_MESSAGE"));
		this.io.write(diff.toString());
	    }
	}
	this.io.write(isPlayerFirst ? RESOURCE.getString("PLAYER_IS_FIRST_MESSAGE")
		: RESOURCE.getString("AI_IS_FIRST_MESSAGE"));
    }

    /**
     * If the AI has to defend itself, then score multiplier increase
     */
    public static void botIsDefending() {
	score += multiplier * BOT_IS_DEFENDING;
    }

    private void announceTheWinner(final char winnerSign) {
	if (this.playerSign == winnerSign) {
	    this.io.write(RESOURCE.getString("YOU_WON_MESSAGE"));
	    score += bot.getDifficulty().getWinScore();
	    this.io.write(getYourScoreMessage());
	    this.isPlayerWinner = true;
	} else if (this.botSign == winnerSign) {
	    this.io.write(RESOURCE.getString("YOU_LOST_MESSAGE"));
	}
    }

    /**
     * Indicate if a row has only equal signs
     * 
     * @return true if all of the elements in one of the rows are equal and not
     *         null
     */
    private boolean isEqualRowDetected() {
	for (int count = 0; count < ROWS.getLinesCount(); count++) {
	    int[] row = ROWS.getLine(count);
	    if (isEqualLine(row)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Indicate if a column has only equal signs
     * 
     * @return true if all of the elements in one of the columns are equal and
     *         not null
     */
    private boolean isEqualColDetected() {
	for (int count = 0; count < COLS.getLinesCount(); count++) {
	    int[] col = COLS.getLine(count);
	    if (isEqualLine(col)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Indicate if a line has only equal signs
     * 
     * @return true if all of the elements in the specific line are equal and
     *         not null
     */
    private boolean isEqualLine(final int[] line) {
	final char[] sign = new char[line.length];
	for (int index = 0; index < line.length; index++) {
	    sign[index] = this.field.getElement(line[index]);
	}
	if (Objects.equals(sign[0], Character.MIN_VALUE)) {
	    return false;
	}
	for (int index = 0; index < sign.length - 1; index++) {
	    if (!Objects.equals(sign[index], sign[index + 1])) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Indicate if a diagonal has only equal signs
     * 
     * @return true if all of the elements in one of the diagonals are equal and
     *         not null
     */
    private boolean isEqualDiagonalDetected() {
	for (int count = 0; count < DIAGONALS.getLinesCount(); count++) {
	    int[] diagonal = DIAGONALS.getLine(count);
	    if (isEqualLine(diagonal)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Indicate if a line has only equal signs
     * 
     * @return true if there is one or more lines with equal elements , and
     *         these elements are non null
     */
    private boolean isEqualLineDetected() {
	this.isGameEnded = isEqualRowDetected() || isEqualColDetected() || isEqualDiagonalDetected();
	return this.isGameEnded;
    }

    /**
     * Indicates if someone win the game
     * 
     * @return true if there is a winner
     */
    private boolean weHaveAWinner() {
	if (this.field.getEmptyPositionCount() > 4) {
	    return false;
	}
	return isEqualLineDetected();
    }

    private String getYourScoreMessage() {
	return RESOURCE.getString("YOUR_SCORE_MESSAGE") + getScore();
    }

    private void drawGame() {
	this.io.write(RESOURCE.getString("DRAW_GAME_MESSAGE"));
	this.isGameEnded = true;
    }

    private void increaseScore() {
	score *= multiplier;
	increaseMultiplier();
    }

    private void increaseMultiplier() {
	multiplier *= MULTIPLIER_RATE;
    }

    private void botMakeMove(final AI bot) {
	this.field.decreasePositionCount();
	bot.makeMove();
	this.field.printMatrix();
    }

    /**
     * Noting special in this method, except the rule that in every player's
     * move the score is increased
     * 
     * @param position
     *            - the position that the player wants to mark
     */
    private void playerMakeMove(final int position) {
	increaseScore();
	this.field.decreasePositionCount();
	final int row = this.field.getRow(position);
	final int col = this.field.getCol(position);
	this.field.getMatrix()[row][col] = this.playerSign;
	this.field.printMatrix();
    }
}