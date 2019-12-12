/*
 * Eric Dubuis, Berner Fachhochschule,
 * Biel, Switzerland.
 * Copyright (c) 2007
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package ch.bfh.due1.jdt.simple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.JButton;


import org.apache.log4j.Logger;

import ch.bfh.due1.jdt.framework.Editor;
import ch.bfh.due1.jdt.framework.Tool;
import ch.bfh.due1.jdt.framework.View;

/**
 * An instance of this class denotes a tool button on a tool bar.
 * <p>
 * This class originates from JDraw 2.0 by D. Gruntz, www.gruntz.ch.
 * <p>
 * PENDING: Tool button's states to not match well the editor's state. Fix it.
 */
public class ToolButton extends JButton implements MouseListener,
		MouseMotionListener {
	private static final long serialVersionUID = -6584712527541857593L;

	private Logger log = Logger.getLogger(ToolButton.class);

	private Icon icon;

	private Editor editor;

	private Tool tool;

	// incremented by enter/down
	// released by exit/up
	private int state;

	private boolean selected;

	private int width;

	private int height;

	public ToolButton(String text, Icon icon, final Tool tool,
			final Editor editor) {
		this.tool = tool;
		this.editor = editor;
		this.icon = icon;
		this.width = icon != null ? icon.getIconWidth() : 24;
		this.height = icon != null ? icon.getIconHeight() : 24;

		addMouseListener(this);
		addMouseMotionListener(this);

		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));

		setToolTipText(text);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//setSelected(false);
		//setFocusPainted(false);
		// if (tool instanceof AlignTool) {
		//System.out.println(this.toString());
		// }
		 if (!selected) {
			if (icon != null)
				icon.paintIcon(this, g, 0, 0);
			if (state > 0) {
				int w = icon != null ? icon.getIconWidth() - 1 : 23;
				int h = icon != null ? icon.getIconHeight() - 1 : 23;
				g.setColor(state == 1 ? Color.white : Color.gray);
				g.drawLine(0, 0, w, 0);
				g.drawLine(0, 0, 0, h);
				g.setColor(state == 2 ? Color.white : Color.gray);
				g.drawLine(0, h, w, h);
				g.drawLine(w, 0, w, h);
			}
		} else { // selected
			if (state == 2) {
				if (icon != null)
					icon.paintIcon(this, g, 1, 1);
			} else {
				if (icon != null)
					icon.paintIcon(this, g, 0, 0);
			}
			int w = icon != null ? icon.getIconWidth() - 1 : 23;
			int h = icon != null ? icon.getIconHeight() - 1 : 23;
			g.setColor(Color.gray);
			g.drawLine(0, 0, w, 0);
			g.drawLine(0, 0, 0, h);
			g.setColor(Color.white);
			g.drawLine(0, h, w, h);
			g.drawLine(w, 0, w, h);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		state++;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		state--;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		state++;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		state--;
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public void checkState() {
		View v = editor.getCurrentView();
		if (v != null) {
			Tool t = v.getTool();
			if (t != null) {
				selected = tool.equals(t);
				log.debug("ToolButton " + tool.getClass().getSimpleName()
						+ " selected=" + selected + ", state=" + state);
			}
		}
		repaint();
	}

	@Override
	public String toString() {
		return new Date().getTime() + ": TB_State[tool=" + this.tool.getClass().getSimpleName()
				+ ",selected=" + this.selected + ",state=" + this.state + "]";
	}
}
