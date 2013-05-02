package com.epam.struts.xsl;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

class ErrorListenerImpl implements ErrorListener {

    @Override
    public void warning(TransformerException exception) throws TransformerException {
        throw exception;
    }

    @Override
    public void error(TransformerException exception) throws TransformerException {
        throw exception;
    }

    @Override
    public void fatalError(TransformerException exception) throws TransformerException {
        throw exception;
    }

}
