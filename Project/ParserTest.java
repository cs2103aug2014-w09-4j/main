package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testAdd() {
		
		Data data = new Data();
		
		Parser parser = new Parser();
		
		data.setInput("add do tutorial");
				
		parser.parse();
		
		assertEquals("1", Data.getCommand(), "add");
		
		assertEquals("1", Data.getDescription(), "do tutorial");

	}
	
	@Test
	public void testDelete() {
		
		Data data = new Data();
		
		Parser parser = new Parser();
		
		data.setInput("delete do tutorial");
				
		parser.parse();
		
		assertEquals("1", Data.getCommand(), "delete");
		
		assertEquals("1", Data.getDescription(), "do tutorial");

	}
	
	@Test
	public void testChange() {
		
		Data data = new Data();
		
		Parser parser = new Parser();
		
		data.setInput("change do tutorial to study for exams");
				
		parser.parse();
		
		assertEquals("1", Data.getCommand(), "change");
		
		assertEquals("1", Data.getDescription(), "do tutorial");

		assertEquals("1", Data.getNewDescription(), "study for exams");

	}

}
