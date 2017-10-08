package com.ubs.opsit.interviews.service;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ubs.opsit.interviews.exception.InvalidTimeException;
import com.ubs.opsit.interviews.model.BerlinClock;

public class BerlinClockImpl implements TimeConverter
{
	private BerlinClock berlinClock = new BerlinClock();

	private int tokenizedTime[] = new int[3];

	private final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9])";
	
	private final char BERLIN_CLOCK_SECONDS_ALL_BULBS_ON[] = {'Y'};
	
	private final char BERLIN_CLOCK_MULTIPLE_OF_FIVE_MINUTES_ALL_BULBS_ON[] = {'Y','Y','R','Y','Y','R','Y','Y','R','Y','Y'};
	private final char BERLIN_CLOCK_SINGLE_MINUTES_ALL_BULBS_ON[] = {'Y','Y','Y','Y'};
	
	private final char BERLIN_CLOCK_MULTIPLE_OF_FIVE_HOURS_ALL_BULBS_ON[] = {'R','R','R','R'};
	private final char BERLIN_CLOCK_SINGLE_HOURS_ALL_BULBS_ON[] = {'R','R','R','R'};
	
	private final char SWITCH_OFF_BULB ='O';

	protected BerlinClock getBerlinClock() 
	{
		return berlinClock;
	}

	public int[] getTokenizedTime() 
	{
		return tokenizedTime;
	}
		
	protected void setTokenizedTime(int[] tokenizedTime) 
	{
		this.tokenizedTime = tokenizedTime;
	}

	public String convertTime(String time) throws InvalidTimeException
	{		
		validateAndTokenizeTimeHoursMinutesSeconds(time);
		
		setBerlinClockEvenOddSecondsBulb();
		
		setBerlinClockMultipleOfFiveMinutesBulbs();
		setBerlinClockSingleMinutesBulbs();
		
		setBerlinClockMultipleOfFiveHoursBulbs();
		setBerlinClockSingleHoursBulbs();
		
		return displayBerlinTime();
	}
	
	protected void validateAndTokenizeTimeHoursMinutesSeconds(String time) throws InvalidTimeException
	{		
		Pattern patternTime24Hrs = Pattern.compile(TIME24HOURS_PATTERN);
		Matcher matcher = patternTime24Hrs.matcher(time);		
		
		if(matcher.matches())
		{
			for(int i=0;i<3;i++)
			tokenizedTime[i] = Integer.parseInt(matcher.group(i+1));
		}
		else
		{
			throw new InvalidTimeException(time);
		}
	}	

	protected void setBerlinClockEvenOddSecondsBulb() 
	{
		char berlinClockEvenOddSeconds[] = BERLIN_CLOCK_SECONDS_ALL_BULBS_ON.clone();

		if (tokenizedTime[2] % 2 != 0) 
		{
			berlinClockEvenOddSeconds[0] = SWITCH_OFF_BULB;
		}
		
		berlinClock.setEvenOddSeconds(berlinClockEvenOddSeconds);
	}

	protected void setBerlinClockMultipleOfFiveMinutesBulbs() 
	{
		char mulipleOfFiveMinutes[] = BERLIN_CLOCK_MULTIPLE_OF_FIVE_MINUTES_ALL_BULBS_ON.clone();
		
		int quotientMinutesDividedByFive = (int) (tokenizedTime[1] / 5);
		
		for (int i = quotientMinutesDividedByFive; i < mulipleOfFiveMinutes.length; i++) 
		{
			mulipleOfFiveMinutes[i] = SWITCH_OFF_BULB;
		}		

		berlinClock.setMulipleOfFiveMinutes(mulipleOfFiveMinutes);
	}

	protected void setBerlinClockSingleMinutesBulbs() 
	{
		char singleMinutes[] = BERLIN_CLOCK_SINGLE_MINUTES_ALL_BULBS_ON.clone();
				
		int remainderMinutesDividedByFive = tokenizedTime[1] % 5;

		for (int i = remainderMinutesDividedByFive; i < singleMinutes.length; i++) 
		{
			singleMinutes[i] = SWITCH_OFF_BULB;
		}

		berlinClock.setSingleMinutes(singleMinutes);
	}
	
	protected void setBerlinClockMultipleOfFiveHoursBulbs() 
	{
		char mulipleOfFiveHours[] = BERLIN_CLOCK_MULTIPLE_OF_FIVE_HOURS_ALL_BULBS_ON.clone();
		
		int quotientHoursDividedByFive = tokenizedTime[0] / 5;
		
		for (int i = quotientHoursDividedByFive; i < mulipleOfFiveHours.length; i++) 
		{
			mulipleOfFiveHours[i] = SWITCH_OFF_BULB;
		}

		berlinClock.setMulipleOfFiveHours(mulipleOfFiveHours);
	}

	protected void setBerlinClockSingleHoursBulbs() 
	{
		char singleHours[] = BERLIN_CLOCK_SINGLE_HOURS_ALL_BULBS_ON.clone();
		
		int remainderHoursDividedByFive = tokenizedTime[0] % 5;

		for (int i = remainderHoursDividedByFive; i < singleHours.length; i++) 
		{
			singleHours[i] = SWITCH_OFF_BULB;
		}

		berlinClock.setSingleHours(singleHours);
	}
	
	protected String displayBerlinTime() 
	{		
		StringBuilder display = new StringBuilder();
        
		display.append(berlinClock.getEvenOddSeconds());
		display.append(System.lineSeparator());
		
		display.append(berlinClock.getMulipleOfFiveHours());
		display.append(System.lineSeparator());
		display.append(berlinClock.getSingleHours());
		display.append(System.lineSeparator());
		
		display.append(berlinClock.getMulipleOfFiveMinutes());
		display.append(System.lineSeparator());
		display.append(berlinClock.getSingleMinutes());		

		return display.toString();
	}		
}
