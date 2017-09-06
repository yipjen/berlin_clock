package com.ubs.opsit.interviews;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BerlinClock implements TimeConverter {

	@Override
	public String convertTime(String aTime) throws IllegalArgumentException {

		int[] timeArray = timeParser(aTime);
		if (!(timeArray[0] >= 0 && timeArray[1] >= 0 && timeArray[2] >= 0)) {
			throw new IllegalArgumentException();
		}

		String resultClock;
		String newline = System.getProperty("line.separator");

		// First row
		// Lamp is yellow Y when value of second is even

		if (timeArray[2] % 2 == 0) {
			resultClock = "Y" + newline;
		} else {
			resultClock = "O" + newline;
		}

		// Second row
		// Each lamp represents 5 hours. Quotient of hour/5 represents number of
		// lamps to be lighted
		resultClock = resultClock.concat(fourLampsRowBuilder(timeArray[0] / 5,
				"R"));
		resultClock = resultClock.concat(newline);

		// Third row
		// Each lamp represents 1 hour. Taking lamps in second row into
		// account, remainder of hour/5 represents number of lamps to be lighted
		resultClock = resultClock.concat(fourLampsRowBuilder(timeArray[0] % 5,
				"R"));
		resultClock = resultClock.concat(newline);

		// Fourth row
		// Each lamp represents 5 minutes. Quotient of minute/5 represents
		// number of lamps to be lighted
		resultClock = resultClock
				.concat(elevenLampsRowBuilder(timeArray[1] / 5));
		resultClock = resultClock.concat(newline);

		// Fifth row
		// Each lamp represents 1 minute. Taking lamps in fourth row into
		// account, remainder of minute/5 represents number of lamps to be
		// lighted
		resultClock = resultClock.concat(fourLampsRowBuilder(timeArray[1] % 5,
				"Y"));

		return resultClock;
	}

	/*
	 * Parse the time String aTime to an int[] in the format of { hour, minute,
	 * second }; return {-1, -1, -1} when aTime is not in the format HH:mm:ss of
	 * if it any of its values is invalid
	 * 
	 * Comment: Our own verification is used as SimpleDateFormat accepts hour >
	 * 24, minute > 59 and second > 59 as valid values
	 * 
	 * @param aTime time string to be verified and parsed
	 * 
	 * @return int[] in the format of { hour, minute, second } when input is
	 * valid; return {-1, -1, -1} otherwise
	 */
	private int[] timeParser(String aTime) {

		int hour = -1;
		int minute = -1;
		int second = -1;
		boolean timeValid = false;

		// ensure input is in the pattern "\\d\\d:\\d\\d:\\d\\d"
		if (aTime == null || !aTime.matches("\\d\\d:\\d\\d:\\d\\d")) {
			timeValid = false;
		} else if (aTime.matches("24:00:00")) { // special case
			timeValid = true;
			hour = 24;
			minute = 0;
			second = 0;
		} else {
			// parse input aTime into integers and ensure 0<=hour<24,
			// 0<=minute<60, 0<=second<60
			try {
				Pattern p = Pattern.compile("([0-9]+):([0-9]+):([0-9]+)");
				Matcher m = p.matcher(aTime);
				m.matches();
				hour = Integer.parseInt(m.group(1));
				minute = Integer.parseInt(m.group(2));
				second = Integer.parseInt(m.group(3));
				if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60
						&& second >= 0 && second < 60) {
					timeValid = true;
				} else {
					timeValid = false;
				}
			} catch (Exception e) { // return false if there is an error in
									// parsing integer
				timeValid = false;
			}

		}

		if (timeValid) {
			return new int[] { hour, minute, second };
		}
		return new int[] { -1, -1, -1 };

	}

	/*
	 * Returns a string representing a row with four lamps, with @param
	 * numberOfLights lighted up in colour @param c
	 * 
	 * Comment: Switch is used for efficiency. Even though string concatenation
	 * is time consuming, it is a trade off so code can be reused for all rows
	 * with 4 lamps
	 * 
	 * @param numberOfLamps number of lamps to be turned on;
	 * 
	 * @param c colour of the lamp in one character
	 * 
	 * @return string representing a row with four light
	 */
	private String fourLampsRowBuilder(int numberOfLamps, String c) {
		if (numberOfLamps < 0 || numberOfLamps > 4 || !c.matches("[RY]")) {
			return "";
		}

		String result = "";
		switch (numberOfLamps) {
		case 0:
			result = "OOOO";
			break;
		case 1:
			result = c + "OOO";
			break;
		case 2:
			result = c + c + "OO";
			break;
		case 3:
			result = c + c + c + "O";
			break;
		case 4:
			result = c + c + c + c;
			;
			break;
		}
		return result;
	}

	/*
	 * Returns a string representing a row with 11 lamps, with @param
	 * numberOfLampss lighted up. The 3rd, 6th and 9th lamps are red R and all
	 * other lamps are yellow Y
	 * 
	 * Comment: loop and String.concat is used for code simplicity, even though
	 * using switch and hard coding output would be more efficient
	 * 
	 * @param numberOfLamps number of lights to be turned on;
	 * 
	 * @return string representing a row with 11 light
	 */
	private String elevenLampsRowBuilder(int numberOfLamps) {

		if (numberOfLamps < 0 || numberOfLamps > 11) {
			return "";
		}

		String output = "";
		for (int i = 1; i <= numberOfLamps; i++) {
			if (i % 3 != 0) {
				output = output.concat("Y");
			} else {
				output = output.concat("R");
			}
		}
		for (int j = 0; j < 11 - numberOfLamps; j++) {
			output = output.concat("O");
		}

		return output;
	}
}
