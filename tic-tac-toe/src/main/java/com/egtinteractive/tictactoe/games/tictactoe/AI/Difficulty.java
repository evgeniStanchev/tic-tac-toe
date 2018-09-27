package com.egtinteractive.tictactoe.games.tictactoe.AI;

public enum Difficulty {
    EASY(1) {
	@Override
	public Tactic getFirstTacticState(final boolean isPlayerFirst) {
	    return Tactic.RANDOM;
	}
    },
    MEDIUM(2) {

	@Override
	public Tactic getFirstTacticState(final boolean isPlayerFirst) {
	    return Tactic.NORMAL;
	}
    },
    IMPOSSIBLE(3) {
	@Override
	public Tactic getFirstTacticState(final boolean isPlayerFirst) {
	    return isPlayerFirst ? Tactic.SECOND : Tactic.FIRST;
	}
    };

    final private int winScore = 30;
    final private int power;

    private Difficulty(final int power) {
	this.power = power;
    }
    
    public int getPower(){
	return this.power;
    }

    public int getWinScore() {
	return winScore * this.power;
    }

    abstract Tactic getFirstTacticState(final boolean isPlayerFirst);
}