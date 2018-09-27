package com.egtinteractive.tictactoe.state;

import java.math.BigDecimal;

import com.egtinteractive.tictactoe.coin.Coin;
import com.egtinteractive.tictactoe.games.tictactoe.TicTacToe;

public interface State {
    Coin putCoin(final GameMachine tttMachine, final Coin coin);

    boolean startTheGame(final GameMachine tttMachine, final TicTacToe ttt);

    boolean quitPlaying(final GameMachine tttMachine);

    int makeMove(final GameMachine tttMachine, final int index);

    String setResult(final GameMachine tttMachine, final int score, final boolean didPlayerWon);

    BigDecimal ejectCoins(final GameMachine tttMachine);
}