/**
// code is far away from bug with Buddha protection
//
//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package edu.nus.comp.cs2103t.taskerino.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.nus.comp.cs2103t.taskerino.common.Assist;
import edu.nus.comp.cs2103t.taskerino.common.LoggerFactory;

/**
 * This class  specifies dimensions and other details for components in Help Frame
 * which will be shown to the user upon called.
 * 
 * @author Wang YanHao
 *
 */
public class HelpComponents {
	private static final String className = new Throwable() .getStackTrace()[0].getClassName();
	private static final Assist assist = new Assist();

	private static final String HELP_ADD = "add";
	private static final String HELP_DELETE = "delete";
	private static final String HELP_CHANGE = "change";
	private static final String HELP_COMPLETE = "complete";
	private static final String HELP_SEARCH = "search";
	private static final String HELP_GOTO = "goto";

	private JPanel contentPanel = new JPanel();
	private JLabel sessionTitle = new JLabel();
	private JTextArea detailedDescription = new JTextArea();

	// default constructor
	public HelpComponents() {}

	public HelpComponents(String helpType) {
		final String methodName = "HelpComponents";

		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set up help panel detailed description.");
		if (helpType == null) {
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
			}
		}

		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set up content panel overview.");
		setPanelOverview();
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(detailedDescription, gbc);
	}

	
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
		System.out.println("should come out");
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
		return contentPanel;
	}
	
	// dummy main for easy testing
	public static void main (String[] a) {
		JFrame frame = new JFrame("test");
		
		HelpComponents components = new HelpComponents("add");
		
		frame.getContentPane().add(components.getContentPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
