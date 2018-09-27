package com.egtinteractive.tictactoe.tests.exceptipons;

import static org.testng.Assert.assertEquals;
import java.util.UUID;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.state.State;
import com.egtinteractive.tictactoe.state.StateMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class ScoringWithTheSameNameTest extends SetupTests {

    @DataProvider
    public Object[][] ttt() {
	final String name = UUID.randomUUID().toString();
	final Integer[] money = { 5, 5 };
	final Integer difficulty = 1;
	final String[] userName = { name };
	return new Object[][] { { name, money, difficulty, new Integer[] { 1, 4, 7 }, userName },
		{ name, money, difficulty, new Integer[] { 2, 4, 6 }, userName } };
    }

    @Test(dataProvider = "ttt", groups = "exceptions")
    public void usingtheSameNameTwice(final String name, final Integer[] money, final Integer difficulty,
	    final Integer[] position, final String[] userName) {
	setList(money, difficulty, position, userName);
	final GameMachine gm = machineBuilder();
	setList(money, difficulty, position, userName);
	machineBuilder();
	final State actual = gm.getState();
	final State expected = StateMachine.STAND_BY;
	assertEquals(actual, expected);
    }
}