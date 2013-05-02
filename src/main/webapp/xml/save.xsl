<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:x="http://www.epam.com/products" 
                exclude-result-prefixes="x">
    <xsl:output method="xml"/>
    
    <xsl:param name="category"/>
    <xsl:param name="subcategory"/>
    <xsl:param name="name"/>
    <xsl:param name="producer"/>
    <xsl:param name="color"/>
    <xsl:param name="model"/>
    <xsl:param name="date"/>
    <xsl:param name="price"/>
    <xsl:param name="instock"/>
        
    <xsl:template name="add">
        <xsl:element name="product" namespace="{namespace-uri()}">
            <xsl:attribute name="name">
                <xsl:value-of select="$name" />
            </xsl:attribute>
            <xsl:element name="producer" namespace="{namespace-uri()}">
                <xsl:value-of select="$producer" />
            </xsl:element>
            <xsl:element name="model" namespace="{namespace-uri()}">
                <xsl:value-of select="$model" />
            </xsl:element>
            <xsl:element name="date" namespace="{namespace-uri()}">
                <xsl:value-of select="$date" />
            </xsl:element>
            <xsl:element name="color" namespace="{namespace-uri()}">
                <xsl:value-of select="$color" />
            </xsl:element>
            <xsl:choose>
                <xsl:when test="$price='' or $price=0">
                    <xsl:element name="instock" namespace="{namespace-uri()}">
                        <xsl:value-of select="$instock" />
                    </xsl:element>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:element name="price" namespace="{namespace-uri()}">
                        <xsl:value-of select="$price" />
                    </xsl:element>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:element>
    </xsl:template>
    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>
    <xsl:template match="x:subcategory">
        <xsl:copy>
            <xsl:apply-templates select="@* | *" />
            <xsl:if test="@name=$subcategory and ../@name=$category">
                <xsl:call-template name="add" />
            </xsl:if>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>