<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
    xmlns:xsams="http://vamdc.org/xml/xsams/0.3"
    xmlns:nltcs="http://vamdc.org/xml/xsams/0.3/cases/nltcs"
    xmlns:ltcs="http://vamdc.org/xml/xsams/0.3/cases/ltcs"
    xmlns:dcs="http://vamdc.org/xml/xsams/0.3/cases/dcs"
    xmlns:hunda="http://vamdc.org/xml/xsams/0.3/cases/hunda"
    xmlns:hundb="http://vamdc.org/xml/xsams/0.3/cases/hundb"
    xmlns:stcs="http://vamdc.org/xml/xsams/0.3/cases/stcs"
    xmlns:lpcs="http://vamdc.org/xml/xsams/0.3/cases/lpcs"
    xmlns:asymcs="http://vamdc.org/xml/xsams/0.3/cases/asymcs"
    xmlns:asymos="http://vamdc.org/xml/xsams/0.3/cases/asymos"
    xmlns:sphcs="http://vamdc.org/xml/xsams/0.3/cases/sphcs"
    xmlns:sphos="http://vamdc.org/xml/xsams/0.3/cases/sphos"
    xmlns:ltos="http://vamdc.org/xml/xsams/0.3/cases/ltos"
    xmlns:lpos="http://vamdc.org/xml/xsams/0.3/cases/lpos"
    xmlns:nltos="http://vamdc.org/xml/xsams/0.3/cases/nltos">
    
  <xsl:output method="xml" encoding="UTF-8" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" />
  
  <xsl:param name="id"/>
  <xsl:param name="css-location"/>
    
  <xsl:template match="xsams:XSAMSData">
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
        <title>Single-state view of XSAMS</title>
        <link rel="stylesheet" type="text/css">
          <xsl:attribute name="href"><xsl:value-of select="$css-location"/></xsl:attribute>
        </link>
      </head>
      <body>
        <h1>Single-state view of XSAMS</h1>
        <xsl:if test="xsams:Sources/xsams:Source[1]/xsams:Comments">
          <p><xsl:value-of select="xsams:Sources/xsams:Source[1]/@sourceID"/></p>
        </xsl:if>
        <xsl:apply-templates/>
      </body>
    </html>
  </xsl:template>
    
    <xsl:template match="xsams:Molecule">
      <xsl:if test="xsams:MolecularState[@stateID=$id]">
        <h2>Species</h2>
        <p>
          <xsl:value-of select="descendant::xsams:InChI"/>
          <xsl:text> (</xsl:text>
          <xsl:value-of select="descendant::xsams:InChIKey"/>
          <xsl:text>)</xsl:text>
        </p>
        <xsl:apply-templates/>
      </xsl:if>
    </xsl:template>
   
    
    <xsl:template match="xsams:MolecularChemicalSpecies/xsams:ChemicalName/xsams:Value">
        <p>
            <xsl:text>Molecule name: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:MolecularChemicalSpecies/xsams:OrdinaryStructuralFormula/xsams:Value">
        <p>
            <xsl:text>Structural formula: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:MolecularChemicalSpecies/xsams:StoichiometricFormula">
        <p>
            <xsl:text>Stoichiometric formula: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Atom">
      <xsl:if test="xsams:Isotope/xsams:Ion/xsams:AtomicState[@stateID=$id]">
        <h2>Species</h2>
        <p>
            <xsl:choose>
                <xsl:when test="xsams:Isotope/xsams:IsotopeParameters/xsams:MassNumber">
                    <xsl:text>Isotope: </xsl:text>
                    <sup><xsl:value-of select="xsams:Isotope/xsams:IsotopeParameters/xsams:MassNumber"/></sup>
                    <xsl:value-of select="xsams:ChemicalElement/xsams:ElementSymbol"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>Element: </xsl:text>
                    <xsl:value-of select="xsams:ChemicalElement/xsams:ElementSymbol"/>
                    <xsl:text> (isotope unspecified)</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </p>
        <p>
          <xsl:value-of select="descendant::xsams:InChI"/>
          <xsl:if test="descendant::xsams:InChIKey">
            <xsl:text> (</xsl:text>
            <xsl:value-of select="descendant::xsams:InChIKey"/>
            <xsl:text>)</xsl:text>
          </xsl:if>
        </p>
        <xsl:if test="xsams:Isotope/xsams:IsotopeParameters/xsams:NuclearSpin">
          <p>
            <xsl:text> Nuclear spin: </xsl:text>
           <xsl:value-of select="xsams:Isotope/xsams:IsotopeParameters/xsams:NuclearSpin"/>
          </p>
        </xsl:if>
        <xsl:apply-templates/>
      </xsl:if>
    </xsl:template>
    
    <xsl:template match="xsams:Isotope">
      <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="xsams:Ion">
      <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="xsams:IonCharge">
        <p>
            <xsl:text>Ion charge: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:AtomicState|xsams:MolecularState">
      <xsl:if test="@stateID=$id">
        <h2>State</h2>
        <p>
            <xsl:text>State description: </xsl:text>
            <xsl:value-of select="xsams:Description"/>
        </p>
        <xsl:apply-templates/>
      </xsl:if>
    </xsl:template>
    
    <xsl:template match="xsams:StateEnergy">
        <p>
            <xsl:text>Energy above ground state: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:IonizationEnergy">
        <p>
            <xsl:text>Ionization energy: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:LandeFactor">
        <p>
            <xsl:text>Lande factor: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:QuantumDefect">
        <p>
            <xsl:text>Quantum defect: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Lifetime">
        <p>
            <xsl:text>Lifetime: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Polarizability">
        <p>
            <xsl:text>Polarizability: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:StatisticalWeight">
        <p>
            <xsl:text>Statistical weight: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:HyperfineConstantA">
        <p>
            <xsl:text>Hyperfine constant A: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:HyperfineConstantB">
        <p>
            <xsl:text>Hyperfine constant B: </xsl:text>
            <xsl:call-template name="quantity-with-unit">
                <xsl:with-param name="quantity" select="."/>
            </xsl:call-template>
        </p>
    </xsl:template>
    
  <xsl:template match="xsams:AtomicQuantumNumbers">
    <p>
      <xsl:text>Quantum numbers for entire state: </xsl:text>
      <xsl:for-each select="xsams:TotalAngularMomentum"><i>J</i><xsl:text> = </xsl:text><xsl:value-of select="."/><xsl:text> </xsl:text></xsl:for-each>
      <xsl:for-each select="xsams:Kappa"><i>&#954;</i><xsl:text> = </xsl:text><xsl:value-of select="."/><xsl:text> </xsl:text></xsl:for-each>
      <xsl:for-each select="xsams:Parity"><xsl:text>parity = </xsl:text><xsl:value-of select="."/><xsl:text> </xsl:text></xsl:for-each>
      <xsl:for-each select="xsams:HyperfineMomentum"><i>F</i><xsl:text> = </xsl:text><xsl:value-of select="."/><xsl:text> </xsl:text></xsl:for-each>
      <xsl:for-each select="xsams:MagneticQuantumNumber"><i>m</i><xsl:text> = </xsl:text><xsl:value-of select="."/><xsl:text> </xsl:text></xsl:for-each>
    </p>  
  </xsl:template>
  
    
  <xsl:template match="xsams:AtomicComposition/xsams:Component">
    <p>
      <xsl:text>Electronic composition: </xsl:text>
      <xsl:for-each select="xsams:Configuration/xsams:ConfigurationLabel"><xsl:value-of select="."/><xsl:text> </xsl:text></xsl:for-each>
      <xsl:for-each select="xsams:Configuration/xsams:AtomicCore/xsams:ElementCore"><xsl:text>[</xsl:text><xsl:value-of select="."/><xsl:text>] </xsl:text></xsl:for-each>
      <xsl:for-each select="xsams:Configuration/xsams:Shells/xsams:Shell">
        <xsl:value-of select="xsams:PrincipalQuantumNumber"/>
        <xsl:choose>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=0"><xsl:text>s</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=1"><xsl:text>p</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=2"><xsl:text>d</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=3"><xsl:text>f</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=4"><xsl:text>g</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=5"><xsl:text>h</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=6"><xsl:text>i</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=7"><xsl:text>k</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=8"><xsl:text>l</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=9"><xsl:text>m</xsl:text></xsl:when>
          <xsl:when test="xsams:OrbitalAngularMomentum/xsams:Value=10"><xsl:text>n</xsl:text></xsl:when>
        </xsl:choose>
        <sup><xsl:value-of select="xsams:NumberOfElectrons"/></sup>
        <xsl:text> </xsl:text>
      </xsl:for-each>
      <xsl:for-each select="xsams:Term/xsams:LS">
        <sup><xsl:value-of select="(xsams:S*2)+1"/></sup>
        <xsl:choose>
          <xsl:when test="xsams:L/xsams:Value=0"><xsl:text>S</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=1"><xsl:text>P</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=2"><xsl:text>D</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=3"><xsl:text>F</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=4"><xsl:text>G</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=5"><xsl:text>H</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=6"><xsl:text>I</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=7"><xsl:text>K</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=8"><xsl:text>L</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=9"><xsl:text>M</xsl:text></xsl:when>
          <xsl:when test="xsams:L/xsams:Value=10"><xsl:text>N</xsl:text></xsl:when>
        </xsl:choose>
        <sub><xsl:value-of select="../../../../xsams:AtomicQuantumNumbers/xsams:TotalAngularMomentum"/></sub>
      </xsl:for-each>
    </p>
  </xsl:template>
    
    <xsl:template match="xsams:TotalStatisticalWeight">
        <p>
            <xsl:text>Total statistical weight: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:NuclearStatisticalWeight">
        <p>
            <xsl:text>Nuclear statistical weight: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:NuclearSpinIsomer">
        <p>
            <xsl:text>Nuclear-spin isomer: </xsl:text>
            <xsl:value-of select="."/>
        </p>
    </xsl:template>
    
  <xsl:template match="xsams:Case[@caseID='nltcs']">
    <p>
      <xsl:text>Quantum description of state as </xsl:text>
      <a href="http://www.vamdc.eu/documents/cbc-0.3/nltcs-0.3.html">closed-shell, non-linear, triatomic molecule</a>
      <xsl:text>: </xsl:text>
      <xsl:apply-templates/>
    </p>
  </xsl:template>
  
  <xsl:template match="xsams:Case[@caseID='ltcs']">
    <p>
      <xsl:text>Quantum description of state as </xsl:text>
      <a href="http://www.vamdc.eu/documents/cbc-0.3/ltcs-0.3.html">closed-shell, linear, triatomic molecule</a>
      <xsl:text>: </xsl:text>
      <xsl:apply-templates/>
    </p>
  </xsl:template>
  
  <xsl:template match="xsams:Case[@caseID='dcs']">
    <p>
      <xsl:text>Quantum description of state as </xsl:text>
      <a href="http://www.vamdc.eu/documents/cbc-0.3/dcs-0.3.html">closed-shell, diatomic molecule</a>
      <xsl:text>: </xsl:text>
      <xsl:apply-templates/>
    </p>
  </xsl:template>
  
  <xsl:template match="xsams:Case[@caseID='hunda']">
    <p>
      <xsl:text>Quantum description of state as </xsl:text>
      <a href="http://www.vamdc.eu/documents/cbc-0.3/hunda-0.3.html">open-shell, Hund's case (a) molecule</a>
      <xsl:text>: </xsl:text>
      <xsl:apply-templates/>
    </p>
  </xsl:template>
  
  <xsl:template match="xsams:Case[@caseID='hundb']">
    <p>
      <xsl:text>Quantum description of state as </xsl:text>
      <a href="http://www.vamdc.eu/documents/cbc-0.3/hundb-0.3.html">open-shell, Hund's case (b) molecule</a>
      <xsl:text>: </xsl:text>
      <xsl:apply-templates/>
    </p>
  </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='stcs']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/stcs-0.3.html">Quantum description of state as closed-shell, symmetric top molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="stcs:QNs/stcs:ElecStateLabel"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="stcs:QNs/stcs:vi"/>
            <xsl:text>, li=</xsl:text><xsl:value-of select="stcs:QNs/stcs:li"/>
            <xsl:text>, vibrational inversion-partity =</xsl:text><xsl:value-of select="stcs:QNs/stcs:vibInv"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="stcs:QNs/stcs:vibSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="stcs:QNs/stcs:J"/>
            <xsl:text>, K=</xsl:text><xsl:value-of select="stcs:QNs/stcs:K"/>
            <xsl:text>, rotational symmetry=</xsl:text><xsl:value-of select="stcs:QNs/stcs:rotSym"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="stcs:QNs/stcs:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="stcs:QNs/stcs:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="stcs:QNs/stcs:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="stcs:QNs/stcs:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='lpcs']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/lpcs-0.3.html">Quantum description of state as closed-shell, linear, polyatomic  molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:ElecStateLabel"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:vi"/>
            <xsl:text>, li=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:li"/>
            <xsl:text>, l=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:l"/>
            <xsl:text>, vibrational inversion-partity =</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:vibInv"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:vibRefl"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:J"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:parity"/>
            <xsl:text>, Kronig parity=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:kronigParity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:asSym"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='asymcs']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/asymcs-0.3.html">Quantum description of state as closed-shell, asymmetric top molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:ElecStateLabel"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:vi"/>
            <xsl:text>, vibrational inversion-partity =</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:vibInv"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:vibRefl"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:J"/>
            <xsl:text>, Ka=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:Ka"/>
            <xsl:text>, Kc=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:Kc"/>
            <xsl:text>, rotational symmetry=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:rotSym"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='asymos']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/asymos-0.3.html">Quantum description of state as open-shell, asymmetric top molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="asymos:QNs/asymos:ElecStateLabel"/>
            <xsl:text>, electronic symmetry=</xsl:text><xsl:value-of select="asymos:QNs/asymos:elecSym"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="asymos:QNs/asymos:elecInv"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="asymos:QNs/asymos:S"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="asymos:QNs/asymos:vi"/>
            <xsl:text>, vibrational inversion-partity =</xsl:text><xsl:value-of select="asymos:QNs/asymos:vibInv"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="asymos:QNs/asymos:vibRefl"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="asymos:QNs/asymos:N"/>
            <xsl:text>, Ka=</xsl:text><xsl:value-of select="asymos:QNs/asymos:Ka"/>
            <xsl:text>, Kc=</xsl:text><xsl:value-of select="asymos:QNs/asymos:Kc"/>
            <xsl:text>, rotational symmetry=</xsl:text><xsl:value-of select="asymos:QNs/asymos:rotSym"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="asymos:QNs/asymos:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="asymos:QNs/asymos:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="asymos:QNs/asymos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="asymos:QNs/asymos:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='sphcs']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/sphcs-0.3.html">Quantum description of state as closed-shell, spherical top molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:ElecStateLabel"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:vi"/>
            <xsl:text>, li=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:li"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:J"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='sphos']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/sphos-0.3.html">Quantum description of state as open-shell, spherical top molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="sphos:QNs/sphos:ElecStateLabel"/>
            <xsl:text>, electronic symmetry=</xsl:text><xsl:value-of select="sphos:QNs/sphos:elecSym"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="sphos:QNs/sphos:elecInv"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="sphos:QNs/sphos:S"/>
            <xsl:text>, v1=</xsl:text><xsl:value-of select="sphos:QNs/sphos:vi"/>
            <xsl:text>, l1=</xsl:text><xsl:value-of select="sphos:QNs/sphos:li"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="sphos:QNs/sphos:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="sphos:QNs/sphos:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="sphos:QNs/sphos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="sphos:QNs/sphos:N"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="sphos:QNs/sphos:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="sphos:QNs/sphos:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="sphos:QNs/sphos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="sphos:QNs/sphos:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='ltos']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/ltos-0.3.html">Quantum description of state as open-shell, linear, triatomic molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="ltos:QNs/ltos:ElecStateLabel"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:elecInv"/>
            <xsl:text>, electronic reflection-parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:elecRefl"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="ltos:QNs/ltos:S"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="ltos:QNs/ltos:N"/>
            <xsl:text>, v1=</xsl:text><xsl:value-of select="ltos:QNs/ltos:v1"/>
            <xsl:text>, v2=</xsl:text><xsl:value-of select="ltos:QNs/ltos:v2"/>
            <xsl:text>, v3=</xsl:text><xsl:value-of select="ltos:QNs/ltos:v3"/>
            <xsl:text>, l2=</xsl:text><xsl:value-of select="ltos:QNs/ltos:l2"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="ltos:QNs/ltos:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="ltos:QNs/ltos:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="ltos:QNs/ltos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="ltos:QNs/ltos:N"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="ltos:QNs/ltos:I"/>
            <xsl:text>, F1=</xsl:text><xsl:value-of select="ltos:QNs/ltos:F1"/>
            <xsl:text>, F2=</xsl:text><xsl:value-of select="ltos:QNs/ltos:F2"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="ltos:QNs/ltos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:parity"/>
            <xsl:text>, Kronig parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:kronigParity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="ltos:QNs/ltos:asSym"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='lpos']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/lpos-0.3.html">Quantum description of state as open-shell, linear, polyatomic molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="lpos:QNs/lpos:ElecStateLabel"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:elecInv"/>
            <xsl:text>, electronic reflection-parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:elecRefl"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="lpos:QNs/lpos:S"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="lpos:QNs/lpos:N"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="lpos:QNs/lpos:vi"/>
            <xsl:text>, li=</xsl:text><xsl:value-of select="lpos:QNs/lpos:li"/>
            <xsl:text>, l=</xsl:text><xsl:value-of select="lpos:QNs/lpos:l"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="lpos:QNs/lpos:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="lpos:QNs/lpos:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="lpos:QNs/lpos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="lpos:QNs/lpos:N"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="lpos:QNs/lpos:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="lpos:QNs/lpos:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="lpos:QNs/lpos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:parity"/>
            <xsl:text>, Kronig parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:kronigParity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="lpos:QNs/lpos:asSym"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='nltos']">
        <p>
            <a href="http://www.vamdc.eu/documents/cbc-0.3/nltos-0.3.html">Quantum description of state as open-shell, non-linear, triatomic molecule: </a>
            <xsl:text>Label=</xsl:text><xsl:value-of select="nltos:QNs/nltos:ElecStateLabel"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="nltos:QNs/nltos:elecInv"/>
            <xsl:text>, electronic reflection-parity=</xsl:text><xsl:value-of select="nltos:QNs/nltos:elecRefl"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="nltos:QNs/nltos:S"/>
            <xsl:text>, v1=</xsl:text><xsl:value-of select="nltos:QNs/nltos:v1"/>
            <xsl:text>, v2=</xsl:text><xsl:value-of select="nltos:QNs/nltos:v2"/>
            <xsl:text>, v3=</xsl:text><xsl:value-of select="nltos:QNs/nltos:v3"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="nltos:QNs/nltos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="nltos:QNs/nltos:N"/>
            <xsl:text>, Ka=</xsl:text><xsl:value-of select="nltos:QNs/nltos:Ka"/>
            <xsl:text>, Kc=</xsl:text><xsl:value-of select="nltos:QNs/nltos:Kc"/>
            <xsl:text>, F1=</xsl:text><xsl:value-of select="nltos:QNs/nltos:F1"/>
            <xsl:text>, F2=</xsl:text><xsl:value-of select="nltos:QNs/nltos:F2"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="nltos:QNs/nltos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="nltos:QNs/nltos:parity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="nltos:QNs/nltos:asSym"/>
        </p>
    </xsl:template>
    
    <xsams:Case>
    <xsl:apply-templates/>
  </xsams:Case>
       
  <xsl:template match="nltcs:QNs">
    <xsl:apply-templates/>
  </xsl:template>
  <xsl:template match="nltcs:ElecStateLabel">
    <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:v1">
    <i>v</i><sub>1</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:v2">
    <i>v</i><sub>2</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:v3">
    <i>v</i><sub>3</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:J">
    <i>J</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:Ka">
    <i>K</i><sub>a</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:Kc">
    <i>K</i><sub>c</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:F1">
    <i>F</i><sub>1</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:F2">
    <i>F</i><sub>2</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:F">
    <i>F</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:parity">
    <xsl:text>parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="nltcs:asSym">
    <xsl:text>symmetry</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  
  <xsl:template match="ltcs:QNs">
    <xsl:apply-templates/>
  </xsl:template>
  <xsl:template match="ltcs:ElecStateLabel">
    <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:v1">
    <i>v</i><sub>1</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:v2">
    <i>v</i><sub>2</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:v3">
    <i>v</i><sub>3</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:l2">
    <i>l</i><sub>2</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:J">
    <i>J</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:Ka">
    <i>K</i><sub>a</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:Kc">
    <i>K</i><sub>c</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:F1">
    <i>F</i><sub>1</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:F2">
    <i>F</i><sub>2</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:F">
    <i>F</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:parity">
    <xsl:text>parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:kronigParity">
    <xsl:text>Kronig parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="ltcs:asSym">
    <xsl:text>symmetry</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  
  <xsl:template match="dcs:QNs">
    <xsl:apply-templates/>
  </xsl:template>
  <xsl:template match="dcs:ElecStateLabel">
    <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="dcs:v">
    <i>v</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="dcs:l">
    <i>l</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="dcs:J">
    <i>J</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="dcs:F1">
    <i>F</i><sub>1</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="dcs:parity">
    <xsl:text>parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="dcs:asSym">
    <xsl:text>symmetry</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  
  <xsl:template match="hunda:QNs">
    <xsl:apply-templates/>
  </xsl:template>
  <xsl:template match="hunda:ElecStateLabel">
    <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:v">
    <i>v</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:Lambda">
    <xsl:text>|Λ|</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:Sigma">
    <xsl:text>|Σ|</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:Omega">
    <xsl:text>Ω</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:J">
    <i>J</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:S">
    <i>S</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:F1">
    <i>F</i><sub>1</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:F">
    <i>F</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:parity">
    <xsl:text>parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:kronigParity">
    <xsl:text>Kronig parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:elecInv">
    <xsl:text>inversion parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:reflectInv">
    <xsl:text>reflection parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hunda:asSym">
    <xsl:text>symmetry</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  
  <xsl:template match="hundb:QNs">
    <xsl:apply-templates></xsl:apply-templates>
  </xsl:template>
  <xsl:template match="hundb:ElecStateLabel">
    <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:v">
    <i>v</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:Lambda">
    <xsl:text>|Λ|</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:Omega">
    <xsl:text>Ω</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:J">
    <i>J</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:S">
    <i>S</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:N">
    <i>N</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:F1">
    <i>F</i><sub>1</sub><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:F">
    <i>F</i><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:SpinComponentLabel">
    <xsl:text>spin component</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:parity">
    <xsl:text>parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:kronigParity">
    <xsl:text>Kronig parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:elecInv">
    <xsl:text>inversion parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:reflectInv">
    <xsl:text>reflection parity</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="hundb:asSym">
    <xsl:text>symmetry</xsl:text><xsl:text> = </xsl:text> <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
    
  <xsl:template match="stcs:ElectStateLabel">
    <xsl:value-of select="."/><xsl:text> </xsl:text>
  </xsl:template>
  <xsl:template match="stcs:vi">
    <i>v</i><sub><xsl:value-of select="@mode"/></sub><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:li">
    <i>l</i><sub><xsl:value-of select="@mode"/></sub><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:vibInv">
    <i>vibInv</i><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:vibSym">
    <i>vibSym</i><sub><xsl:value-of select="@group"/></sub><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:J">
    <i>J</i><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:K">
    <i>K</i><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:rotSym">
    <i>rotSym</i><sub><xsl:value-of select="@group"/></sub><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:I">
    <i>I</i><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:Fj">
    <i>F</i><sub><xsl:value-of select="@j"/></sub><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:F">
    <i>F</i><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:r">
    <i>r</i><xsl:text> = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  <xsl:template match="stcs:parity">
    <xsl:text>parity = </xsl:text><xsl:value-of select="."/>
  </xsl:template>
  
    
    <xsl:template match="xsams:Case[@caseID='lpcs']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:ElecStateLabel"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:vi"/>
            <xsl:text>, li=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:li"/>
            <xsl:text>, l=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:l"/>
            <xsl:text>, vibrational inversion-partity =</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:vibInv"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:vibRefl"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:J"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:parity"/>
            <xsl:text>, Kronig parity=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:kronigParity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="lpcs:QNs/lpcs:asSym"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='asymcs']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:ElecStateLabel"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:vi"/>
            <xsl:text>, vibrational inversion-partity =</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:vibInv"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:vibRefl"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:J"/>
            <xsl:text>, Ka=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:Ka"/>
            <xsl:text>, Kc=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:Kc"/>
            <xsl:text>, rotational symmetry=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:rotSym"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="asymcs:QNs/asymcs:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='asymos']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="asymos:QNs/asymos:ElecStateLabel"/>
            <xsl:text>, electronic symmetry=</xsl:text><xsl:value-of select="asymos:QNs/asymos:elecSym"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="asymos:QNs/asymos:elecInv"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="asymos:QNs/asymos:S"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="asymos:QNs/asymos:vi"/>
            <xsl:text>, vibrational inversion-partity =</xsl:text><xsl:value-of select="asymos:QNs/asymos:vibInv"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="asymos:QNs/asymos:vibRefl"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="asymos:QNs/asymos:N"/>
            <xsl:text>, Ka=</xsl:text><xsl:value-of select="asymos:QNs/asymos:Ka"/>
            <xsl:text>, Kc=</xsl:text><xsl:value-of select="asymos:QNs/asymos:Kc"/>
            <xsl:text>, rotational symmetry=</xsl:text><xsl:value-of select="asymos:QNs/asymos:rotSym"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="asymos:QNs/asymos:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="asymos:QNs/asymos:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="asymos:QNs/asymos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="asymos:QNs/asymos:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='sphcs']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:ElecStateLabel"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:vi"/>
            <xsl:text>, li=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:li"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:J"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="sphcs:QNs/sphcs:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='sphos']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="sphos:QNs/sphos:ElecStateLabel"/>
            <xsl:text>, electronic symmetry=</xsl:text><xsl:value-of select="sphos:QNs/sphos:elecSym"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="sphos:QNs/sphos:elecInv"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="sphos:QNs/sphos:S"/>
            <xsl:text>, v1=</xsl:text><xsl:value-of select="sphos:QNs/sphos:vi"/>
            <xsl:text>, l1=</xsl:text><xsl:value-of select="sphos:QNs/sphos:li"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="sphos:QNs/sphos:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="sphos:QNs/sphos:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="sphos:QNs/sphos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="sphos:QNs/sphos:N"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="sphos:QNs/sphos:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="sphos:QNs/sphos:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="sphos:QNs/sphos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="sphos:QNs/sphos:parity"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='ltos']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="ltos:QNs/ltos:ElecStateLabel"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:elecInv"/>
            <xsl:text>, electronic reflection-parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:elecRefl"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="ltos:QNs/ltos:S"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="ltos:QNs/ltos:N"/>
            <xsl:text>, v1=</xsl:text><xsl:value-of select="ltos:QNs/ltos:v1"/>
            <xsl:text>, v2=</xsl:text><xsl:value-of select="ltos:QNs/ltos:v2"/>
            <xsl:text>, v3=</xsl:text><xsl:value-of select="ltos:QNs/ltos:v3"/>
            <xsl:text>, l2=</xsl:text><xsl:value-of select="ltos:QNs/ltos:l2"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="ltos:QNs/ltos:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="ltos:QNs/ltos:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="ltos:QNs/ltos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="ltos:QNs/ltos:N"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="ltos:QNs/ltos:I"/>
            <xsl:text>, F1=</xsl:text><xsl:value-of select="ltos:QNs/ltos:F1"/>
            <xsl:text>, F2=</xsl:text><xsl:value-of select="ltos:QNs/ltos:F2"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="ltos:QNs/ltos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:parity"/>
            <xsl:text>, Kronig parity=</xsl:text><xsl:value-of select="ltos:QNs/ltos:kronigParity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="ltos:QNs/ltos:asSym"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='lpos']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="lpos:QNs/lpos:ElecStateLabel"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:elecInv"/>
            <xsl:text>, electronic reflection-parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:elecRefl"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="lpos:QNs/lpos:S"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="lpos:QNs/lpos:N"/>
            <xsl:text>, vi=</xsl:text><xsl:value-of select="lpos:QNs/lpos:vi"/>
            <xsl:text>, li=</xsl:text><xsl:value-of select="lpos:QNs/lpos:li"/>
            <xsl:text>, l=</xsl:text><xsl:value-of select="lpos:QNs/lpos:l"/>
            <xsl:text>, vibrational symmetry =</xsl:text><xsl:value-of select="lpos:QNs/lpos:vibRefl"/>
            <xsl:text>, rotational symmetry =</xsl:text><xsl:value-of select="lpos:QNs/lpos:rotSym"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="lpos:QNs/lpos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="lpos:QNs/lpos:N"/>
            <xsl:text>, I=</xsl:text><xsl:value-of select="lpos:QNs/lpos:I"/>
            <xsl:text>, Fj=</xsl:text><xsl:value-of select="lpos:QNs/lpos:Fj"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="lpos:QNs/lpos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:parity"/>
            <xsl:text>, Kronig parity=</xsl:text><xsl:value-of select="lpos:QNs/lpos:kronigParity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="lpos:QNs/lpos:asSym"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:Case[@caseID='nltos']">
        <p>
            <xsl:text>Label=</xsl:text><xsl:value-of select="nltos:QNs/nltos:ElecStateLabel"/>
            <xsl:text>, electronic inversion-parity=</xsl:text><xsl:value-of select="nltos:QNs/nltos:elecInv"/>
            <xsl:text>, electronic reflection-parity=</xsl:text><xsl:value-of select="nltos:QNs/nltos:elecRefl"/>
            <xsl:text>, S=</xsl:text><xsl:value-of select="nltos:QNs/nltos:S"/>
            <xsl:text>, v1=</xsl:text><xsl:value-of select="nltos:QNs/nltos:v1"/>
            <xsl:text>, v2=</xsl:text><xsl:value-of select="nltos:QNs/nltos:v2"/>
            <xsl:text>, v3=</xsl:text><xsl:value-of select="nltos:QNs/nltos:v3"/>
            <xsl:text>, J=</xsl:text><xsl:value-of select="nltos:QNs/nltos:J"/>
            <xsl:text>, N=</xsl:text><xsl:value-of select="nltos:QNs/nltos:N"/>
            <xsl:text>, Ka=</xsl:text><xsl:value-of select="nltos:QNs/nltos:Ka"/>
            <xsl:text>, Kc=</xsl:text><xsl:value-of select="nltos:QNs/nltos:Kc"/>
            <xsl:text>, F1=</xsl:text><xsl:value-of select="nltos:QNs/nltos:F1"/>
            <xsl:text>, F2=</xsl:text><xsl:value-of select="nltos:QNs/nltos:F2"/>
            <xsl:text>, F=</xsl:text><xsl:value-of select="nltos:QNs/nltos:F"/>
            <xsl:text>, parity=</xsl:text><xsl:value-of select="nltos:QNs/nltos:parity"/>
            <xsl:text>, as-symmetry=</xsl:text><xsl:value-of select="nltos:QNs/nltos:asSym"/>
        </p>
    </xsl:template>
    
    <xsl:template match="xsams:PartitionFunction">
        <p>
            <xsl:text>Partitionfunction: &#xa;</xsl:text>
            <xsl:text>T: </xsl:text>
            <xsl:value-of select="xsams:T/xsams:DataList"/>
            <xsl:text>&#xa;Q: </xsl:text>
            <xsl:value-of select="xsams:Q/xsams:DataList"/>
            <xsl:text>&#xa;</xsl:text>
        </p>
    </xsl:template>
    
    
    <xsl:template name="quantity-with-unit">
        <xsl:param name="quantity"/>
        <xsl:value-of select="xsams:Value"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="xsams:Value/@units"/>
    </xsl:template>
    
    <xsl:template match="@*|text()"/>
    
</xsl:stylesheet>
