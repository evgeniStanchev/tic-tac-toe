package com.egtinteractive.tictactoe.tests.machine;

import static org.testng.Assert.assertEquals;
import java.util.UUID;
import org.testng.annotations.Test;

import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.state.State;
import com.egtinteractive.tictactoe.state.StateMachine;
import com.egtinteractive.tictactoe.tests.resources.SetupTests;

public class StandByAfterPlayingTest extends SetupTests {

    private final String name = UUID.randomUUID().toString();
    private final Integer[] money = { 5, 5 };
    private final Integer difficulty = 1;
    private final Integer[] position = { 1, 4, 7 };
    private final String[] userName = { name };

    @Test(groups="machine")
    public void correctInput() {
	setList(money, difficulty, position, userName);
	final GameMachine gm = machineBuilder();
	final State actual = gm.getState();
	final State expected = StateMachine.STAND_BY;
	assertEquals(actual, expected);
    }
}