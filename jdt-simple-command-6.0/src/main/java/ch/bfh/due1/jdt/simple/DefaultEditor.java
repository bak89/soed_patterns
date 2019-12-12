/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2010
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.bfh.due1.jdt.framework.Clipboard;
import ch.bfh.due1.jdt.framework.CommandHandler;
import ch.bfh.due1.jdt.framework.CommandHandlerEvent;
import ch.bfh.due1.jdt.framework.CommandHandlerListener;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Logger;
import ch.bfh.due1.jdt.framework.Shape;
import ch.bfh.due1.jdt.framework.ShapeHandle;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.View;


/**
 * A variant of an implementation of the editor interface. It can handle several
 * views, and it supports a clip board.
 */
public final class DefaultEditor implements Editor {
	/**
	 * Default tool.
	 */
	private Tool defaultTool;

	/**
	 * A list of other tools.
	 */
	private List<Tool> otherTools = new ArrayList<Tool>();

	/**
	 * The command handler used.
	 */
	private CommandHandler commandHandler;

	/**
	 * A map of log4j loggers.
	 */
	private Map<Class<?>, org.apache.log4j.Logger> loggers = new HashMap<Class<?>, org.apache.log4j.Logger>();

	/**
	 * A clip board for shapes. Note that the editor supports only one clip
	 * board at a time.
	 */
	private Clipboard clipboard = new ClipboardImpl();

	/**
	 * 
	 */
	private List<View> views = new ArrayList<View>();

	/**
	 * The current view.
	 */
	private View currentView;

	/**
	 * The reference to the editor's environment.
	 */
	private JdtEditor environment;

	/**
	 * Implements a listener for the command handler in place. Calls
	 * checkEditorState() upon notification.
	 *
	 * @author Eric Dubuis
	 */
	private class CommandHandlerListenerImpl implements CommandHandlerListener {

		@Override
		public void commandHandlerChanged(CommandHandlerEvent event) {
			checkEditorState();
		}
	}

	/**
	 * A simple adapter to adapt our Logger interface to log4j.
	 * 
	 * @author Eric Dubuis
	 */
	private class LoggerAdapter implements Logger {
		/**
		 * The effective log4j logger.
		 */
		private org.apache.log4j.Logger effectiveLogger;

