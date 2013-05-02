<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet" type="text/css" href="style.css">
<script src="js/validation.js"></script>
    <html>
        <head><title>Struts + JDOM</title></head>
        <body>
            <div id="wrapper">
                <div id="header"> 
                    <tiles:insert attribute="header"/>
                </div>
                <section id="middle">
                    <tiles:insert attribute="body"/>
                </section>
            </div>
            <div id="footer">
                <tiles:insert attribute="footer"/>
            </div>
        </body>
    </html>