package com.egtinteractive.tictactoe.tests.machine;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class GetChange extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups="machine")
    public void getChange() {
	setList(money, difficulty, position, userName);
	final GameMachine machine = machineBuilder();
	final int expected = machine.getCredit().intValue();
	final int actual = machine.ejectCredit().intValue();
	assertEquals(actual, expected);
    }
}