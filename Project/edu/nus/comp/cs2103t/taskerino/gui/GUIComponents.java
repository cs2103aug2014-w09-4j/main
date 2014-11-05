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
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
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

//@author A0113742N

/**
 * This class specifies dimensions and other details for each component in GUI. <br>
 * There are all together five categories for the components: <br>
 * 
 * 	1. Menu: <br>
 * 		Enable user to change the outlook of the GUI display mode; <br>
 * 		Enable user to have alternative ways of accessing certain functions (e.g exit the program). <br>
 * 
 *  2. Tags: <br>
 *  	Enable user to see existing tasks based on different task categories. <br>
 * 
 * 	3. User Task Area: <br>
 * 		Enable user to see existing tasks in a form. <br>
 * 
 *  4. User Input Area: <br>
 *  	Enable user to type commands. <br>
 *  
 *  5. User Feedback Area: <br>
 *  	Enable user to access the feedback given by the program after executing their commands. <br>
 */
public class GUIComponents {
	private static final String className = new Throwable() .getStackTrace()[0].getClassName();
	private static Controller controller = Controller.getController();
	private CommandHistory commandHistory = CommandHistory.getCommandHistory();

	private static final Font FONT_CONTENT_TABLE_TITLE = new Font("SansSerif", Font.PLAIN, 15);
	private static final Font FONT_CONTENT_TABLE = new Font("SansSerif", Font.PLAIN, 15);
	private static final Font FONT_FEEDBACK = new Font("SansSerif", Font.PLAIN, 15);
	private static final Font FONT_TAG_LABEL = new Font("SansSerif", Font.PLAIN, 15);
	private static final Font FONT_TAG = new Font("SansSerif", Font.PLAIN, 15);
	private static final Font FONT_INPUT_LABEL = new Font("SansSerif", Font.PLAIN, 15);
	private static final Font FONT_INPUT_AREA = new Font("SansSerif", Font.PLAIN, 15);

	// panel that combines all components
	private JPanel contentPanel = new JPanel();
	
	// variables for tags
	private JPanel tagPanel;

	private static final String TAG_ALL = "all";
	private static final int TAG_ALL_INDEX = 0;
	private static final String TAG_SEARCH = "search";
	private static final int TAG_SEARCH_INDEX = 1;
	private static final String TAG_COMPLETE = "complete";
	private static final int TAG_COMPLETE_INDEX = 2;
	private static final String TAG_UNCOMPLETE = "uncomplete";
	private static final int TAG_UNCOMPLETE_INDEX = 3;
	private static final String[] tagBoxItems = {TAG_ALL, TAG_SEARCH, TAG_COMPLETE, TAG_UNCOMPLETE};
	private static String selectedItem;
	private static JLabel tagLabel;
	private static JComboBox<Object> tagBox;
	
	// variable for user input
	private JPanel inputPanel;
	private JLabel inputLabel;
	private JTextField userInputArea = new JTextField();
	
	// variable for user output
	private JPanel feedbackPanel;
	private JLabel feedbackToUser;
	private static final String WELCOME_MESSAGE = "Welcome to TASKERINO !!";

	// variable for menu
	private JMenuBar menuBar;
	
	// variables for user tasks
	private static JTable userTaskTable;
	private static JScrollPane taskScrollPane;
	
	private Object rowData[][] = {};
	private static final int ROW_HEIGHT = 30;
	private static final int ROW_MARGIN = 0;
	
	private static final Object[] columnNames = {"Index", "Task Name", "Start Date", "Due Date", "Status"};
	private static final int COLUMN_ONE_SIZE = 20;
	private static final int COLUMN_TWO_SIZE = 450;
	private static final int COLUMN_THREE_SIZE = 70;
	private static final int COLUMN_FOUR_SIZE = 70;
	private static final int COLUMN_FIVE_SIZE = 70;
	
	private DefaultTableModel dataModel;
	private static final Color BASE_COLOR = Color.WHITE;
	private static final Color ALTERNATIVE_COLOR = Color.LIGHT_GRAY;
	
	private static final int SCROLLABLE_INCREMENT = 20;

