package com.egtinteractive.tictactoe.tests.game;

import static com.egtinteractive.tictactoe.resources.BundleUtils.RESOURCE;
import static org.testng.Assert.assertTrue;
import java.util.UUID;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.ListIO;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class EasyBotLoseTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 1, 4, 7 };
    private final String[] userName = { UUID.randomUUID().toString() };

    @Test(groups = "gaming")
    public void playerMustWinEasily() {
	setList(money, difficulty, position, userName);
	final GameMachine machine = machineBuilder();
	try (ListIO io = (ListIO) machine.getIO()) {
	    assertTrue(RESOURCE.getString("YOU_WON_MESSAGE").equals(io.getLastMessage()));
	}
    }
}