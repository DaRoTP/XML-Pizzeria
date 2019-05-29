<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Pizzas</h2>
    <xsl:for-each select="pizzeria/pizzas/pizza">
        <xsl:value-of select="pizza_name"/>
         <xsl:for-each select="pizzeria/pizzas/pizza/topping">
            <xsl:value-of select="topping"/>
         </xsl:for-each>
    </xsl:for-each>

    <h2>Bevereges</h2>
    <table border="1">
        <tr bgcolor="#9acd32">
            <th style="text-align:left">beverage name</th>
            <th style="text-align:left">price</th>
            <th style="text-align:left">units</th>
        </tr>
        <xsl:for-each select="pizzeria/beverages/beverage">
        <tr>
            <td><xsl:value-of select="beverage_name"/></td>
            <td><xsl:value-of select="price"/></td>
            <td><xsl:value-of select="units"/></td>
        </tr>
        </xsl:for-each>
    </table>

    <h2>Job Positions</h2>
    <table border="1">
        <tr bgcolor="#9acd32">
            <th style="text-align:left">Position</th>
            <th style="text-align:left">Position description</th>
        </tr>
        <xsl:for-each select="pizzeria/job_positions/job_position">
        <tr>
            <td><xsl:value-of select="position_name"/></td>
            <td><xsl:value-of select="position_description"/></td>
        </tr>
        </xsl:for-each>
    </table>

    <h2>Employees</h2>
    <table border="1">
        <tr bgcolor="#9acd32">
            <th style="text-align:left">name</th>
            <th style="text-align:left">surname</th>
            <th style="text-align:left">personal_No</th>
            <th style="text-align:left">phone_number</th>
            <th style="text-align:left">e_mail</th>
            <th style="text-align:left">employment_date</th>
            <th style="text-align:left">salary</th>
            <th style="text-align:left">positionID</th>
        </tr>
        <xsl:for-each select="pizzeria/employees/employee">
        <tr>
            <td><xsl:value-of select="name"/></td>
            <td><xsl:value-of select="surname"/></td>
            <td><xsl:value-of select="personal_No"/></td>
            <td><xsl:value-of select="phone_number"/></td>
            <td><xsl:value-of select="e_mail"/></td>
            <td><xsl:value-of select="employment_date"/></td>
            <td><xsl:value-of select="salary"/></td>
            <td><xsl:value-of select="positionID"/></td>
        </tr>
        </xsl:for-each>
    </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>

