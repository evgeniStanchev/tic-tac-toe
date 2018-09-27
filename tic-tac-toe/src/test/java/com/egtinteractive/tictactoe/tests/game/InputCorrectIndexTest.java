package com.egtinteractive.tictactoe.tests.game;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class InputCorrectIndexTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 4 };
    private final String[] userName = null;

    @Test(groups = "gaming")
    public void inputCorrectIndex() {
	setList(money, difficulty, position, userName);
	final GameMachine machine = machineBuilder();

	if (machine.getCurrentTTT().isPlayerFirst()) {
	    assertEquals(machine.getCurrentTTT().getField().getEmptyPositionCount(),
		    machine.getCurrentTTT().getField().getMaxPositionCount() - 2);
	} else {
	    assertEquals(machine.getCurrentTTT().getField().getEmptyPositionCount(),
		    machine.getCurrentTTT().getField().getMaxPositionCount() - 3);
	}
    }
}