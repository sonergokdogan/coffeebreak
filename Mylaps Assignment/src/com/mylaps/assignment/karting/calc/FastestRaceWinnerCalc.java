package com.mylaps.assignment.karting.calc;

import java.util.ArrayList;
import java.util.List;

import com.mylaps.assignment.karting.model.RaceResult;

/*
 *  This class implements WinnerCalcIntf to realize the
 *  race winner calculation: When a pilot finishes all 
 *  laps before others is the winner.
 *   
 */
public class FastestRaceWinnerCalc implements WinnerCalcIntf {

	@Override
	public RaceResult findWinner(List<ArrayList<Long>> raceData, int numberOfLaps) {

		long minimumRaceTime = Long.MAX_VALUE;
		int fastestKartInRace = -1;

		RaceResult raceResult = new RaceResult();

		/*
		 * This loop is to find the minimum race completion time of the karts
		 * which completed the race. Those who couldn't complete the race is out
		 * of scope. We'll use this value to understand if a lap time was made
		 * during the race, or after it is finished.
		 */
		for (int i = 0; i < raceData.size(); i++) {

			// A kart shall complete the race to be counted in fastest race
			// calculation
			if (raceData.get(i).size() == numberOfLaps) {
				long timeDelta = raceData.get(i).get(numberOfLaps - 1) - raceData.get(i).get(0);
				if (timeDelta < minimumRaceTime) {
					minimumRaceTime = timeDelta;
					raceResult.setFastestKartInTheRace(i);
					raceResult.setMinimumRaceTime(minimumRaceTime);
				}
			}
		}

		if (fastestKartInRace == -1) {
			// Race never completed!
			return null;
		}

		//This part is to find the fastest lap of the race winner. First; get the laps of the winner
		ArrayList<Long> lapTimesOfFastestKart = raceData.get(raceResult.getWinner().getKartNumber());
		
		//Now iterate over the laps and find the fastest one
		for (int i = 0; i < lapTimesOfFastestKart.size(); i++) {
			long lapTime = lapTimesOfFastestKart.get(i);
			//If you find a faster lap than the former one, update it
			if (lapTime < raceResult.getWinner().getFastestLapTime()) {
				raceResult.getWinner().setFastestLapTime(lapTime);
				raceResult.getWinner().setFastestLap(i);
			}
		}

		return raceResult;
	}

}
