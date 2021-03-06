package com.egtinteractive.tictactoe.tests.game;

import static com.egtinteractive.tictactoe.resources.BundleUtils.RESOURCE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.state.StateMachine;
import com.egtinteractive.tictactoe.tests.resources.ListIO;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class PlayerLoseTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 3;
    private final Integer[] position = { 0, 1, 2, 3 };
    private final String[] userName = null;

    @Test(groups = "gaming")
    public void playerLose() {
	setList(money, difficulty, position, userName);
	final GameMachine machine = machineBuilder();
	assertEquals(machine.getState(), StateMachine.STAND_BY);
	try (ListIO io = (ListIO) machine.getIO()) {
	    assertTrue(RESOURCE.getString("YOU_LOST_MESSAGE").equals(io.getLastMessage()));
	}
    }
}