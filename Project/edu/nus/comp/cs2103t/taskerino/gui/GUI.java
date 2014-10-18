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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoundedRangeModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.google.gson.JsonSyntaxException;

import edu.nus.comp.cs2103t.taskerino.common.Data;
import edu.nus.comp.cs2103t.taskerino.common.LoggerFactory;
import edu.nus.comp.cs2103t.taskerino.logic.Logic;
import edu.nus.comp.cs2103t.taskerino.parser.Parser;
import edu.nus.comp.cs2103t.taskerino.storage.Storage;


/**
 * This class creates a Graphical User Interface which user can interact with by
 * typing in the text field and receive feedback depending on the input.
 * 
 * @author Wang YanHaob
 *
 */
public class GUI{
	private static final String className = new Throwable() .getStackTrace()[0].getClassName();
	private static final String APP_NAME = "Taskerino";

	// Object used to set up JTable
	Object rowData[][] = {{"test", null, "done"}};
	Object columnNames[] = { "Task Name", "Date", "Status" };

	// all components in JFrame
	private JPanel panel = new JPanel();
	private JTextField userInputArea = new JTextField();
	private JLabel feedbackToUser = new JLabel("Welcome!");
	private JScrollPane feedbackScrollPane = new JScrollPane();
	private JButton tagAll = new JButton("All");
	private JTable userTaskTable;
	private JScrollPane taskScrollPane;

	// variable for JScrollPanel
	private final String UP = "Up";
	private final String DOWN = "Down";
	private final int SCROLLABLE_INCREMENT = 14;


	public GUI() {
		LoggerFactory.logp(Level.CONFIG, className, "GUI", "Creating JFrame...");

		JFrame frame = new JFrame(APP_NAME);

		Component contents = createComponents();

		frame.getContentPane().add(contents);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		frame.pack();
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// set focus for user input
		userInputArea.requestFocusInWindow();
		
		LoggerFactory.logp(Level.CONFIG, className, "GUI", "Successfully setted up GUI.");
	}

	/**
	 * Create and configure components that will be added to the JFrame, and display to the user.
	 * 
	 * @return Component to be displayed on the JFrame
	 */
	private Component createComponents() {
		LoggerFactory.logp(Level.CONFIG, className, "createComponents", "Creating components for JFrame...");

		setUserInput();
		setUserOutput();
		setUserTask();

		setPanel();

		LoggerFactory.logp(Level.CONFIG, className, "createComponents", "Successfully setted up all components for JFrame.");
		return panel;
	}


