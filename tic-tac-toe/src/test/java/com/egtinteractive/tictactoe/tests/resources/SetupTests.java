package com.egtinteractive.tictactoe.tests.resources;
//Трябва да оправя тестовете:
//Създава се на всеки тест файл ресурси 
//и се затварят след теста 
//отварянето на ресурс в текущия клас е грешно, тъй като се предава и след това се затваря
//Реално не могат да се правят никакви промени във файла, а той се предава затворен

import static com.egtinteractive.tictactoe.resources.BundleUtils.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.testng.annotations.DataProvider;

import com.egtinteractive.tictactoe.IO.IO;
import com.egtinteractive.tictactoe.coin.Coin;
import com.egtinteractive.tictactoe.state.GameMachine;
import com.egtinteractive.tictactoe.state.GameMachine.MachineBuilder;

public class SetupTests {
    private static final String URL = CONFIG.getString("driverProtocol") + CONFIG.getString("database");
    private static final String USERNAME = CONFIG.getString("username");
    private static final String PASSWORD = CONFIG.getString("password");
    public final List<String> list = new ArrayList<>();

    public void setList(final Integer[] money, final Integer difficulty, final Integer[] position,
	    final String[] userName) {
	if (!list.isEmpty()) {
	    list.clear();
	}
	for (Integer integer : money) {
	    if (Objects.nonNull(integer)) {
		list.add(integer.toString());
	    }
	}
	if (Objects.nonNull(difficulty)) {
	    list.add(difficulty.toString());
	}
	if (Objects.nonNull(position)) {
	    for (Integer integer : position) {
		list.add(integer.toString());
	    }
	}
	if (Objects.nonNull(userName)) {
	    for (String string : userName) {
		list.add(string);
	    }
	}
    }

    @DataProvider(name = "machine")
    public Object[][] createMachineData() {
	return new Object[][] { { machineBuilder() } };
    }

    protected void putEnoughMoneyForPlaying(final GameMachine machine) {
	while (machine.getCredit().compareTo(machine.getGamePrice()) < 0) {
	    machine.putCoin(Coin.FIVE);
	}
    }

    protected GameMachine machineBuilder() {
	try (final IO io = new ListIO(list)) {
	    final MachineBuilder builder = new MachineBuilder(io);
	    final GameMachine gameMachine = new GameMachine(builder);
	    return gameMachine;
	} catch (final Exception e) {
	    throw new RuntimeException(e);
	}
    }

    protected int getRowsCount(final String query) {
	try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Statement statement = connection.createStatement()) {
	    try (ResultSet rs = statement.executeQuery(query)) {
		rs.next();
		return rs.getInt("rowcount");
	    }
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }
}