package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.ProgrammableRemoteControl;
import ru.sbt.mipt.oop.smarthome.commands.SensorCommand;
import ru.sbt.mipt.oop.tests.utils.TestSensorCommand;

import java.util.HashMap;
import java.util.Map;

public class ProgrammableRemoteControlTest {

	@Test
	public void programmableRCSendsCommandByButton() {
		// given
		TestSensorCommand testSensorCommand = new TestSensorCommand();
		String button = "A";
		String rcId = "1";
		Map<String, SensorCommand> commands = new HashMap<>();
		commands.put(button, testSensorCommand);
		ProgrammableRemoteControl programmableRC = new ProgrammableRemoteControl(rcId, commands);
		// when
		programmableRC.onButtonPressed(button, rcId);
		// then
		Assertions.assertTrue(testSensorCommand.isSent());
	}

	@Test
	public void programmableRCSendsCommandWhenItIsSetAndButtonIsPressed() {
		// given
		TestSensorCommand testSensorCommand = new TestSensorCommand();
		String button = "A";
		String rcId = "1";
		ProgrammableRemoteControl programmableRC = new ProgrammableRemoteControl(rcId);
		programmableRC.setCommand(button, testSensorCommand);
		// when
		programmableRC.onButtonPressed(button, rcId);
		// then
		Assertions.assertTrue(testSensorCommand.isSent());
	}

	@Test
	public void programmableRCThrowsExceptionWhenCommandIsSetToIllegalButton() {
		// given
		TestSensorCommand testSensorCommand = new TestSensorCommand();
		String illegalButton = "X";
		String rcId = "1";
		ProgrammableRemoteControl programmableRC = new ProgrammableRemoteControl(rcId);
		// then
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> programmableRC.setCommand(illegalButton, testSensorCommand));
	}
}
