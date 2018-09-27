package com.egtinteractive.tictactoe.launcher;

import static com.egtinteractive.tictactoe.resources.BundleUtils.RESOURCE;
import com.egtinteractive.tictactoe.IO.IO;
import com.egtinteractive.tictactoe.IO.ScannerConsoleIO;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.state.GameMachine.MachineBuilder;

public class Launcher {
    static {
	RESOURCE.getString("WELCOME_MESSAGE");
    };

    public static void main(String[] args) {
	try (IO output = new ScannerConsoleIO()) {
	    final MachineBuilder machineBuilder = new MachineBuilder(output);
	    new GameMachine(machineBuilder);
	} catch (final Exception e) {
	    throw new RuntimeException(e);
	}
    }
}