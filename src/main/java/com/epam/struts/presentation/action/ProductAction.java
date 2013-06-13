package com.epam.struts.presentation.action;

import com.epam.struts.presentation.form.ProductForm;
import com.epam.struts.resources.Constants;
import com.epam.struts.service.ProductXMLService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.MappingDispatchAction;

public final class ProductAction extends MappingDispatchAction {

    private static final String SUCCESS_MAPPING = "success";

    public ActionForward document(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        saveToken(request);
        return mapping.findForward(SUCCESS_MAPPING);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProductForm productForm = (ProductForm) form;
        if (isTokenValid(request)) {
            List<Integer> ids = new ArrayList<Integer>();
            for (String id : productForm.getInStocksIds()) {
                ids.add(Integer.parseInt(id));
            }
            int category = Integer.parseInt(productForm.getCategory());
            int subcategory = Integer.parseInt(productForm.getSubcategory());
            ProductXMLService.update(productForm.getDocument(), ids, category, subcategory);
        }
        resetToken(request);
        ActionRedirect redirect = new ActionRedirect(mapping.findForward(SUCCESS_MAPPING));
        redirect.addParameter(Constants.CATEGORY, productForm.getCategory());
        redirect.addParameter(Constants.SUBCATEGORY, productForm.getSubcategory());
        return redirect;
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProductForm productForm = (ProductForm) form;
        if (isTokenValid(request)) {
            ProductXMLService.addProduct(new HashMap<String, Object>(), productForm.getProduct());
        }
        resetToken(request);
        ActionRedirect redirect = new ActionRedirect(mapping.findForward(SUCCESS_MAPPING));
        redirect.addParameter(Constants.CATEGORY, productForm.getCategory());
        redirect.addParameter(Constants.SUBCATEGORY, productForm.getSubcategory());
        return redirect;
    }
}
