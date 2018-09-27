package com.egtinteractive.tictactoe.tests.machine;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class EjectMoney extends SetupTests {

    private final Integer[] money = { 5, 1, 1 };
    private final Integer difficulty = null;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups="machine")
    public void ejectCredit() {
	setList(money, difficulty, position, userName);
	String eject = "e";
	list.add(eject);
	final GameMachine machine = machineBuilder();

	final int expected = BigDecimal.ZERO.intValue();
	final int actual = machine.getCredit().intValue();
	assertEquals(actual, expected);
    }
}