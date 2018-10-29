package com.svan001.foodtruck.util.exception;

public class NotFoundException extends Exception {

    public NotFoundException(Object resourceId, String additionalMessage) {
        super("Couldn't find resource [" + resourceId + "] : " + additionalMessage);
    }
}
