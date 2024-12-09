package utils;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.mail.*;

public class OtpFetcher 
{
	public String fetchOtpFromEmail() 
	{ 
		        String host = Constant.host;  
		        String username = Constant.username;  
		        String password = Constant.password;  

		        Properties properties = new Properties();
		        properties.put("mail.imap.host", host);
		        properties.put("mail.imap.port", "993");
		        properties.put("mail.imap.ssl.enable", "true");  //SSL for secure connection

		        try {
		            // Creating a session and connect to Gmail IMAP server
		            Session emailSession = Session.getDefaultInstance(properties);
		            Store store = emailSession.getStore("imap");
		            store.connect(username, password);  // Connecting to email account

		            // Opening inbox folder
		            Folder emailFolder = store.getFolder("INBOX");
		            emailFolder.open(Folder.READ_ONLY);  // Read-only mode, to avoid marking emails as read

		            // Searching for unread messages
		            Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

		            if (messages.length > 0) {
		                // Get the most recent message
		                Message message = messages[messages.length - 1];
		                
		                // Extract text content from the email
		                String content = getTextFromMessage(message);

		                // Regex pattern to extract OTP (OTP is a 6-digit number)
		                Pattern otpPattern = Pattern.compile("\\d{6}");
		                Matcher matcher = otpPattern.matcher(content);

		                if (matcher.find()) {
		                	
		                    String otp= matcher.group(0); // OTP found
		                    return otp; //otp returned
		                 
		                }
		                else {
		                    System.out.println("No OTP found in the email content.");
		                }
		            }
		            else {
		                System.out.println("No unread emails found.");
		            }
		            
		            // Close connections
		            emailFolder.close(false);
		            store.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return null;
		    }
	
	//method to extract text content from mail
	private String getTextFromMessage(Message message) throws Exception {
	    if (message.isMimeType("text/plain")) {
	        return message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        return getTextFromMimeMultipart(mimeMultipart);
	    }
	    return "";
	}
	
	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
	    StringBuilder result = new StringBuilder();
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result.append(bodyPart.getContent());
	        } else if (bodyPart.isMimeType("text/html")) {
	            // Optional: Handle HTML if required
	            String html = (String) bodyPart.getContent();
	            result.append(html);
	        }
	    }
	    return result.toString();
	}
		

}

