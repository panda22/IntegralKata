import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Post {

	public String message;
	public int secondsSincePost;
	public String posterName;
	
	public Post(String text, int secondsAgo, String name) {
		message = text;
		secondsSincePost = secondsAgo;
		posterName = name;
	}
}
