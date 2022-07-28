import java.io.IOException;
import java.security.Key;
import java.util.Properties;
import java.util.Scanner;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class App 
{
	static Scanner scn = new Scanner(System.in);
  
	public static int sendEmail(String message, String subject, String to, final String from,final String password, String file_path,String ourkeyvalue)throws Exception,IOException {
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		

//		 Session Instantiation
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,password);
			}
		});
		
		// Compose message
		MimeMessage mail = new MimeMessage(session);
		
		mail.setFrom(from);
		mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mail.setSubject(subject);	
		
		
		if (ourkeyvalue.length()!=16) {
			System.out.println("Length of UKey should be equal to 16 ");
			return -1;
		}
		Encryption enc = new Encryption(ourkeyvalue);
		Key key = enc.generateKey();
		
		String encryptedMessage = enc.encrypt(message, key);
		char[] a = encryptedMessage.toCharArray();
		StringBuffer result = new StringBuffer();

		int s =4;
		for (int i=0; i<ourkeyvalue.length(); i++){
			if ((ourkeyvalue.charAt(i)>='A' && ourkeyvalue.charAt(i)<='Z') || (ourkeyvalue.charAt(i)>='a' && ourkeyvalue.charAt(i)<='z')){
				if (Character.isUpperCase(ourkeyvalue.charAt(i))){
					char ch = (char)(((int)ourkeyvalue.charAt(i) + s - 65) % 26 + 65);
					result.append(ch);
				}
				else
				{
					char ch = (char)(((int)ourkeyvalue.charAt(i) + s - 97) % 26 + 97);
					result.append(ch);
				}
			}else {
				result.append(ourkeyvalue.charAt(i));
			}
        }
		char[] kvarray = result.toString().toCharArray();
		
		char[] temp = new char[encryptedMessage.length()+ourkeyvalue.length()];
		int i=0;
		int k=0;
		for (i=0;i<encryptedMessage.length()/2;i++) {
			temp[k++]=a[i];
		}
		int z=i;
		for (int j=0;j<ourkeyvalue.length();j++) {
			temp[k++]=kvarray[j];
		}
		for (int l=z;l<encryptedMessage.length();l++) {
			temp[k++]=a[l];
		}
		String mailtext = String.copyValueOf(temp);
		System.out.print("Encrypted message : ");
//		System.out.println(mailtext);
		// Set text
//		mail.setText(mailtext);
                BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
                messageBodyPart.setText(mailtext);
		Multipart multipart = new MimeMultipart();
                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
//                System.out.println();
//                String filename = "file.txt";
//                String filename = "C:\\Users\\yousuf\\Documents\\file.txt";
                String filename = file_path.substring(file_path.lastIndexOf("/") + 1);
                DataSource source = new FileDataSource(file_path);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                mail.setContent(multipart);
		// Send message
		try{
			Transport.send(mail);                
                        System.out.println("Helllllllllllllllooooooooooooooooooooooo");

			System.out.println("Sent message succesfully.");
			return 1;
		}catch(Exception e){
			System.out.println("Unsuccesfull");
			return 0;
		}

	}
//    public static void main(String[] args) throws Exception{
//         sendEmail("Please find the attached file...", "File attachment", "sajjad18801@gmail.com", "azizmsajjad@gmail.com","pass***1", "C:/Users/yousuf/Documents/file.txt", "1100110011111100");          
//    }
}
//import java.io.IOException;
//import java.security.Key;
//import java.util.Properties;
//import java.util.Scanner;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.Authenticator;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//public class App 
//{
//	static Scanner scn = new Scanner(System.in);
//  
//	public static int sendEmail(String message, String subject, String to, final String from,final String password,String ourkeyvalue)throws Exception,IOException {
//		Properties properties = System.getProperties();
//		
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		properties.put("mail.smtp.port", "465");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//		
//
////		 Session Instantiation
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(from,password);
//			}
//		});
//		
//		// Compose message
//		MimeMessage mail = new MimeMessage(session);
//		
//		mail.setFrom(from);
//		mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		mail.setSubject(subject);	
//		
//		
//		if (ourkeyvalue.length()!=16) {
//			System.out.println("Length of UKey should be equal to 16 ");
//			return -1;
//		}
//		Encryption enc = new Encryption(ourkeyvalue);
//		Key key = enc.generateKey();
//		
//		String encryptedMessage = enc.encrypt(message, key);
//		char[] a = encryptedMessage.toCharArray();
//		StringBuffer result = new StringBuffer();
//
//		int s =4;
//		for (int i=0; i<ourkeyvalue.length(); i++){
//			if ((ourkeyvalue.charAt(i)>='A' && ourkeyvalue.charAt(i)<='Z') || (ourkeyvalue.charAt(i)>='a' && ourkeyvalue.charAt(i)<='z')){
//				if (Character.isUpperCase(ourkeyvalue.charAt(i))){
//					char ch = (char)(((int)ourkeyvalue.charAt(i) + s - 65) % 26 + 65);
//					result.append(ch);
//				}
//				else
//				{
//					char ch = (char)(((int)ourkeyvalue.charAt(i) + s - 97) % 26 + 97);
//					result.append(ch);
//				}
//			}else {
//				result.append(ourkeyvalue.charAt(i));
//			}
//        }
//		char[] kvarray = result.toString().toCharArray();
//		
//		char[] temp = new char[encryptedMessage.length()+ourkeyvalue.length()];
//		int i=0;
//		int k=0;
//		for (i=0;i<encryptedMessage.length()/2;i++) {
//			temp[k++]=a[i];
//		}
//		int z=i;
//		for (int j=0;j<ourkeyvalue.length();j++) {
//			temp[k++]=kvarray[j];
//		}
//		for (int l=z;l<encryptedMessage.length();l++) {
//			temp[k++]=a[l];
//		}
//		String mailtext = String.copyValueOf(temp);
//		System.out.print("Encrypted message : ");
////		System.out.println(mailtext);
//		// Set text
////		mail.setText(mailtext);
//                BodyPart messageBodyPart = new MimeBodyPart();
//
//         // Now set the actual message
//                messageBodyPart.setText(mailtext);
//		Multipart multipart = new MimeMultipart();
//                // Set text message part
//                multipart.addBodyPart(messageBodyPart);
//
//                // Part two is attachment
//                messageBodyPart = new MimeBodyPart();
//                String filename = "file.txt";
//                DataSource source = new FileDataSource(filename);
//                messageBodyPart.setDataHandler(new DataHandler(source));
//                messageBodyPart.setFileName(filename);
//                multipart.addBodyPart(messageBodyPart);
//
//                // Send the complete message parts
//                mail.setContent(multipart);
//		// Send message
//		try{
//			Transport.send(mail);
//			System.out.println("Sent message succesfully.");
//			return 1;
//		}catch(Exception e){
//			System.out.println("Unsuccesfull");
//			return 0;
//		}
//
//	}
//    public static void main(String[] args) throws Exception{
//         sendEmail("hello", "mail", "sajjad18801@gmail.com", "azizmsajjad@gmail.com","pass***1", "1010101010101010");          
//    }
//}
