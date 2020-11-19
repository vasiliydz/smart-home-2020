package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.devices.signalization.Signalization;
import ru.sbt.mipt.oop.smarthome.devices.signalization.SignalizationActivateAction;
import ru.sbt.mipt.oop.smarthome.devices.signalization.SignalizationDeactivateAction;

public class SignalizationTest {

	@Test
	public void signalizationIsActivatedWhenMethodActivateIsCalled() {
		// given
		Signalization signalization = new Signalization();
		// when
		signalization.execute(new SignalizationActivateAction("123"));
		// then
		Assertions.assertTrue(signalization.isActivated());
	}

	@Test
	public void signalizationIsDeactivatedWhenMethodDeactivateIsCalledWithRightCode() {
		// given
		Signalization signalization = new Signalization();
		signalization.execute(new SignalizationActivateAction("123"));
		// when
		signalization.execute(new SignalizationDeactivateAction("123"));
		// then
		Assertions.assertTrue(signalization.isDeactivated());
	}

	@Test
	public void signalizationIsAlertedWhenMethodDeactivateIsCalledWithWrongCode() {
		// given
		Signalization signalization = new Signalization();
		signalization.execute(new SignalizationActivateAction("123"));
		// when
		signalization.execute(new SignalizationDeactivateAction("321"));
		//then
		Assertions.assertTrue(signalization.isAlerted());
	}
}
