package com.um.cloudfixum.cloudfixum.email;

public interface EmailPort {

    void sendEmail(String content, String email, String subject);
}
