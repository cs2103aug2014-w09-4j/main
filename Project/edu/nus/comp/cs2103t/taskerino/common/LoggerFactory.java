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
package edu.nus.comp.cs2103t.taskerino.common;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//@author A0113742N

/**
 * This class enable all the classes in a project to record down information
 * on a single local output file using a common logger.
 */
public class LoggerFactory {
	private static final Logger logger = Logger.getLogger("Project.LoggerFactory");
	private static final Level loggerLvl = Level.INFO;
	
	static {
		logger.setLevel(loggerLvl);
		
		try {
			FileHandler fh = new FileHandler("log.log", true);
			// change output format
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private LoggerFactory(){}
	
	public static void logp(Level level, String sourceClass, String sourceMethod, String msg) {
		logger.logp(level, sourceClass, sourceMethod, msg);
	}
	
	public static void logp(Level level, String sourceClass, String sourceMethod, String msg, Throwable thrown) {
		logger.logp(level, sourceClass, sourceMethod, msg, thrown);
	}
}
