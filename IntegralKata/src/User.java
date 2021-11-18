import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class User {
	private ArrayList<Post> myPosts = new ArrayList<Post>();
	private ArrayList<User> following = new ArrayList<User>();
	private String name = "";
	
	public User(String name) {
		this.name = name;
	}
	
	public void post(String message, int secondsAgo) {
		myPosts.add(new Post(message, secondsAgo, name));
	}

	public void followUser(User user) {
		following.add(user);
	}

	public String viewTimeline(User user) {
		return printPosts(user.myPosts, true, false);
	}

	public String viewMyTimeline() {
		return printPosts(myPosts, false, false);
	}

	public int getFollowerCount() {
		return following.size();
	}
	
	public String viewMyWall() {
		ArrayList<Post> posts = new ArrayList<Post>();
		for(int i = 0; i != following.size(); i++) {
			posts.addAll(following.get(i).myPosts);
		}
		posts.addAll(myPosts);
		return printPosts(posts, true, true);
	}

	private void sortPosts(ArrayList<Post> posts) {
		Collections.sort(posts, new Comparator<Post>() {
			@Override
			public int compare(Post o1, Post o2) {
				return Integer.compare(o2.secondsSincePost, o1.secondsSincePost);
			}
		});
	}

	private String getTimeStamp(Post post) {
		if(post.secondsSincePost < 60){
			return " (" + post.secondsSincePost + " seconds ago)";
		} else if (post.secondsSincePost < 120) {
			return " (1 minute ago)";
		} else {
			return " (" + post.secondsSincePost / 60 + " minutes ago)";
		}
	}	

	private String printPosts(ArrayList<Post> posts, boolean printTimestamps, boolean printNames) {
		String wall = "";
		sortPosts(posts);

		for(int i = (posts.size()-1); i >=0; i--) {
			Post post =  posts.get(i);
			
			if(printNames){ wall += post.posterName + " - "; }
			
			wall += post.message;
			
			if(printTimestamps){ wall += getTimeStamp(post); }
			
			if(i != 0){ wall += "\n"; }
		};
		return wall;
	}
	
};
