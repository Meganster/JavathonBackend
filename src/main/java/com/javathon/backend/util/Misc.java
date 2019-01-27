package com.javathon.backend.util;

import com.javathon.backend.model.ErrorModel;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

public class Misc {
    public static List<ErrorModel> buildErrorResponse(Errors errors) {
        List<ErrorModel> resultList = new ArrayList<>();
        errors.getFieldErrors().forEach(x -> resultList.add(new ErrorModel(x.getField(), x.getCode(), x.getDefaultMessage())));
        return resultList;
    }
}
