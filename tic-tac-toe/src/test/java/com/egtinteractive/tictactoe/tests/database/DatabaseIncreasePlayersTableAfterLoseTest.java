package com.egtinteractive.tictactoe.tests.database;

import static org.testng.Assert.assertEquals;
import java.util.UUID;
import org.testng.annotations.Test;
import com.egtinteractive.tictactoe.resources.BundleUtils;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class DatabaseIncreasePlayersTableAfterLoseTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 3;
    private final Integer[] position = { 1, 7, 6 };
    private final String[] userName = { UUID.randomUUID().toString() };

    @Test(groups = "database")
    public void playerLoseGamesRowsIncrease() {
	setList(money, difficulty, position, userName);
	@SuppressWarnings("unused")
	GameMachine machine = machineBuilder();
	final int expected = getRowsCount(BundleUtils.QUERY.getString("countPlayersTable")) + 1;
	final String[] newUserName = { UUID.randomUUID().toString() };
	setList(money, difficulty, position, newUserName);
	machine = machineBuilder();
	final int count = getRowsCount(BundleUtils.QUERY.getString("countPlayersTable"));
	assertEquals(expected, count);
    }
}