package com.egtinteractive.tictactoe.tests.resources;

import static com.egtinteractive.tictactoe.resources.BundleUtils.RESOURCE;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.egtinteractive.tictactoe.IO.IO;

public final class ListIO implements IO {

    private final List<String> list;
    private final Iterator<String> it;

    private String winMessage;

    public ListIO(final List<String> list) {
	this.list = list;
	this.it = list.iterator();
    }

    public List<String> getList() {
	return this.list;
    }

    @Override
    public Integer nextInt() {
	try {
	    return Integer.valueOf(it.next());
	} catch (NumberFormatException e) {
	    return null;
	}
    }

    @Override
    public void write(final String string) {
	if (Objects.equals(string, RESOURCE.getString("YOU_WON_MESSAGE"))
		|| Objects.equals(string, RESOURCE.getString("YOU_LOST_MESSAGE"))) {
	    this.winMessage = string;
	}
	System.out.println(string);
    }

    @Override
    public void writeErr(final String string) {
	System.err.println(string);
    }

    @Override
    public String next() {
	try {
	    return it.next();
	} catch (NoSuchElementException e) {
	    return null;
	}
    }

    @Override
    public boolean hasNext() {
	return it.hasNext();
    }

    @Override
    public void close() {
    }

    public String getLastMessage() {
	return this.winMessage;
    }
}