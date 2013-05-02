package com.epam.struts.tag;

import com.epam.struts.resources.Constants;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import org.jdom2.Element;

public final class ProductCountTag extends TagSupport {

    private static final Logger logger = Logger.getLogger("com.epam.struts.tag");
    private Element element;

    public void setElement(Element element) {
        this.element = element;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            int count = 0;
            if (Constants.CATEGORY.equals(element.getName())) {
                for (Element i : element.getChildren()) {
                    count += i.getChildren().size();
                }
            } else if (Constants.SUBCATEGORY.equals(element.getName())) {
                count += element.getChildren().size();
            }
            out.write("(" + count + ")");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return SKIP_BODY;
    }
}
