package com.egtinteractive.tictactoe.score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.egtinteractive.tictactoe.IO.IO;

import static com.egtinteractive.tictactoe.resources.BundleUtils.*;

public final class ConnectionBuilder {

    private final String username;
    private final String password;
    private String url;

    ConnectionBuilder() {
	this.url = CONFIG.getString("driverProtocol") + CONFIG.getString("database");
	this.username = CONFIG.getString("username");
	this.password = CONFIG.getString("password");
    }

    void printScore(final IO io) {
	int currentCount = 1;
	io.write(System.lineSeparator() + "****************************");
	io.write("********Hall of Fame********");
	io.write("****************************");
	try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
		Statement statement = connection.createStatement()) {
	    statement.executeUpdate(QUERY.getString("useDatabase"));
	    final String query = QUERY.getString("selectQuery");
	    try (ResultSet rs = statement.executeQuery(query)) {
		while (rs.next()) {
		    io.write(currentCount + "." + rs.getString("Nickname") + " - " + rs.getInt("Score"));
		    currentCount++;
		}
		io.write("****************************" + System.lineSeparator());
	    }
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private boolean thisNameExistsAlready(String name) throws SQLException {
	try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
		Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection
			.prepareStatement(QUERY.getString("preparedSelectQuery"))) {
	    preparedStatement.setString(1, name);
	    ResultSet rs = preparedStatement.executeQuery();
	    return rs.next();
	}
    }

    boolean updateOnWinQuery(final String name, final int score) {
	try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
		Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection
			.prepareStatement(QUERY.getString("preparedInsertQuery"));
		PreparedStatement preparedGamesStatement = connection
			.prepareStatement(QUERY.getString("preparedInsertGamesQuery"))) {
	    if (thisNameExistsAlready(name)) {
		return false;
	    }
	    connection.setAutoCommit(false);
	    statement.executeUpdate(QUERY.getString("useDatabase"));
	    preparedGamesStatement.setBoolean(1, true);
	    preparedGamesStatement.executeUpdate();
	    preparedStatement.setString(1, name);
	    preparedStatement.setInt(2, score);
	    preparedStatement.executeUpdate();
	    connection.commit();
	    return true;
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    boolean updateOnLoseQuery(final String name) {
	try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
		Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = connection
			.prepareStatement(QUERY.getString("preparedInsertQuery"));
		PreparedStatement preparedGamesStatement = connection
			.prepareStatement(QUERY.getString("preparedInsertGamesQuery"))) {
	    connection.setAutoCommit(false);
	    statement.executeUpdate(QUERY.getString("useDatabase"));
	    preparedGamesStatement.setBoolean(1, false);
	    preparedGamesStatement.executeUpdate();
	    preparedStatement.setString(1, name);
	    preparedStatement.setInt(2, 0);
	    preparedStatement.executeUpdate();
	    connection.commit();
	    return true;
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }
}