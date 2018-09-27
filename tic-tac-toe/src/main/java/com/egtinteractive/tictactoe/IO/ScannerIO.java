package com.egtinteractive.tictactoe.IO;

import java.util.Scanner;

public abstract class ScannerIO implements IO {

    private final Scanner reader;

    ScannerIO(final Scanner reader) {
	this.reader = reader;
    }

    protected Scanner getReader() {
	return this.reader;
    }

    @Override
    public void close() {
	this.reader.close();
    }

    @Override
    public Integer nextInt() {
	return getReader().nextInt();
    }

    @Override
    public String next() {
	return getReader().next();
    }

    @Override
    public boolean hasNext() {
	return getReader().hasNext();
    }

}