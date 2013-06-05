<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<c:set var="catName"
       value="${productForm.document.rootElement.children[productForm.category].attributes[0].value}" />
<c:set var="subcatName"
       value="${productForm.document.rootElement.children[productForm.category].children[productForm.subcategory].attributes[0].value}" />
<html:form action="save" onsubmit="return validateAdd()" style="padding: 10px;">
    <dl>
        <dt>
        <label for="name">
            <bean:message key="string.name"/>
        </label>
        </dt>
        <dd>
            <html:text property="product.name" styleId="name"
                       onchange="validateExists(this)" onkeyup="validateExists(this)"/>
            <span id="nameError" class="errors"></span>
        </dd>
        <dt>
        <label for="producer">
            <bean:message key="string.producer"/>
        </label>
        </dt>
        <dd>
            <html:text property="product.producer" styleId="producer"
                       onchange="validateExists(this)" onkeyup="validateExists(this)"/>
            <span id="producerError" class="errors"></span>
        </dd>
        <dt>
        <label for="model">
            <bean:message key="string.model"/>
        </label>
        </dt>
        <dd>
            <html:text property="product.model" styleId="model"
                       onchange="validateModel(this)" onkeyup="validateModel(this)"/>
            <span id="modelError" class="errors"></span>
        </dd>

        <dt>
        <label for="date">
            <bean:message key="string.date"/>
        </label>
        </dt>
        <dd>
            <html:text property="product.date" styleId="date"
                       onchange="validateDate(this)" onkeyup="validateDate(this)"/>
            <span id="dateError" class="errors"></span>
        </dd>

        <dt>
        <label for="color">
            <bean:message key="string.color"/>
        </label>
        </dt>
        <dd>
            <html:text property="product.color" styleId="color"
                       onchange="validateExists(this)" onkeyup="validateExists(this)"/>
            <span id="colorError" class="errors"/>
        </dd>
        <dt>
        <label for="instock">
            <bean:message key="string.instock"/>
        </label>
        </dt>
        <dd>
            <html:checkbox property="product.inStock" styleId="inStock"
                           onchange="validateCheckBox(this)"/>
        </dd>
        <dt>
        <label for="price">
            <bean:message key="string.price"/>
        </label>
        </dt>
        <dd>
            <html:text property="product.price" styleId="price" disabled="true" value=""
                       onchange="validatePrice(this)" onkeyup="validatePrice(this)"/>
            <span id="priceError" class="errors"/>
        </dd>
    </dl>
    <html:hidden property="product.category" value="${catName}"/>
    <html:hidden property="product.subcategory" value="${subcatName}"/>   
    <html:hidden property="category" value="${productForm.category}"/>
    <html:hidden property="subcategory" value="${productForm.subcategory}"/>
    <html:submit>
        <bean:message key="string.save"/>
    </html:submit>
    <input type="button" value='<bean:message key="string.cancel"/>'
           onclick="location.href = 'products.do?category=${productForm.category}&subcategory=${productForm.subcategory}'">
</html:form>