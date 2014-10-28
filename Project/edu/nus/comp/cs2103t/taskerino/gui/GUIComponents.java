package edu.nus.comp.cs2103t.taskerino.gui;
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


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BoundedRangeModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import edu.nus.comp.cs2103t.taskerino.common.CommandHistory;
import edu.nus.comp.cs2103t.taskerino.common.Controller;
import edu.nus.comp.cs2103t.taskerino.common.LoggerFactory;
import edu.nus.comp.cs2103t.taskerino.common.Task;



/**
 * This class specifies dimensions and other details for each component in GUI.
 * There are all together five categories for the components:
 * 
 * 	1. Menu:
 * 		Enable user to change the outlook of the GUI display mode;
 * 		Enable user to have alternative ways of accessing certain functions (e.g exit the program).
 * 
 *  2. Tags:
 *  	Enable user to see existing tasks based on different task categories.
 * 
 * 	3. User Task Area:
 * 		Enable user to see existing tasks in a form.
 * 
 *  4. User Input Area:
 *  	Enable user to type commands.
 *  
 *  5. User Feedback Area:
 *  	Enable user to access the feedback given by the program after executing their commands.
 * 
 * 
 * @author Wang YanHao
 * 
 */

public class GUIComponents implements ItemListener {
	private static final String className = new Throwable() .getStackTrace()[0].getClassName();
	private static Controller controller = Controller.getController();
	private CommandHistory commandHistory = CommandHistory.getCommandHistory();

	private static final Font font = new Font("SansSerif", Font.PLAIN, 20);

	// panel that combines all components
	private JPanel contentPanel = new JPanel();
	
	// variables for tags
	private	static final String PACKAGE = "javax.swing.";
	private JPanel tagPanel;

	private static String[] tagBoxItems = {"ALL"};	// should be Vector DS, but declared as array for ease of implementation, will change if logic and storage support further such function
	private static String selectedItem;
	private JComboBox<Object> tagBox;
	
	// variable for user input
	private JPanel inputPanel;
	private JTextField userInputArea = new JTextField();
	
	// variable for user output
	private JPanel feedbackPanel;
	private JLabel feedbackToUser;
	private static final String WELCOME_MESSAGE = "Welcome to TASKERINO !!";

	// variable for menu
	private JMenuBar menuBar;
	
	// variables for user tasks
	private JTable userTaskTable;
	private JScrollPane taskScrollPane;
	
	private Object rowData[][] = {};
	private static final int ROW_HEIGHT = 30;
	private static final int ROW_MARGIN = 0;
	
	private Object columnNames[] = { "Index", "Task Name", "Start Date", "Due Date", "Status" };
	private static final int COLUMN_ONE_SIZE = 50;
	private static final int COLUMN_TWO_SIZE = 450;
	private static final int COLUMN_THREE_SIZE = 70;
	private static final int COLUMN_FOUR_SIZE = 70;
	private static final int COLUMN_FIVE_SIZE = 50;
	
	private DefaultTableModel dataModel;
	private static final Color BASE_COLOR = Color.WHITE;
	private static final Color ALTERNATIVE_COLOR = Color.LIGHT_GRAY;
	
	private static final int SCROLLABLE_INCREMENT = 20;