	// variables for hot keys
	private static final String UP = "Up";
	private static final String DOWN = "Down";
	private static final String ALT_UP = "Alt_Up";
	private static final String ALT_DOWN = "Alt_Down";
	private static final String TAB = "Tab";
	private static final String DELETE = "Delete";
	
	
	// constructor
	protected GUIComponents() {
		setTagBox();
		setUserTask();
		setUserInput();
		setUserFeedback();
		setFont();
		
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
	 * Make input text filed to be the default focused component.
	 */
	public void focusInputTextField() {
		userInputArea.requestFocusInWindow();
	}
	
	
	/**
	 * Set Fond size and style for all sub-panel.
	 */
	private void setFont() {
		tagLabel.setFont(FONT_TAG_LABEL);
		tagBox.setFont(FONT_TAG);
		inputLabel.setFont(FONT_INPUT_LABEL);
		userInputArea.setFont(FONT_INPUT_AREA);
		
		feedbackToUser.setFont(FONT_FEEDBACK);
		userTaskTable.setFont(FONT_CONTENT_TABLE);
		userTaskTable.getTableHeader().setFont(FONT_CONTENT_TABLE_TITLE);
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
		
		inputLabel = new JLabel("Input Area :");
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
			public void actionPerformed(ActionEvent event) {
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
		// enable hot key "tab"
		userInputArea.setFocusTraversalKeysEnabled(false);
		
		// add key bindings to the JTextField which user type input commands
		int condition = JComponent.WHEN_FOCUSED;
		InputMap inMap = userInputArea.getInputMap(condition);
		ActionMap actMap = userInputArea.getActionMap();
		
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), TAB);
		
		actMap.put(UP, new UserInputAreaAction(UP));
		actMap.put(DOWN, new UserInputAreaAction(DOWN));
		actMap.put(TAB, new UserInputAreaAction(TAB));
	}
	
	
	/**
	 * Set ActionListener for action performed when user press "up", "down" or "tab" on keyboard. <br>
	 * If there is a hot key clash with other components, those actions will be invoked 
	 * when userInputArea is in focus.
	 */
	@SuppressWarnings("serial")
	private class UserInputAreaAction extends AbstractAction {
		public UserInputAreaAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
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
			} else if (name.equals(TAB)) {
				// change focus
				userTaskTable.requestFocusInWindow();
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
		// enable hot key "tab"
		userTaskTable.setFocusTraversalKeysEnabled(false);
		userTaskTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "none");
		
