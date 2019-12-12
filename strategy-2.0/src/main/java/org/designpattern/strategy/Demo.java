/*
 * Copyright (c) 2015 Eric Dubuis, Berner Fachhochschule, Switzerland.
 *
 * Software Engineering and Design -- Design patterns
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package org.designpattern.strategy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * Main class running the demo.
 */

public class Demo extends JFrame {
	private static final long serialVersionUID = 1L;
	private String[] fieldTypes = { "E-Mail", "Number", "Date" };
	private Map<String, FieldEvaluation> namedFEs = new HashMap<>();

	private FieldEvaluator genericFE = new FieldEvaluator();

	private JTextField genericTF;

	/**
	 * Constructor constructing the GUI.
	 */
	public Demo() {
		super("Strategy Pattern Demo Application");
		initFieldEvaluations();
		init();
		pack();
	}

	/**
	 *
	 */
	private void initFieldEvaluations() {
		namedFEs.put("E-Mail", new EmailFieldEvaluation());
		namedFEs.put("Number", new NumberFieldEvaluation());
		namedFEs.put("Date", new DateFieldEvaluation());
		// set current field evaluation to e-mail field evaluation
		genericFE.setFieldEvaluation(namedFEs.get("E-Mail"));
	}

	/**
	 * Constructs the GUI.
	 */
	private void init() {
		JComboBox<String> combo = new JComboBox<>(fieldTypes);
		combo.setPreferredSize(new Dimension(150, 27));
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> source = (JComboBox<String>) e.getSource();
				setFieldEvaluation((String) source.getSelectedItem());
			}
		});
		genericTF = new JTextField(25);
		genericTF.setBackground(Color.WHITE); // J2SDK 1.4 white --> WHITE, etc.
		genericTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				boolean result = genericFE.evaluate(genericTF.getText());
				if (result) {
					genericTF.setBackground(Color.GREEN);
				} else {
					genericTF.setBackground(Color.RED);
				}
			}
		});
		DocumentListener dl = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				Document d = e.getDocument();
				if (d.getLength() > 0) {
					if (genericFE.evaluate(genericTF.getText())) {
						genericTF.setBackground(Color.GREEN);
					} else {
						genericTF.setBackground(Color.YELLOW);
					}
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				Document d = e.getDocument();
				if (d.getLength() == 0) {
					genericTF.setBackground(Color.WHITE);
				} else {
					if (genericFE.evaluate(genericTF.getText())) {
						genericTF.setBackground(Color.GREEN);
					} else {
						genericTF.setBackground(Color.YELLOW);
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// Not used -- intentionally left empty
			}
		};
		genericTF.getDocument().addDocumentListener(dl);

		// We'll put everything into JPanel containers.

		//Layout the labels in a panel.
		JPanel labelPane = new JPanel();
		labelPane.setLayout(new GridLayout(0, 1));
		labelPane.add(combo);

		//Layout the text fields in a panel
		JPanel fieldPane = new JPanel();
		fieldPane.setLayout(new GridLayout(0, 1));
		fieldPane.add(genericTF);

		//Put the panels in another panel, labels on left,
		//text fields on right
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20,
				20, 20));
		contentPane.setLayout(new BorderLayout());
		contentPane.add(labelPane, BorderLayout.CENTER);
		contentPane.add(fieldPane, BorderLayout.EAST);

		// Add everything to the frame's content.
		getContentPane().add(contentPane);
	}


	/**
	 * Select the field evaluation and set it at the field evaluation.
	 *
	 * @param selectedItem
	 *            represents the selected field evaluation
	 */
	protected void setFieldEvaluation(String selectedItem) {
		FieldEvaluation selectedFE = namedFEs.get(selectedItem);
		// TODO -- Clients usually have to configure the context object.
	}

	/**
	 * Entry point for the Swing GUI.
	 *
	 * @param args
	 *            not used...
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new Demo();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			};
		});
	}
}