	// variables for hotkeys
	private static final String UP = "Up";
	private static final String DOWN = "Down";
	private static final String CTRL_UP = "Ctrl_Up";
	private static final String CTRL_DOWN = "Ctrl_Down";
	
	
	// constructor
	public GUIComponents() {
		setTagBox();
		setUserTask();
		setUserInput();
		setUserFeedback();
		
		setPanelOverview();
	}

	
	/**
	 * A menu which provides the ability to switch between different LAF's
	 * and alternative ways of accessing certain functions.
	 * 
	 * @return menuBar
	 */
	public JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = createMenuBar();
		}
		return menuBar;
	}

	
	/**
	 * A content panel to be added into a high level container. (JFrame)
	 * 
	 * @return contentPanel
	 */
	public Component getContentPanel() {
		return contentPanel;
	}

	
	/**
	 * Set up content panel: <br>
	 * 1. Set layout to GridBagLayout; <br>
	 * 2. Specify dimension for each component; <br>
	 * 3. Add Components into contentPanel.
	 */
	private void setPanelOverview() {
		final String methodName = "setPanelOverview";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set up content panel overview.");
		
		// set layout for contentPanel
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		contentPanel.setFont(font);
		contentPanel.setLayout(layout);
		
		//Add tagPanel
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add component tagPanel to contentPanel.");
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPanel.add(tagPanel, gbc);

		//Add taskScrollPane
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add component taskScrollPane to contentPanel.");
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		contentPanel.add(taskScrollPane, gbc);

		//Add inputPanel
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add component inputPanel to contentPanel.");
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(inputPanel, gbc);
		
		//Add feedbackPanel
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add component feedbackPanel to contentPanel.");
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 10, 10, 10);
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(feedbackPanel, gbc);
	}
	
	
	/**
	 * Set up JLabel which is used to display feedback to the user. 
	 */
	private void setUserFeedback() {
		feedbackToUser = new JLabel(WELCOME_MESSAGE);
		feedbackPanel = new JPanel();
		feedbackPanel.add(feedbackToUser);
	}
	
	
	/**
	 * Set up JTextField which executes user's input command by
	 * initializing Parser and Logic classes, and display feedback 
	 * to user after retrieving the feedback from Logic class.
	 */
	private void setUserInput() {
		final String methodName = "setUserInput";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set up components for user inputs.");
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel inputLabel = new JLabel("Input Area :");
		inputLabel.setDisplayedMnemonic(KeyEvent.VK_I);
		inputLabel.setLabelFor(userInputArea);
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 10);
		inputPanel.add(inputLabel, gbc);
		
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		inputPanel.add(userInputArea, gbc);
		
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Add action listener for userInputArea.");
		// add action listener for event when user presses "enter" in keyboard
		userInputArea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String inputCommand = userInputArea.getText();
				LoggerFactory.logp(Level.INFO, className, methodName, "User input commands: \n" + inputCommand);
				controller.executeUserCommand(inputCommand);
				
				userInputArea.setText("");
				
				String outputFeedBack = controller.getUserFeedback();
				feedbackToUser.setText(outputFeedBack);
				
				// refresh userTaskTable
				updateTaskTable();
			}
		});
		
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set hotkeys for user input text field.");
		// add key bindings to the JTextField which user type input commands
		int condition = JComponent.WHEN_FOCUSED;
		InputMap inMap = userInputArea.getInputMap(condition);
		ActionMap actMap = userInputArea.getActionMap();
		
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
		
		actMap.put(UP, new UpDownAction(UP));
		actMap.put(DOWN, new UpDownAction(DOWN));
	}
	
	
	/**
	 * Set ActionListener for action performed when user press "up" or "down" on keyboard.
	 */
	@SuppressWarnings("serial")
	private class UpDownAction extends AbstractAction {
		public UpDownAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			String name = getValue(AbstractAction.NAME).toString();

			if (name.equals(UP)) {
				String previousCommand = commandHistory.getPreCommand();
				userInputArea.setText(previousCommand);
				
				if (previousCommand.isEmpty()) {
					feedbackToUser.setText("No more command in the history!");
				} else {
					feedbackToUser.setText(" ");
				}
			} else if (name.equals(DOWN)) {
				String postCommand = commandHistory.getPostCommand();
				userInputArea.setText(postCommand);
				
				if (postCommand.isEmpty()) {
					feedbackToUser.setText("You have reached the end of command history!");
				} else {
					feedbackToUser.setText(" ");
				}
			}
		}
	}
	

	/**
	 * Set up JScrollPane which is used to display all user's tasks.
	 */
	private void setUserTask() {
		final String methodName = "setUserTask";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set up user task JScrollPane.");

		setTable();
		taskScrollPane = new JScrollPane(userTaskTable);

		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set hotkeys for user task scroll pane.");
		// add key bindings to the JLabel which display feedback to user
		int condition = JLabel.WHEN_IN_FOCUSED_WINDOW;
		InputMap inMap = userTaskTable.getInputMap(condition);
		ActionMap actMap = userTaskTable.getActionMap();

		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK), CTRL_UP);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK), CTRL_DOWN);

		actMap.put(CTRL_UP, new CtrlUpDownAction(CTRL_UP, taskScrollPane.getVerticalScrollBar().getModel(), 
				SCROLLABLE_INCREMENT));
		actMap.put(CTRL_DOWN, new CtrlUpDownAction(CTRL_DOWN, taskScrollPane.getVerticalScrollBar().getModel(), 
				SCROLLABLE_INCREMENT));
	}


	/**
	 * Initialize and set userTaskTable properties.
	 */
	@SuppressWarnings("serial")
	private void setTable() {
		final String methodName = "setTable";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Set up user task JTable.");

		dataModel = new DefaultTableModel(rowData, columnNames);

		userTaskTable = new JTable(dataModel){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				LoggerFactory.logp(Level.CONFIG, className, methodName, "Create alternative coloring for rows in JTable.");
				
				Component returnComp = super.prepareRenderer(renderer, row, column);
				if (!returnComp.getBackground().equals(getSelectionBackground())){
					Color coloring = (row % 2 == 0 ? ALTERNATIVE_COLOR : BASE_COLOR);
					returnComp.setBackground(coloring);
					coloring = null;
				}
				return returnComp;
			}
		};

		// set row size
		userTaskTable.setRowHeight(ROW_HEIGHT);
		userTaskTable.setRowMargin(ROW_MARGIN);

		// set column size
		userTaskTable.getColumnModel().getColumn(0).setPreferredWidth(COLUMN_ONE_SIZE);
		userTaskTable.getColumnModel().getColumn(1).setPreferredWidth(COLUMN_TWO_SIZE);
		userTaskTable.getColumnModel().getColumn(2).setPreferredWidth(COLUMN_THREE_SIZE);
		userTaskTable.getColumnModel().getColumn(3).setPreferredWidth(COLUMN_FOUR_SIZE);
		userTaskTable.getColumnModel().getColumn(4).setPreferredWidth(COLUMN_FIVE_SIZE);

		updateTaskTable();
	}


	/**
	 * Update userTaskTable entries.
	 */
	private void updateTaskTable() {
		final String methodName = "updateTaskTable";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Update userTask table.");
		
		// get all user tasks
		ArrayList<Task> userTasks = controller.getUserTasks();

		// clear table data
		dataModel.setRowCount(0);

		LoggerFactory.logp(Level.CONFIG, className, methodName, "Reset tasks in userTask table.");
		// reset table data
		for (Task useTask: userTasks) {
			String[] data = new String[5];

			data[0] = "" + useTask.getTaskIndex();
			data[1] = useTask.getTaskName();
			data[2] = useTask.getStartDate() != null ? "" + useTask.getStartDate() : "";
			data[3] = useTask.getDueDate() != null ? "" + useTask.getDueDate() : "";
			data[4] = "" + useTask.getStatus();

			dataModel.addRow(data);
		}
	}


	/**
	 * Set ActionListener for action performed when user press "Ctrl + up" or "Ctrl + down" on keyboard.
	 */
	@SuppressWarnings("serial")
	private class CtrlUpDownAction extends AbstractAction {
		private BoundedRangeModel vScrollBarModel;
		private int scrollableIncrement;

		public CtrlUpDownAction(String name, BoundedRangeModel model, int scrollableIncrement) {
			super(name);
			this.vScrollBarModel = model;
			this.scrollableIncrement = scrollableIncrement;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			String name = getValue(AbstractAction.NAME).toString();
			int value = vScrollBarModel.getValue();

			if (name.equals(CTRL_UP)) {
				value -= scrollableIncrement;
				vScrollBarModel.setValue(value);
			} else if (name.equals(CTRL_DOWN)) {
				value += scrollableIncrement;
				vScrollBarModel.setValue(value);
			}
		}
	}
	
	
	/**
	 * Set up JComboBox which display a set of tags.
	 */
	private void setTagBox() {
		final String methodName = "setTagBox";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Setting up tagBox.");
		
		tagBox = new JComboBox<Object>();
		
		JLabel label = new JLabel("Select Tags:");
		label.setDisplayedMnemonic('S');
		label.setLabelFor(tagBox);
		
		tagPanel = new JPanel();
		tagPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
		tagPanel.add(label);
		tagPanel.add(tagBox);
		
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Select tag in the tagBox.");
		tagBox.removeItemListener(this);
		tagBox.setModel(new DefaultComboBoxModel<Object>(tagBoxItems));
		tagBox.setSelectedIndex(0);
		tagBox.addItemListener(this);
		tagBox.requestFocusInWindow();

		if (selectedItem != null) {
			tagBox.setSelectedItem(selectedItem);
		}
	}
	
	
	/**
	 *  Create menu bar.
	 */
	private JMenuBar createMenuBar() {
		final String methodName = "createMenuBar";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Creating menu bar.");
		
		JMenuBar menuBar = new JMenuBar();

		menuBar.add(createFileMenu());
		menuBar.add(createLAFMenu());

		return menuBar;
	}
	
	
	/**
	 *  Create menu items for the Application menu
	 */
	private JMenu createFileMenu() {
		final String methodName = "createFileMenu";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Creating file bar.");
		
		JMenu menu = new JMenu("Application");
		menu.setMnemonic(KeyEvent.VK_A);

		menu.addSeparator();
		menu.add(new ExitAction());

		return menu;
	}

	/**
	 *	Close the frame.
	 */
	@SuppressWarnings("serial")
	class ExitAction extends AbstractAction {
		public ExitAction()	{
			putValue(Action.NAME, "Exit");
			putValue(Action.SHORT_DESCRIPTION, getValue(Action.NAME));
			putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_E));
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	/**
	 *  Create menu items for the Look & Feel menu.
	 */
	private JMenu createLAFMenu() {
		final String methodName = "createLAFMenu";
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Creating Look & Feel menu.");
		
		ButtonGroup buttons = new ButtonGroup();

		JMenu menu = new JMenu("Look & Feel");
		menu.setMnemonic(KeyEvent.VK_L);

		String lafId = UIManager.getLookAndFeel().getID();
		UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < lafInfo.length; i++) {
			String laf = lafInfo[i].getClassName();
			String name= lafInfo[i].getName();

			Action action = new ChangeLookAndFeelAction(laf, name);
			JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(action);
			menu.add(menuItem);
			buttons.add(menuItem);

			if (name.equals(lafId)) {
				menuItem.setSelected(true);
			}
		}

		return menu;
	}

	
	/**
	 *  Change the LAF and recreate contentPanel in GUIComponents to update effect.
	 */
	@SuppressWarnings("serial")
	class ChangeLookAndFeelAction extends AbstractAction {
		final String innerClassName = className + "/ChangeLookAndFeelAction";
		
		private String laf;

		protected ChangeLookAndFeelAction(String laf, String name) {
			this.laf = laf;
			putValue(Action.NAME, name);
			putValue(Action.SHORT_DESCRIPTION, getValue(Action.NAME));
		}

		public void actionPerformed(ActionEvent event) {
			LoggerFactory.logp(Level.CONFIG, innerClassName, "Action Listener", "Updating Look & Feel.");
			
			try {
				JMenuItem menuItem = (JMenuItem)event.getSource();
				JPopupMenu popup = (JPopupMenu)menuItem.getParent();
				JRootPane rootPane = SwingUtilities.getRootPane(popup.getInvoker());
				Component component = rootPane.getContentPane().getComponent(0);
				rootPane.getContentPane().remove(component);

				UIManager.setLookAndFeel(laf);
				GUIComponents bindings = new GUIComponents();
				rootPane.getContentPane().add(bindings.getContentPanel());
				SwingUtilities.updateComponentTreeUI(rootPane);
				rootPane.requestFocusInWindow();
				
			} catch (Exception exception) {
				LoggerFactory.logp(Level.SEVERE, innerClassName, "Action Listener", 
						"Failed to load Look & Feel." + exception.getMessage());
				exception.printStackTrace();
			}
		}
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// Implement the ItemListener interface
		String componentName = (String)e.getItem();
		changeTableModel(getClassName(componentName));
		selectedItem = componentName;
	}
	
	/**
	 *  Use the component name to build the class name
	 */
	private String getClassName(String componentName) {
		//  The table header is in a child package
		if (componentName.equals("TableHeader")) {
			return PACKAGE + "table.JTableHeader";
		} else {
			return PACKAGE + "J" + componentName;
		}
	}
	
	/**
	 *  Change the TabelModel in the table for the selected component
	 */
	private void changeTableModel(String className) {
		// dummy class, will implement in future if logic has support this funciton
	}
}
