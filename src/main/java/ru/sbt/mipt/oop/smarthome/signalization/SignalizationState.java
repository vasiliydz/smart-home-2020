package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;

public interface SignalizationState {
	/*
	В зависимости от состояния сигнализация ведёт себя по-разному
	Состояние принимает на вход Event, обрабатывает его и возвращает
	состояние сигнализации после обработки
	*/
	SignalizationState handle(Event event);
}
