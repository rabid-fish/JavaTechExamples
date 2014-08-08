<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:import href="example-import.xsl"/>
	<xsl:include href="example-include.xsl"/>

	<xsl:template match="/root">
		<html>
			<body>
				<h1>My Contacts</h1>
				<xsl:apply-templates />
				
				<div id='data'>
					<xsl:copy-of select="*"/>
				</div>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="/root/contact">
		<p>
			<xsl:apply-templates select="firstName"/>
			<xsl:apply-templates select="lastName"/>
		</p>
	</xsl:template>

</xsl:stylesheet>
