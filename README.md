# xsams-to-hitran
=================

Summary
-------
A RESTFUL web-application to generate file in HITRAN format from XSAMS.

The application follows the "XSAMS-consumer" standard of VAMDC.
HITRAN format follows the HITRAN2004 [1] standard with parameters provided in 160-character ".par" files.

Known issues
------------
**Molecule 11 (NH<sub>3</sub> or Ammonia)** Cannot get `l` value.<br />
**Molecule 24 (CH<sub>3</sub>Cl or Methyl Chloride)** Cannot get `l` value.

It seems that it is impossible to get this field with the current xsams schema:
```
<stcs:l>0</stcs:l>
```

**Molecule 34 (O):** The atomic data are not translatable for now.<br />
**Molecule 40 (CH<sub>3</sub>Br or Methyl Bromide):** This molecule is not found in VAMDC.<br />
**Molecule 49 (COCl<sub>2</sub> or Phosgene):** This molecule is not found in VAMDC.

**Molecule 6, iso. 3 (CH<sub>3</sub>D or Methane):**<br />
In HITRAN:<br />
- Vibrational states are describer by a character string.<br />
- Rotational quanta are the traditional `J`, `K`, `C` (`C<sub>3v</sub>`) group).

In MeCaSDa:<br />
- Vibrational  states are describes by 6 vibrational quanta and a vibrational symmetry (`D<sub>∞h</sub>` group).<br />
- Rotational quanta are given by `J`, `C` (`C<sub>3v</sub>` group) and `N`, where `N` is a numbering index. This is the tensorial formalism notation described in Ref. [2].

**Molecule 38 (C<sub>2</sub>H<sub>4</sub> or Ethene):** For ethene, in the HITRAN databases the local quanta are given in the form `J` `K<sub>a</sub>` `K<sub>c</sub>`, that is the traditional asymmetric top notation. In *ECaSDa*, we use `J` `C` `α`, the tensorial formalism notation, as defined in Equation (56) of Ref. [3].

References
----------
[1] [L. S. Rothman, et al., "The HITRAN 2004 Molecular Spectroscopic Database", J. Quant. Spectrosc. Radiat. Transfer 96, 139-205 (2005).](http://hitran.org/media/refs/HITRAN-2004.pdf)<br />
[2] [A. El Hilali, et al., "Development of the Hamiltonian and transition moment operators of symmetric top molecules using the O(3)⊃C<sub>∞v</sub>⊃C<sub>3v</sub> group chain", J. Mol. Spectrosc. 234, 176-181 (2005).](http://www.sciencedirect.com/science/article/pii/S0022285205002109/pdfft?md5=9ef183cd7e332899cb7dab9b2314189d&pid=1-s2.0-S0022285205002109-main.pdf)
[3] [W. Raballand, et al., "Spectroscopy of X<sub>2</sub>Y<sub>4</sub> (D<sub>2h</sub>) molecules: tensorial formalism adapted to the O(3)⊃D<sub>2h</sub> chain, Hamiltonian and transition moment operators", J. Mol. Spectrosc. 217, 239-248 (2003).](http://www.sciencedirect.com/science/article/pii/S0022285202000383/pdfft?md5=7edaa05488c3076a1e2796bf791510e0&pid=1-s2.0-S0022285202000383-main.pdf)<br />
