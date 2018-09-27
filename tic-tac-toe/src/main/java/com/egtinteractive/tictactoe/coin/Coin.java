package com.egtinteractive.tictactoe.coin;

import java.math.BigDecimal;

public enum Coin {
    ONE(new BigDecimal("1.00")), //
    TWO(new BigDecimal("2.00")), //
    THREE(new BigDecimal("3.00")), //
    FOUR(new BigDecimal("4.00")), //
    FIVE(new BigDecimal("5.00"));

    private final BigDecimal value;

    private Coin(final BigDecimal value) {
	this.value = value;
    }

    public static Coin getValue(final int value) {
	for (Coin coin : Coin.values()) {
	    if (coin.value.intValue() == value) {
		return coin;
	    }
	}
	return null;
    }

    public BigDecimal getValue() {
	return this.value;
    }
}