/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2006
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bfh.due1.jdt.framework.ActionManager;
import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Sheet;
import ch.bfh.due1.jdt.framework.SheetFactory;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.ToolFactory;
import ch.bfh.due1.jdt.framework.View;
import ch.bfh.due1.jdt.framework.ViewFactory;


/**
 * Main driver class for a simple drawing editor.
 * 
 * @author Eric Dubuis
 */
public class JdtEditor {
	/** The name of the tool. */
	private static final String TOOL_NAME = "Simple Drawing Tool";

	/** The list of tool buttons. */
	private List<ToolButton> toolButtons = new ArrayList<ToolButton>();

	/** The log4j logger for this class, named "jdt.app.simpe.JdtEditor". */
	private org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(JdtEditor.class.getName());

	/**
	 * The status field of the display. PENDING: Should become a scroll pane.
	 */
	private JTextField statusField;

	/**
	 * The reference to the implementation of the editor.
	 */
	private DefaultEditor editor;

	/**
	 * Tabbed pane with views.
	 */
	private JTabbedPane tabbedViewPane;

	/**
	 * The action manager.
	 */
	private ActionManager actionManager;

	/**
	 * Initializes the editor.
	 */
	public JdtEditor() {
		init();
	}

	/**
	 * Checks the actions.
	 */
	void checkActions() {
		getActionManager().checkActions();
	}

	/**
	 * Returns the action manager.
	 * @return the action manager
	 */
	private ActionManager getActionManager() {
		return this.actionManager;
	}

	/**
	 * Checks the state of the tool buttons and repaints them.
	 */
	void checkTools() {
		for (ToolButton tb : this.toolButtons) {
			tb.checkState();
		}
	}

	/**
	 * Displays the given string in the status field of the application.
	 * 
	 * @param s
	 *            status message
	 */
	void showStatus(String s) {
		this.statusField.setText(s);
	}

