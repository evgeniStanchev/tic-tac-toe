package com.egtinteractive.tictactoe.tests.nulls;

import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class MachineTakeNullValueForPositionsTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = null;
    private final String[] userName = null;

    @Test(groups="nulls")
    public void nullPositions() {
	setList(money, difficulty, position, userName);
	machineBuilder();
    }
}