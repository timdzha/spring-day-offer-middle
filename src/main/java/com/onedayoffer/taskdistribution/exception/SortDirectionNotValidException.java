package com.onedayoffer.taskdistribution.exception;

import org.springframework.data.domain.Sort;

import java.text.MessageFormat;
import java.util.Arrays;

public class SortDirectionNotValidException extends RuntimeException {
    public SortDirectionNotValidException(String s) {
        super(MessageFormat.format("task sort not valid: {0}. task valid: {1}",
                s, Arrays.toString(Sort.Direction.values())));
    }
}
