package com.egtinteractive.tictactoe.state;

import java.math.BigDecimal;
import java.util.Objects;
import static com.egtinteractive.tictactoe.resources.BundleUtils.*;

import com.egtinteractive.tictactoe.coin.Coin;
import com.egtinteractive.tictactoe.games.tictactoe.TicTacToe;
import com.egtinteractive.tictactoe.score.Score;

public enum StateMachine implements State {

    STAND_BY {
	@Override
	public boolean startTheGame(final GameMachine machine, final TicTacToe ttt) {
	    if (machine.getCredit().compareTo(machine.getGamePrice()) < 0) {
		machine.getIO().writeErr(RESOURCE.getString("NOT_ENOUGH_CREDIT_ERROR_MESSAGE")
			+ machine.getGamePrice().subtract(machine.getCredit()).intValue());
		return false;
	    }
	    machine.setCredit(machine.getCredit().subtract(machine.getGamePrice()));
	    machine.setState(PLAYING);
	    machine.getIO().write(RESOURCE.getString("START_GAME_CORRECT_MESSAGE"));
	    ttt.chooseDifficulty();
	    ttt.playGame();
	    if (ttt.isGameEnded()) {
		machine.quitTheGame();
	    }
	    return true;
	}
    },

    PLAYING {
	@Override
	public int makeMove(final GameMachine machine, final int index) {
	    return index;
	}

	@Override
	public boolean quitPlaying(final GameMachine machine) {
	    if (machine.getCurrentTTT().isPlayerWinner()) {
		machine.setState(SCORING);
		machine.setResult(machine.getCurrentTTT(), true);
	    } else {
		machine.setState(SCORING);
		machine.setResult(machine.getCurrentTTT(), false);
		machine.getIO().write(RESOURCE.getString("QUIT_PLAYING_CORRECT_MESSAGE"));
	    }
	    machine.start();
	    return true;
	}
    },

    SCORING {
	@Override
	public String setResult(final GameMachine machine, final int currentScore, final boolean didPlayerWon) {
	    String name = "";
	    if (didPlayerWon) {
		machine.getIO().write(RESOURCE.getString("WRITE_YOUR_NICKNAME_MESSAGE"));
		name = machine.getIO().next();
	    }

	    final Score score = new Score(machine.getIO());
	    score.insertInfo(name, currentScore, didPlayerWon);
	    score.leaderBoard(machine.getIO());
	    machine.setState(STAND_BY);
	    machine.startTheGame();
	    return name;
	}
    };

    @Override
    public int makeMove(final GameMachine machine, final int index) {
	machine.getIO().writeErr(RESOURCE.getString("MAKE_MOVE_ERROR_MESSAGE"));
	return 0;
    }

    @Override
    public BigDecimal ejectCoins(final GameMachine machine) {
	BigDecimal change = BigDecimal.ZERO;
	if (Objects.equals(machine.getCredit().intValue(), 0)) {
	    machine.getIO().write(RESOURCE.getString("NO_COINS_ERROR_MESSAGE"));
	} else {
	    change = change.add(machine.getCredit());
	    machine.setCredit(BigDecimal.ZERO);
	    machine.getIO().write(
		    RESOURCE.getString("EJECT_COINS_CORRECT_MESSAGE") + change.intValue() + System.lineSeparator());
	    machine.getIO().writeErr(RESOURCE.getString("NOT_ENOUGH_CREDIT_ERROR_MESSAGE") + machine.getGamePrice());
	    machine.getIO().write(RESOURCE.getString("CHOICE_MESSAGE"));
	}
	return change;
    }

    @Override
    public Coin putCoin(final GameMachine machine, Coin coin) {
	while (machine.getCredit().compareTo(machine.getGamePrice()) < 0 && machine.getIO().hasNext()) {
	    final String next = machine.getIO().next();
	    try {
		coin = Coin.getValue(Integer.valueOf(next));
		if (Objects.isNull(coin)) {
		    machine.getIO().writeErr(RESOURCE.getString("WRONG_INPUT_ERROR_MESSAGE"));
		} else {
		    machine.getIO()
			    .writeErr(RESOURCE.getString("CREDIT_INCREASED_MESSAGE") + coin.getValue().intValue());
		    machine.increaseCredit(coin.getValue());
		    final int subtract = machine.getGamePrice().subtract(machine.getCredit()).intValue();
		    if (subtract <= 0) {
			break;
		    } else {
			machine.getIO().writeErr(RESOURCE.getString("NOT_ENOUGH_CREDIT_ERROR_MESSAGE") + subtract);
		    }
		}
	    } catch (NumberFormatException e) {
		if ("e".equalsIgnoreCase(next)) {
		    machine.ejectCredit();
		} else {
		    machine.getIO().writeErr(RESOURCE.getString("WRONG_INPUT_ERROR_MESSAGE"));
		}
	    }

	}
	machine.startTheGame();
	return coin;
    }

    @Override
    public boolean startTheGame(final GameMachine machine, final TicTacToe ttt) {
	machine.getIO().writeErr(RESOURCE.getString("START_GAME_ERROR_MESSAGE"));
	return false;
    }

    @Override
    public String setResult(final GameMachine machine, final int score, final boolean didPlayerWon) {
	machine.getIO().writeErr(RESOURCE.getString("SET_SCORE_ERROR_MESSAGE"));
	return null;
    }

    @Override
    public boolean quitPlaying(final GameMachine machine) {
	machine.getIO().writeErr(RESOURCE.getString("QUIT_PLAYING_ERROR_MESSAGE"));
	return false;
    }
}