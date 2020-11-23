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
		Assertions.assertTrue(testSensorCommand.isExecuted());
	}

	@Test
	public void programmableRCDoesNodSendCommandWhenOtherButtonIsPressed() {
		// given
		TestSensorCommand testSensorCommand = new TestSensorCommand();
		String commandButton = "A";
		String pressedButton = "B";
		String rcId = "1";
		Map<String, SensorCommand> commands = new HashMap<>();
		commands.put(commandButton, testSensorCommand);
		ProgrammableRemoteControl programmableRC = new ProgrammableRemoteControl(rcId, commands);
		// when
		programmableRC.onButtonPressed(pressedButton, rcId);
		// then
		Assertions.assertFalse(testSensorCommand.isExecuted());
	}
}
