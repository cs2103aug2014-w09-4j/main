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
package Project;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoundedRangeModel;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

import com.google.gson.JsonSyntaxException;


/**
 * This class creates a Graphical User Interface which user can interact with by
 * typing in the text field and receive feedback depending on the input.
 * 
 * @author Wang YanHao
 *
 */
public class GUI{
	private static final String APP_NAME = "Taskerino";
    private static final String UP = "Up";
    private static final String DOWN = "Down";
    private static final int SCROLLABLE_INCREMENT = 14;
    
	private final JPanel panel = new JPanel();
	private final JTextField userInputArea = new JTextField();
	private final JLabel outputToUserArea = new JLabel();
	private final JScrollPane scrollPane = new JScrollPane();

	public GUI() {
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
	}

	private Component createComponents(){
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		// add action listener for event when user presses "enter" in keyboard
		userInputArea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String inputCommand = userInputArea.getText();
				userInputArea.setText("");
				
				// inputCommand is passed into parser and logic
				Common.setInput(inputCommand);
				(new Parser()).parse();
				String outputFeedBack = null;
				try {
					outputFeedBack = (new Logic()).executeUserCommand();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// handle the exceptions
					e.printStackTrace();
				}
				
				outputToUserArea.setText(outputFeedBack);
			}
		});

		scrollPane.getViewport().add(outputToUserArea);

        // add key bindings to the JLabel which display feedback to user
        int condition = JLabel.WHEN_IN_FOCUSED_WINDOW;
        InputMap inMap = outputToUserArea.getInputMap(condition);
        ActionMap actMap = outputToUserArea.getActionMap();
		
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
        
        actMap.put(UP, new UpDownAction(UP, scrollPane.getVerticalScrollBar().getModel(), 
        		SCROLLABLE_INCREMENT));
        actMap.put(DOWN, new UpDownAction(DOWN, scrollPane.getVerticalScrollBar().getModel(), 
        		SCROLLABLE_INCREMENT));
        
		// set layout for JPanel
		panel.add(userInputArea);
		panel.add(scrollPane);
		
		panel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.setLayout(layout);
		
		gbc.fill = GridBagConstraints.BOTH; 
		
		// set dimension for input text field
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 0;
		layout.setConstraints(userInputArea, gbc);
		
		// set dimension for output label
		gbc.weightx = 1.0;
		gbc.weighty = 3.0;
		gbc.gridheight = 3;
		gbc.gridwidth = 0;
		layout.setConstraints(scrollPane, gbc);
		
		return panel;
	}
	
	
	// Action performed when user press "up" or "down" on keyboard
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
		Data.task = Storage.loadTasksFromFile();
		new GUI();
	}
}
