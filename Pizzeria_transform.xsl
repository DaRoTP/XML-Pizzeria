<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
    

   <head>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />

            <style>
            
                .bevereges-stripes>tbody>tr:nth-child(odd)>td {
                background-color: #EDFFF5;
                }

                .jobpos-stripes>tbody>tr:nth-child(odd)>td {
                background-color: #FFFEF4;
                }

                .employee-stripes>tbody>tr:nth-child(odd)>td{
                background-color: #E4E9FF;
                }

                .pizzasDiv{
                float: left;
                display: table;
                width:25%;
                margins: 0;
                }
                
            </style>

</head>

    <body>
    <h2 style="text-align:center">◆ Pizzas ◆</h2>
        <hr/>
        <xsl:for-each select="pizzeria/pizzas/pizza">
            <div class="container pizzasDiv table">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th><xsl:value-of select="pizza_name"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <xsl:value-of select="topping[position()>1]"/>
                                <span>, </span>
                                <xsl:value-of select="topping[position()>2]"/>
                                <span>, </span>
                                <xsl:value-of select="topping[position()>3]"/>
                                <xsl:value-of select="topping[position()>4]"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </xsl:for-each>

        <h2 style="text-align:center">◆ Bevereges ◆</h2>
        <hr/>
        <table class="table table-striped bevereges-stripes">
            <tr class="bg-success">
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


        <h2 style="text-align:center">◆ Job Positions ◆</h2>
        <hr/>
        <table class="table table-striped jobpos-stripes">
            <tr class="bg-warning">
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

        <h2 style="text-align:center">◆ Employees ◆</h2>
        <hr/>
        <table class="table employee-stripes">
        <thead>
            <tr class="bg-info">
                <th style="text-align:left">NAME</th>
                <th style="text-align:left">SURNAME</th>
                <th style="text-align:left">PERSONAL NUM.</th>
                <th style="text-align:left">PHONE NUM.</th>
                <th style="text-align:left">E-MAIL</th>
                <th style="text-align:left">EMPLOYEMENT DATE</th>
                <th style="text-align:left">SLARY (ZŁ)</th>
            </tr>
        </thead>
            <xsl:for-each select="pizzeria/employees/employee">
            <tr>
                <td><xsl:value-of select="name"/></td>
                <td><xsl:value-of select="surname"/></td>
                <td><xsl:value-of select="personal_No"/></td>
                <td><xsl:value-of select="phone_number"/></td>
                <td><xsl:value-of select="e_mail"/></td>
                <td><xsl:value-of select="employment_date"/></td>
                <td><xsl:value-of select="salary"/></td>
            </tr>
            </xsl:for-each>
        </table>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>

