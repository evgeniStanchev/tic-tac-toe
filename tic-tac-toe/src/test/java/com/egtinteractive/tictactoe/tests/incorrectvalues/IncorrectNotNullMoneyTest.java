package com.egtinteractive.tictactoe.tests.incorrectvalues;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.state.StateMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class IncorrectNotNullMoneyTest extends SetupTests {

    private final Integer[] money = { 0, 7, 8, 9 };
    private final Integer difficulty = null;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups = "nulls")
    public void nullDifficulty() {
	setList(money, difficulty, position, userName);
	final GameMachine gm = machineBuilder();
	final int expected = 0;
	final int actual = gm.getCredit().intValue();
	assertEquals(actual, expected);
	assertEquals(gm.getState(), StateMachine.STAND_BY);
    }
}