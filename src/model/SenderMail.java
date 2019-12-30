package model;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SenderMail {

    private String from;
    private String to;
    private String subject;
    private String messageBody;
    private String fileName;
    private String host;

    private Properties properties;

    private MimeMessage message;
    private BodyPart messageBodyPart;
    private Multipart multipart;

    private Authenticator authenticator;

    public SenderMail () {
    	
    	from = "carrierapregressa@gmail.com";
        to = "";
        subject = "";
        messageBody = "";
        fileName = "";
        authenticator = new SMTPAuthenticator ();
        properties = System.getProperties();
        host = "smtp.mailtrap.io";
        properties.put ( "mail.smtp.host", host );
        properties.put ( "mail.smtp.starttls.enable", "true" );
        properties.put ( "mail.smtp.port", "2525" );
        properties.put ( "mail.smtp.auth", "true" );
        properties.put ( "mail.smtp.ssl", "no" );
        properties.put ( "mail.smtp.tls", "yes" );
    }

    public void sendMail ( String from, String to, String subject, String messageBody, String fileName ) {
        try {
        	
            Session session = Session.getDefaultInstance ( properties, authenticator );
            message = new MimeMessage ( session );
            message.setFrom ( new InternetAddress ( from ) );
            message.addRecipient ( Message.RecipientType.TO,
                                new InternetAddress ( to ) );
            message.setSubject ( subject );

            multipart = new MimeMultipart ();
            messageBodyPart = new MimeBodyPart ();
            messageBodyPart.setContent ( messageBody, "text/html" );
            multipart.addBodyPart ( messageBodyPart );
            message.setText(messageBody);
            
            /* 
            messageBodyPart = new MimeBodyPart ();
            DataSource source = new FileDataSource ( fileName );
            messageBodyPart.setDataHandler ( new DataHandler ( source ) );
            messageBodyPart.setFileName ( fileName );
            multipart.addBodyPart ( messageBodyPart );
            message.setContent ( multipart );
             */	
         
            Transport.send ( message );
            System.out.println ( "Message send successfully...." );
        } catch ( Exception me ) {
            me.printStackTrace ();
        }
    } 

    private void performTask () {
        sendMail ( from, to, subject, messageBody, fileName );
    }

    public static void main ( String[] args ) {
        new SenderMail ().performTask ();
    }
}

/**
  * SimpleAuthenticator is used to do simple authentication
  * when the SMTP server requires it.
  */

class SMTPAuthenticator extends Authenticator {

    private static final String SMTP_AUTH_USER = "a0536c3700ec66";
    private static final String SMTP_AUTH_PASSWORD = "7fd50e83f20b4d";

    public PasswordAuthentication getPasswordAuthentication () {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PASSWORD;

        return new PasswordAuthentication( username,  password );
    }
}