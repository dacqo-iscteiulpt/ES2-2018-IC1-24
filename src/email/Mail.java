package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public void sendEmail(String email, String description)
    {
    	if(email.isEmpty())
    		email = "dqueimado@gmail.com";
        final String username = "dacqoes2@gmail.com";
        final String password = "ZAQ!\"WSX";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dacqoES2@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Info regarding help on Project usage...");
            message.setText("Email regarding help about ES2 project,\n\n" + description + "\n\nThanks");
            
            Transport.send(message);

        } 

        catch (MessagingException e) 
        {
            // throw new RuntimeException(e);
            System.out.println("Username or Password are incorrect ... exiting !");
        }
    }
}