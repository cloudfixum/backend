package com.um.cloudfixum.cloudfixum.config;

import com.um.cloudfixum.cloudfixum.common.Constant;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, String>> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        Map<String, String> jsonError = new HashMap<>();
        int statusCode = Integer.parseInt(status.toString());

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            jsonError.put(Constant.ERROR, Constant.NOT_FOUND);
            return new ResponseEntity<>(jsonError, HttpStatus.NOT_FOUND);
        }

        if (statusCode == HttpStatus.FORBIDDEN.value()) {
            jsonError.put("status", "403");
            jsonError.put(Constant.ERROR, "Forbidden");
            jsonError.put("message", "Access denied");
            return new ResponseEntity<>(jsonError, HttpStatus.FORBIDDEN);
        }

        jsonError.put(Constant.ERROR, String.valueOf(statusCode));
        return new ResponseEntity<>(jsonError, HttpStatus.valueOf(statusCode));

    }

    @Override
    public String getErrorPath() {
        return null;
    }
}