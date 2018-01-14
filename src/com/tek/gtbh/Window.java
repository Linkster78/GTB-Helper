package com.tek.gtbh;

import java.util.ArrayList;
import java.util.StringJoiner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.tek.gtbh.lib.MatchLib;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JTextPane output;

	public Window() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 381, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("Guess The Build [HELPER]");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 35, 355, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener( new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			    change();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    change();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    change();
			  }
		});
		
		JLabel lblYourWorduse = new JLabel("Your word: (USE '#' FOR UNKNOWN LETTERS)");
		lblYourWorduse.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourWorduse.setBounds(10, 11, 355, 14);
		frame.getContentPane().add(lblYourWorduse);
		
		output = new JTextPane();
		output.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(output);
		scrollPane.setBounds(10, 66, 355, 194);
		frame.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Reload Dictionary");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MatchLib.loadDictionary();
				
				change();
			}
		});
		btnNewButton.setBounds(10, 267, 355, 23);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
	
	public void change() {
	      ArrayList<String> matches = MatchLib.findMatches('#', textField.getText());
	      
	      if(matches == null) {
	    	  output.setText("");
	      }else {
	    	  StringJoiner joiner = new StringJoiner(System.lineSeparator());
	    	  
	    	  for(String match : matches) {
	    		  joiner.add(match);
	    	  }
	    	  
	    	  output.setText(joiner.toString());
	      }
	}
}
