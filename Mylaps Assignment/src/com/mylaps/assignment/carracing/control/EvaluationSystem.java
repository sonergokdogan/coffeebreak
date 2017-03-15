package com.mylaps.assignment.carracing.control;

import java.util.Map;

public class EvaluationSystem {

	public static void evaluateLaps() {

		System.out.println("------------------------------------------------------------");
		System.out.println("-----------------RACE RESULTS-------------------------------");
		System.out.println("");

		// Run the loop for every lap
		for (int i = 1; i <= TimingSystem.getInstance().getNumberOfCompletedLaps(); i++) {

			// Run the loop for every car
			System.out.println("* Lap number: " + i + " Size:" + TimingSystem.getInstance().getRegisteredResultsForLap(i).size());
			for (Map.Entry<Integer, Long> entry : TimingSystem.getInstance().getRegisteredResultsForLap(i).entrySet()) {
				if (entry != null) {
					System.out.print("Kart number: " + entry.getKey());
					System.out.println("    Time: " + entry.getValue());
				}
			}
		}
	}
}
