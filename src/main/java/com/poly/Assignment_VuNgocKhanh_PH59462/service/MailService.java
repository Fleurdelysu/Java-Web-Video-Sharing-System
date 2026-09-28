package com.poly.Assignment_VuNgocKhanh_PH59462.service;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;



public class MailService {


    private static final String EMAIL =
            "your_email@gmail.com";


    private static final String PASSWORD =
            "your_app_password";



    public static void send(
            String to,
            String subject,
            String content
    ) {


        Properties props =
                new Properties();


        props.put(
                "mail.smtp.host",
                "smtp.gmail.com"
        );


        props.put(
                "mail.smtp.port",
                "587"
        );


        props.put(
                "mail.smtp.auth",
                "true"
        );


        props.put(
                "mail.smtp.starttls.enable",
                "true"
        );



        Session session =
                Session.getInstance(
                        props,
                        new Authenticator() {


                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {


                                return new PasswordAuthentication(
                                        EMAIL,
                                        PASSWORD
                                );

                            }

                        }
                );



        try {


            Message message =
                    new MimeMessage(session);



            message.setFrom(
                    new InternetAddress(EMAIL)
            );



            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );



            message.setSubject(subject);



            message.setText(content);



            Transport.send(message);



        } catch (Exception e) {


            throw new RuntimeException(e);


        }


    }

}