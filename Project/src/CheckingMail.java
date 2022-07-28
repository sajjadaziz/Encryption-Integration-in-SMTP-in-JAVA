import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.*;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
public class CheckingMail {
 
    /**
     * @param args
     */
    static String subjectm="";
    static String bodym="";
    static String fromm="";
    static String attachFiles = "";
    public static void receiveMail(String Email,String password) throws Exception {
        String hostval = "pop.gmail.com";
        String mailStrProt = "pop3";
        try {
      //Set property values
      Properties propvals = new Properties();
      propvals.put("mail.pop3.host", hostval);
      propvals.put("mail.pop3.port", "995");
      propvals.put("mail.pop3.starttls.enable", "true");
      Session emailSessionObj = Session.getDefaultInstance(propvals);  
      //Create POP3 store object and connect with the server
      Store store = emailSessionObj.getStore("pop3s");
      store.connect(hostval, Email, password);
//      
//        Scanner scn = new Scanner(System.in);
//        Properties props = new Properties();
//        			
//        	props.put("mail.smtp.host","smtp.gmail.com");
//        	props.put("mail.smtp.socketFactory.port", "465");
//        	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        	props.put("mail.smtp.auth", "true");
//        	props.put("mail.smtp.port", "465");
//        
////System.out.println("hellooooooooooooooooo");
//        Session session = Session.getDefaultInstance(props, null);
//        // System.out.print("Email? ");
//        // String Email = scn.next();
//        // char[] pass = System.console().readPassword("Enter Password : ");
//        // final String password = new String(pass);
//        System.out.println("hellooooooooooooooooo");
//        Store store = session.getStore("imaps");
//        try{
//            store.connect("smtp.gmail.com", Email,password);
//        System.out.println("hellooooooooooooooooo");
        
//         System.out.println(store);
    
            Folder inbox = store.getFolder("INBOX");
//            inbox.open(Folder.READ_WRITE); 
            inbox.open(Folder.READ_ONLY);
//            int messageCount = inbox.getMessageCount();
//            System.out.println("Total Messages " + messageCount);
//            int startMessage = messageCount - 5;
//            int endMessage = messageCount;
    
//            if (messageCount < 5) {
//                startMessage = 0;
//            }
    
            Message[] messages = inbox.getMessages();
//            System.out.println("Total Messages " + messages.length);
//           for (int i = messageobjs.length-1, n = 0; i >0; i--) {
//         Message indvidualmsg = messages[messages.length-1];
//         System.out.println("Printing individual messages");
//         System.out.println("No# " + (0 + 1));
//         System.out.println("Email Subject: " + indvidualmsg.getSubject());
//         System.out.println("Sender: " + indvidualmsg.getFrom()[0]);
//         System.out.println("Content: " + indvidualmsg.getContent().toString());

      
            
            Message message = messages[messages.length-1];
//            String content= message.getContent().toString();
            String content = "";
//            System.out.println(content);
            System.out.println(message.getSubject());
            fromm = String.valueOf(message.getFrom()[0]);
            subjectm = message.getSubject();
            
            String contentType = message.getContentType();
//            String attachFiles = "";
            if (contentType.contains("multipart")) {
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
            System.out.println("Count : "+numberOfParts);
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
//                                                        messageContent = part.getContent().toString();
//            System.out.println("Content : "+messageContent);
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile("C:\\Users\\yousuf\\Documents" + File.separator + fileName);
						} else {
							content = part.getContent().toString();
            System.out.println("Content : "+content);
						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object contnt = message.getContent();
					if (contnt != null) {
						content = contnt.toString();
					}
				}
            System.out.println("\t Attachments: " + attachFiles);
//            List<String> downloadedAttachments = new ArrayList<String>();
//            Multipart multiPart = (Multipart) message.getContent();
//            int numberOfParts = multiPart.getCount();
//            for (int partCount = 0; partCount < numberOfParts; partCount++) {
//                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
//                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
//                    String file = part.getFileName();
//                    String downloadDirectory = null;
//                    part.saveFile(downloadDirectory + File.separator + part.getFileName());
//                    downloadedAttachments.add(file);
//                }
//            }
//            System.out.println(downloadedAttachments);
            
//            Multipart multipart = (Multipart) message.getContent();
//	     for(int k = 0; k < multipart.getCount(); k++){
//	       BodyPart bodyPart = multipart.getBodyPart(k);  
//	       InputStream stream = 
//                             (InputStream) bodyPart.getInputStream();  
//	       BufferedReader bufferedReader = 
//	    	   new BufferedReader(new InputStreamReader(stream));  
// 
//	        while (bufferedReader.ready()) {  
//	    	       System.out.println(bufferedReader.readLine());  
//	    	}  
//	    	   System.out.println();  
//	      }  
	   
            System.out.println("111111111 hellooooooooooooooooo "+content);
            int sizeOfByte = 16;
            int emhalflength = (content.length()/2)-((sizeOfByte/2));
            String dkeyvalue = content.substring(emhalflength,emhalflength+sizeOfByte);
//             System.out.println(dkeyvalue);
            StringBuffer result = new StringBuffer();
            int s=22;
            for (int i=0; i<dkeyvalue.length(); i++){
                if ((dkeyvalue.charAt(i)>='A' && dkeyvalue.charAt(i)<='Z') || (dkeyvalue.charAt(i)>='a' && dkeyvalue.charAt(i)<='z')){
                    if (Character.isUpperCase(dkeyvalue.charAt(i))){
                        char ch = (char)(((int)dkeyvalue.charAt(i) + s - 65) % 26 + 65);
                        result.append(ch);
                    }
                    else
                    {
                        char ch = (char)(((int)dkeyvalue.charAt(i) + s - 97) % 26 + 97);
                        result.append(ch);
                    }
                }else {
                    result.append(dkeyvalue.charAt(i));
                }
            }
            String resultkey = result.toString();
//            int sizeOfByte = 16;
//            int emhalflength = (content.length()/2)-((sizeOfByte/2)+1);
//            String dkeyvalue = content.substring(emhalflength,emhalflength+sizeOfByte);
//            // System.out.println(dkeyvalue);
//            StringBuffer result = new StringBuffer();
//            int s=22;
//            for (int i=0; i<dkeyvalue.length(); i++){
//                if ((dkeyvalue.charAt(i)>='A' && dkeyvalue.charAt(i)<='Z') || (dkeyvalue.charAt(i)>='a' && dkeyvalue.charAt(i)<='z')){
//                    if (Character.isUpperCase(dkeyvalue.charAt(i))){
//                        char ch = (char)(((int)dkeyvalue.charAt(i) + s - 65) % 26 + 65);
//                        result.append(ch);
//                    }
//                    else
//                    {
//                        char ch = (char)(((int)dkeyvalue.charAt(i) + s - 97) % 26 + 97);
//                        result.append(ch);
//                    }
//                }else {
//                    result.append(dkeyvalue.charAt(i));
//                }
//            }
//            String resultkey = result.toString();
            // System.out.println(resultkey);
            String msg = content.substring(0,emhalflength) + content.substring(emhalflength+sizeOfByte,content.length());
            System.out.println("MSG hellooooooooooooooooo "+msg); 
            System.out.println("KEY hellooooooooooooooooo "+resultkey); 
            Encryption enc = new Encryption(resultkey);
            System.out.println("00000000000000000000000000000000 hellooooooooooooooooo");
            Key key = enc.generateKey();
            System.out.println("11111111111111111111111111111111 hellooooooooooooooooo");
            String decryptedvalue = enc.decrypt(msg, key);
            System.out.println("22222222222222222222222222222222 hellooooooooooooooooo");
            System.out.println("Decrypted ::::::::::::::: "+decryptedvalue);
            System.out.print("Decrypted value is : ");
            System.out.println(decryptedvalue);
            bodym=decryptedvalue;
            inbox.close(true);
            System.out.println("Done....");
            store.close();
//            scn.close();
        }catch(Exception e){
            System.out.println("Invalid Inputs");
        }
        }
        public static String subject(){
            if (subjectm==""){
                return "NULL";
            }else {
                return subjectm;
            }
        }
        public static String body(){
            if (bodym==""){
                return "NULL";
            }else {
                return bodym;
            }
        }
        public static String from(){
            if (fromm==""){
                return "NULL";
            }else {
                return fromm;
            }
        }
        public static String attach(){
            if (attachFiles==""){
                return "NULL";
            }else {
                return attachFiles;
            }
        }
//        public static void main(String[] args) throws Exception{
//        receiveMail("sajjad18801@gmail.com", "msajjad2001");
//    }
}