package com.egtinteractive.tictactoe.tests.database;

import static org.testng.Assert.assertEquals;
import java.util.UUID;
import org.testng.annotations.Test;
import com.egtinteractive.tictactoe.resources.BundleUtils;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class BothOfTheTablesIncreaseRows extends SetupTests {
    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 1, 4, 7 };
    private final String[] userName = { UUID.randomUUID().toString() };

    @Test(groups = "database")
    public void playerWinIncreaseBothTables() {
	setList(money, difficulty, position, userName);
	@SuppressWarnings("unused")
	GameMachine machine = machineBuilder();
	final int playersCount = getRowsCount(BundleUtils.QUERY.getString("countPlayersTable"));
	final int gamesCount = getRowsCount(BundleUtils.QUERY.getString("countGamesTable"));
	assertEquals(playersCount, gamesCount);
	final int expectedIncreasedPlayersCount = playersCount + 1;
	final int expectedIncreasedGamesCount = gamesCount + 1;
	final String[] newUserName = { UUID.randomUUID().toString() };
	setList(money, difficulty, position, newUserName);
	machine = machineBuilder();
	final int actualIncreasedPlayersCount = getRowsCount(BundleUtils.QUERY.getString("countPlayersTable"));
	final int actualIncreaseGamesCount = getRowsCount(BundleUtils.QUERY.getString("countGamesTable"));
	assertEquals(expectedIncreasedPlayersCount, actualIncreasedPlayersCount);
	assertEquals(expectedIncreasedGamesCount, actualIncreaseGamesCount);
	assertEquals(expectedIncreasedPlayersCount, expectedIncreasedGamesCount);
	assertEquals(actualIncreasedPlayersCount, actualIncreaseGamesCount);
    }
}