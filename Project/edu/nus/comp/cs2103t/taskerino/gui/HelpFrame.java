//@author A0113742N
package edu.nus.comp.cs2103t.taskerino.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.nus.comp.cs2103t.taskerino.common.Assist;
import edu.nus.comp.cs2103t.taskerino.common.LoggerFactory;

/**
 * This class pop up a new JFrame which displays the a list of instructions
 * that gives the user a general guideline of how to use Taskerino.
 */
public class HelpFrame {
	private static final String className = new Throwable() .getStackTrace()[0].getClassName();
	private static final Assist assist = new Assist();
	private static final String APP_NAME = "Taskerino";

	private final JFrame frame;
	private static final int HELP_FRAME_WIDTH = 750;
	private static final int HELP_FRAME_HEIGHT = 600;

	private String helpType;
	private static final String HELP_ADD = "add";
	private static final String HELP_DELETE = "delete";
	private static final String HELP_CHANGE = "change";
	private static final String HELP_COMPLETE = "complete";
	private static final String HELP_SEARCH = "search";
	private static final String HELP_GOTO = "goto";

	private JPanel contentPanel = new JPanel();
	private JLabel sessionTitle = new JLabel();
	private JTextArea detailedDescription = new JTextArea();
	private JButton exitButton = new JButton("Press any key to contunue.");
	
	private static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 30);
	private static final Font FONT_DESCRIPTION = new Font("SansSerif", Font.PLAIN, 16);
	private static final Font FONT_BUTTON = new Font("SansSerif", Font.PLAIN, 14);


	public HelpFrame(String helpType) {
		this.helpType = helpType;

		frame = new JFrame(APP_NAME);
		frame.getContentPane().add(getContentPanel());
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        
		frame.pack();
		frame.setSize(HELP_FRAME_WIDTH, HELP_FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		exitButton.setFocusable(true);;

	}

	
	private void setExitButton() {
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	}


	private void setDetailedDescription(String helpType) {
		if (helpType == null || helpType.equals("")) {
			// no specific helpType -> general help!
			setGeneralHelpContent();
		} else {
			switch (helpType) {
				case HELP_ADD:
					setAddHelpContent();
					break;
				case HELP_DELETE:
					setDeleteHelpContent();
					break;
				case HELP_CHANGE:
					setChangeHelpContent();
					break;
				case HELP_COMPLETE:
					setCompleteHelpContent();
					break;
				case HELP_SEARCH:
					setSearchHelpContent();
					break;
				case HELP_GOTO:
					setGotoHelpContent();
					break;
				default:
					assert false : "HelpType: " + helpType;
			}
		}
	}


	/**
	 * Set up content panel: <br>
	 * 1. Set layout to GridBagLayout; <br>
	 * 2. Specify dimension for each component; <br>
	 * 3. Add Components into contentPanel.
	 */
	private void setPanelOverview() {
		final String methodName = "setPanelOverview";
		detailedDescription.setEditable(false);
		detailedDescription.setLineWrap(true);
		// set layout for contentPanel
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		contentPanel.setLayout(layout);

		//Add sessionTitle
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add component sessionTitle to contentPanel.");
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPanel.add(sessionTitle, gbc);

		//Add detailedDescription
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add component detailedDescription to contentPanel.");
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		contentPanel.add(detailedDescription, gbc);

		//Add exitButton
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add component detailedDescription to contentPanel.");
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		contentPanel.add(exitButton, gbc);
	}

	
/**********************************************************************************
    Methods that set title and detailed descriptions based on input Command.
**********************************************************************************/
	
	private void setGeneralHelpContent() {
		ArrayList<String> contents = assist.generalHelp();
		sessionTitle.setText(contents.get(0));
		detailedDescription.setText(contents.get(1));
	}

	private void setGotoHelpContent() {
		ArrayList<String> contents = assist.gotoHelp();
		sessionTitle.setText(contents.get(0));
		detailedDescription.setText(contents.get(1));
	}

	private void setSearchHelpContent() {
		ArrayList<String> contents = assist.searchHelp();
		sessionTitle.setText(contents.get(0));
		detailedDescription.setText(contents.get(1));
	}

	private void setCompleteHelpContent() {
		ArrayList<String> contents = assist.completeHelp();
		sessionTitle.setText(contents.get(0));
		detailedDescription.setText(contents.get(1));
	}

	private void setChangeHelpContent() {
		ArrayList<String> contents = assist.changeHelp();
		sessionTitle.setText(contents.get(0));
		detailedDescription.setText(contents.get(1));
	}

	private void setDeleteHelpContent() {
		ArrayList<String> contents = assist.deleteHelp();
		sessionTitle.setText(contents.get(0));
		detailedDescription.setText(contents.get(1));
	}

	private void setAddHelpContent() {
		ArrayList<String> contents = assist.addHelp();
		sessionTitle.setText(contents.get(0));
		detailedDescription.setText(contents.get(1));
	}


	/**
	 * A content panel to be added into a high level container. (JFrame)
	 * 
	 * @return contentPanel
	 */
	public Component getContentPanel() {
		setDetailedDescription(helpType);
		setExitButton();
		setPanelOverview();
		setFont();

		return contentPanel;
	}


	/**
	 * Set Fond size and style for all sub-panel.
	 */
	private void setFont() {
		sessionTitle.setFont(FONT_TITLE);
		detailedDescription.setFont(FONT_DESCRIPTION);
		exitButton.setFont(FONT_BUTTON);
	}


	/**
	 * Inner class to close the window when user press any key.
	 */
	private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
				frame.dispose();
            }
            return false;
        }
    }
}
