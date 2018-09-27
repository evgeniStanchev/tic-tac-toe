package com.egtinteractive.tictactoe.tests.nulls;

import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class MachineTakeNullValueForMoneyTest extends SetupTests {
    private final Integer[] money = { null, null };
    private final Integer difficulty = null;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups="nulls")
    public void nullMoney() {
	setList(money, difficulty, position, userName);
	machineBuilder();
    }
}