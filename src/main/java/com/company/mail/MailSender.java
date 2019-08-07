package com.company.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailSender {
    private String Sender;
    private String SenderPas;
    private Session session;
    public MailSender(String sender, String senderpas){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        this.Sender = sender;//
        this.SenderPas = senderpas;
        this.session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, senderpas);
                    }
                });
    }

    public void SendMessage(String Recipient, String title, String text) throws MessagingException {
// -- Create a new message --
        Message msg = new MimeMessage(this.session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(Recipient));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(Recipient, false));
        msg.setSubject(title);
        msg.setText(text);
        msg.setSentDate(new Date());
        Transport.send(msg);
    }


}
