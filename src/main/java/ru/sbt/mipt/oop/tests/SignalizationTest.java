package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.signalization.Signalization;

public class SignalizationTest {

	@Test
	public void signalizationIsActivatedWhenMethodActivateIsCalled() {
		// given
		Signalization signalization = new Signalization();
		// when
		signalization.activate("123");
		// then
		Assertions.assertTrue(signalization.isActivated());
	}

	@Test
	public void signalizationIsDeactivatedWhenMethodDeactivateIsCalledWithRightCode() {
		// given
		Signalization signalization = new Signalization();
		signalization.activate("123");
		// when
		signalization.deactivate("123");
		// then
		Assertions.assertTrue(signalization.isDeactivated());
	}

	@Test
	public void signalizationIsAlertedWhenMethodDeactivateIsCalledWithWrongCode() {
		// given
		Signalization signalization = new Signalization();
		signalization.activate("123");
		// when
		signalization.deactivate("321");
		//then
		Assertions.assertTrue(signalization.isAlerted());
	}
}
