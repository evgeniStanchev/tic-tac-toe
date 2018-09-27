package com.egtinteractive.tictactoe.state;

import java.math.BigDecimal;

import com.egtinteractive.tictactoe.IO.IO;
import com.egtinteractive.tictactoe.coin.Coin;
import com.egtinteractive.tictactoe.games.tictactoe.TicTacToe;
import com.egtinteractive.tictactoe.games.tictactoe.TicTacToe.TTTBuilder;
import static com.egtinteractive.tictactoe.resources.BundleUtils.*;

public final class GameMachine {
    private final IO io;
    private final BigDecimal gamePrice;
    private TicTacToe currentTTT;
    private State state;
    private BigDecimal credit;

    public GameMachine(final MachineBuilder builder) {
	this.io = builder.io;
	this.state = builder.state;
	this.gamePrice = builder.gamePrice;
	this.credit = builder.credit;
	start();
    }

    public static class MachineBuilder {
	private IO io;
	private State state;
	private BigDecimal credit;
	private BigDecimal gamePrice;

	public MachineBuilder(final IO io) {
	    this.io = io;
	    this.state = StateMachine.STAND_BY;
	    this.credit = BigDecimal.ZERO;
	    this.gamePrice = BigDecimal.TEN;
	}

	public void setPrice(final BigDecimal gamePrice) {
	    this.gamePrice = gamePrice;
	}

	public GameMachine build() {
	    return new GameMachine(this);
	}
    }

    public IO getIO() {
	return this.io;
    }

    public TicTacToe getCurrentTTT() {
	return currentTTT;
    }

    public BigDecimal getGamePrice() {
	return gamePrice;
    }

    public BigDecimal getCredit() {
	return credit;
    }

    public State getState() {
	return state;
    }

    void setResult(final TicTacToe ttt, final boolean didPlayerWon) {
	state.setResult(this, ttt.getScore(), didPlayerWon);
    }

    void setState(final State state) {
	this.state = state;
    }

    void increaseCredit(final BigDecimal value) {
	this.credit = this.credit.add(value);
    }

    void setCredit(final BigDecimal newCredit) {
	this.credit = newCredit;
    }

    public BigDecimal ejectCredit() {
	return this.state.ejectCoins(this);
    }

    void start() {
	this.io.write(RESOURCE.getString("CHOICE_MESSAGE"));
	this.io.write(RESOURCE.getString("NOT_ENOUGH_CREDIT_ERROR_MESSAGE") + this.gamePrice);
	putCoin(null);
    }

    public Coin putCoin(final Coin coin) {
	this.state.putCoin(this, coin);
	return coin;
    }

    public boolean startTheGame() {
	final TTTBuilder tttBuilder = new TTTBuilder(this.io);
	this.currentTTT = new TicTacToe(tttBuilder);
	return state.startTheGame(this, this.currentTTT);
    }

    boolean quitTheGame() {
	return state.quitPlaying(this);
    }
}