package com.egtinteractive.tictactoe.tests.nulls;

import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class MachineTakeNullValueForDifficultyTest extends SetupTests {

    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = null;
    private final Integer[] position = { 1, 2, 3, 4 };
    private final String[] userName = null;

    @Test(groups="nulls")
    public void nullDifficulty() {
	setList(money, difficulty, position, userName);
	machineBuilder();
    }
}