		/**
		 * Initializes the log4j logger.
		 * 
		 * @param l
		 *            the logger
		 */
		public LoggerAdapter(org.apache.log4j.Logger l) {
			this.effectiveLogger = l;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void debug(String message) {
			this.effectiveLogger.debug(message);
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public void info(String message) {
			this.effectiveLogger.info(message);
		}
	}

	/**
	 * Trivial implementation of a clip board.
	 */
	private class ClipboardImpl implements Clipboard {
		private List<Shape> shapes = new LinkedList<Shape>();

		/**
		 * @inheritDoc
		 * @see ch.bfh.due1.jdt.framework.Clipboard#put(java.util.List)
		 */
		@Override
		public void put(List<Shape> shapes) {
			this.shapes.addAll(shapes);
		}

		/**
		 * @inheritDoc
		 * @see ch.bfh.due1.jdt.framework.Clipboard#get()
		 */
		@Override
		public List<Shape> get() {
			return this.shapes;
		}

		/**
		 * @inheritDoc
		 * @see ch.bfh.due1.jdt.framework.Clipboard#remove(java.util.List)
		 */
		@Override
		public void remove(List<Shape> shapes) {
			this.shapes.removeAll(shapes);
		}

		/**
		 * @inheritDoc
		 * @see ch.bfh.due1.jdt.framework.Clipboard#clear()
		 */
		@Override
		public void clear() {
			this.shapes.clear();
		}
	}

	/**
	 * Initializes the editor.
	 * 
	 * @param env
	 *            callback parameter for the editor's environment.
	 */
	public DefaultEditor(JdtEditor env) {
		this.environment = env;
		this.commandHandler = createCommandHandler(this);
	}

	/**
	 * @inheritDoc
	 * @see ch.bfh.due1.jdt.framework.Editor#registerDefaultTool(ch.bfh.due1.jdt.framework.Tool)
	 */
	@Override
	public void registerDefaultTool(Tool t) {
		this.defaultTool = t;
	}

	/**
	 * @inheritDoc
	 * @see ch.bfh.due1.jdt.framework.Editor#registerTool(ch.bfh.due1.jdt.framework.Tool)
	 */
	@Override
	public void registerTool(Tool t) {
		this.otherTools.add(t);
	}

	/**
	 * 'Relay' method that creates and registers a view via this editor's
	 * environment.
	 *
	 * @see ch.bfh.due1.jdt.framework.Editor#createView()
	 */
	@Override
	public void createView() {
		this.environment.createView();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#registerView(ch.bfh.due1.jdt.framework.View)
	 */
	@Override
	public void registerView(View view) {
		this.views.add(view);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getViewCount()
	 */
	@Override
	public int getViewCount() {
		return this.views.size();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getIthView(int)
	 */
	@Override
	public View getIthView(int ith) {
		return this.views.get(ith);
	}

	/**
	 * @see ch.bfh.due1.jdt.framework.Editor#getCurrentView()
	 */
	@Override
	public View getCurrentView() {
		return this.currentView;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#setCurrentView(int)
	 */
	@Override
	public void setCurrentView(int ith) {
		Tool t = null;
		if (this.currentView != null) {
			t = this.currentView.getTool();
		}
		this.currentView = this.views.get(ith);
		if (t != null) {
			this.currentView.setTool(t);
		} else if (this.defaultTool != null) {
			this.currentView.setTool(this.defaultTool);
		}
	}

	/**
	 * Displays the given string in the status field of the application.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#showStatus(java.lang.String)
	 */
	@Override
	public void showStatus(String s) {
		this.environment.showStatus(s);
	}

	/**
	 * Calls checkEditorState() helper method and sets the default tool.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#toolDone()
	 */
	@Override
	public void toolDone() {
		getCurrentView().setTool(this.defaultTool);
		checkEditorState();
	}

	/**
	 * Checks the state of the tool and actions by calling checkTools() and
	 * checkActions().
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#checkEditorState()
	 */
	@Override
	public void checkEditorState() {
		this.environment.checkTools();
		this.environment.checkActions();
	}

	/**
	 * Returns a JDT logger, based on a log4j logger. If the logger doen not
	 * exist then it is created.
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getLogger(java.lang.Class)
	 */
	@Override
	public Logger getLogger(Class<?> logFacility) {
		org.apache.log4j.Logger logger = null;
		if (this.loggers.containsKey(logFacility)) {
			logger = this.loggers.get(logFacility);
		} else {
			logger = org.apache.log4j.Logger.getLogger(logFacility);
			this.loggers.put(logFacility, logger);
		}
		return new LoggerAdapter(logger);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#addShape(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public void addShape(Shape s) {
		getCurrentView().addShape(s);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#addToSelection(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public void addToSelection(Shape s) {
		getCurrentView().addToSelection(s);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#clearSelection()
	 */
	@Override
	public void clearSelection() {
		getCurrentView().clearSelection();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getCommandHandler()
	 */
	@Override
	public CommandHandler getCommandHandler() {
		return this.commandHandler;
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getSelection()
	 */
	@Override
	public List<Shape> getSelection() {
		return getCurrentView().getSelection();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getSelectionHandles()
	 */
	@Override
	public List<ShapeHandle> getSelectionHandles() {
		return getCurrentView().getSelectionHandles();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getShapes()
	 */
	@Override
	public List<Shape> getShapes() {
		return getCurrentView().getShapes();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#removeFromSelection(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public void removeFromSelection(Shape s) {
		getCurrentView().removeFromSelection(s);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#removeShape(ch.bfh.due1.jdt.framework.Shape)
	 */
	@Override
	public boolean removeShape(Shape s) {
		return getCurrentView().removeShape(s);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#setCursor(java.awt.Cursor)
	 */
	@Override
	public void setCursor(Cursor c) {
		getCurrentView().setCursor(c);
	}

	/**
	 * @inheritDoc
	 * 
	 * @see ch.bfh.due1.jdt.framework.Editor#getClipboard()
	 */
	@Override
	public Clipboard getClipboard() {
		return this.clipboard;
	}

	/**
	 * Returns the class name of this tool implementation.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName();
	}

	/**
	 * Returns a command handler. Tries to instantiate a command handler as
	 * specified with a property and returns it. If it cannot be instantiated,
	 * returns a dummy command handler.
	 * 
	 * @return a dummy default handler.
	 */
	private final CommandHandler createCommandHandler(Editor e) {
		if (this.commandHandler == null) {
			this.commandHandler = loadCommandHandler();
		}
		this.commandHandler.addCommandHandlerListener(
				new CommandHandlerListenerImpl());
		return this.commandHandler;
	}

	/**
	 * Tries to instantiate a command handler as given by a property.
	 * 
	 * @return a command handler, or null otherwise if a command handler cannot
	 *         be instantiated
	 */
	private final CommandHandler loadCommandHandler() {
		CommandHandlerLoader loader = null;
		CommandHandler commandHandler = null;
		try {
			loader = new CommandHandlerLoader();
			commandHandler = loader.createCommandHandler();
		} catch (Exception e) {

			getLogger(this.getClass()).info(e.getMessage());
			this.environment
					.showStatus("Cannot instantiate command handler specified with property "
							+ CommandHandlerLoader.PROP_CLASSNAME);
		}
		return commandHandler;
	}

}
