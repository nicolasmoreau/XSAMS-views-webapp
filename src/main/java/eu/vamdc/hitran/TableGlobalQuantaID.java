package eu.vamdc.hitran;

import java.util.HashMap;
import java.util.Map;

public class TableGlobalQuantaID {
	private Map<String, Integer> TableGlobalQ = new HashMap<String, Integer>();

	/**
	 * Do the mapping of the global Q identification number. This code is assigned
	 * to molecules in order to sort them.This operation is needed before doing
	 * anything else.
	 */

	public void doMapping() {
		/**
		 * Class 1: Diatomic molecules
		 */
		/* Carbon Monoxide */
		TableGlobalQ.put("UGFAIRIUMAVXCW", 1);
		/* Hydrogen Fluoride */
		TableGlobalQ.put("KRHYYFGTRYWZRS", 1);
		/* Hydrogen Chloride */
		TableGlobalQ.put("VEXZGXHMUGYJMC", 1);
		/* Hydrogen Bromide */
		TableGlobalQ.put("CPELXLSAUQHCOX", 1);
		/* Hydrogen Iodide */
		TableGlobalQ.put("XMBWDFGMSWQBCA", 1);
		/* Molecular Nitrogen */
		TableGlobalQ.put("IJGRMHOSHXDMSA", 1);
		/* Nitric Oxide Cation */
		TableGlobalQ.put("KEJOCWOXCDWNID", 1);
		/* Molecular Hydrogen */
		TableGlobalQ.put("UFHFLCQGNIYNRP", 1);
		/* Carbon Monosulfide */
		TableGlobalQ.put("DXHPZXWIPWDXHJ", 1);
		/**
		 * Class 2: Diatmic molecules with different electronic levels
		 */
		/* Molecular Oxygen */
		TableGlobalQ.put("MYMOFIZGZYHOMD", 2);
		/**
		 * Class 3: Diatomic molecules with doublet-Pi electronic state
		 */
		/* Nitric Oxide */
		TableGlobalQ.put("MWUXSHHQAYIFBG", 3);
		/* Hydroxyl Radical */
		TableGlobalQ.put("TUJKJAMUKRIRHC", 3);
		/* Chlorine Monoxide */
		TableGlobalQ.put("NHYCGSASNAIGLD", 3);
		/**
		 * Class 4: Linear triatomic
		 */
		/* Nitrous Oxide */
		TableGlobalQ.put("GQPLMRYTRLFLPF", 4);
		/* Carbonyl Sulfide */
		TableGlobalQ.put("JJWKPURADFRFRB", 4);
		/* Hydrogen Cyanide */
		TableGlobalQ.put("LELOWRISYMNNSU", 4);
		/**
		 * Class 5: Linear triatomic with large Fermi resonance
		 */
		/* Carbon Dioxide */
		TableGlobalQ.put("CURLTUGMZLYLDI", 5);
		/**
		 * Class 6: Non-linear triatomic
		 */
		/* Water */
		TableGlobalQ.put("XLYOFNOQVPJJNP", 6);
		/* Ozone */
		TableGlobalQ.put("CBENFWSGALASAD", 6);
		/* Sulfur Dioxide */
		TableGlobalQ.put("RAHZWNYVWXNFOC", 6);
		/* Nitrogen Dioxide */
		TableGlobalQ.put("JCXJVPUVTGWSNB", 6);
		/* Hypochlorous Acid */
		TableGlobalQ.put("QWPPOHNGKGFGJK", 6);
		/* Hydrogen Sulfide */
		TableGlobalQ.put("RWSOTUBLDIXVET", 6);
		/* Hydroperoxyl Radical */
		TableGlobalQ.put("OUUQCZGPVNCOIJ", 6);
		/* Hypobromous Acid */
		TableGlobalQ.put("CUILPNURFADTPE", 6);
		/**
		 * Class 7: Linear tetratomic
		 */
		/* Acetylene */
		TableGlobalQ.put("HSFWRNGVRCDJHI", 7);
		/* Cyanogen */
		TableGlobalQ.put("NHFDIUPJVYYTLG", 7);
		TableGlobalQ.put("JMANVNJQNLATNU", 7);
		/**
		 * Class 8: Pyramidal tetratomic
		 */
		/* Ammonia */
		TableGlobalQ.put("QGZKDVFQNNGYKY", 8);
		/* Phosphine */
		TableGlobalQ.put("XYFCBTPGUUZFHI", 8);
		/* Sulfur Trioxide */
		TableGlobalQ.put("AKEJUJNQAAGONA", 8);
		/**
		 * Class 9: Non-linear tetratomic
		 */
		/* Formaldehyde */
		TableGlobalQ.put("WSFSSNUMVMOOMR", 9);
		/* Hydrogen Peroxide */
		TableGlobalQ.put("MHAJPDPJQMAIIY", 9);
		/* Carbonyl Fluoride */
		TableGlobalQ.put("IYRWEQXVUNLMAY", 9);
		/**
		 * Class 10: Pentatomic or greater polyatomic
		 */
		/* Methane */
		TableGlobalQ.put("VNWKTOKETHGBQD", 10);
		/* Carbon Tetrafluoride */
		TableGlobalQ.put("TXEYQDLBPFQVAA", 10);
		/* Ethylene */
		TableGlobalQ.put("VGGSQFUCUMXWEO", 10);
		/* Germane */
		TableGlobalQ.put("QUZPNFFHZPRKJD", 10);
		/* Ruthenium tetroxide: Not Yet implemented */
		TableGlobalQ.put("GJFMDWMEOCWXGJ", 10);

		/* Cyanoacetylene - Propynenitrile */
		TableGlobalQ.put("LNDJVIYUJOJFSO", 10);

		/**
		 * Individual molecule notation
		 */
		/* Chlorine Nitrate */
		TableGlobalQ.put("XYLGPCWDPLOBGP", 10);
		/* Methyl Chloride - Chloromethane */
		TableGlobalQ.put("NEHMKBQYUWJMIP", 10);
		/* Ethane */
		TableGlobalQ.put("OTMSDBZUPAUEDD", 10);
		/* Nitric Acid */
		TableGlobalQ.put("ATQYDUAHJAWUEM", 10);
		/* Sulfur Hexafluoride */
		TableGlobalQ.put("SFZCNBIFKDRMGX", 10);
		/* Formic Acid */
		TableGlobalQ.put("BDAGIHXWWSANSR", 10);
		/* Methanol */
		TableGlobalQ.put("OKKJLVBELUTLKV", 10);
		/* Methyl Cyanide */
		TableGlobalQ.put("WEVYAHXRMPXWCK", 10);

		/* TODO: Diacetylene, Butatrienylidene: 43 */
		TableGlobalQ.put("MRJFCQHWODZEPM", 10);

		// /* Methyl Bromide - Bromomethane */
		// TableGlobalQ.put("GZUXJHMPEANEGY", 40);
		// TableGlobalQ.put("GZUXJHMPEANEGY", 40);
		// TableGlobalQ.put("GZUXJHMPEANEGY", 40);
	}

	/**
	 * Gets the global quanta code. This code is a way to represent the relevant
	 * informations about quantum levels. In the HITRAN 2004 edition, the
	 * vibrational quantum numbers are directly incorporated as a 15-character
	 * field.
	 * 
	 * @param inChIKey
	 * @return the ID of the class as defined in Table-3 of the HITRAN 2004 edition.
	 *         0 if the molecule is not defined.
	 */

	public int getHitranGlobalQCode(String inChIKey) {
		String Molecule = inChIKey.substring(0, 14);
		if (TableGlobalQ.containsKey(Molecule)) {
			return TableGlobalQ.get(Molecule);
		}
		return 0;
	}
}
