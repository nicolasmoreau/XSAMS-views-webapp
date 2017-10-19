package eu.vamdc.hitran;

import java.util.HashMap;
import java.util.Map;

public class TableGlobalQuantaID {
	private Map<String, Integer> TableGlobalQ = new HashMap<String, Integer>();

	/**
	 * Do the mapping of the global Q identification number. This code is
	 * assigned to molecules in order to sort them.This operation is needed
	 * before doing anything else.
	 */

	public void doMapping() {
		/**
		 * Class 1: Diatomic molecules
		 */
		/* Carbon Monoxide */
		TableGlobalQ.put("UGFAIRIUMAVXCW-RGIGPVFXSA-N", 1);
		TableGlobalQ.put("UGFAIRIUMAVXCW-ZDOIIHCHSA-N", 1);
		TableGlobalQ.put("UGFAIRIUMAVXCW-HQMMCQRPSA-N", 1);
		TableGlobalQ.put("UGFAIRIUMAVXCW-VQEHIDDOSA-N", 1);
		TableGlobalQ.put("UGFAIRIUMAVXCW-UHFFFAOYSA-N", 1);
		TableGlobalQ.put("UGFAIRIUMAVXCW-OUBTZVSYSA-N", 1);
		/* Hydrogen Fluoride */
		TableGlobalQ.put("KRHYYFGTRYWZRS-UHFFFAOYSA-N", 1);
		/* Hydrogen Chloride */
		TableGlobalQ.put("VEXZGXHMUGYJMC-NJFSPNSNSA-N", 1);
		TableGlobalQ.put("VEXZGXHMUGYJMC-UHFFFAOYSA-N", 1);
		/* Hydrogen Bromide */
		TableGlobalQ.put("CPELXLSAUQHCOX-BJUDXGSMSA-N", 1);
		TableGlobalQ.put("CPELXLSAUQHCOX-OUBTZVSYSA-N", 1);
		TableGlobalQ.put("CPELXLSAUQHCOX-UHFFFAOYSA-N", 1);
		/* Hydrogen Iodide */
		TableGlobalQ.put("XMBWDFGMSWQBCA-UHFFFAOYSA-N", 1);
		/* Molecular Nitrogen */
		TableGlobalQ.put("IJGRMHOSHXDMSA-UHFFFAOYSA-N", 1);
		/* Nitric Oxide Cation */
		TableGlobalQ.put("KEJOCWOXCDWNID-UHFFFAOYSA-N", 1);
		/**
		 * Class 2: Diatmic molecules with different electronic levels
		 */
		/* Molecular Oxygen */
		TableGlobalQ.put("MYMOFIZGZYHOMD-UHFFFAOYSA-N", 2);
		TableGlobalQ.put("MYMOFIZGZYHOMD-NJFSPNSNSA-N", 2);
		TableGlobalQ.put("MYMOFIZGZYHOMD-OUBTZVSYSA-N", 2);
		/**
		 * Class 3: Diatomic molecules with doublet-Pi electronic state
		 */
		/* Nitric Oxide */
		TableGlobalQ.put("MWUXSHHQAYIFBG-HQMMCQRPSA-N", 3);
		TableGlobalQ.put("MWUXSHHQAYIFBG-UHFFFAOYSA-N", 3);
		TableGlobalQ.put("MWUXSHHQAYIFBG-OUBTZVSYSA-N", 3);
		/* Hydroxyl Radical */
		TableGlobalQ.put("TUJKJAMUKRIRHC-MICDWDOJSA-N", 3);
		TableGlobalQ.put("TUJKJAMUKRIRHC-UHFFFAOYSA-N", 3);
		TableGlobalQ.put("TUJKJAMUKRIRHC-NJFSPNSNSA-N", 3);
		/* Chlorine Monoxide */
		TableGlobalQ.put("NHYCGSASNAIGLD-UHFFFAOYSA-N", 3);
		TableGlobalQ.put("NHYCGSASNAIGLD-NJFSPNSNSA-N", 3);
		/**
		 * Class 4: Linear triatomic
		 */
		/* Nitrous Oxide */
		TableGlobalQ.put("GQPLMRYTRLFLPF-VQEHIDDOSA-N", 4);
		TableGlobalQ.put("GQPLMRYTRLFLPF-YZRHJBSPSA-N", 4);
		TableGlobalQ.put("GQPLMRYTRLFLPF-OUBTZVSYSA-N", 4);
		TableGlobalQ.put("GQPLMRYTRLFLPF-LBPDFUHNSA-N", 4);
		TableGlobalQ.put("GQPLMRYTRLFLPF-UHFFFAOYSA-N", 4);
		/* Carbonyl Sulfide */
		TableGlobalQ.put("JJWKPURADFRFRB-LBPDFUHNSA-N", 4);
		TableGlobalQ.put("JJWKPURADFRFRB-HQMMCQRPSA-N", 4);
		TableGlobalQ.put("JJWKPURADFRFRB-YZRHJBSPSA-N", 4);
		TableGlobalQ.put("JJWKPURADFRFRB-OUBTZVSYSA-N", 4);
		TableGlobalQ.put("JJWKPURADFRFRB-UHFFFAOYSA-N", 4);
		/* Hydrogen Cyanide */
		TableGlobalQ.put("LELOWRISYMNNSU-VQEHIDDOSA-N", 4);
		TableGlobalQ.put("LELOWRISYMNNSU-OUBTZVSYSA-N", 4);
		TableGlobalQ.put("LELOWRISYMNNSU-UHFFFAOYSA-N", 4);
		/**
		 * Class 5: Linear triatomic with large Fermi resonance
		 */
		/* Carbon Dioxide */
		TableGlobalQ.put("CURLTUGMZLYLDI-OUBTZVSYSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-UKEFCNGDSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-HQMMCQRPSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-JFJVQQHZSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-FTOQCNSHSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-VQEHIDDOSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-ZDOIIHCHSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-RGIGPVFXSA-N", 5);
		TableGlobalQ.put("CURLTUGMZLYLDI-UHFFFAOYSA-N", 5);
		/**
		 * Class 6: Non-linear triatomic
		 */
		/* Water */
		TableGlobalQ.put("XLYOFNOQVPJJNP-DYCDLGHISA-N", 6);
		TableGlobalQ.put("XLYOFNOQVPJJNP-OUBTZVSYSA-N", 6);
		TableGlobalQ.put("XLYOFNOQVPJJNP-XKYOGGAFSA-N", 6);
		TableGlobalQ.put("XLYOFNOQVPJJNP-DQGQKLTASA-N", 6);
		TableGlobalQ.put("XLYOFNOQVPJJNP-UHFFFAOYSA-N", 6);
		TableGlobalQ.put("XLYOFNOQVPJJNP-NJFSPNSNSA-N", 6);
		/* Ozone */
		TableGlobalQ.put("CBENFWSGALASAD-UHFFFAOYSA-N", 6);
		TableGlobalQ.put("CBENFWSGALASAD-YZRHJBSPSA-N", 6);
		TableGlobalQ.put("CBENFWSGALASAD-LBPDFUHNSA-N", 6);
		TableGlobalQ.put("CBENFWSGALASAD-NJFSPNSNSA-N", 6);
		TableGlobalQ.put("CBENFWSGALASAD-OUBTZVSYSA-N", 6);
		/* Sulfur Dioxide */
		TableGlobalQ.put("RAHZWNYVWXNFOC-UHFFFAOYSA-N", 6);
		TableGlobalQ.put("RAHZWNYVWXNFOC-YZRHJBSPSA-N", 6);
		/* Nitrogen Dioxide */
		TableGlobalQ.put("JCXJVPUVTGWSNB-UHFFFAOYSA-N", 6);
		/* Hypochlorous Acid */
		TableGlobalQ.put("QWPPOHNGKGFGJK-NJFSPNSNSA-N", 6);
		TableGlobalQ.put("QWPPOHNGKGFGJK-UHFFFAOYSA-N", 6);
		/* Hydrogen Sulfide */
		TableGlobalQ.put("RWSOTUBLDIXVET-UHFFFAOYSA-N", 6);
		TableGlobalQ.put("RWSOTUBLDIXVET-NJFSPNSNSA-N", 6);
		TableGlobalQ.put("RWSOTUBLDIXVET-OUBTZVSYSA-N", 6);
		/* Hydroperoxyl Radical */
		TableGlobalQ.put("OUUQCZGPVNCOIJ-UHFFFAOYSA-N", 6);
		/* Hypobromous Acid */
		TableGlobalQ.put("CUILPNURFADTPE-OUBTZVSYSA-N", 6);
		TableGlobalQ.put("CUILPNURFADTPE-UHFFFAOYSA-N", 6);
		TableGlobalQ.put("CUILPNURFADTPE-BJUDXGSMSA-N", 6);
		/**
		 * Class 7: Linear tetratomic
		 */
		/* Acetylene */
		TableGlobalQ.put("HSFWRNGVRCDJHI-OUBTZVSYSA-N", 7);
		TableGlobalQ.put("HSFWRNGVRCDJHI-QDNHWIQGSA-N", 7);
		TableGlobalQ.put("HSFWRNGVRCDJHI-UHFFFAOYSA-N", 7);
		/**
		 * Class 8: Pyramidal tetratomic
		 */
		/* Ammonia */
		TableGlobalQ.put("QGZKDVFQNNGYKY-OUBTZVSYSA-N", 8);
		TableGlobalQ.put("QGZKDVFQNNGYKY-UHFFFAOYSA-N", 8);
		/* Phosphine */
		TableGlobalQ.put("XYFCBTPGUUZFHI-UHFFFAOYSA-N", 8);
		/**
		 * Class 9: Non-linear tetratomic
		 */
		/* Formaldehyde */
		TableGlobalQ.put("WSFSSNUMVMOOMR-HQMMCQRPSA-N", 9);
		TableGlobalQ.put("WSFSSNUMVMOOMR-UHFFFAOYSA-N", 9);
		TableGlobalQ.put("WSFSSNUMVMOOMR-OUBTZVSYSA-N", 9);
		/* Hydrogen Peroxide */
		TableGlobalQ.put("MHAJPDPJQMAIIY-UHFFFAOYSA-N", 9);
		/* Carbonyl Fluoride */
		TableGlobalQ.put("IYRWEQXVUNLMAY-UHFFFAOYSA-N", 9);
		/**
		 * Class 10: Pentatomic or greater polyatomic
		 */
		/* Methane */
		TableGlobalQ.put("VNWKTOKETHGBQD-UHFFFAOYSA-N", 10);
		TableGlobalQ.put("VNWKTOKETHGBQD-VVKOMZTBSA-N", 10);
		TableGlobalQ.put("VNWKTOKETHGBQD-MICDWDOJSA-N", 10);
		TableGlobalQ.put("VNWKTOKETHGBQD-OUBTZVSYSA-N", 10);
		/* Carbon Tetrafluoride */
		TableGlobalQ.put("TXEYQDLBPFQVAA-UHFFFAOYSA-N", 10);
		/* Ethylene */
		TableGlobalQ.put("VGGSQFUCUMXWEO-UHFFFAOYSA-N", 10);
		/**
		 * Individual molecule notation
		 */
		/* Chlorine Nitrate */
		TableGlobalQ.put("XYLGPCWDPLOBGP-UHFFFAOYSA-N", 10);
		/* Methyl Chloride - Chloromethane */
		TableGlobalQ.put("NEHMKBQYUWJMIP-UHFFFAOYSA-N", 10);
		/* Ethane */
		TableGlobalQ.put("OTMSDBZUPAUEDD-UHFFFAOYSA-N", 10);
		/* Nitric Acid */
		TableGlobalQ.put("ATQYDUAHJAWUEM-UHFFFAOYSA-N", 10);
		/* Sulfur Hexafluoride */
		TableGlobalQ.put("SFZCNBIFKDRMGX-UHFFFAOYSA-N", 10);
		/* Formic Acid */
		TableGlobalQ.put("BDAGIHXWWSANSR-OUBTZVSYSA-N", 10);
		TableGlobalQ.put("BDAGIHXWWSANSR-UHFFFAOYSA-N", 10);
		/* Methanol */
		TableGlobalQ.put("OKKJLVBELUTLKV-OUBTZVSYSA-N", 10);
		TableGlobalQ.put("OKKJLVBELUTLKV-UHFFFAOYSA-N", 10);

		// /* Methyl Bromide - Bromomethane */
		// TableGlobalQ.put("GZUXJHMPEANEGY-VQEHIDDOSA-N", 40);
		// TableGlobalQ.put("GZUXJHMPEANEGY-UHFFFAOYSA-N", 40);
		// TableGlobalQ.put("GZUXJHMPEANEGY-JVVVGQRLSA-N", 40);
		// /* Methyl Cyanide */
		// TableGlobalQ.put("WEVYAHXRMPXWCK-UHFFFAOYSA-N", 41);
		// TableGlobalQ.put("WEVYAHXRMPXWCK-OUBTZVSYSA-N", 41);
		// TableGlobalQ.put("WEVYAHXRMPXWCK-VQEHIDDOSA-N", 41);
		// /* Diacetylene */
		// /* TODO */
		// /* Cyanoacetylene - Propynenitrile*/
		// TableGlobalQ.put("LNDJVIYUJOJFSO-UHFFFAOYSA-N", 44);
		// /* Molecular Hydrogen */
		// TableGlobalQ.put("UFHFLCQGNIYNRP-UHFFFAOYSA-N", 45);
		// /* Carbon Monosulfide */
		// TableGlobalQ.put("DXHPZXWIPWDXHJ-VQEHIDDOSA-N", 46);
		// TableGlobalQ.put("DXHPZXWIPWDXHJ-HQMMCQRPSA-N", 46);
		// TableGlobalQ.put("DXHPZXWIPWDXHJ-UHFFFAOYSA-N", 46);
		// TableGlobalQ.put("DXHPZXWIPWDXHJ-OUBTZVSYSA-N", 46);
		// /* Sulfur Trioxide */
		// TableGlobalQ.put("AKEJUJNQAAGONA-UHFFFAOYSA-N", 47);
	}

	/**
	 * Gets the global quanta code. This code is a way to represent the relevant
	 * informations about quantum levels. In the HITRAN 2004 edition, the
	 * vibrational quantum numbers are directly incorporated as a 15-character
	 * field.
	 * 
	 * @param inChIKey
	 * @return the ID of the class as defined in Table-3 of the HITRAN 2004
	 *         edition. 0 if the molecule is not defined.
	 */

	public int getHitranGlobalQCode(String inChIKey) {
		if (TableGlobalQ.containsKey(inChIKey)) {
			return TableGlobalQ.get(inChIKey);
		}
		return 0;
	}
}
