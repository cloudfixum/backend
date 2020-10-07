package com.um.cloudfixum.cloudfixum.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class EmailHtml {
    @Autowired
    private TemplateEngine templateEngine;

    public String generateMailHtml(String text)
    {
        Map<String, Object> variables = new HashMap<>();
        variables.put("mailtext", text);

        final String templateFileName = "mail"; //Name of the template file without extension
        String output = this.templateEngine.process(templateFileName, new Context(Locale.getDefault(), variables));
        System.out.println(output);
        return output;

    }
}
