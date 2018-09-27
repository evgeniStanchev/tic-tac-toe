package com.egtinteractive.tictactoe.tests.machine;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public final class PutCoinTest extends SetupTests {

    private final Integer[] money = { 5, 2, 1 };
    private final Integer difficulty = null;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups="machine")
    public void putCoinIncreaseTheCreditCorrectly() {
	setList(money, difficulty, position, userName);
	final GameMachine gm = machineBuilder();
	int expected = 0;
	for (Integer money : this.money) {
	    expected += money;
	}
	final int actual = gm.getCredit().intValue();
	assertEquals(actual, expected);
    }
}