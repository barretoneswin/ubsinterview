package com.ubs.opsit.interviews.service;

import org.junit.Assert;
import org.junit.Test;

import com.ubs.opsit.interviews.exception.InvalidTimeException;

public class BerlinClockImplTest {
	
	public void convertAndAssertValidBerlinTime(String time,String expectedValue) throws InvalidTimeException
	{
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();
		String returnedValue = berlinClockImpl.convertTime(time);	    
	    Assert.assertEquals(expectedValue, returnedValue.toString());	
	}	
	
	@Test
	public void validBerlinTimeJustBeforeMidNight() throws InvalidTimeException
	{
		final String time = "22:38:02";
		final String expectedValue = "Y"+System.lineSeparator()+
				                     "RRRR"+System.lineSeparator()+
                                     "RROO"+System.lineSeparator()+
                                     "YYRYYRYOOOO"+System.lineSeparator()+
                                     "YYYO";
				
		convertAndAssertValidBerlinTime(time,expectedValue);		
	}

	@Test
	public void validBerlinTimePostAfternoon() throws InvalidTimeException
	{
		final String time = "12:38:09";
		final String expectedValue = "O"+System.lineSeparator()+
									 "RROO"+System.lineSeparator()+
   	                                 "RROO"+System.lineSeparator()+
	                                 "YYRYYRYOOOO"+System.lineSeparator()+
	                                 "YYYO";
				
		convertAndAssertValidBerlinTime(time,expectedValue);	
	}
	
	@Test
	public void validBerlinTimePostMidnight() throws InvalidTimeException
	{
		final String time = "00:38:10";
		final String expectedValue = "Y"+System.lineSeparator()+
									 "OOOO"+System.lineSeparator()+
                                     "OOOO"+System.lineSeparator()+
                                     "YYRYYRYOOOO"+System.lineSeparator()+
                                     "YYYO";
				
		convertAndAssertValidBerlinTime(time,expectedValue);	
	}
	
	@Test
	public void validBerlinTimeEarlyMorning() throws InvalidTimeException
	{
		final String time = "8:12:00";
		final String expectedValue = "Y"+System.lineSeparator()+
				                     "ROOO"+System.lineSeparator()+
                                     "RRRO"+System.lineSeparator()+
                                     "YYOOOOOOOOO"+System.lineSeparator()+
                                     "YYOO";
				
	    convertAndAssertValidBerlinTime(time,expectedValue);		
	}
	
	@Test
	public void validBerlinTimeLateEvening() throws InvalidTimeException
	{
		final String time = "18:21:00";
		final String expectedValue = "Y"+System.lineSeparator()+
				                     "RRRO"+System.lineSeparator()+
				                     "RRRO"+System.lineSeparator()+
				                     "YYRYOOOOOOO"+System.lineSeparator()+
				                     "YOOO";
				
		convertAndAssertValidBerlinTime(time,expectedValue);		
	}
	
	@Test
	public void validBerlinMaxTime()  throws InvalidTimeException
	{
		final String time = "24:59:59";
		final String expectedValue = "O"+System.lineSeparator()+
				               		 "RRRR"+System.lineSeparator()+
				               		 "RRRR"+System.lineSeparator()+
				               		 "YYRYYRYYRYY"+System.lineSeparator()+
				               		 "YYYY";
				
		convertAndAssertValidBerlinTime(time,expectedValue);		
	}
	
	@Test
	public void validBerlinMinTime() throws InvalidTimeException
	{
		final String time = "00:00:00";
		final String expectedValue = "Y"+System.lineSeparator()+
				               		 "OOOO"+System.lineSeparator()+
		                             "OOOO"+System.lineSeparator()+
		                             "OOOOOOOOOOO"+System.lineSeparator()+
		                             "OOOO";
		
		convertAndAssertValidBerlinTime(time,expectedValue);
	}
	
