<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/jdom.tld" prefix="jdom" %>

<c:forEach var="cat" items="${productForm.document.rootElement.children}" varStatus="i">
    <h3>
    <html:link action="subcategory" paramId="category" paramProperty="index" paramName="i">
        ${cat.attributes[0].value}
        </html:link>
        <jdom:countProducts element="${cat}"/>
    </h3>
    <hr/>
</c:forEach>   
