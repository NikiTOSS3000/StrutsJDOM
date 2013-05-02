package com.epam.struts.service;

import com.epam.struts.model.Product;
import com.epam.struts.presentation.form.ProductForm;
import com.epam.struts.presentation.servlet.JDOMServlet;
import com.epam.struts.resources.Constants;
import com.epam.struts.util.MessageManager;
import com.epam.struts.xsl.XSLTransformer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public final class XMLService {

    private static final Logger logger = Logger.getLogger("com.epam.struts.service");
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final static String ERROR_MSG_PREFIX = "Errors: ";

    public static Document getDocument(String name) {
        Document document = null;
        try {
            lock.readLock().lock();
            document = new SAXBuilder().build(new File(name));
        } catch (JDOMException ex) {
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            lock.readLock().unlock();
        }
        return document;
    }

    public static void update(Document document, List<Integer> ids, int category, int subcategory) {
        Element root = document.getRootElement();
        Namespace namespace = root.getNamespace();
        List<Element> subcategories = root.getChildren().get(category).getChildren();
        List<Element> products = subcategories.get(subcategory).getChildren();
        int currIndex = 0;
        int currId = ids.size() > 0 ? ids.get(currIndex++) : -1;
        for (int i = 0; i < products.size(); i++) {
            Element product = products.get(i);
            Element inStock = product.getChild(Constants.INSTOCK, namespace);
            if (i == currId) {
                if (currIndex < ids.size()) {
                    currId = ids.get(currIndex++);
                }
                if (inStock != null) {
                    String text = inStock.getText();
                    product.removeChild(Constants.INSTOCK, namespace);
                    Element price = new Element(Constants.PRICE, namespace);
                    price.setText(text);
                    product.addContent(price);
                }
            } else {
                if (inStock != null) {
                    inStock.setText("false");
                } else {
                    product.removeChild(Constants.PRICE, namespace);
                    inStock = new Element(Constants.INSTOCK, namespace);
                    inStock.setText("false");
                    product.addContent(inStock);
                }
            }
        }
        writeDocument(document, JDOMServlet.getPath() + MessageManager.getStr("XML_PATH"));
    }

    public static String getTransformedResponse(String xml, String xsl, HashMap<String, Object> param) {
        String s = null;
        XSLTransformer transformer = new XSLTransformer();
        try {
            s = transformer.transform(xml, xsl, param);
        } catch (TransformerException ex) {
            logger.error(ex);
            s = ERROR_MSG_PREFIX + ex.getMessage();
        }
        return s;
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

    public static void addProduct(HashMap<String, Object> param, Product product) {
        String xsl = Constants.SAVE;
        String xml = JDOMServlet.getPath() + MessageManager.getStr("XML_PATH");
        param.put(Constants.CATEGORY, product.getCategory());
        param.put(Constants.SUBCATEGORY, product.getSubcategory());
        param.put(Constants.NAME, product.getName());
        param.put(Constants.PRODUCER, product.getProducer());
        param.put(Constants.MODEL, product.getModel());
        param.put(Constants.COLOR, product.getColor());
        param.put(Constants.DATE, product.getDate());
        param.put(Constants.PRICE, product.getPrice());
        String inStock = ((Boolean) product.isInStock()).toString();
        param.put(Constants.INSTOCK, inStock);
        lock.readLock().lock();
        String transformedResponse = getTransformedResponse(xml, xsl, param);
        lock.readLock().unlock();
        lock.writeLock().lock();
        writeInFile(transformedResponse, xml);
        lock.writeLock().unlock();
    }

    public static void writeDocument(Document document, String file) {
        try {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            lock.writeLock().lock();
            xmlOutput.output(document, new FileWriter(file));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            lock.writeLock().unlock();
        }
    }
}
