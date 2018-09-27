package com.egtinteractive.tictactoe.IO;

public interface IO extends AutoCloseable {

    Integer nextInt();

    String next();

    boolean hasNext();

    default void write(final String string) {
	System.out.println(string);
    }

    default void writeErr(final String string) {
	System.err.println(string);
    }
}