package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.smarthome.MessageSender;

public class TestMessageSender implements MessageSender {
	private int sentMessages;

	public TestMessageSender() {
		sentMessages = 0;
	}

	public int countSentMessages() {
		return sentMessages;
	}

	@Override
	public void send(String message) {
		sentMessages++;
	}
}
