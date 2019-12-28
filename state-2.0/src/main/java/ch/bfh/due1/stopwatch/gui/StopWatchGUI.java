/**
 * Copyright (C) 2015-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.stopwatch.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.designpattern.stopwatch.state.StateFactoryImpl;

import ch.bfh.due1.stopwatch.core.Button;
import ch.bfh.due1.stopwatch.core.StateFactory;
import ch.bfh.due1.stopwatch.core.StopWatch;
import ch.bfh.due1.stopwatch.core.Timer;
import ch.bfh.due1.stopwatch.timer.Ticker;
import ch.bfh.due1.stopwatch.timer.TimerImpl;


/**
 * A main program for a stop watch. Creates a GUI consisting of a display and
 * buttons. Initializes the core of the stop watch and the timer.
 */
public class StopWatchGUI {
	private static JLabel statusLabel = null;

	private static JButton button1 = null;

	private static JButton button2 = null;

	private static class ButtonHandler implements ActionListener {
		private StopWatch sw;

		private ButtonHandler(StopWatch sw) {
			this.sw = sw;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton current = (JButton) e.getSource();
			statusLabel.setText("");
			if (current == button1) {
				// B1 pressed:
				try {
					this.sw.button1Pressed();
				} catch (Exception ex) {
					statusLabel.setText(ex.getMessage());
				}
			} else if (current == button2) {
				// B2 pressed:
				try {
					this.sw.button2Pressed();
				} catch (Exception ex) {
					statusLabel.setText(ex.getMessage());
				}
			}
		}
	}

	private static class ButtonDelegate implements Button {
		private JButton b;
		private ButtonDelegate(JButton b) {
			this.b = b;
		}

		@Override
		public void setText(String text) {
			this.b.setText(text);
		}
	}

	private static Button bDelegate1 = null;
	private static Button bDelegate2 = null;


	public static void main(String[] args) throws Exception {

		JFrame frame = new JFrame("Stop Watch");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		// Instantiate buttons:
		button1 = new JButton();
		button2 = new JButton();

		bDelegate1 = new ButtonDelegate(button1);
		bDelegate2 = new ButtonDelegate(button2);

		// Configure buttons:
		button1.setText("B1");
		button2.setText("B2");

		// Configure size of buttons
		button1.setPreferredSize(new Dimension(150, 22));
		button2.setPreferredSize(new Dimension(150, 22));

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.setPreferredSize(new Dimension(320, 34));
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

		// Instantiate StopWatchPannel (classic way...)
		StopWatchPanel display = new StopWatchPanel();
		frame.getContentPane().add(display, BorderLayout.CENTER);

		Timer timer = new TimerImpl();
		String problem = "";
		StopWatch stopWatch = null;
		try {
			StateFactory fac = new StateFactoryImpl();
			stopWatch = new StopWatch(fac, timer, display, bDelegate1, bDelegate2);
		} catch (Exception ex) {
			problem = ex.getMessage();
		}

		// Connections:
		Ticker ticker = (Ticker) timer;
		ticker.addTickListener(display);
		ActionListener al = new ButtonHandler(stopWatch);
		button1.addActionListener(al);
		button2.addActionListener(al);

		// The status panel
		JPanel status = new JPanel();
		statusLabel = new JLabel(problem);
		status .add(statusLabel);
		status.setBorder(BorderFactory.createTitledBorder("Problems"));
		status.setPreferredSize(new Dimension(320, 56));
		frame.getContentPane().add(status, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
