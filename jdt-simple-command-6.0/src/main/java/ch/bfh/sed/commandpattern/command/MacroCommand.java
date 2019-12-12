/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.sed.commandpattern.command;

// TODO: Complete...
import ch.bfh.due1.jdt.framework.AbstractCommand;
import ch.bfh.due1.jdt.framework.Command;


/**
 * A command that is a composition of commands. For example when moving several
 * selected shapes then for each shape an individual move command can be used.
 *
 * @author Eric Dubuis
 */
public class MacroCommand extends AbstractCommand {
	// TODO: Complete...

	/**
	 * Creates an instance of a macro command with an empty list of commands.
	 */
	public MacroCommand() {
	}


	/**
	 * Creates an instance of a macro command with an initial command.
	 *
	 * @param c
	 *            The initial command.
	 */
	public MacroCommand(Command c) {
		// TODO: Complete...
	}

	/**
	 * Adds a command.
	 *
	 * @param c
	 *            A command.
	 */
	@Override
	public void addCommand(Command c) {
		// TODO: Complete...
	}

	/**
	 * Executes every command in the list of commands of this macro command. The
	 * order is defined by the order of added commands to this macro
	 * command.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#execute()
	 */
	@Override
	public void execute() {
		// TODO: Complete...
	}

	/**
	 * Undoes every command in the list of commands of this macro command. The
	 * order is defined by the inverse order of added commands to this macro
	 * command.
	 *
	 * @see ch.bfh.due1.jdt.framework.Command#undo()
	 */
	@Override
	public void undo() {
		// TODO: Complete...
	}
}
