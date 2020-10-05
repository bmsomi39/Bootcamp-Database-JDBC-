
import task8.BootcampAdmin.src.Search;
import task8.BootcampAdmin.src.Capture;

/**
 * Factory class:
 * Plays the role of connecting the main
 * menu with the classes corresponding 
 * to each option
 * @author Bonga Msomi
 *
 */
public class PoisedFactory {

	
	public Poised createBootcamp(String line) {
		
		    if (line == null || line.isEmpty()) 
	            return null; 
	        if ("1".equals(line)) { //1 - Capture
	            return (Poised) new Capture(); 
	        } 
	        else if ("2".equals(line)) { //2 - Update 
	            return new Update(); 
	        }
	        else if ("3".equals(line)) { //3  - Finalize
	            return new Finalize(); 
	        }
	        else if ("4".equals(line)) { //4 - View projects that still need to be completed
	            return new ViewComplete(); 
	        }
	        else if ("5".equals(line)) { //5 - View past due projects
	            return new ViewPastDue(); 
	        }
	        else if ("6".equals(line)) { //6 Search for a project
	            return new Search(); 
	        }
	        
	        
	        
	        
	        return null;
	}
	

}

/*public class NotificationFactory { 
    public Notification createNotification(String channel) 
    { 
        if (channel == null || channel.isEmpty()) 
            return null; 
        if ("SMS".equals(channel)) { 
            return new SMSNotification(); 
        } 
        else if ("EMAIL".equals(channel)) { 
            return new EmailNotification(); 
        } 
        else if ("PUSH".equals(channel)) { 
            return new PushNotification(); 
        } 
        return null; 
    } 
} */