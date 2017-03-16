package com.mylaps.assignment.karting.calc;

import java.util.ArrayList;
import java.util.List;

import com.mylaps.assignment.karting.model.RaceResult;

/*
 *  This class implements WinnerCalcIntf to realize the
 *  race winner calculation, when the fastest lap pilot
 *  is the winner. Eventhough a pilot couldn't finish 
 *  the race, if he/she has a fastest lap time before
 *  any other pilot completes the race, this method
 *  will find him/her as the winner. 
 *  
 *  A total race time calculation is not safe since the
 *  winning pilot would not complete the race.
 *  
 */
public class FastestLapWinnerCalc implements WinnerCalcIntf {

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
					fastestKartInRace = i;
				}
			}
		}

		if (fastestKartInRace == -1) {
			// Race never completed!
			return null;
		}

		// We've already found the fastest race completion time and the pilot
		// who made it
		raceResult.setFastestKartInTheRace(fastestKartInRace);
		raceResult.setMinimumRaceTime(minimumRaceTime);

		/*
		 * Now we'll calculate the valid laps of each kart by checking the lap
		 * times for the minimum and valid one. We additively take the lap
		 * duration and if the lap time is lower than the former set minimum we
		 * update it with the new one.
		 */

		// this is the loop for the karts
		for (int i = 0; i < raceData.size(); i++) {

			int raceDuration = 0;

			ArrayList<Long> lapTimes = raceData.get(i);

			// this will be the loop for the laps. See the above line
			for (int j = 1; j < lapTimes.size(); j++) {

				// A lap time is the difference between two arbitrary time of
				// day
				// values
				long lapTime = lapTimes.get(j) - lapTimes.get(j - 1);

				// Total race duration since start
				raceDuration += lapTime;

				// Anybody has finished before?
				if (raceDuration <= minimumRaceTime) {
					// Anybody hasn't finished before. If this lap time
					// is any smaller than the former minimum, update it (and
					// the winner as well)
					if (lapTime < raceResult.getWinner().getFastestLapTime()) {

						raceResult.getWinner().setFastestLap(j);
						raceResult.getWinner().setFastestLapTime(lapTime);
						raceResult.getWinner().setKartNumber(i);
					}
				}
			}
		}

		return raceResult;
	}
}
