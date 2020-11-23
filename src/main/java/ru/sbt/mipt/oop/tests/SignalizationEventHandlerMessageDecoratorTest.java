package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.eventhandlers.SignalizationEventHandlerMessageDecorator;
import ru.sbt.mipt.oop.smarthome.devices.signalization.*;
import ru.sbt.mipt.oop.tests.utils.TestActionable;
import ru.sbt.mipt.oop.tests.utils.TestEventActedToAll;
import ru.sbt.mipt.oop.tests.utils.TestEventHandler;
import ru.sbt.mipt.oop.tests.utils.TestMessageSender;

public class SignalizationEventHandlerMessageDecoratorTest {

	@Test
	public void eventsAreHandledWhenSignalizationIsDeactivated() {
		// given
		Signalization signalization = new Signalization();
		TestActionable testActionable = new TestActionable("1");
		TestEventHandler testEventHandler = new TestEventHandler(testActionable);
		TestMessageSender sender = new TestMessageSender();
		SignalizationEventHandlerMessageDecorator handler =
				new SignalizationEventHandlerMessageDecorator(testEventHandler, signalization, sender);
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertTrue(testActionable.isActed());
	}

	@Test
	public void messagesNotSentWhenSignalizationIsDeactivated() {
		// given
		Signalization signalization = new Signalization();
		TestActionable actionable = new TestActionable("1");
		TestEventHandler testEventHandler = new TestEventHandler(actionable);
		TestMessageSender sender = new TestMessageSender();
		SignalizationEventHandlerMessageDecorator handler =
				new SignalizationEventHandlerMessageDecorator(testEventHandler, signalization, sender);
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertEquals(0, sender.countSentMessages());
	}

	@Test
	public void eventsAreNotHandledWhenSignalizationIsActivatedAndGotEvent() {
		// given
		Signalization signalization = new Signalization();
		TestActionable testActionable = new TestActionable("1");
		TestEventHandler testEventHandler = new TestEventHandler(testActionable);
		TestMessageSender sender = new TestMessageSender();
		SignalizationEventHandlerMessageDecorator handler =
				new SignalizationEventHandlerMessageDecorator(testEventHandler, signalization, sender);
		signalization.execute(new SignalizationActivateAction("123"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertFalse(testActionable.isActed());
	}

	@Test
	public void alertMessageSentWhenSignalizationIsActivatedAndGotEvent() {
		// given
		Signalization signalization = new Signalization();
		TestActionable testActionable = new TestActionable("1");
		TestEventHandler testEventHandler = new TestEventHandler(testActionable);
		TestMessageSender sender = new TestMessageSender();
		SignalizationEventHandlerMessageDecorator handler =
				new SignalizationEventHandlerMessageDecorator(testEventHandler, signalization, sender);
		signalization.execute(new SignalizationActivateAction("123"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertTrue(sender.countSentMessages() > 0);
	}

	@Test
	public void eventsAreNotHandledWhenSignalizationIsAlertedAndGotEvent() {
		// given
		Signalization signalization = new Signalization();
		TestActionable testActionable = new TestActionable("1");
		TestEventHandler testEventHandler = new TestEventHandler(testActionable);
		TestMessageSender sender = new TestMessageSender();
		SignalizationEventHandlerMessageDecorator handler =
				new SignalizationEventHandlerMessageDecorator(testEventHandler, signalization, sender);
		signalization.execute(new SignalizationActivateAction("123"));
		signalization.execute(new SignalizationAlertAction());
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertFalse(testActionable.isActed());
	}

	@Test
	public void alertMessageSentWhenSignalizationIsAlertedAndGotEvent() {
		// given
		Signalization signalization = new Signalization();
		TestActionable testActionable = new TestActionable("1");
		TestEventHandler testEventHandler = new TestEventHandler(testActionable);
		TestMessageSender sender = new TestMessageSender();
		SignalizationEventHandlerMessageDecorator handler =
				new SignalizationEventHandlerMessageDecorator(testEventHandler, signalization, sender);
		signalization.execute(new SignalizationActivateAction("123"));
		signalization.execute(new SignalizationAlertAction());
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertTrue(sender.countSentMessages() > 0);
	}
}
