package eu.vamdc.hitran;

import java.util.HashMap;
import java.util.Map;

public class TableMoleculeHitran {
	private Map<String, Integer> TableM = new HashMap<String, Integer>();

	/**
	 * Do the mapping of molecular species identification number. This operation is
	 * needed before doing anything else.
	 */

	public void doMapping() {
		/* Water */
		TableM.put("XLYOFNOQVPJJNP", 1);
		/* Carbon Dioxide */
		TableM.put("CURLTUGMZLYLDI", 2);
		/* Ozone */
		TableM.put("CBENFWSGALASAD", 3);
		/* Nitrous Oxide */
		TableM.put("GQPLMRYTRLFLPF", 4);
		/* Carbon Monoxide */
		TableM.put("UGFAIRIUMAVXCW", 5);
		/* Methane */
		TableM.put("VNWKTOKETHGBQD", 6);
		/* Molecular Oxygen */
		TableM.put("MYMOFIZGZYHOMD", 7);
		/* Nitric Oxide */
		TableM.put("MWUXSHHQAYIFBG", 8);
		/* Sulfur Dioxide */
		TableM.put("RAHZWNYVWXNFOC", 9);
		/* Nitrogen Dioxide */
		TableM.put("JCXJVPUVTGWSNB", 10);
		/* Ammonia */
		TableM.put("QGZKDVFQNNGYKY", 11);
		/* Nitric Acid */
		TableM.put("ATQYDUAHJAWUEM", 12);
		/* Hydroxyl Radical */
		TableM.put("TUJKJAMUKRIRHC", 13);
		/* Hydrogen Fluoride */
		TableM.put("KRHYYFGTRYWZRS", 14);
		/* Hydrogen Chloride */
		TableM.put("VEXZGXHMUGYJMC", 15);
		/* Hydrogen Bromide */
		TableM.put("CPELXLSAUQHCOX", 16);
		/* Hydrogen Iodide */
		TableM.put("XMBWDFGMSWQBCA", 17);
		/* Chlorine Monoxide */
		TableM.put("NHYCGSASNAIGLD", 18);
		/* Carbonyl Sulfide */
		TableM.put("JJWKPURADFRFRB", 19);
		/* Formaldehyde */
		TableM.put("WSFSSNUMVMOOMR", 20);
		/* Hypochlorous Acid */
		TableM.put("QWPPOHNGKGFGJK", 21);
		/* Molecular Nitrogen */
		TableM.put("IJGRMHOSHXDMSA", 22);
		/* Hydrogen Cyanide */
		TableM.put("LELOWRISYMNNSU", 23);
		/* Methyl Chloride - Chloromethane */
		TableM.put("NEHMKBQYUWJMIP", 24);
		/* Hydrogen Peroxide */
		TableM.put("MHAJPDPJQMAIIY", 25);
		/* Acetylene */
		TableM.put("HSFWRNGVRCDJHI", 26);
		/* Ethane */
		TableM.put("OTMSDBZUPAUEDD", 27);
		/* Phosphine */
		TableM.put("XYFCBTPGUUZFHI", 28);
		/* Carbonyl Fluoride */
		TableM.put("IYRWEQXVUNLMAY", 29);
		/* Sulfur Hexafluoride */
		TableM.put("SFZCNBIFKDRMGX", 30);
		/* Hydrogen Sulfide */
		TableM.put("RWSOTUBLDIXVET", 31);
		/* Formic Acid */
		TableM.put("BDAGIHXWWSANSR", 32);
		/* Hydroperoxyl Radical */
		TableM.put("OUUQCZGPVNCOIJ", 33);
		/* Oxygen Atom */
		/* TODO */
		/* Chlorine Nitrate */
		TableM.put("XYLGPCWDPLOBGP", 35);
		/* Nitric Oxide Cation */
		TableM.put("KEJOCWOXCDWNID", 36);
		/* Hypobromous Acid */
		TableM.put("CUILPNURFADTPE", 37);
		/* Ethylene */
		TableM.put("VGGSQFUCUMXWEO", 38);
		/* Methanol */
		TableM.put("OKKJLVBELUTLKV", 39);
		/* Methyl Bromide - Bromomethane */
		TableM.put("GZUXJHMPEANEGY", 40);
		/* Methyl Cyanide */
		TableM.put("WEVYAHXRMPXWCK", 41);
		/* Carbon Tetrafluoride */
		TableM.put("TXEYQDLBPFQVAA", 42);
		/* Diacetylene, Butatrienylidene */
		TableM.put("MRJFCQHWODZEPM", 43);
		/* Cyanoacetylene - Propynenitrile */
		TableM.put("LNDJVIYUJOJFSO", 44);
		/* Molecular Hydrogen */
		TableM.put("UFHFLCQGNIYNRP", 45);
		/* Carbon Monosulfide */
		TableM.put("DXHPZXWIPWDXHJ", 46);
		/* Sulfur Trioxide */
		TableM.put("AKEJUJNQAAGONA", 47);
		/* Cyanogen */
		TableM.put("NHFDIUPJVYYTLG", 48);
		TableM.put("JMANVNJQNLATNU", 48);
		/* Germane: Not Yet implemented */
		TableM.put("QUZPNFFHZPRKJD", 52);


		/* Ruthenium tetroxide: Not Yet implemented */
		TableM.put("GJFMDWMEOCWXGJ", 99);
		/* Uranium hexafluoride: Not Yet implemented */
		TableM.put("SANRKQGLYCLAFE", 98);
		/* Silicon tetrafluoride: Not Yet implemented */
		TableI.put("ABTOQLMXBSRXSM", 99);

	}

	/**
	 * Gets the HITRAN molecular species identification number. This number has no
	 * intrinsic meaning; a molecule is assigned a sequential number in the
	 * chronological order of its introduction into HITRAN.
	 * 
	 * @param inChIKey
	 * @return the current ID corresponding to the molecule, 99 if the molecule does
	 *         not exist in HITRAN
	 */
	public int getHitranMCode(String inChIKey) {
		String Molecule = inChIKey.substring(0, 14);

		if (TableM.containsKey(Molecule)) {
			return TableM.get(Molecule);
		}
		return 99; // default code for unknown molecule.
	}
}
