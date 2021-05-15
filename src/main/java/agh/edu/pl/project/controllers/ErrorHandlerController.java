package agh.edu.pl.project.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.UNAUTHORIZED.value() || statusCode == HttpStatus.FORBIDDEN.value()) {
                return "403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "profile";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "profile";
            }
        }
        return "profile";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
