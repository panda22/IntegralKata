import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class KataTest {

	@Test
    void testViewMyWall() {
        User user = new User("Alice");
        user.post("I love the weather today.", 60);
        assertEquals(user.viewMyTimeline(), "I love the weather today.");
    }
	
	@Test
	void testViewMyWallShowsNewestFirst() {
		User user = new User("Alice");
        user.post("This is my First Post", 60);
        user.post("I'm loving this!", 30);
        assertEquals(user.viewMyTimeline(), "I'm loving this!\nThis is my First Post");
	}
	
	@Test
	void testAliceCanViewBobsWall() {
		User alice = new User("Alice");
		User bob = new User("Bob");
		bob.post("Darn! We lost!", 120);
		bob.post("Good game though.", 60);
		assertEquals(alice.viewTimeline(bob), "Good game though. (1 minute ago)\nDarn! We lost! (2 minutes ago)");
	}
	
	@Test
	void testFollowUserSavesUsers() {
		User alice = new User("Alice");
		User bob = new User("Bob");
		User charlie = new User("Charlie");
		alice.followUser(charlie);
		alice.followUser(bob);
		assertEquals(alice.getFollowerCount(), 2);
	}
	
	@Test
	void testTimelineShowsAllFollowedInOrder() {
		User alice = new User("Alice");
		User bob = new User("Bob");
		User charlie = new User("Charlie");
		charlie.followUser(alice);
		charlie.followUser(bob);
		bob.post("Darn! We lost!", 120);
		alice.post("I love the weather today.", (60*5));
		bob.post("Good game though.", 60);
		charlie.post("I'm in New York today! Anyone wants to have a coffee?", 15);
		assertEquals(charlie.viewMyWall(), "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)\nBob - Good game though. (1 minute ago)\nBob - Darn! We lost! (2 minutes ago)\nAlice - I love the weather today. (5 minutes ago)");
	}
}