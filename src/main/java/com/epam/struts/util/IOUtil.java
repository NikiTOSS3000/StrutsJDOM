
package com.epam.struts.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public final class IOUtil {
    private static Logger logger = Logger.getLogger(IOUtil.class);
    
    private IOUtil(){}
    
    
    public static boolean writeResponse(String s, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            pw.write(s);
            pw.close();
        } catch (IOException ex) {
            logger.error(ex);
            return false;
        }
        return true;
    }

    public static boolean sendRedirect(String s, HttpServletResponse response) {
        try {
            response.sendRedirect(s);
        } catch (IOException ex) {
            logger.error(ex);
            return false;
        }
        return true;
    }

    public static boolean writeInFile(String s, String file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(s);
            writer.close();
            return true;
        } catch (IOException ex) {
            logger.error(ex);
            return false;
        }
    }
    
    public static void writeDocument(Document document, String file) {
        try {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(file));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