	/**
	 * Set up JPanel: 
	 * set layout to GridBagLayout;
	 * add Components into JPAnel;
	 * specify dimension for each component.
	 * 
	 * @param panelComponents : ArrayList of components to be added to the panel.
	 */
	private void setPanel() {
		// set layout for JPanel
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		LoggerFactory.logp(Level.CONFIG, className, "setPanel", "Set layout for JPanel to GridBagLayout.");
		panel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.setLayout(layout);
		
		//Add tagAll
		LoggerFactory.logp(Level.CONFIG, className, "setPanel", "Add component tagAll to JPanel.");
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(tagAll, gbc);

		//Add taskScrollPane
		LoggerFactory.logp(Level.CONFIG, className, "setPanel", "Add component taskScrollPane to JPanel.");
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(taskScrollPane, gbc);

		//Add userInputArea
		LoggerFactory.logp(Level.CONFIG, className, "setPanel", "Add component userInputArea to JPanel.");
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(userInputArea, gbc);
		
		//Add feedbackToUser
		LoggerFactory.logp(Level.CONFIG, className, "setPanel", "Add component feedbackToUser to JPanel.");
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 10, 10, 10);
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(feedbackToUser, gbc);
	}

	/**
	 * Set up JLabel which is used to display feedback to the user. 
	 */
	private void setUserOutput() {
		feedbackScrollPane.getViewport().add(feedbackToUser);
	}

	/**
	 * Set up JTextField which executes user's input command by
	 * initializing Parser and Logic classes, and display feedback 
	 * to user after retrieving the feedback from Logic class.
	 */
	private void setUserInput() {
		LoggerFactory.logp(Level.CONFIG, className, "setUserInput", "Add ActionListener for user inputs.");
		
		// add action listener for event when user presses "enter" in keyboard
		userInputArea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String inputCommand = userInputArea.getText();
				LoggerFactory.logp(Level.INFO, className, "setUserInput", "User input commands: \n" + inputCommand);

				userInputArea.setText("");

				// inputCommand is passed into parser and logic
				LoggerFactory.logp(Level.INFO, className, "setUserInput", "Send user input commands to Data.");
				Data.setInput(inputCommand);

				LoggerFactory.logp(Level.INFO, className, "setUserInput", "Initialize Paser.");
				(new Parser()).parse();

				String outputFeedBack = "";
				try {
					LoggerFactory.logp(Level.INFO, className, "setUserInput", "Asking Logic for feedback...");
					outputFeedBack = (new Logic()).executeUserCommand();
					LoggerFactory.logp(Level.INFO, className, "setUserInput", "Successfully get feedback from Logic: \n" + outputFeedBack);
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// handle the exceptions
					LoggerFactory.logp(Level.WARNING, className, "setUserInput", e.getMessage());
					e.printStackTrace();
				}

				feedbackToUser.setText(outputFeedBack);
			}
		});
	}

	/**
	 * Set up JScrollPane which is used to display all user's tasks.
	 */
	private void setUserTask() {
		LoggerFactory.logp(Level.CONFIG, className, "setUserOutput", "Creating user task table..");

		setTable();
		taskScrollPane = new JScrollPane(userTaskTable);

		// add key bindings to the JLabel which display feedback to user
		int condition = JLabel.WHEN_IN_FOCUSED_WINDOW;
		InputMap inMap = userTaskTable.getInputMap(condition);
		ActionMap actMap = userTaskTable.getActionMap();

		LoggerFactory.logp(Level.CONFIG, className, "setUserOutput", "Setting hotkeys...");
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);

		LoggerFactory.logp(Level.CONFIG, className, "setUserOutput", "Setting hotkeys for UP key...");
		actMap.put(UP, new UpDownAction(UP, taskScrollPane.getVerticalScrollBar().getModel(), 
				SCROLLABLE_INCREMENT));

		LoggerFactory.logp(Level.CONFIG, className, "setUserOutput", "Setting hotkeys for DOWN key...");
		actMap.put(DOWN, new UpDownAction(DOWN, taskScrollPane.getVerticalScrollBar().getModel(), 
				SCROLLABLE_INCREMENT));
	}

	/**
	 * Initialize and set userTaskTable properties.
	 */
	@SuppressWarnings("serial")
	private void setTable() {
		userTaskTable = new JTable(rowData, columnNames){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// set column size
		userTaskTable.getColumnModel().getColumn(0).setPreferredWidth(450);
		userTaskTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		userTaskTable.getColumnModel().getColumn(2).setPreferredWidth(50);
	}

	/**
	 * Set ActionListener for action performed when user press "up" or "down" on keyboard.
	 */
	@SuppressWarnings("serial")
	private class UpDownAction extends AbstractAction {
		private BoundedRangeModel vScrollBarModel;
		private int scrollableIncrement;

		public UpDownAction(String name, BoundedRangeModel model, int scrollableIncrement) {
			super(name);
			this.vScrollBarModel = model;
			this.scrollableIncrement = scrollableIncrement;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			String name = getValue(AbstractAction.NAME).toString();
			int value = vScrollBarModel.getValue();

			if (name.equals(UP)) {
				value -= scrollableIncrement;
				vScrollBarModel.setValue(value);
			} else if (name.equals(DOWN)) {
				value += scrollableIncrement;
				vScrollBarModel.setValue(value);
			}
		}
	}


	public static void main(String[] args) throws JsonSyntaxException, IOException {
		LoggerFactory.logp(Level.INFO, className, "Main", "Start logger!");

		Data.task = Storage.loadTasksFromFile();
		new GUI();
	}
}
