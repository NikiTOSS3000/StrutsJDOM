package com.epam.struts.presentation.form;

import com.epam.struts.model.Product;
import com.epam.struts.presentation.servlet.JDOMServlet;
import com.epam.struts.service.ProductXMLService;
import com.epam.struts.util.MessageManager;
import org.apache.struts.action.ActionForm;
import org.jdom2.Document;

public final class ProductForm extends ActionForm{
    private Document document;
    private String category;
    private String subcategory;
    private String[] inStocksIds;
    private Product product;

    public ProductForm() {
        product = new Product();
        document = ProductXMLService.getDocument(JDOMServlet.getPath() + MessageManager.getStr("XML_PATH"));
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String[] getInStocksIds() {
        return inStocksIds;
    }

    public void setInStocksIds(String[] inStocksIds) {
        this.inStocksIds = inStocksIds;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
