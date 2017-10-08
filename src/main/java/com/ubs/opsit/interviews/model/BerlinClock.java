package com.ubs.opsit.interviews.model;

public class BerlinClock 
{
	private char evenOddSeconds[] = new char[1];
	
	private char mulipleOfFiveMinutes[] = new char[11];
	private char singleMinutes[] = new char[4];		

	private char mulipleOfFiveHours[] = new char[4];
	private char singleHours[] = new char[4];
	
	public char[] getEvenOddSeconds() 
	{
		return evenOddSeconds;
	}
	
	public void setEvenOddSeconds(char[] evenOddSeconds) 
	{
		this.evenOddSeconds = evenOddSeconds;
	}
	
	public char[] getMulipleOfFiveMinutes() 
	{
		return mulipleOfFiveMinutes;
	}
	
	public void setMulipleOfFiveMinutes(char[] mulipleOfFiveMinutes) 
	{
		this.mulipleOfFiveMinutes = mulipleOfFiveMinutes;
	}
	
	public char[] getSingleMinutes() 
	{
		return singleMinutes;
	}
	
	public void setSingleMinutes(char[] singleMinutes) 
	{
		this.singleMinutes = singleMinutes;
	}
	
	public char[] getMulipleOfFiveHours() 
	{
		return mulipleOfFiveHours;
	}
	
	public void setMulipleOfFiveHours(char[] mulipleOfFiveHours) 
	{
		this.mulipleOfFiveHours = mulipleOfFiveHours;
	}
	
	public char[] getSingleHours() 
	{
		return singleHours;
	}
	
	public void setSingleHours(char[] singleHours) 
	{
		this.singleHours = singleHours;
	}
		
}
