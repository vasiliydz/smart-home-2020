package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.MessageSender;

public class SmsSender implements MessageSender {

	@Override
	public void send(String message) {
		System.out.println("Sent sms: " + message);
	}
}
