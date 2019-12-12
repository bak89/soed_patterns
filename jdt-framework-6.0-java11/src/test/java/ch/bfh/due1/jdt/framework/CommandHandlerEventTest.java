package ch.bfh.due1.jdt.framework;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.bfh.due1.jdt.framework.Command;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.CommandHandlerEvent;
import ch.bfh.due1.jdt.framework.CommandHandlerListener;

/**
 * Tests the command handler event class.
 *
 * @author Eric Dubuis
 */
public class CommandHandlerEventTest {
	private class CommandHandlerMock implements CommandHandler {

		@Override
		public void addCommand(Command c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void undoLast() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void redoLast() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean undoPossible() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean redoPossible() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void addCommandHandlerListener(CommandHandlerListener listener) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeCommandHandlerListener(
				CommandHandlerListener listener) {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Tests if a correctly initialized command handler event
	 * object returns the command handler instance given.
	 */
	@Test
	public void test() {
		CommandHandler ch = new CommandHandlerMock();
		CommandHandlerEvent e = new CommandHandlerEvent(ch);
		assertNotNull(e.getCommandHandler());
		assertSame(ch, e.getCommandHandler());
	}
}
