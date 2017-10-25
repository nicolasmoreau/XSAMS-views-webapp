# xsams-to-hitran
=================

Summary
-------
A RESTFUL web-application to generate file in HITRAN format from XSAMS.

The application follows the "XSAMS-consumer" standard of VAMDC.
HITRAN format follows the HITRAN2004 [1] standard with parameters provided in 160-character ".par" files.

Known issues
------------
**Molecule 11 (NH<sub>3</sub> or Ammonia):** This molecule is not well handled yet.<br />
**Molecule 34 (O):** The atomic data are not translatable for now.<br />
**Molecule 40 (CH<sub>3</sub>Br or Methyl Bromide):** This molecule is not found in VAMDC.<br />
**Molecule 49 (COCl<sub>2</sub> or Phosgene):** This molecule is not found in VAMDC.

**Molecule 38 (C<sub>2</sub>H<sub>4</sub> or Ethene):** For ethene, in the HITRAN databases the local quanta are given in the form *J* *K<sub>a</sub>* *K<sub>c</sub>*, that is the traditional asymmetric top notation. In *ECaSDa*, we use *J* *C* *α*, the tensorial formalism notation, as defined in Equation (56) of Ref. [2].


References
----------
[1] [L. S. Rothman, et al., "The HITRAN 2004 Molecular Spectroscopic Database", J. Quant. Spectrosc. Radiat. Transfer 96, 139-205 (2005).](http://hitran.org/media/refs/HITRAN-2004.pdf)<br />
[2] [W. Raballand, et al., "Spectroscopy of X<sub>2</sub>Y<sub>4</sub> (D<sub>2h</sub>) molecules: tensorial formalism adapted to the O(3) ⊃ D<sub>2h</sub> chain, Hamiltonian and transition moment operators", J. Mol. Spectrosc. 217, 239-248 (2003).](http://www.sciencedirect.com/science/article/pii/S0022285202000383/pdfft?md5=7edaa05488c3076a1e2796bf791510e0&pid=1-s2.0-S0022285202000383-main.pdf)