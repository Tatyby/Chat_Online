package Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
    String nameFileLog = "src/main/log.txt";
    private static Logger logger;

    public void log(String msg) {
        try {
            FileWriter fileWriter = new FileWriter(nameFileLog, true);
            fileWriter.write(formatMessageLog(msg));
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public String formatMessageLog(String msg) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String message;
        message = "[" + formatter.format(calendar.getTime()) + " " + "] " + ": " + msg;
        return message;
    }
}
