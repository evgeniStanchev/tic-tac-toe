package com.egtinteractive.tictactoe.tests.game;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class InputCorrectIndexTwiceTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 4, 4 };
    private final String[] userName = null;

    @Test(groups = "gaming")
    public void inputCorrectIndexTwice() {
	setList(money, difficulty, position, userName);
	final GameMachine machine = machineBuilder();
	int expected = machine.getCurrentTTT().getField().getMaxPositionCount() - 2;
	if (!machine.getCurrentTTT().isPlayerFirst())
	    expected--;

	final int actual = machine.getCurrentTTT().getField().getEmptyPositionCount();
	assertEquals(actual, expected);
    }
}