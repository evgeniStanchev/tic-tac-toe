package com.egtinteractive.tictactoe.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class ScannerFileIO extends ScannerIO {
    public ScannerFileIO(final File file) throws FileNotFoundException {
	super(new Scanner(file));
    }
}
