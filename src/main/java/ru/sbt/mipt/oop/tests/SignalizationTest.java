package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.signalization.SignalizationActivateEvent;
import ru.sbt.mipt.oop.smarthome.signalization.SignalizationDeactivateEvent;
import ru.sbt.mipt.oop.smarthome.signalization.SignalizationEventHandler;
import ru.sbt.mipt.oop.smarthome.signalization.StopAlertEvent;
import ru.sbt.mipt.oop.tests.utils.TestActionable;
import ru.sbt.mipt.oop.tests.utils.TestEventActedToAll;
import ru.sbt.mipt.oop.tests.utils.TestEventHandler;
import ru.sbt.mipt.oop.tests.utils.TestMessageSender;

import java.util.Arrays;

public class SignalizationTest {

	@Test
	public void eventsAreHandledWhenSignalizationIsDeactivated() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{true, true, true, true},
				getFlagsArray(actionables));
	}

	@Test
	public void messagesNotSentWhenSignalizationIsDeactivated() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertEquals(0, sender.countSentMessages());
	}

	@Test
	public void eventsAreNotHandledWhenSignalizationIsActivatedAndGotEvent() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{false, false, false, false},
				getFlagsArray(actionables));
	}

	@Test
	public void alertMessageSentWhenSignalizationIsActivatedAndGotEvent() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertEquals(1, sender.countSentMessages());
	}

	@Test
	public void eventsAreNotHandledWhenSignalizationIsActivatedAndGotWrongPassword() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		// when
		handler.handleEvent(new SignalizationDeactivateEvent("wrong password"));
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{false, false, false, false},
				getFlagsArray(actionables));
	}

	@Test
	public void alertMessageSentWhenSignalizationIsActivatedAndGotWrongPassword() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		// when
		handler.handleEvent(new SignalizationDeactivateEvent("wrong password"));
		// then
		Assertions.assertEquals(1, sender.countSentMessages());
	}

	@Test
	public void eventsAreHandledWhenSignalizationIsDeactivatedByPassword() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("12345678"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{true, true, true, true},
				getFlagsArray(actionables));
	}

	@Test
	public void alertMessageNotSentWhenSignalizationIsDeactivatedByPassword() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("12345678"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertEquals(0, sender.countSentMessages());
	}

	@Test
	public void eventsAreNotHandledWhenAlert() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("wrong"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{false, false, false, false},
				getFlagsArray(actionables));
	}

	@Test
	public void messageSentWhenAlert() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("wrong"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertEquals(2, sender.countSentMessages());
	}

	@Test
	public void eventsAreNotHandledWhenAlertGotWrongPassword() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("wrong"));
		handler.handleEvent(new StopAlertEvent("wrong"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{false, false, false, false},
				getFlagsArray(actionables));
	}

	@Test
	public void messageSentWhenAlertGotWrongPassword() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("wrong")); // sent
		// when
		handler.handleEvent(new StopAlertEvent("wrong")); // sent
		// then
		Assertions.assertEquals(2, sender.countSentMessages());
	}

	@Test
	public void eventsAreHandledWhenAlertIsStopped() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("wrong"));
		handler.handleEvent(new StopAlertEvent("12345678"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{true, true, true, true},
				getFlagsArray(actionables));
	}

	@Test
	public void messageNotSentWhenAlertIsStopped() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		TestMessageSender sender = new TestMessageSender();
		EventHandler handler = buildSignalizationHandler(home, sender);
		handler.handleEvent(new SignalizationActivateEvent("12345678"));
		handler.handleEvent(new SignalizationDeactivateEvent("wrong")); // sent
		handler.handleEvent(new StopAlertEvent("12345678"));
		// when
		handler.handleEvent(new TestEventActedToAll());
		// then
		Assertions.assertEquals(1, sender.countSentMessages());
	}




	private TestActionable[] createTestActionables() {
		return new TestActionable[] {
				new TestActionable("1"),
				new TestActionable("2"),
				new TestActionable("3"),
				new TestActionable("4")
		};
	}

	private SmartHome buildSimpleHome(Actionable[] actionables) {
		Room room1 = new Room("room1", Arrays.asList(actionables[0], actionables[1]));
		Room room2 = new Room("room2", Arrays.asList(actionables[2], actionables[3]));
		return new SmartHome("smartHome", Arrays.asList(room1, room2));

	}

	private EventHandler buildSignalizationHandler(SmartHome home, MessageSender sender) {
		EventHandler handler = new TestEventHandler(home);
		return new SignalizationEventHandler(handler, sender);
	}

	private boolean[] getFlagsArray(TestActionable[] actionables) {
		return new boolean[] {
				actionables[0].isActed(),
				actionables[1].isActed(),
				actionables[2].isActed(),
				actionables[3].isActed()
		};
	}
}
