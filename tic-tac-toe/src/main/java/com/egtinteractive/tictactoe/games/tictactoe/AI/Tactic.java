package com.egtinteractive.tictactoe.games.tictactoe.AI;

import static com.egtinteractive.tictactoe.resources.Positions.*;

public enum Tactic {

    /**
     * The AI can be either: ---------------------------------------------------
     * First, Third, Fifth, Seventh, Ninth -------------------------------------
     * or Second, Forth, Sixth, Eighth -----------------------------------------
     * 
     * 1. FIRST: Best move is to get a corner ----------------------------------
     * 
     * THIRD MOVE: -------------------------------------------------------------
     * - if the center is still unmarked, the best move is to mark the center---
     * - else if the opposite corner of AI's first move is not empty, the best
     * move is to mark some other corner then in FIFTH MOVE to take random
     * available corner---------------------------------------------------------
     * - else if some NON-opposite corner of AI's first move is not empty, the
     * best move is to take the opposite corner --------------------------------
     * - else if the near middle of AI's first move is marked, the best move is
     * to take the center and then in FIFTH MOVE to take the other near middle--
     * - else if the distant middle of AI's first move is marked, the best move
     * is to take the center ---------------------------------------------------
     * 
     * 2. SECOND: - if center is unmarked, the best move is to mark the
     * center-------------------------------------------------------------------
     * - if center is marked, the best move is to mark some corner--------------
     * THEN IN FORTH MOVE:------------------------------------------------------
     * - if AI's first move is ... to be continued
     */

    FIRST {
	void makeMove(final AI bot) {
	    bot.setFirstIndex(bot.getMover().setRandomCorner(bot.getSign()));
	    bot.setTactic(Tactic.THIRD);
	}
    },
    SECOND {
	void makeMove(final AI bot) {
	    bot.setFirstIndex(bot.getField().getFirstOccurrenceOf(bot.getPlayerSign()));
	    if (!bot.getField().isCenter(bot.getFirstIndex())) {
		bot.getMover().setCenter(bot.getSign());
	    } else {
		bot.getMover().setAvailableCorner();
	    }
	    bot.setTactic(Tactic.FORTH);
	}
    },
    THIRD {
	void makeMove(final AI bot) {
	    if (bot.getField().getElement(CENTER.getPosition(0)) != Character.MIN_VALUE) {
		bot.getMover().setReverseCorner(bot.getSign(), bot.getFirstIndex());
		bot.setTactic(Tactic.NORMAL);
		return;
	    }
	    if (!bot.getField().isEmptyOppoCorner(bot.getFirstIndex())) {
		bot.getMover().setRandomCorner(bot.getSign());
		bot.setTactic(Tactic.RANDOM_CORNER);
		return;
	    }
	    if (bot.getField().getCornerSum() == 2 && bot.getField().isEmptyOppoCorner(bot.getFirstIndex())) {
		bot.getMover().setReverseCorner(bot.getSign(), bot.getFirstIndex());
		bot.setTactic(Tactic.NORMAL);
		return;
	    }
	    if (bot.getChecker().isNearMiddleSet(bot.getFirstIndex())) {
		bot.getMover().setCenter(bot.getSign());
		bot.setTactic(Tactic.NEAR_MIDDLE);
		return;
	    }
	    if (bot.getChecker().isDistantMiddleSet(bot.getFirstIndex())) {
		bot.getMover().setCenter(bot.getSign());
		bot.setTactic(Tactic.NORMAL);
		return;
	    }
	}
    },
    FORTH {
	void makeMove(final AI bot) {
	    if (bot.getMover().setRoundedCorner()) {
		bot.setTactic(Tactic.NORMAL);
		return;
	    }
	    if (bot.getField().isCorner(bot.getFirstIndex())
		    && !bot.getField().isEmptyOppoCorner(bot.getFirstIndex())) {
		bot.getMover().setMiddle(bot.getSign());
		bot.setTactic(Tactic.NORMAL);
		return;
	    }
	    bot.getMover().setSmartly();
	    bot.setTactic(Tactic.NORMAL);
	}
    },
    RANDOM_CORNER {
	void makeMove(final AI bot) {
	    bot.getMover().setRandomCorner(bot.getSign());
	    bot.setTactic(Tactic.NORMAL);
	}
    },
    NEAR_MIDDLE {
	void makeMove(final AI bot) {
	    bot.getMover().setNearMiddle(bot.getSign(), bot.getField().getMarkedCornerIndex(bot.getSign()));
	    bot.setTactic(Tactic.NORMAL);
	}
    },
    RANDOM {
	void makeMove(final AI bot) {
	    bot.getMover().setRandomly();
	}
    },
    NORMAL {
    };

    void makeMove(final AI bot) {
	bot.getMover().setSmartly();
    }
}