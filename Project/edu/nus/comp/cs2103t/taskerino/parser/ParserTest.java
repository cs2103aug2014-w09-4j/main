//@author A0110594L
package edu.nus.comp.cs2103t.taskerino.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nus.comp.cs2103t.taskerino.common.Data;

public class ParserTest {

	@Test
	public void testAdd() {
		
		Data data = new Data();
		
		Parser parser = new Parser();
		
		data.setInput("add do tutorial");
				
		parser.parse();
		
		assertEquals("1", "add", Data.getCommand());
		
		assertEquals("1", "do tutorial", Data.getDescription());

	}
	
	@Test
	public void testDelete() {
		
		Data data = new Data();
		
		Parser parser = new Parser();
		
		data.setInput("delete do tutorial");
				
		parser.parse();
		
		assertEquals("1", "delete", Data.getCommand());
		
		assertEquals("1", "do tutorial", Data.getDescription());

	}
	
	@Test
	public void testChange() {
		
		Data data = new Data();
		
		Parser parser = new Parser();
		
		data.setInput("change do tutorial to study for exams");
				
		parser.parse();
		
		assertEquals("1", "change", Data.getCommand());
		
		assertEquals("1", "do tutorial", Data.getDescription());

		assertEquals("1", "study for exams", Data.getNewDescription());

	}

}
