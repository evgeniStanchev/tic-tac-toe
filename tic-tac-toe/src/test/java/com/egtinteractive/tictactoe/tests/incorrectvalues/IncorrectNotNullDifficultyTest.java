package com.egtinteractive.tictactoe.tests.incorrectvalues;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class IncorrectNotNullDifficultyTest extends SetupTests {
    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = null;
    private final Integer[] position = { 1 };
    private final String[] userName = null;

    @Test(groups = "nulls")
    public void nullDifficulty() {
	setList(money, difficulty, position, userName);
	final GameMachine gm = machineBuilder();
	int expected = 0;
	if (!gm.getCurrentTTT().isPlayerFirst()) {
	    expected = 1;
	}
	final int actual = gm.getCurrentTTT().getField().getMaxPositionCount()
		- gm.getCurrentTTT().getField().getEmptyPositionCount();
	assertEquals(actual, expected);
    }
}