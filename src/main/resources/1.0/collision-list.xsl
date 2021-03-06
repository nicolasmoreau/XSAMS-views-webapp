<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:xsams="http://vamdc.org/xml/xsams/1.0"
  version="2.0">
  
  <xsl:import href="species-name.xsl"/>
  <xsl:include href="sources.xsl"/>
  
  <xsl:param name="id"/>
  <xsl:param name="state-list-location"/>
  <xsl:param name="state-location"/>
  <xsl:param name="line-list-location"/>
  <xsl:param name="collision-location"/>
  <xsl:param name="css-location"/>
  <xsl:param name="js-location"/>
  
  <!-- These keys are used in the identification of species, below. -->
  <xsl:key name="atomic-states" match="/xsams:XSAMSData/xsams:Species/xsams:Atoms/xsams:Atom/xsams:Isotope/xsams:Ion/xsams:AtomicState" use="@stateID"/>
  <xsl:key name="molecular-states" match="/xsams:XSAMSData/xsams:Species/xsams:Molecules/xsams:Molecule/xsams:MolecularState" use="@stateID"/>
  <xsl:key name="atomic-ions" match="/xsams:XSAMSData/xsams:Species/xsams:Atoms/xsams:Atom/xsams:Isotope/xsams:Ion" use="@speciesID"/>
  <xsl:key name="molecules" match="/xsams:XSAMSData/xsams:Species/xsams:Molecules/xsams:Molecule" use="@speciesID"/>
  <xsl:key name="particles" match="/xsams:XSAMSData/xsams:Species/xsams:Particles/xsams:Particle" use="@speciesID"/>
  <xsl:key name="sources" match="/xsams:XSAMSData/xsams:Sources/xsams:Source" use="@sourceID"/>
  
  <xsl:template match="xsams:XSAMSData">
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
        <title>Collision list</title>
        <link rel="stylesheet" type="text/css">
          <xsl:attribute name="href"><xsl:value-of select="$css-location"/></xsl:attribute>
        </link>
        <script type="text/javascript" src="{$js-location}"></script>
      </head>
      <body>
        <h1>Collisions</h1>
        
        <p>
          <xsl:text>(Switch to view of </xsl:text>
          <a>
            <xsl:attribute name="href"><xsl:value-of select="$line-list-location"/></xsl:attribute>
            <xsl:text>radiative transitions</xsl:text>
          </a>
          <xsl:text> or </xsl:text>
          <a>
            <xsl:attribute name="href"><xsl:value-of select="$state-list-location"/></xsl:attribute>
            <xsl:text>states</xsl:text>
          </a>
          
          <xsl:text>)</xsl:text>
        </p>
        <p>
          <xsl:call-template name="query-source">
            <xsl:with-param name="source" select="xsams:Sources/xsams:Source[1]"/>
          </xsl:call-template>
        </p>
        <form action="../csv/collision-list.csv" method="post" enctype="multipart/form-data" onsubmit="copyTableToFormField('t1', 't1Content');">
          <p>
            <input id="t1Content" type="hidden" name="content" value="initial"/>
            <input type="submit" value="Show table in CSV format"/>  
          </p>
        </form>
        <table id="t1">
          <tr>
            <th>ID</th>
            <th>Species</th>
            <th>Source</th>
            <th>Detail</th>
          </tr>
          <xsl:for-each select="xsams:Processes/xsams:Collisions/xsams:CollisionalTransition">
            <xsl:sort select="@id"/>
            <xsl:call-template name="collision">
              <xsl:with-param name="collision" select="."/>
            </xsl:call-template>
          </xsl:for-each>  
        </table>
      </body>
    </html>
  </xsl:template>
  
  <xsl:template name="collision">
    <xsl:param name="collision"/>
    <tr>
      <td><xsl:value-of select="@id"/></td>
      <td>
        <xsl:for-each select="$collision/xsams:Reactant">
          <xsl:call-template name="participant">
            <xsl:with-param name="participant" select="."/>
          </xsl:call-template>
          <xsl:if test="position() != last()">
            <xsl:text> + </xsl:text>
          </xsl:if>
        </xsl:for-each>
        <xsl:text> &#8594; </xsl:text>
        <xsl:for-each select="$collision/xsams:Product">
          <xsl:call-template name="participant">
            <xsl:with-param name="participant" select="."/>
          </xsl:call-template>
          <xsl:if test="position() != last()">
            <xsl:text> + </xsl:text>
          </xsl:if>
        </xsl:for-each>
      </td>
      <td>
        <xsl:call-template name="sources-short">
          <xsl:with-param name="sources" select="key('sources', $collision/xsams:SourceRef)"/>
        </xsl:call-template>
      </td>
      <td>
        <a>
          <xsl:attribute name="href">
            <xsl:value-of select="$collision-location"/>
            <xsl:text>?id=</xsl:text>
            <xsl:value-of select="$collision/@id"/>
          </xsl:attribute>
          <xsl:text>detail</xsl:text>
        </a>
      </td>
    </tr>
  </xsl:template>
  
  <xsl:template name="participant">
    <xsl:param name="participant"/>
    
    <xsl:call-template name="atomic-ion">
      <xsl:with-param name="ion" select="key('atomic-ions', xsams:SpeciesRef)"/>
      <xsl:with-param name="state" select="key('atomic-states', xsams:StateRef)"/>
      <xsl:with-param name="state-location" select="$state-location"/>
    </xsl:call-template>
    
    <xsl:call-template name="molecule">
      <xsl:with-param name="molecule" select="key('molecules', xsams:SpeciesRef)"/>
      <xsl:with-param name="state" select="key('molecular-states', xsams:StateRef)"/>
      <xsl:with-param name="state-location" select="$state-location"/>
    </xsl:call-template>
    
    <xsl:call-template name="particle">
      <xsl:with-param name="particle" select="key('particles', xsams:SpeciesRef)"/>
    </xsl:call-template>
    
  </xsl:template>
  
  <xsl:template match="text()|@*"/>
  
</xsl:stylesheet>