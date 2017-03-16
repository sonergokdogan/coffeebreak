package com.mylaps.assignment.karting.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.mylaps.assignment.karting.calc.FastestLapWinnerCalc;
import com.mylaps.assignment.karting.control.Race;

public class EvaluationTest {

	@Test
	public void test1() {

		Race race = Race.getInstance();
		
		race.startRace(3, 3, 1000, new FastestLapWinnerCalc());

		race.newLapRecord(0, 0);
		race.newLapRecord(0, 2);
		race.newLapRecord(0, 5);
		race.newLapRecord(0, 9);
		race.newLapRecord(1, 0);
		race.newLapRecord(1, 8);
		race.newLapRecord(1, 9);
		race.newLapRecord(2, 0);
		race.newLapRecord(2, 6);
		race.newLapRecord(2, 9);
		race.newLapRecord(2, 10);

		assertEquals(race.finishRace().getFastestKartInTheRace(), 0);
		assertEquals(race.finishRace().getWinner().getFastestLap(), 2);
		assertEquals(race.finishRace().getWinner().getKartNumber(), 1);

	}

	@Test
	public void test2() {

		Race race = Race.getInstance();

		race.startRace(3, 5, 1000, new FastestLapWinnerCalc());

		race.newLapRecord(0, 1);
		race.newLapRecord(0, 6);
		race.newLapRecord(0, 9);
		race.newLapRecord(0, 15);
		race.newLapRecord(0, 20);
		race.newLapRecord(1, 2);
		race.newLapRecord(1, 8);
		race.newLapRecord(1, 12);
		race.newLapRecord(1, 18);
		race.newLapRecord(1, 21);
		race.newLapRecord(2, 3);
		race.newLapRecord(2, 7);
		race.newLapRecord(2, 10);
		race.newLapRecord(2, 17);
		race.newLapRecord(2, 21);

		assertEquals(race.finishRace(), null);

	}
	
	@Test
	public void test3() {

		Race race = Race.getInstance();

		race.startRace(4, 4, 1000, new FastestLapWinnerCalc());

		race.newLapRecord(0, 1);
		race.newLapRecord(0, 6);
		race.newLapRecord(0, 9);
		race.newLapRecord(0, 15);
		race.newLapRecord(0, 20);
		race.newLapRecord(1, 2);
		race.newLapRecord(1, 8);
		race.newLapRecord(1, 12);
		race.newLapRecord(1, 18);
		race.newLapRecord(1, 21);
		race.newLapRecord(2, 3);
		race.newLapRecord(2, 7);
		race.newLapRecord(2, 10);
		race.newLapRecord(2, 17);
		race.newLapRecord(2, 21);

		assertEquals(race.finishRace().getFastestKartInTheRace(), 2);
		assertEquals(race.finishRace().getWinner().getFastestLap(), 2);
		assertEquals(race.finishRace().getWinner().getKartNumber(), 0);
	}

}
