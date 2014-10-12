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

/**
 * This class specifies a component and its corresponding dimension
 * when it is inserted into JPanel in GUI class which will be shown
 * to the user.
 * The default layout for the GUI class is GridBagLayout.
 * 
 * @author Wang YanHao
 *
 */
public class PanelComponent {
	private Component component;
	private double weightx;
	private double weighty;
	private int gridheight;
	private int gridwidth;
	
	public PanelComponent (Component component, double weightx, double weighty, 
			int gridheight, int gridwidth){
		this.component = component;
		this.weightx = weightx;
		this.weighty = weighty;
		this.gridheight = gridheight;
		this.gridwidth = gridwidth;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public double getWeightx() {
		return weightx;
	}

	public void setWeightx(double weightx) {
		this.weightx = weightx;
	}

	public double getWeighty() {
		return weighty;
	}

	public void setWeighty(double weighty) {
		this.weighty = weighty;
	}

	public int getGridheight() {
		return gridheight;
	}

	public void setGridheight(int gridheight) {
		this.gridheight = gridheight;
	}

	public int getGridwidth() {
		return gridwidth;
	}

	public void setGridwidth(int gridwidth) {
		this.gridwidth = gridwidth;
	}
}
