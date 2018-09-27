package com.egtinteractive.tictactoe.tests.game;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class InputIndexOutOfBoundsTest extends SetupTests {
    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 10 };
    private final String[] userName = null;

    @Test(groups = "gaming")
    public void inputIndexOutOfBounds() {

	setList(money, difficulty, position, userName);

	final GameMachine machine = machineBuilder();
	final int expected = machine.getCurrentTTT().getField().getMaxPositionCount();
	final int actual = machine.getCurrentTTT().getField().getEmptyPositionCount();
	if (machine.getCurrentTTT().isPlayerFirst()) {
	    assertEquals(actual, expected);
	}
    }
}