
package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Test;

import tracker.requirement.Command;
import tracker.requirement.Requirement;
import tracker.requirement.enums.CommandValue;
import tracker.requirement.enums.Rejection;

/**
 * JUnit test for Command class and its functionality
 * 
 * @author Summer Mims
 */
public class CommandTest
{
	private Command c;

	/**
	 * 
	 * test constructors for Command
	 *
	 */
	@Test
	public void testConstructor()
	{
		// create new requirement
		Requirement r1 = new Requirement("Summary 1", "Acceptance ID 1");
		// create invalid accept command - empty estimate
		try
		{
			c = new Command(CommandValue.ACCEPT, r1.getSummary(),
					r1.getAcceptanceTestId(), 1, "", null, null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid estimate.", e.getMessage());
		}
		// create invalid accept command - null estimate
		try
		{
			c = new Command(CommandValue.ACCEPT, r1.getSummary(),
					r1.getAcceptanceTestId(), 1, null, null, null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid estimate.", e.getMessage());
		}
		// create invalid accept command - invalid priority
		try
		{
			c = new Command(CommandValue.ACCEPT, r1.getSummary(),
					r1.getAcceptanceTestId(), 4, "1", null, null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid priority.", e.getMessage());
		}
		// create invalid accept command - invalid priority
		try
		{
			c = new Command(CommandValue.ACCEPT, r1.getSummary(),
					r1.getAcceptanceTestId(), 0, "1", null, null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid priority.", e.getMessage());
		}
		// create Accept valid command
		c = new Command(CommandValue.ACCEPT, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", null, null);
		assertEquals("ACCEPT", c.getCommand().toString());
		assertEquals(r1.getSummary(), c.getSummary());
		assertEquals(r1.getAcceptanceTestId(), c.getAcceptanceTestId());
		assertEquals(1, c.getPriority());
		assertEquals("1", c.getEstimate());
		assertNull(c.getDeveloperId());
		assertNull(c.getRejectionReason());

		// create invalid assign command - null developer
		try
		{
			c = new Command(CommandValue.ASSIGN, r1.getSummary(),
					r1.getAcceptanceTestId(), 1, "1", null, null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid developer id.", e.getMessage());
		}
		// create invalid assign command - empty developer id
		try
		{
			c = new Command(CommandValue.ASSIGN, r1.getSummary(),
					r1.getAcceptanceTestId(), 1, "1", "", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid developer id.", e.getMessage());
		}
		// create assign valid command
		c = new Command(CommandValue.ASSIGN, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", null);
		assertEquals("ASSIGN", c.getCommand().toString());
		assertEquals(r1.getSummary(), c.getSummary());
		assertEquals(r1.getAcceptanceTestId(), c.getAcceptanceTestId());
		assertEquals(1, c.getPriority());
		assertEquals("1", c.getEstimate());
		assertEquals("smims", c.getDeveloperId());
		assertNull(c.getRejectionReason());

		// create fail valid command
		c = new Command(CommandValue.FAIL, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", null);
		assertEquals("FAIL", c.getCommand().toString());
		assertEquals(r1.getSummary(), c.getSummary());
		assertEquals(r1.getAcceptanceTestId(), c.getAcceptanceTestId());
		assertEquals(1, c.getPriority());
		assertEquals("1", c.getEstimate());
		assertEquals("smims", c.getDeveloperId());
		assertNull(c.getRejectionReason());

		// create complete valid command
		c = new Command(CommandValue.COMPLETE, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", null);
		assertEquals("COMPLETE", c.getCommand().toString());
		assertEquals(r1.getSummary(), c.getSummary());
		assertEquals(r1.getAcceptanceTestId(), c.getAcceptanceTestId());
		assertEquals(1, c.getPriority());
		assertEquals("1", c.getEstimate());
		assertEquals("smims", c.getDeveloperId());
		assertNull(c.getRejectionReason());

		// create pass valid command
		c = new Command(CommandValue.PASS, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", null);
		assertEquals("PASS", c.getCommand().toString());
		assertEquals(r1.getSummary(), c.getSummary());
		assertEquals(r1.getAcceptanceTestId(), c.getAcceptanceTestId());
		assertEquals(1, c.getPriority());
		assertEquals("1", c.getEstimate());
		assertEquals("smims", c.getDeveloperId());
		assertNull(c.getRejectionReason());

		// create invalid reject command - null rejection
		try
		{
			c = new Command(CommandValue.REJECT, r1.getSummary(),
					r1.getAcceptanceTestId(), 1, "1", "smims", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Rejection Reason is null.", e.getMessage());
		}
		// create reject valid command
		c = new Command(CommandValue.REJECT, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", Rejection.DUPLICATE);
		assertEquals("REJECT", c.getCommand().toString());
		assertEquals(r1.getSummary(), c.getSummary());
		assertEquals(r1.getAcceptanceTestId(), c.getAcceptanceTestId());
		assertEquals(1, c.getPriority());
		assertEquals("1", c.getEstimate());
		assertEquals("smims", c.getDeveloperId());
		assertEquals("DUPLICATE", c.getRejectionReason().toString());

		// create valid revise command
		c = new Command(CommandValue.REVISE, r1.getSummary(),
				r1.getAcceptanceTestId(), 1, "1", "smims", null);
		assertEquals("REVISE", c.getCommand().toString());
		assertEquals(r1.getSummary(), c.getSummary());
		assertEquals(r1.getAcceptanceTestId(), c.getAcceptanceTestId());
		assertEquals(1, c.getPriority());
		assertEquals("1", c.getEstimate());
		assertEquals("smims", c.getDeveloperId());
		assertEquals(null, c.getRejectionReason());
		// create invalid revise - null summary
		try
		{
			c = new Command(CommandValue.REVISE, null, r1.getAcceptanceTestId(),
					1, "1", "smims", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid summary.", e.getMessage());
		}
		// create invalid revise - null acceptanceTestId
		try
		{
			c = new Command(CommandValue.REVISE, r1.getSummary(), null, 1, "1",
					"smims", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid acceptance test id.", e.getMessage());
		}
		// create invalid revise - empty summary
		try
		{
			c = new Command(CommandValue.REVISE, "", r1.getAcceptanceTestId(),
					1, "1", "smims", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid summary.", e.getMessage());
		}
		// create invalid revise - empty acceptanceTestId
		try
		{
			c = new Command(CommandValue.REVISE, r1.getSummary(), "", 1, "1",
					"smims", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Invalid acceptance test id.", e.getMessage());
		}

		// test null command
		try
		{
			c = new Command(null, r1.getSummary(), r1.getAcceptanceTestId(), 1,
					"1", "smims", null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertEquals("Command is null.", e.getMessage());
		}
	}

}
