package com.ubs.opsit.interviews.exception;

public class InvalidTimeException extends Exception 
{
	public InvalidTimeException(String time) 
	{
		super("Input time is invalid [" + time + "]");
	}
}
