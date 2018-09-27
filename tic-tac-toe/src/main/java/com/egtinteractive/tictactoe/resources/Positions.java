package com.egtinteractive.tictactoe.resources;

public enum Positions {
    /**
     * 
     * There is a 3 types of positions:
     * 
     * CORNER - Corners;
     * 
     * MIDDLE - Middle;
     * 
     * CENTER - Center;
     * 
     * For the tactics we need also:
     * 
     * REVERSE_CORNER - Corners in reverse order;
     * 
     * ALL_POSITIONS - All of the positions in ascending order;
     */
    CORNER(new int[] { 0, 2, 6, 8 }), //
    MIDDLE(new int[] { 1, 3, 5, 7 }), //
    CENTER(new int[] { 4 }), //
    REVERSE_CORNER(new int[] { 8, 6, 2, 0 }), //
    ALL_POSITIONS(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });

    private int[] positions;

    public int getLength() {
	return this.positions.length;
    }

    private Positions(final int[] type) {
	this.positions = type;
    }

    public static int getCenterPosition() {
	return CENTER.getPosition(0);
    }

    public int getPosition(final int index) {
	return this.positions[index];
    }
}