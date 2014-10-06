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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.google.gson.JsonSyntaxException;


/**
 * This class creates a Graphical User Interface which user can interact with by
 * typing in the text field and receive feedback depending on the input.
 * 
 * @author Wang YanHao
 *
 */
public class GUI{
	private final String APP_NAME = "Taskerino";
	
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
		final JTextField input = new JTextField();
		final JLabel output = new JLabel();
		final JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		panel.add(input);
		panel.add(scrollPane);
		
		// add action listener for event when user presses "enter" in keyboard
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String inputCommand = input.getText();
				input.setText("");
				
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
				
				output.setText(outputFeedBack);
			}
		});

		scrollPane.getViewport().add(output);
		
		panel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.setLayout(layout);
		
		gbc.fill = GridBagConstraints.BOTH; 
		
		// set dimension for input text field
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 1;
		gbc.gridwidth = 0;
		layout.setConstraints(input, gbc);
		
		// set dimension for output label
		gbc.weightx = 1.0;
		gbc.weighty = 3.0;
		gbc.gridheight = 3;
		gbc.gridwidth = 0;
		layout.setConstraints(scrollPane, gbc);
		
		return panel;
	}
	
	public static void main(String[] args) throws JsonSyntaxException, IOException {
		Common.task = Storage.loadTasksFromFile();
		new GUI();
	}
}
