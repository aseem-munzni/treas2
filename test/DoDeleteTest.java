
import java.util.ArrayList;
import org.junit.Assert.*;

public class DoDeleteTest {

	private	int num;
	private BookServer testServlet;
	private String testId;

	
	@Before
	public void before() {
		System.out.println("Before Test Case");
		testServlet = new BookServer();
		num = BookServer.books.size();
		ArrayList<String> list = new ArrayList<String>(BookServer.books.keySet());
		testId = list.get(0);
		assertTrue("Repository contains " + num + " books", true);
	}

	@Test
	public void doDelete() { 
		System.out.println("Test");
		testServlet.doTheDeletion(testId);
	}

	@After
	public void after() {
		System.out.println("After Test Case");
		assertTrue("Repository now contains " + BookServer.books.size() + " books", BookServer.books.size() == num -1);
	}

	
}

