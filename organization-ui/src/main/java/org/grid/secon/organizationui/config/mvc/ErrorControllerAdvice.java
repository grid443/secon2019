package org.grid.secon.organizationui.config.mvc;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler(Exception.class)
    ModelAndView handleException(Exception ex) {
        Map<String, Object> model = new HashMap<>();
        String errorMessage = StringUtils.isEmpty(ex.getMessage()) ? "Internal Server Error" : ex.getMessage();
        model.put("errorMessage", errorMessage);
        return new ModelAndView("error", model);
    }
}