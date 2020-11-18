package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.List;

public class ListMessageSender implements MessageSender {
	private final List<MessageSender> senders;

	public ListMessageSender(List<MessageSender> senders) {
		this.senders = new ArrayList<>(senders);
	}

	@Override
	public void send(String message) {
		for (MessageSender sender : senders) {
			sender.send(message);
		}
	}
}
