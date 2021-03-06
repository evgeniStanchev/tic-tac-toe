package com.egtinteractive.tictactoe.tests.machine;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.state.State;
import com.egtinteractive.tictactoe.state.StateMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class StandByTest extends SetupTests {

    private final Integer[] money = { 5 };
    private final Integer difficulty = null;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups="machine")
    public void correctInput() {
	setList(money, difficulty, position, userName);
	final GameMachine gm = machineBuilder();
	final State actual = gm.getState();
	final State expected = StateMachine.STAND_BY;
	assertEquals(actual, expected);
    }
}