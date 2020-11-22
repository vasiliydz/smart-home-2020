package ru.sbt.mipt.oop.smarthome;

import rc.RemoteControl;
import ru.sbt.mipt.oop.smarthome.commands.EmptyCommand;
import ru.sbt.mipt.oop.smarthome.commands.SensorCommand;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProgrammableRemoteControl implements RemoteControl {
	private final Collection<String> availableButtons =
			Arrays.asList("A", "B", "C", "D", "1", "2", "3", "4");
	private Map<String, SensorCommand> buttonCommandMap;
	private final String rcId;

	public ProgrammableRemoteControl(String rcId, Map<String, SensorCommand> commands) {
		this.rcId = rcId;
		buttonCommandMap = new HashMap<>();
		for (String button: availableButtons) {
			SensorCommand command = commands.getOrDefault(button, new EmptyCommand());
			buttonCommandMap.put(button, command);
		}
	}

	public ProgrammableRemoteControl(String rcId) {
		this(rcId, new HashMap<>());
	}

	public void setCommand(String buttonCode, SensorCommand command) {
		if (!availableButtons.contains(buttonCode)) {
			throw new IllegalArgumentException("Button code " + buttonCode + " is not supported");
		}
		buttonCommandMap.put(buttonCode, command);
	}

	@Override
	public void onButtonPressed(String buttonCode, String rcId) {
		if (this.rcId.equals(rcId)) {
			if (availableButtons.contains(buttonCode)) {
				buttonCommandMap.get(buttonCode).send();
			}
		}
	}
}
