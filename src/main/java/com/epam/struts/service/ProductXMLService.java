package com.epam.struts.service;

import com.epam.struts.model.Product;
import com.epam.struts.presentation.servlet.JDOMServlet;
import com.epam.struts.resources.Constants;
import com.epam.struts.util.IOUtil;
import com.epam.struts.util.MessageManager;
import com.epam.struts.xsl.TransformerService;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public final class ProductXMLService {

    private static final Logger logger = Logger.getLogger("com.epam.struts.service");
    private static final TransformerService service = TransformerService.getInstance();

    public static Document getDocument(String name) {
        Document document = null;
        try {
            service.getReadLock().lock();
            document = new SAXBuilder().build(new File(name));
        } catch (JDOMException ex) {
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            service.getReadLock().unlock();
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
        service.getReadLock().lock();
        IOUtil.writeDocument(document, JDOMServlet.getPath() + MessageManager.getStr("XML_PATH"));
        service.getReadLock().unlock();
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
        String xslPath = JDOMServlet.getPath() + MessageManager.getStr("SAVE_XSL");
        service.getReadLock().lock();
        String transformedResponse = service.getTransformedResponse(xml, xslPath, xsl, param);
        service.getReadLock().unlock();
        service.getWriteLock().lock();
        IOUtil.writeInFile(transformedResponse, xml);
        service.getWriteLock().unlock();
    }

}