		// add key bindings to the JTable which display feedback to user
		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inMap = userTaskTable.getInputMap(condition);
		ActionMap actMap = userTaskTable.getActionMap();

		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.ALT_DOWN_MASK), ALT_UP);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.ALT_DOWN_MASK), ALT_DOWN);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), TAB);
		inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);

		actMap.put(ALT_UP, new UserTaskTableAction(ALT_UP, taskScrollPane.getVerticalScrollBar().getModel(), 
				SCROLLABLE_INCREMENT));
		actMap.put(ALT_DOWN, new UserTaskTableAction(ALT_DOWN, taskScrollPane.getVerticalScrollBar().getModel(), 
				SCROLLABLE_INCREMENT));
		actMap.put(TAB, new UserTaskTableAction(TAB));
		actMap.put(DELETE, new UserTaskTableAction(DELETE));
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
		for (int i=0; i<userTasks.size(); i++) {
			String[] data = new String[5];

			data[0] = (1 + i) + "";
			data[1] = userTasks.get(i).getTaskName();
			data[2] = userTasks.get(i).getStartDate() != null ? "" + userTasks.get(i).getStartDate() : "";
			data[3] = userTasks.get(i).getDueDate() != null ? "" + userTasks.get(i).getDueDate() : "";
			data[4] = userTasks.get(i).getStatus();

			dataModel.addRow(data);
		}
	}

	/**
	 * Set focus of JTable to the row with taskIndex.
	 */
	public static void setTaskTableFocus(int taskIndex) {
		userTaskTable.setRowSelectionInterval(taskIndex, taskIndex);
		userTaskTable.scrollRectToVisible(userTaskTable.getCellRect(taskIndex, 0, true));
	}
	

	/**
	 * Set ActionListener for action performed when user press "Alt + up", "Alt + down",
	 * "tab" or "delete" on keyboard. <br>
	 * If there is a hot key clashes with other components, those actions will be invoked 
	 * when userTaskTable is in focus.
	 */
	@SuppressWarnings("serial")
	private class UserTaskTableAction extends AbstractAction {
		private BoundedRangeModel vScrollBarModel;
		private int scrollableIncrement;

		public UserTaskTableAction(String name, BoundedRangeModel model, int scrollableIncrement) {
			super(name);
			this.vScrollBarModel = model;
			this.scrollableIncrement = scrollableIncrement;
		}

		public UserTaskTableAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			String name = getValue(AbstractAction.NAME).toString();
			
			if (name.equals(ALT_UP) || name.equals(ALT_DOWN)) {
				int value = vScrollBarModel.getValue();
				
				if (name.equals(ALT_UP)) {
					value -= scrollableIncrement;
					vScrollBarModel.setValue(value);
				} else if (name.equals(ALT_DOWN)) {
					value += scrollableIncrement;
					vScrollBarModel.setValue(value);
				} 
				
			} else if (name.equals(TAB)) {
				// change focus
				focusInputTextField();
				
			} else if (name.equals(DELETE)) {
				// delete selected lines of rows
				int[] selectedRowIndices = userTaskTable.getSelectedRows();
				Arrays.sort(selectedRowIndices);
				
				for (int i=selectedRowIndices.length-1; i>=0; i--) {
					String inputCommand = "delete " + (selectedRowIndices[i] + 1);
					controller.executeUserCommand(inputCommand);
				}
				
				// impossible to select non-existent rows, can safely set feedback here
				String outputFeedBack = "Delete tasks with indices: ";
				for (int i=0; i<selectedRowIndices.length; i++) {
					outputFeedBack += (selectedRowIndices[i] + 1) + " ";
				}
				outputFeedBack += "successfully";
				feedbackToUser.setText(outputFeedBack);
				
				// refresh userTaskTable
				updateTaskTable();
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
		
		tagLabel = new JLabel("Select Tags:");
		tagLabel.setDisplayedMnemonic('S');
		tagLabel.setLabelFor(tagBox);
		
		tagPanel = new JPanel();
		tagPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
		tagPanel.add(tagLabel);
		tagPanel.add(tagBox);
		
		LoggerFactory.logp(Level.CONFIG, className, methodName, "Select tag in the tagBox.");
		tagBox.setModel(new DefaultComboBoxModel<Object>(tagBoxItems));
		tagBox.setSelectedIndex(0);
		setSelectedItem(tagBoxItems[0]);
		tagBox.requestFocusInWindow();
		
		tagBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ItemSelectable item = (ItemSelectable)event.getSource();
				setSelectedItem(selectedString(item));
				updateTaskTable();
			}
		});
	}
	
	/**
	 * Get the selected tag's name in String representation.
	 * @param item -- Selected Tag
	 * @return Tag's name
	 */
	private String selectedString(ItemSelectable item) {
	    Object selected[] = item.getSelectedObjects();
	    return ((selected.length == 0) ? "null" : (String)selected[0]);
	}  

	
	public static String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * Reset the seletectedItem and the tagBox in GUIComponents class.
	 * @param selectedItem TagName
	 */
	public static void setSelectedItem(String selectedItem) {
		GUIComponents.selectedItem = selectedItem;
		switch (selectedItem) {
			case TAG_ALL:
				tagBox.setSelectedIndex(TAG_ALL_INDEX);
				break;
			case TAG_SEARCH:
				tagBox.setSelectedIndex(TAG_SEARCH_INDEX);
				break;
			case TAG_COMPLETE:
				tagBox.setSelectedIndex(TAG_COMPLETE_INDEX);
				break;
			case TAG_UNCOMPLETE:
				tagBox.setSelectedIndex(TAG_UNCOMPLETE_INDEX);
				break;
			default:
			    assert false : selectedItem;
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

		public void actionPerformed(ActionEvent event) {
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
				bindings.focusInputTextField();
			} catch (Exception exception) {
				LoggerFactory.logp(Level.SEVERE, innerClassName, "Action Listener", 
						"Failed to load Look & Feel." + exception.getMessage());
				exception.printStackTrace();
			}
		}
	}
	
}