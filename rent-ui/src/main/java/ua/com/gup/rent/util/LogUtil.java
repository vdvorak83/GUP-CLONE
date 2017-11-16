package ua.com.gup.rent.util;

import java.io.PrintWriter;
import java.io.StringWriter;


public final class LogUtil {

    public static String getExceptionStackTrace(Exception ex) {
        StringWriter stack = new StringWriter();
        ex.printStackTrace(new PrintWriter(stack));
        return " [" + ex.getClass() + "] " + stack.toString();
    }
}