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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * @author Wang YanHao
 *
 */
public class GUI{
	private final String APP_NAME = "TBA";
	
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
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(4, 1));
		
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String inputCommand = input.getText();
				output.setText(inputCommand);
			}
		});

		pane.add(input);
		pane.add(output);
		
		return pane;
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
