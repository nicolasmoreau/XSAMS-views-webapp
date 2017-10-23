package eu.vamdc.hitran;

import java.util.HashMap;
import java.util.Map;

public class TableMoleculeHitran {
	private Map<String, Integer> TableM = new HashMap<String, Integer>();

	/**
	 * Do the mapping of molecular species identification number. This operation
	 * is needed before doing anything else.
	 */

	public void doMapping() {
		/* Water */
		TableM.put("XLYOFNOQVPJJNP-DYCDLGHISA-N", 1);
		TableM.put("XLYOFNOQVPJJNP-OUBTZVSYSA-N", 1);
		TableM.put("XLYOFNOQVPJJNP-XKYOGGAFSA-N", 1);
		TableM.put("XLYOFNOQVPJJNP-DQGQKLTASA-N", 1);
		TableM.put("XLYOFNOQVPJJNP-UHFFFAOYSA-N", 1);
		TableM.put("XLYOFNOQVPJJNP-NJFSPNSNSA-N", 1);
		/* Carbon Dioxide */
		TableM.put("CURLTUGMZLYLDI-OUBTZVSYSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-UKEFCNGDSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-HQMMCQRPSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-JFJVQQHZSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-FTOQCNSHSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-VQEHIDDOSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-ZDOIIHCHSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-RGIGPVFXSA-N", 2);
		TableM.put("CURLTUGMZLYLDI-UHFFFAOYSA-N", 2);
		/* Ozone */
		TableM.put("CBENFWSGALASAD-UHFFFAOYSA-N", 3);
		TableM.put("CBENFWSGALASAD-YZRHJBSPSA-N", 3);
		TableM.put("CBENFWSGALASAD-LBPDFUHNSA-N", 3);
		TableM.put("CBENFWSGALASAD-NJFSPNSNSA-N", 3);
		TableM.put("CBENFWSGALASAD-OUBTZVSYSA-N", 3);
		/* Nitrous Oxide */
		TableM.put("GQPLMRYTRLFLPF-VQEHIDDOSA-N", 4);
		TableM.put("GQPLMRYTRLFLPF-YZRHJBSPSA-N", 4);
		TableM.put("GQPLMRYTRLFLPF-OUBTZVSYSA-N", 4);
		TableM.put("GQPLMRYTRLFLPF-LBPDFUHNSA-N", 4);
		TableM.put("GQPLMRYTRLFLPF-UHFFFAOYSA-N", 4);
		/* Carbon Monoxide */
		TableM.put("UGFAIRIUMAVXCW-RGIGPVFXSA-N", 5);
		TableM.put("UGFAIRIUMAVXCW-ZDOIIHCHSA-N", 5);
		TableM.put("UGFAIRIUMAVXCW-HQMMCQRPSA-N", 5);
		TableM.put("UGFAIRIUMAVXCW-VQEHIDDOSA-N", 5);
		TableM.put("UGFAIRIUMAVXCW-UHFFFAOYSA-N", 5);
		TableM.put("UGFAIRIUMAVXCW-OUBTZVSYSA-N", 5);
		/* Methane */
		TableM.put("VNWKTOKETHGBQD-UHFFFAOYSA-N", 6);
		TableM.put("VNWKTOKETHGBQD-VVKOMZTBSA-N", 6);
		TableM.put("VNWKTOKETHGBQD-MICDWDOJSA-N", 6);
		TableM.put("VNWKTOKETHGBQD-OUBTZVSYSA-N", 6);
		/* Molecular Oxygen */
		TableM.put("MYMOFIZGZYHOMD-UHFFFAOYSA-N", 7);
		TableM.put("MYMOFIZGZYHOMD-NJFSPNSNSA-N", 7);
		TableM.put("MYMOFIZGZYHOMD-OUBTZVSYSA-N", 7);
		/* Nitric Oxide */
		TableM.put("MWUXSHHQAYIFBG-HQMMCQRPSA-N", 8);
		TableM.put("MWUXSHHQAYIFBG-UHFFFAOYSA-N", 8);
		TableM.put("MWUXSHHQAYIFBG-OUBTZVSYSA-N", 8);
		/* Sulfur Dioxide */
		TableM.put("RAHZWNYVWXNFOC-UHFFFAOYSA-N", 9);
		TableM.put("RAHZWNYVWXNFOC-YZRHJBSPSA-N", 9);
		/* Nitrogen Dioxide */
		TableM.put("JCXJVPUVTGWSNB-UHFFFAOYSA-N", 10);
		/* Ammonia */
		TableM.put("QGZKDVFQNNGYKY-OUBTZVSYSA-N", 11);
		TableM.put("QGZKDVFQNNGYKY-UHFFFAOYSA-N", 11);
		/* Nitric Acid */
		TableM.put("ATQYDUAHJAWUEM-UHFFFAOYSA-N", 12);
		/* Hydroxyl Radical */
		TableM.put("TUJKJAMUKRIRHC-MICDWDOJSA-N", 13);
		TableM.put("TUJKJAMUKRIRHC-UHFFFAOYSA-N", 13);
		TableM.put("TUJKJAMUKRIRHC-NJFSPNSNSA-N", 13);
		/* Hydrogen Fluoride */
		TableM.put("KRHYYFGTRYWZRS-UHFFFAOYSA-N", 14);
		/* Hydrogen Chloride */
		TableM.put("VEXZGXHMUGYJMC-NJFSPNSNSA-N", 15);
		TableM.put("VEXZGXHMUGYJMC-UHFFFAOYSA-N", 15);
		/* Hydrogen Bromide */
		TableM.put("CPELXLSAUQHCOX-BJUDXGSMSA-N", 16);
		TableM.put("CPELXLSAUQHCOX-OUBTZVSYSA-N", 16);
		TableM.put("CPELXLSAUQHCOX-UHFFFAOYSA-N", 16);
		/* Hydrogen Iodide */
		TableM.put("XMBWDFGMSWQBCA-UHFFFAOYSA-N", 17);
		/* Chlorine Monoxide */
		TableM.put("NHYCGSASNAIGLD-UHFFFAOYSA-N", 18);
		TableM.put("NHYCGSASNAIGLD-NJFSPNSNSA-N", 18);
		/* Carbonyl Sulfide */
		TableM.put("JJWKPURADFRFRB-LBPDFUHNSA-N", 19);
		TableM.put("JJWKPURADFRFRB-HQMMCQRPSA-N", 19);
		TableM.put("JJWKPURADFRFRB-YZRHJBSPSA-N", 19);
		TableM.put("JJWKPURADFRFRB-OUBTZVSYSA-N", 19);
		TableM.put("JJWKPURADFRFRB-UHFFFAOYSA-N", 19);
		/* Formaldehyde */
		TableM.put("WSFSSNUMVMOOMR-HQMMCQRPSA-N", 20);
		TableM.put("WSFSSNUMVMOOMR-UHFFFAOYSA-N", 20);
		TableM.put("WSFSSNUMVMOOMR-OUBTZVSYSA-N", 20);
		/* Hypochlorous Acid */
		TableM.put("QWPPOHNGKGFGJK-NJFSPNSNSA-N", 21);
		TableM.put("QWPPOHNGKGFGJK-UHFFFAOYSA-N", 21);
		/* Molecular Nitrogen */
		TableM.put("IJGRMHOSHXDMSA-UHFFFAOYSA-N", 22);
		/* Hydrogen Cyanide */
		TableM.put("LELOWRISYMNNSU-VQEHIDDOSA-N", 23);
		TableM.put("LELOWRISYMNNSU-OUBTZVSYSA-N", 23);
		TableM.put("LELOWRISYMNNSU-UHFFFAOYSA-N", 23);
		/* Methyl Chloride - Chloromethane */
		TableM.put("NEHMKBQYUWJMIP-UHFFFAOYSA-N", 24);
		/* Hydrogen Peroxide */
		TableM.put("MHAJPDPJQMAIIY-UHFFFAOYSA-N", 25);
		/* Acetylene */
		TableM.put("HSFWRNGVRCDJHI-OUBTZVSYSA-N", 26);
		TableM.put("HSFWRNGVRCDJHI-QDNHWIQGSA-N", 26);
		TableM.put("HSFWRNGVRCDJHI-UHFFFAOYSA-N", 26);
		/* Ethane */
		TableM.put("OTMSDBZUPAUEDD-UHFFFAOYSA-N", 27);
		/* Phosphine */
		TableM.put("XYFCBTPGUUZFHI-UHFFFAOYSA-N", 28);
		/* Carbonyl Fluoride */
		TableM.put("IYRWEQXVUNLMAY-UHFFFAOYSA-N", 29);
		/* Sulfur Hexafluoride */
		TableM.put("SFZCNBIFKDRMGX-UHFFFAOYSA-N", 30);
		/* Hydrogen Sulfide */
		TableM.put("RWSOTUBLDIXVET-UHFFFAOYSA-N", 31);
		TableM.put("RWSOTUBLDIXVET-NJFSPNSNSA-N", 31);
		TableM.put("RWSOTUBLDIXVET-OUBTZVSYSA-N", 31);
		/* Formic Acid */
		TableM.put("BDAGIHXWWSANSR-OUBTZVSYSA-N", 32);
		TableM.put("BDAGIHXWWSANSR-UHFFFAOYSA-N", 32);
		/* Hydroperoxyl Radical */
		TableM.put("OUUQCZGPVNCOIJ-UHFFFAOYSA-N", 33);
		/* Oxygen Atom */
		/* TODO */
		/* Chlorine Nitrate */
		TableM.put("XYLGPCWDPLOBGP-UHFFFAOYSA-N", 35);
		/* Nitric Oxide Cation */
		TableM.put("KEJOCWOXCDWNID-UHFFFAOYSA-N", 36);
		/* Hypobromous Acid */
		TableM.put("CUILPNURFADTPE-OUBTZVSYSA-N", 37);
		TableM.put("CUILPNURFADTPE-UHFFFAOYSA-N", 37);
		TableM.put("CUILPNURFADTPE-BJUDXGSMSA-N", 37);
		/* Ethylene */
		TableM.put("VGGSQFUCUMXWEO-UHFFFAOYSA-N", 38);
		/* Methanol */
		TableM.put("OKKJLVBELUTLKV-OUBTZVSYSA-N", 39);
		TableM.put("OKKJLVBELUTLKV-UHFFFAOYSA-N", 39);
		/* Methyl Bromide - Bromomethane */
		TableM.put("GZUXJHMPEANEGY-VQEHIDDOSA-N", 40);
		TableM.put("GZUXJHMPEANEGY-UHFFFAOYSA-N", 40);
		TableM.put("GZUXJHMPEANEGY-JVVVGQRLSA-N", 40);
		/* Methyl Cyanide */
		TableM.put("WEVYAHXRMPXWCK-UHFFFAOYSA-N", 41);
		TableM.put("WEVYAHXRMPXWCK-OUBTZVSYSA-N", 41);
		TableM.put("WEVYAHXRMPXWCK-VQEHIDDOSA-N", 41);
		/* Carbon Tetrafluoride */
		TableM.put("TXEYQDLBPFQVAA-UHFFFAOYSA-N", 42);
		/* Diacetylene */
		/* TODO */
		/* Cyanoacetylene - Propynenitrile */
		TableM.put("LNDJVIYUJOJFSO-UHFFFAOYSA-N", 44);
		/* Molecular Hydrogen */
		TableM.put("UFHFLCQGNIYNRP-UHFFFAOYSA-N", 45);
		/* Carbon Monosulfide */
		TableM.put("DXHPZXWIPWDXHJ-VQEHIDDOSA-N", 46);
		TableM.put("DXHPZXWIPWDXHJ-HQMMCQRPSA-N", 46);
		TableM.put("DXHPZXWIPWDXHJ-UHFFFAOYSA-N", 46);
		TableM.put("DXHPZXWIPWDXHJ-OUBTZVSYSA-N", 46);
		/* Sulfur Trioxide */
		TableM.put("AKEJUJNQAAGONA-UHFFFAOYSA-N", 47);
		
		/* Germane: Not Yet implemented */
		TableM.put("QUZPNFFHZPRKJD-OUBTZVSYSA-N", 99);
		TableM.put("QUZPNFFHZPRKJD-BJUDXGSMSA-N", 99);
		TableM.put("QUZPNFFHZPRKJD-OIOBTWANSA-N", 99);
		TableM.put("QUZPNFFHZPRKJD-IGMARMGPSA-N", 99);
		TableM.put("QUZPNFFHZPRKJD-AKLPVKDBSA-N", 99);
		
		/* Ruthenium tetroxide: Not Yet implemented */
		TableM.put("GJFMDWMEOCWXGJ-RCUQKECRSA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-SQXARXAMSA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-AZCDSDHNSA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-QULCIFQISA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-CBKDSXNJSA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-PMIWTULXSA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-GIJGHNMVSA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-INZTZQCYSA-N", 99);
		TableM.put("GJFMDWMEOCWXGJ-DURHDXIWSA-N", 99);
	}

	/**
	 * Gets the HITRAN molecular species identification number. This number has
	 * no intrinsic meaning; a molecule is assigned a sequential number in the
	 * chronological order of its introduction into HITRAN.
	 * 
	 * @param inChIKey
	 * @return the current ID corresponding to the molecule, 0 if the molecule
	 *         does not exist in HITRAN
	 */
	public int getHitranMCode(String inChIKey) {
		if (TableM.containsKey(inChIKey)) {
			return TableM.get(inChIKey);
		}
		return 99; // default code for unknown molecule.
	}
}
