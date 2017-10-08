package com.ubs.opsit.interviews.service;

import com.ubs.opsit.interviews.exception.InvalidTimeException;

public interface TimeConverter {

    String convertTime(String aTime) throws InvalidTimeException;

}
