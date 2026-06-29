package aigis;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

	private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static PrintWriter logWriter;

	static {
		try {
			File logFile = new File(System.getProperty("user.home"), "AiGIS_error.log");
			logWriter = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (Exception e) {
			logWriter = null;
		}
	}

	private static void write(String level, String msg) {
		String line = LocalDateTime.now().format(FMT) + " [" + level + "] " + msg;
		System.out.println(line);
		if (logWriter != null) logWriter.println(line);
	}

	public static void Info(String msg) {
		write("INFO", msg);
	}

	public static void Debug(String msg) {
		write("DEBUG", msg);
	}

	public static void Error(String msg) {
		write("ERROR", msg);
	}

	public static void Error(Exception e) {
		write("ERROR", e.getClass().getSimpleName() + ": " + e.getMessage());
		e.printStackTrace();
		if (logWriter != null) e.printStackTrace(logWriter);
	}

	public static void Error(Error e) {
		write("ERROR", e.getClass().getSimpleName() + ": " + e.getMessage());
		e.printStackTrace();
		if (logWriter != null) e.printStackTrace(logWriter);
	}
}