	/**
	 * Initializes the GUI.
	 */
	private void init() {
		JFrame frame = new JFrame(TOOL_NAME);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(frame);
			log.debug("Using native look & feel: "
					+ UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			log.debug("Using cross plattform look & feel: "
					+ UIManager.getCrossPlatformLookAndFeelClassName());
		}

		// Initialize status field early since it is used right after.
		this.statusField = new JTextField();
		this.statusField.setEditable(false);

		// Create tools.
		ToolFactory tfDefaultTool = null;
		List<ToolFactory> tfOtherTools = null;
		ToolFactoriesBuilder tfFactory = createToolFactoriesBuilder();
		if (tfFactory != null) {
			tfDefaultTool = createToolFactoryForDefaultTool(tfFactory);
			tfOtherTools = createToolFactories(tfFactory);
		}

		// Create editor acting as mediator for tools, views, etc.
		this.editor = new DefaultEditor(this);

		// Register
		Tool defaultTool = null;
		if (tfDefaultTool != null) {
			defaultTool = registerDefaultToolWithEditor(tfDefaultTool);
		}
		List<Tool> otherTools = registerToolsWithEditor(tfOtherTools);

		// Window listener.
		WindowListener listener = new WindowAdapter() {
			public @Override
			void windowClosing(WindowEvent e) {
				// Add exit handler.
				System.exit(0);
			}
		};
		frame.addWindowListener(listener);

		this.actionManager = new ActionManager(this.editor);
		MenuBarBuilder mbBuilder = new MenuBarBuilder(this.actionManager);
		JMenuBar menuBar = mbBuilder.getMenuBar();

//
//		JMenu grid = new JMenu("Grid");
//		grid.add("Grid 1").setEnabled(false);
//		grid.add("Grid 2").setEnabled(false);
//		grid.add("Grid 3").setEnabled(false);
//		editMenu.add(grid);
//

		frame.setJMenuBar(menuBar);

		// Define a view.
		View aView = createViewWithSheet(this.editor);

		// Tabbed Pane.
		this.tabbedViewPane = new JTabbedPane();
		addToTabbedViewPane(aView);
		this.tabbedViewPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int index = tabbedViewPane.getSelectedIndex();
				setCurrentView(index);
				tabbedViewPane.setSelectedIndex(tabbedViewPane
						.getSelectedIndex());
			}
		});

		// Install tools.
		JToolBar toolBar = new JToolBar();
		installDefaultTool(toolBar, tfDefaultTool, defaultTool);
		installOtherTools(toolBar, tfOtherTools, otherTools);

		// Place current view into the content pane.
		JPanel center = new JPanel(new BorderLayout());
		center.add(toolBar, BorderLayout.NORTH);
		center.add(tabbedViewPane, BorderLayout.CENTER);
		frame.getContentPane().add(center, BorderLayout.CENTER);
		frame.getContentPane().add(statusField, BorderLayout.SOUTH);

		// Must be called after the tools have been initialized and
		// after the views have been established.
		checkTools();
		checkActions();

		// frame.setSize(600, 400);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Creates and returns a factory for tool factories.
	 * 
	 * @return a factory for tool factories
	 */
	private ToolFactoriesBuilder createToolFactoriesBuilder() {
		ToolFactoriesBuilder tfFactory = null;
		try {
			tfFactory = new ToolFactoriesBuilder();
		} catch (Exception e) {
			log.fatal(e.getMessage());
			this.statusField.setText("Fatal error: " + e.getMessage());
		}
		return tfFactory;
	}

	/**
	 * Creates the tool factory for the default tool.
	 * 
	 * @param tfFactory
	 *            a factory for tool factories
	 * @return the tool factory for the default tool
	 */
	private ToolFactory createToolFactoryForDefaultTool(
			ToolFactoriesBuilder tfFactory) {
		ToolFactory toolFactory = tfFactory.getFactoryForDefaultTool();
		if (toolFactory == null) {
			toolFactory = new DefaultSelectionToolFactory();
		}
		return toolFactory;
	}

	/**
	 * Creates a list of tool factories for tools.
	 * 
	 * @param tfFactory
	 *            a factory for tool factories
	 * @return a list of tool factories
	 */
	private List<ToolFactory> createToolFactories(
			ToolFactoriesBuilder tfFactory) {
		return tfFactory.getOtherToolFactories();
	}

	/**
	 * Creates and registers with the editor the default tool, given the tool
	 * factory for the default tool.
	 * 
	 * @param tfDefaultTool
	 *            the tool factory for the default tool
	 * @return the default tool
	 */
	private Tool registerDefaultToolWithEditor(ToolFactory tfDefaultTool) {
		Tool defaultTool = tfDefaultTool.getTool(this.editor);
		this.editor.registerDefaultTool(defaultTool);
		return defaultTool;
	}

	/**
	 * Creates and registers with the editor a list of tools, given a list of
	 * tool factories.
	 * 
	 * @param toolFactories
	 *            a list of tool factories
	 * @return a list of tools
	 */
	private List<Tool> registerToolsWithEditor(List<ToolFactory> toolFactories) {
		List<Tool> otherTools = new ArrayList<Tool>();
		for (ToolFactory tf : toolFactories) {
			Tool tool = tf.getTool(this.editor);
			this.editor.registerTool(tool);
			otherTools.add(tool);
		}
		return otherTools;
	}

	/**
	 * Installs the default tool on the GUI.
	 * 
	 * @param toolBar
	 *            the tool bar
	 * @param tfDefaultTool
	 *            the factory for the default tool
	 * @param defaultTool
	 *            the installed default tool
	 */
	private void installDefaultTool(JToolBar toolBar,
			ToolFactory tfDefaultTool, Tool defaultTool) {
		String defaultToolName = tfDefaultTool.getName();
		Icon defaultToolIcon = tfDefaultTool.getIcon();
		log.debug("Installing default tool: " + defaultToolName);
		installToolInToolBar(toolBar, defaultToolName, defaultTool,
				defaultToolIcon);
		toolBar.addSeparator();
	}

	/**
	 * Installs the tools on the GUI.
	 * 
	 * @param toolBar
	 *            the tool bar
	 * @param tfOtherTools
	 *            the list of tool factories
	 * @param otherTools
	 *            a list of installed tools
	 */
	private void installOtherTools(JToolBar toolBar,
			List<ToolFactory> tfOtherTools, List<Tool> otherTools) {
		for (int i = 0; i < tfOtherTools.size(); i++) {
			ToolFactory tf = tfOtherTools.get(i);
			Tool tool = otherTools.get(i);
			String toolName = tf.getName();
			Icon toolIcon = tf.getIcon();
			installToolInToolBar(toolBar, toolName, tool, toolIcon);
		}
	}

	/**
	 * Installs a tool in the given tool bar. Sets the tool to the current view
	 * if it is not null.
	 * 
	 * @param toolbar
	 *            the tool bar
	 * @param name
	 *            the tool's name
	 * @param tool
	 *            the tool
	 * @param icon
	 *            the tool's icon
	 */
	private void installToolInToolBar(JToolBar toolbar, String name,
			final Tool tool, Icon icon) {
		ToolButton button = new ToolButton(name, icon, tool, this.editor);
		toolbar.add(button);
		toolButtons.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				View v = getCurrentView();
				if (v != null) {
					v.setTool(tool);
				}
			}
		});
	}

	/**
	 * Returns the current view.
	 * 
	 * @return a view
	 */
	private View getCurrentView() {
		return editor.getCurrentView();
	}

	/**
	 * Sets the current view, given an index.
	 * 
	 * @param index
	 *            an index
	 */
	private void setCurrentView(int index) {
		this.editor.setCurrentView(index);
	}

	/**
	 * Creates a view with a sheet. PENDING: Check moving this into the editor.
	 * 
	 * @param e
	 *            an editor
	 * @return a view
	 */
	private View createViewWithSheet(Editor e) {
		// Get sheet via sheet factory.
		SheetFactory sFac = null;
		Sheet currentSheet = null;
		try {
			sFac = new SheetFactoryImpl();
			currentSheet = sFac.createSheet();
		} catch (Exception ex) {
			log.fatal(ex.getMessage());
			this.statusField.setText("Fatal error: " + ex.getMessage());
		}

		// Define current view.
		View currentView = null;

		try {
			ViewFactory factory = new ViewFactoryImpl();
			currentView = factory.createView(e);
		} catch (Exception ex) {
			log.fatal(ex.getMessage());
			this.statusField.setText("Fatal error: " + ex.getMessage());
		}
		currentView.setSheet(currentSheet);

		return currentView;
	}

	/**
	 * Adds given view to list of views.
	 * 
	 * @param view
	 *            a view
	 */
	private void addToViews(View view) {
		this.editor.registerView(view);
	}

	private void addToTabbedViewPane(View v) {
		addToViews(v);
		// setCurrentView(v);

		int number = this.editor.getViewCount();

		// Content panel.
		JScrollPane sp = new JScrollPane((JComponent) v,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// PENDING: Make sheet white, rest gray.
		//sp.getViewport().setBackground(Color.GRAY);
		sp.setPreferredSize(new Dimension(400, 300));

		this.tabbedViewPane.add(sp, "View " + (number));
		this.tabbedViewPane.setSelectedIndex(number - 1);
		this.editor.setCurrentView(number - 1);
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
	 * Creates a view with a sheet, links it with the editor, and
	 * makes it visible.
	 */
	public void createView() {
		View v = createViewWithSheet(editor);
		addToTabbedViewPane(v);

	}

	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 *            Not used.
	 */
	public static void main(String[] args) {
		new JdtEditor();
	}
}
