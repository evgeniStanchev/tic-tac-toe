package com.egtinteractive.tictactoe.tests.exceptipons;

import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.ListIO;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class IsPlayerWinnerWhenTheGameIsNotEnded extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 5 };
    private final String[] userName = null;

    @Test(groups = "exceptions")
    public void isPlayerWinner() {
	setList(money, difficulty, position, userName);
	final GameMachine machine = machineBuilder();
	try (ListIO io = (ListIO) machine.getIO()) {
	    assertNull(io.getLastMessage());
	}
    }
}