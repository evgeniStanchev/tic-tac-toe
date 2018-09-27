package com.egtinteractive.tictactoe.IO;

import java.util.Scanner;

public final class ScannerConsoleIO extends ScannerIO {
    public ScannerConsoleIO() {
	super(new Scanner(System.in));
    }
}