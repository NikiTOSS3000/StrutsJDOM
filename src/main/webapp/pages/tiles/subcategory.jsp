<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="/WEB-INF/jdom.tld" prefix="jdom" %>
<jsp:useBean id="path" class="java.util.HashMap" scope="page" />

<c:forEach var="subcat" items="${productForm.document.rootElement.children[productForm.category].children}" varStatus="i">
    <c:set target="${path}" property="category" value="${productForm.category}"/>
    <c:set target="${path}" property="subcategory" value="${i.index}"/>
    <h3>
    <html:link action="products" name="path">
        ${subcat.attributes[0].value}
        </html:link>
        <jdom:countProducts element="${subcat}"/>
    </h3>
    <hr/>
</c:forEach> 
<html:form action="category">
    <html:hidden property="category" value="${productForm.category}"/>
    <html:submit>
        <bean:message key="string.back"/>
    </html:submit>
</html:form>
