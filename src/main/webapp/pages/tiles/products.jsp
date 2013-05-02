<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html:form action="update" onsubmit="return validateUpdate()">
    <nested:nest property="document.rootElement.children[${productForm.category}].children[${productForm.subcategory}]">
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Producer</th>
                <th>Model</th>
                <th>Date of issue</th>
                <th>Color</th>
                <th>Price/In Stock</th>
                <nested:iterate property="children" indexId="i">
                <tr>
                    <td>
                        <nested:text property="attributes[0].value" onchange="validateExists(this)"
                                     onkeyup="validateExists(this)" styleId="name_${i}"/>
                    </td>
                    <td><nested:text property="children[0].text" styleId="producer_${i}"
                                 onchange="validateExists(this)" onkeyup="validateExists(this)"/></td>
                    <td><nested:text property="children[1].text" size="4" styleId="model_${i}"
                                 onchange="validateModel(this)" onkeyup="validateModel(this)"/></td>
                    <td><nested:text property="children[2].text" size="8" styleId="date_${i}"
                                 onchange="validateDate(this)" onkeyup="validateDate(this)"/></td>
                    <td><nested:text property="children[3].text" size="8" styleId="color_${i}"
                                 onchange="validateExists(this)" onkeyup="validateExists(this)"/></td>
                    <td>
                        <nested:equal property="children[4].name" value="price">
                            <nested:text property="children[4].text" size="8" styleId="price_${i}"
                                         onchange="validatePrice(this)" onkeyup="validatePrice(this)"/>
                            <input type="checkbox" name="inStocksIds" value="${i}" 
                                   checked="checked" onchange="validateCheckBox(this)" id="inStock_${i}"/>
                        </nested:equal>
                        <nested:equal property="children[4].name" value="instock">
                            <nested:text property="children[4].text" disabled="true"
                                         onchange="validatePrice(this)" onkeyup="validatePrice(this)"
                                         style="background: #eee;" value="" size="8" styleId="price_${i}"/>
                            <input type="checkbox" name="inStocksIds" value="${i}"
                                   onchange="validateCheckBox(this)" id="inStock_${i}"/>
                        </nested:equal>
                    </td>
                </tr>
            </nested:iterate>
        </tr>
    </table>
    <script>var size =${i};</script>
    <input type="button" value='<bean:message key="string.add"/>'
           onclick="location.href = 'add.do?category=${productForm.category}&subcategory=${productForm.subcategory}'">
    <input type="button" value='<bean:message key="string.back"/>'
           onclick="location.href = 'subcategory.do?category=${productForm.category}'">
    <html:hidden property="category" value="${productForm.category}"/>
    <html:hidden property="subcategory" value="${productForm.subcategory}"/>
</nested:nest>
<html:submit>
    <bean:message key="string.update"/>
</html:submit>
</html:form>
