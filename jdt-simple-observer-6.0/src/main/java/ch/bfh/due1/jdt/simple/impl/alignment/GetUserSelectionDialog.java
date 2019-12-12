/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

package ch.bfh.due1.jdt.simple.impl.alignment;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


import org.apache.log4j.Logger;

/**
 * Upon instantiation, presents the user a dialog. Upon choosing the align
 * strategy, the button's event sets the appropriate align strategy of the align
 * tool.
 * <p>
 * This dialog is not definitive.
 * 
 * @author Eric Dubuis
 */
public class GetUserSelectionDialog extends JDialog implements
		AlignToolUserSelectionDialog {

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 8357396339356339446L;

	/**
	 * The associated align tool.
	 */
	private AlignTool alignTool;

	/**
	 * The logger for this class, named
	 * "org.jdt.app.simple.impl.gui.GetUserSelectionDialog".
	 */
	private Logger log = Logger.getLogger(GetUserSelectionDialog.class
			.getName());

	/**
	 * Creates the dialog.
	 */
	public GetUserSelectionDialog() {
		setTitle("Select Align Strategy");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// User pressed "close dialog", make dialog invisible.
				setVisible(false);
			}
		});

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(6, 1));
		content.add(getAlignLeftChoice());
		content.add(getAlignJustifyVerticalChoice());
		content.add(getAlignRightChoice());
		content.add(getAlignTopChoice());
		content.add(getAlignJustifyHorizontalChoice());
		content.add(getAlignBottomChoice());
		getContentPane().add(content);
		pack();
	}

	/**
	 * Records the given align tool object for later use and makes GUI visible
	 * or non-visible respectively in terms of the given value val. If tool is
	 * made invisible then toolDone() on the align tool is called, too.
	 * 
	 * @see ch.bfh.due1.jdt.simple.impl.alignment.AlignToolUserSelectionDialog#setVisible(boolean,
	 *      ch.bfh.due1.jdt.simple.impl.alignment.AlignTool)
	 */
	@Override
	public void setVisible(boolean val, AlignTool alignTool) {
		if (alignTool == null) {
			throw new IllegalArgumentException(
					"Parameter alignTool must not be null");
		}
		this.alignTool = alignTool;
		super.setVisible(val);
		if (val == false) {
			this.alignTool.toolDone();
		}
	}

	/**
	 * Button for "" alignment and corresponding listener.
	 * 
	 * @return a button
	 */
	private JButton getAlignBottomChoice() {
		Icon icon = getIcon("jdt/icon/AlignBottom24.gif");
		JButton button = new JButton(/*"Align to bottom",*/ icon);
		button.setMinimumSize(new Dimension(100, 24));
		button.setPreferredSize(new Dimension(100, 24));
		button.setMaximumSize(new Dimension(100, 24));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alignTool.setAlignBottom();
			}
		});
		return button;
	}

	/**
	 * Button for "" alignment and corresponding listener.
	 * 
	 * @return a button
	 */
	private JButton getAlignJustifyHorizontalChoice() {
		Icon icon = getIcon("jdt/icon/AlignJustifyHorizontal24.gif");
		JButton button = new JButton(/*"Align horizontal",*/ icon);
		button.setMinimumSize(new Dimension(100, 24));
		button.setPreferredSize(new Dimension(100, 24));
		button.setMaximumSize(new Dimension(100, 24));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alignTool.setAlignJustifyHorizontal();
			}
		});
		return button;
	}

	/**
	 * Button for "" alignment and corresponding listener.
	 * 
	 * @return a button
	 */
	private JButton getAlignJustifyVerticalChoice() {
		Icon icon = getIcon("jdt/icon/AlignJustifyVertical24.gif");
		JButton button = new JButton(/*"Align vertical",*/ icon);
		button.setMinimumSize(new Dimension(100, 24));
		button.setPreferredSize(new Dimension(100, 24));
		button.setMaximumSize(new Dimension(100, 24));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alignTool.setAlignJustifyVertical();
			}
		});
		return button;
	}

	/**
	 * Button for "" alignment and corresponding listener.
	 * 
	 * @return a button
	 */
	private JButton getAlignLeftChoice() {
		Icon icon = getIcon("jdt/icon/AlignLeft24.gif");
		JButton button = new JButton(/*"Align to left",*/ icon);
		button.setMinimumSize(new Dimension(100, 24));
		button.setPreferredSize(new Dimension(100, 24));
		button.setMaximumSize(new Dimension(100, 24));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alignTool.setAlignLeft();
			}
		});
		return button;
	}

	/**
	 * Button for "" alignment and corresponding listener.
	 * 
	 * @return a button
	 */
	private JButton getAlignRightChoice() {
		Icon icon = getIcon("jdt/icon/AlignRight24.gif");
		JButton button = new JButton(/*"Align to right",*/ icon);
		button.setMinimumSize(new Dimension(100, 24));
		button.setPreferredSize(new Dimension(100, 24));
		button.setMaximumSize(new Dimension(100, 24));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alignTool.setAlignRight();
			}
		});
		return button;
	}

	/**
	 * Button for "" alignment and corresponding listener.
	 * 
	 * @return a button
	 */
	private JButton getAlignTopChoice() {
		Icon icon = getIcon("jdt/icon/AlignTop24.gif");
		JButton button = new JButton(/*"Align to top",*/ icon);
		button.setMinimumSize(new Dimension(100, 24));
		button.setPreferredSize(new Dimension(100, 24));
		button.setMaximumSize(new Dimension(100, 24));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alignTool.setAlignTop();
			}
		});
		return button;
	}

	/**
	 * Reads an icon, given a name. Currently, the full path relative to a
	 * CLASSPATH component must be given.
	 * 
	 * @param name
	 *            the icon's name, i.e., the full path relative to a CLASSPATH
	 *            component
	 * @return an icon
	 */
	private Icon getIcon(String name) {
		Icon icon = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(name);
		if (is != null && is instanceof BufferedInputStream) {
			BufferedInputStream bis = (BufferedInputStream) is;
			try {
				byte[] iconData = new byte[bis.available()];
				bis.read(iconData);
				icon = new ImageIcon(iconData);
				bis.close();
			} catch (IOException e) {
				// Do nothing, cannot create the icon; just report
				// error.
				log.debug("Cannot create icon with given resource path: "
						+ name);
			}
		} else {
			log.debug("Cannot create icon with given resource path: " + name);
		}
		return icon;
	}
}
