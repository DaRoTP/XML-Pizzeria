<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
    

   <head>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />

            <style>
            
                body{
                background-color:#CEDAC8;
                }
                .bevereges-stripes>tbody>tr:nth-child(odd)>td {
                background-color: #EDFFF5;}
                .bevereges-stripes>tbody>tr:nth-child(even)>td {
                background-color: #ACFFA8;}

                .jobpos-stripes>tbody>tr:nth-child(odd)>td {
                background-color: #FFFEF4;}
                .jobpos-stripes>tbody>tr:nth-child(even)>td {
                background-color: #FFF2AE;}

                .employee-stripes>tbody>tr:nth-child(even)>td{
                background-color: #E4E9FF;}
                .employee-stripes>tbody>tr:nth-child(odd)>td{
                background-color: #C6C3E8;}

                .pizzasDiv{
                float: left;
                display: table;
                width:25%;
                margins: 0;
                }

                .margines{
                margin: auto auto 100px auto;
                display: table;
                }

                .label-type{
                font-size: 10px;
                }
                
            </style>

</head>

    <body>
    <h2 style="text-align:center">◆ Pizzas ◆</h2>
        <hr/>
        <xsl:for-each select="pizzeria/pizzas/pizza">
            <div class="container pizzasDiv table">
                <table class="table employee-stripes table-bordered">

                    <xsl:choose>
                        <xsl:when test="@pizza_type='spicy'">
                            <tr class="bg-danger">
                                <th>
                                    <xsl:value-of select="pizza_name"/>
                                    <span class="label-type"><em>  (spicy)</em></span>
                                </th>
        
                            </tr>
                       </xsl:when>
                        <xsl:when test="@pizza_type='seafood'">
                            <tr class="bg-info">
                                <th>
                                    <xsl:value-of select="pizza_name"/>
                                    <span class="label-type"><em>  (seafood)</em></span>
                                </th>
                            </tr>
                        </xsl:when>
                        <xsl:when test="@pizza_type='mild'">
                            <tr class="bg-success">
                                <th>
                                    <xsl:value-of select="pizza_name"/>
                                    <span class="label-type"><em>  (mild)</em></span>
                                </th>
                            </tr>
                        </xsl:when>
                        <xsl:when test="@pizza_type='chicken'">
                            <tr class="bg-warning">
                                <th>
                                    <xsl:value-of select="pizza_name"/>
                                    <span class="label-type"><em>  (chicken)</em></span>
                                </th>
                            </tr>
                        </xsl:when>
                        <xsl:otherwise>
                            <tr class="bg-info">
                                <th>
                                    <xsl:value-of select="pizza_name"/>
                                </th>
                            </tr>
                        </xsl:otherwise>
                    </xsl:choose>
                    <tr class="employee-stripes">
                        <td>
                            <xsl:value-of select="topping[1]"/>
                            <span>, </span>
                            <xsl:value-of select="topping[2]"/>
                            <span>, </span>
                            <xsl:value-of select="topping[3]"/>
                            <span>, </span>
                            <xsl:value-of select="topping[4]"/>
                        </td>
                    </tr>
                    
                </table>
                
            </div>

        </xsl:for-each>


        <div class="container margines">
        <h2 style="text-align:center">◆ Bevereges ◆</h2>
        <hr/>
            <table class="table table-striped bevereges-stripes">
                <tr class="bg-success">
                    <th style="text-align:left">BEVERAGE NAME</th>
                    <th style="text-align:left">PRICE</th>
                    <th style="text-align:left">UNITS</th>
                    <th style="text-align:left">TOTAL COST</th>
                    <th style="text-align:left">STOCK</th>
                </tr>
                <xsl:for-each select="pizzeria/beverages/beverage">
                
                    <tr>
                        <td><xsl:value-of select="beverage_name"/></td>
                        <td><xsl:value-of select="price"/> <span> zł</span> </td>
                        
                        <td><xsl:value-of select="units"/></td>
                        <td>
                            <xsl:variable name="price-var" select="price"/>
                            <xsl:variable name="unit-var" select="units"/>
                            <xsl:value-of select="format-number($price-var*$unit-var,'#.00')"/>
                            <span> zł</span>
                        </td>
                        <xsl:choose>
                            <xsl:when test="units &gt; 0">
                                <td>IN STOCK</td>
                            </xsl:when>
                            <xsl:otherwise>
                                <td>OUT OF STOCK</td>
                            </xsl:otherwise>
                        </xsl:choose>
                    </tr>
                </xsl:for-each>
            </table>
        </div>

        <div class="container margines">
        <h2 style="text-align:center">◆ Job Positions ◆</h2>
        <hr/>
            <table class="table table-striped jobpos-stripes">
                <tr class="bg-warning">
                    <th style="text-align:left">POSITION</th>
                    <th style="text-align:left">DESCRIPTION</th>
                </tr>
                <xsl:for-each select="pizzeria/job_positions/job_position">
                <tr>
                    <td><xsl:value-of select="position_name"/></td>
                    <td><xsl:value-of select="position_description"/></td>
                </tr>
                </xsl:for-each>
            </table>
        </div>

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
                <th style="text-align:left">SLARY</th>
            </tr>
        </thead>
            <xsl:for-each select="pizzeria/employees/employee">
             <xsl:sort select="salary" order="descending"/>
             <xsl:choose>
                <xsl:when test="positionID[@refid='MnGG']">
                <tr style="color:green">
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="personal_No"/></td>
                    <td><xsl:value-of select="phone_number"/></td>
                    <td><xsl:value-of select="e_mail"/></td>
                    <td><xsl:value-of select="employment_date"/></td>
                    <td><xsl:value-of select="salary"/> <span> zł</span></td>
                </tr>
                </xsl:when>
                <xsl:when test="positionID[@refid='Addd']">
                <tr style="color:red">
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="personal_No"/></td>
                    <td><xsl:value-of select="phone_number"/></td>
                    <td><xsl:value-of select="e_mail"/></td>
                    <td><xsl:value-of select="employment_date"/></td>
                    <td><xsl:value-of select="salary"/> <span> zł</span></td>
                </tr>
                </xsl:when>
                <xsl:when test="positionID[@refid='CSHh']">
                <tr style="color:orange">
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="personal_No"/></td>
                    <td><xsl:value-of select="phone_number"/></td>
                    <td><xsl:value-of select="e_mail"/></td>
                    <td><xsl:value-of select="employment_date"/></td>
                    <td><xsl:value-of select="salary"/> <span> zł</span></td>
                </tr>
                </xsl:when>
                <xsl:when test="positionID[@refid='OWrr']">
                <tr style="color:blue">
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="personal_No"/></td>
                    <td><xsl:value-of select="phone_number"/></td>
                    <td><xsl:value-of select="e_mail"/></td>
                    <td><xsl:value-of select="employment_date"/></td>
                    <td><xsl:value-of select="salary"/> <span> zł</span></td>
                </tr>
                </xsl:when>
                <xsl:when test="positionID[@refid='DPss']">
                <tr style="color:purple">
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="personal_No"/></td>
                    <td><xsl:value-of select="phone_number"/></td>
                    <td><xsl:value-of select="e_mail"/></td>
                    <td><xsl:value-of select="employment_date"/></td>
                    <td><xsl:value-of select="salary"/> <span> zł</span></td>
                </tr>
                </xsl:when>
                <xsl:when test="positionID[@refid='PMkk']">
                <tr style="color:black">
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="personal_No"/></td>
                    <td><xsl:value-of select="phone_number"/></td>
                    <td><xsl:value-of select="e_mail"/></td>
                    <td><xsl:value-of select="employment_date"/></td>
                    <td><xsl:value-of select="salary"/> <span> zł</span></td>
                </tr>
                </xsl:when>
                <xsl:otherwise>
                <tr>
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="surname"/></td>
                    <td><xsl:value-of select="personal_No"/></td>
                    <td><xsl:value-of select="phone_number"/></td>
                    <td><xsl:value-of select="e_mail"/></td>
                    <td><xsl:value-of select="employment_date"/></td>
                    <td><xsl:value-of select="salary"/> <span> zł</span></td>
                </tr>
                </xsl:otherwise>
            </xsl:choose>
            </xsl:for-each>
        </table>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>

