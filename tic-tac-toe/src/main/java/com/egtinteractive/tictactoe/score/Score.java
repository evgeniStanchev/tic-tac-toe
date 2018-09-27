package com.egtinteractive.tictactoe.score;

import java.util.NoSuchElementException;
import java.util.Objects;

import com.egtinteractive.tictactoe.IO.IO;

import static com.egtinteractive.tictactoe.resources.BundleUtils.*;

public final class Score {
    private final ConnectionBuilder connectionBuilder;
    private final IO io;

    public Score(IO io) {
	this.io = io;
	this.connectionBuilder = new ConnectionBuilder();
    }

    public void leaderBoard(final IO io) {
	this.connectionBuilder.printScore(io);
    }

    public void insertInfo(String name, final int score, final boolean didWin) {
	if (Objects.isNull(name)) {
	    throw new RuntimeException(new NoSuchElementException());
	}
	if (didWin) {
	    while (!this.connectionBuilder.updateOnWinQuery(name, score)) {
		this.io.writeErr(RESOURCE.getString("USED_NAME_ALREADY_ERROR_MESSAGE"));
		name = this.io.next();
	    }
	} else {
	    this.connectionBuilder.updateOnLoseQuery(name);
	}
    }
}