	public void convertAndAssertInvalidBerlinTime(String time) throws InvalidTimeException
	{
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();		
		berlinClockImpl.convertTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeCharacterInHours() throws InvalidTimeException
	{
		final String time = "aa:00:00";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeCharacterInMinutes() throws InvalidTimeException
	{
		final String time = "12:aa:00";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeCharacterInSeconds() throws InvalidTimeException
	{
		final String time = "12:01:re";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeNoSeconds() throws InvalidTimeException
	{
		final String time = "12:01";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeNoMinutesSeconds() throws InvalidTimeException
	{
		final String time = "12";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeBlankTime() throws InvalidTimeException
	{
		final String time = "";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeHoursAbove24() throws InvalidTimeException
	{
		final String time = "25:12:54";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeMinutesAbove59() throws InvalidTimeException
	{
		final String time = "22:88:54";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeSecondsAbove59() throws InvalidTimeException
	{
		final String time = "22:59:67";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test(expected=InvalidTimeException.class)
	public void invalidTimeHoursSecondMinutesOutOfRange() throws InvalidTimeException
	{
		final String time = "28:76:67";
		
		convertAndAssertInvalidBerlinTime(time);
	}
	
	@Test
	public void validTokenizationOfInputTime() throws InvalidTimeException
	{
		final int hours = 16;
		final int minutes = 13;
		final int seconds = 11;		
		
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();		
		berlinClockImpl.validateAndTokenizeTimeHoursMinutesSeconds(hours+":"+minutes+":"+seconds);
		
		Assert.assertEquals("Wrong tokenization of hours",hours, berlinClockImpl.getTokenizedTime()[0]);
		Assert.assertEquals("Wrong tokenization of minutes",minutes, berlinClockImpl.getTokenizedTime()[1]);
		Assert.assertEquals("Wrong tokenization of seconds",seconds, berlinClockImpl.getTokenizedTime()[2]);
	}
	
	@Test(expected = InvalidTimeException.class)
	public void InvalidTokenizationOfInputTime() throws InvalidTimeException
	{
		final int hours = 25;
		final int minutes = 13;
		final int seconds = 11;		
				
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();	
		
		berlinClockImpl.validateAndTokenizeTimeHoursMinutesSeconds(hours+":"+minutes+":"+seconds);
	}
	
	@Test
	public void berlinClockOddSecondsBulb() throws InvalidTimeException
	{
		final int hours = 2;
		final int minutes = 13;
		final int seconds = 11;		
				
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();	
		berlinClockImpl.setTokenizedTime(new int[]{hours,minutes,seconds});
		
		berlinClockImpl.setBerlinClockEvenOddSecondsBulb();
		Assert.assertEquals('O', berlinClockImpl.getBerlinClock().getEvenOddSeconds()[0]);
	}
	
	@Test
	public void berlinClockEvenSecondsBulb() throws InvalidTimeException
	{
		final int hours = 2;
		final int minutes = 13;
		final int seconds = 12;		
				
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();	
		berlinClockImpl.setTokenizedTime(new int[]{hours,minutes,seconds});
		
		berlinClockImpl.setBerlinClockEvenOddSecondsBulb();
		Assert.assertEquals('Y', berlinClockImpl.getBerlinClock().getEvenOddSeconds()[0]);
	}
	
	@Test
	public void berlinClockMultipleOfFiveMinutesBulbs() throws InvalidTimeException
	{
		final int hours = 2;
		final int minutes = 18;
		final int seconds = 11;		
		final String expectedValue = "YYROOOOOOOO";
		
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();	
		berlinClockImpl.setTokenizedTime(new int[]{hours,minutes,seconds});
		
		berlinClockImpl.setBerlinClockMultipleOfFiveMinutesBulbs();
		Assert.assertEquals(expectedValue,String.valueOf(berlinClockImpl.getBerlinClock().getMulipleOfFiveMinutes()));
	}
	
	@Test
	public void berlinClockSingleMinutesBulbs() throws InvalidTimeException
	{
		final int hours = 2;
		final int minutes = 18;
		final int seconds = 11;		
		final String expectedValue = "YYYO";
		
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();	
		berlinClockImpl.setTokenizedTime(new int[]{hours,minutes,seconds});
		
		berlinClockImpl.setBerlinClockSingleMinutesBulbs();
		Assert.assertEquals(expectedValue,String.valueOf(berlinClockImpl.getBerlinClock().getSingleMinutes()));
	}
	
	@Test
	public void berlinClockMultipleOfFiveHoursBulbs() throws InvalidTimeException
	{
		final int hours = 18;
		final int minutes = 18;
		final int seconds = 11;		
		final String expectedValue = "RRRO";
		
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();	
		berlinClockImpl.setTokenizedTime(new int[]{hours,minutes,seconds});
		
		berlinClockImpl.setBerlinClockMultipleOfFiveHoursBulbs();
		Assert.assertEquals(expectedValue,String.valueOf(berlinClockImpl.getBerlinClock().getMulipleOfFiveHours()));
	}
	
	@Test
	public void berlinClockSingleHoursBulbs() throws InvalidTimeException
	{
		final int hours = 18;
		final int minutes = 18;
		final int seconds = 11;		
		final String expectedValue = "RRRO";
		
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();	
		berlinClockImpl.setTokenizedTime(new int[]{hours,minutes,seconds});
		
		berlinClockImpl.setBerlinClockSingleHoursBulbs();
		Assert.assertEquals(expectedValue,String.valueOf(berlinClockImpl.getBerlinClock().getSingleHours()));
	}
	
	@Test
	public void displayBerlinTime() throws InvalidTimeException
	{	
		final String evenOddSeconds = "O";
		final String mulipleOfFiveHours = "RROO"; 
		final String singleHours = "RRRO";
		final String mulipleOfFiveMinutes = "RRRO";
		final String singleMinutes = "RRRO";
		
		final String expectedValue = evenOddSeconds+System.lineSeparator()+
	                                 mulipleOfFiveHours+System.lineSeparator()+
	                                 singleHours+System.lineSeparator()+
	                                 mulipleOfFiveMinutes+System.lineSeparator()+
	                                 singleMinutes;
		
		BerlinClockImpl berlinClockImpl = new BerlinClockImpl();
		berlinClockImpl.getBerlinClock().setEvenOddSeconds(evenOddSeconds.toCharArray());
		berlinClockImpl.getBerlinClock().setMulipleOfFiveHours(mulipleOfFiveHours.toCharArray());
		berlinClockImpl.getBerlinClock().setSingleHours(singleHours.toCharArray());
		berlinClockImpl.getBerlinClock().setMulipleOfFiveMinutes(mulipleOfFiveMinutes.toCharArray());
		berlinClockImpl.getBerlinClock().setSingleMinutes(singleMinutes.toCharArray());
		
		String returnedValue = berlinClockImpl.displayBerlinTime();
		
		Assert.assertEquals(expectedValue,returnedValue);
	}
}
