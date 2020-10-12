package com.um.cloudfixum.cloudfixum.controller;

import com.um.cloudfixum.cloudfixum.email.EmailBody;
import com.um.cloudfixum.cloudfixum.email.EmailPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailPort emailPort;

    @PostMapping
    @ResponseBody
    public boolean SendEmail(@RequestBody EmailBody emailBody)  {
        return true;
    }
}
