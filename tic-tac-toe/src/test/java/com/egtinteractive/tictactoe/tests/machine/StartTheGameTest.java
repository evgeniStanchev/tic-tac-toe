package com.egtinteractive.tictactoe.tests.machine;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.State;
import com.egtinteractive.tictactoe.state.StateMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;
import com.egtinteractive.tictactoe.state.GameMachine;

public final class StartTheGameTest extends SetupTests {
    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups="machine")
    public void startTheGameWithNotEnoughMoney() {
	setList(money, difficulty, position, userName);
	final GameMachine gameMachine = machineBuilder();
	assertFalse(gameMachine.startTheGame());
    }

    @Test(groups="machine")
    public void startTheGameWithEnoughMoney() {
	setList(money, difficulty, position, userName);
	final GameMachine gameMachine = machineBuilder();
	final State expected = StateMachine.PLAYING;
	final State actual = gameMachine.getState();
	assertEquals(actual, expected);
    }
}