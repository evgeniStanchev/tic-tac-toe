package com.egtinteractive.tictactoe.tests.game;

import static com.egtinteractive.tictactoe.resources.BundleUtils.RESOURCE;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.ListIO;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class EasyBotWinTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 3, 4, 8 };
    private final String[] userName = null;

    @Test(groups = "gaming")
    public void playerCanLose() {
	setList(money, difficulty, position, userName);
	final GameMachine machine = machineBuilder();
	try (ListIO io = (ListIO) machine.getIO()) {
	    assertTrue(RESOURCE.getString("YOU_LOST_MESSAGE").equals(io.getLastMessage()));
	}
    }
}