package eu.vamdc.hitran;

import java.util.HashMap;
import java.util.Map;

public class TableIsotopologueHitran {
	private Map<String, Integer> TableI = new HashMap<String, Integer>();

	/**
	 * Do the mapping of the isotopologue identification number. This operation
	 * is needed before doing anything else.
	 */

	public void doMapping() {
		/* Water */
		TableI.put("XLYOFNOQVPJJNP-DYCDLGHISA-N", 4);
		TableI.put("XLYOFNOQVPJJNP-OUBTZVSYSA-N", 3);
		TableI.put("XLYOFNOQVPJJNP-XKYOGGAFSA-N", 6);
		TableI.put("XLYOFNOQVPJJNP-DQGQKLTASA-N", 5);
		TableI.put("XLYOFNOQVPJJNP-UHFFFAOYSA-N", 1);
		TableI.put("XLYOFNOQVPJJNP-NJFSPNSNSA-N", 2);
		/* Carbon Dioxide */
		TableI.put("CURLTUGMZLYLDI-OUBTZVSYSA-N", 2);
		TableI.put("CURLTUGMZLYLDI-UKEFCNGDSA-N", 0);
		TableI.put("CURLTUGMZLYLDI-HQMMCQRPSA-N", 3);
		TableI.put("CURLTUGMZLYLDI-JFJVQQHZSA-N", 8);
		TableI.put("CURLTUGMZLYLDI-FTOQCNSHSA-N", 7);
		TableI.put("CURLTUGMZLYLDI-VQEHIDDOSA-N", 4);
		TableI.put("CURLTUGMZLYLDI-ZDOIIHCHSA-N", 6);
		TableI.put("CURLTUGMZLYLDI-RGIGPVFXSA-N", 5);
		TableI.put("CURLTUGMZLYLDI-UHFFFAOYSA-N", 1);
		/* Ozone */
		TableI.put("CBENFWSGALASAD-UHFFFAOYSA-N", 1);
		TableI.put("CBENFWSGALASAD-YZRHJBSPSA-N", 3);
		TableI.put("CBENFWSGALASAD-LBPDFUHNSA-N", 5);
		TableI.put("CBENFWSGALASAD-NJFSPNSNSA-N", 2);
		TableI.put("CBENFWSGALASAD-OUBTZVSYSA-N", 4);
		/* Nitrous Oxide */
		TableI.put("GQPLMRYTRLFLPF-VQEHIDDOSA-N", 2);
		TableI.put("GQPLMRYTRLFLPF-YZRHJBSPSA-N", 4);
		TableI.put("GQPLMRYTRLFLPF-OUBTZVSYSA-N", 3);
		TableI.put("GQPLMRYTRLFLPF-LBPDFUHNSA-N", 5);
		TableI.put("GQPLMRYTRLFLPF-UHFFFAOYSA-N", 1);
		/* Carbon Monoxide */
		TableI.put("UGFAIRIUMAVXCW-RGIGPVFXSA-N", 5);
		TableI.put("UGFAIRIUMAVXCW-ZDOIIHCHSA-N", 6);
		TableI.put("UGFAIRIUMAVXCW-HQMMCQRPSA-N", 3);
		TableI.put("UGFAIRIUMAVXCW-VQEHIDDOSA-N", 4);
		TableI.put("UGFAIRIUMAVXCW-UHFFFAOYSA-N", 1);
		TableI.put("UGFAIRIUMAVXCW-OUBTZVSYSA-N", 2);
		/* Methane */
		TableI.put("VNWKTOKETHGBQD-UHFFFAOYSA-N", 1);
		TableI.put("VNWKTOKETHGBQD-VVKOMZTBSA-N", 4);
		TableI.put("VNWKTOKETHGBQD-MICDWDOJSA-N", 3);
		TableI.put("VNWKTOKETHGBQD-OUBTZVSYSA-N", 2);
		/* Molecular Oxygen */
		TableI.put("MYMOFIZGZYHOMD-UHFFFAOYSA-N", 1);
		TableI.put("MYMOFIZGZYHOMD-NJFSPNSNSA-N", 2);
		TableI.put("MYMOFIZGZYHOMD-OUBTZVSYSA-N", 3);
		/* Nitric Oxide */
		TableI.put("MWUXSHHQAYIFBG-HQMMCQRPSA-N", 3);
		TableI.put("MWUXSHHQAYIFBG-UHFFFAOYSA-N", 1);
		TableI.put("MWUXSHHQAYIFBG-OUBTZVSYSA-N", 2);
		/* Sulfur Dioxide */
		TableI.put("RAHZWNYVWXNFOC-UHFFFAOYSA-N", 1);
		TableI.put("RAHZWNYVWXNFOC-YZRHJBSPSA-N", 2);
		/* Nitrogen Dioxide */
		TableI.put("JCXJVPUVTGWSNB-UHFFFAOYSA-N", 1);
		/* Ammonia */
		TableI.put("QGZKDVFQNNGYKY-OUBTZVSYSA-N", 2);
		TableI.put("QGZKDVFQNNGYKY-UHFFFAOYSA-N", 1);
		/* Nitric Acid */
		TableI.put("ATQYDUAHJAWUEM-UHFFFAOYSA-N", 1);
		/* Hydroxyl Radical */
		TableI.put("TUJKJAMUKRIRHC-MICDWDOJSA-N", 3);
		TableI.put("TUJKJAMUKRIRHC-UHFFFAOYSA-N", 1);
		TableI.put("TUJKJAMUKRIRHC-NJFSPNSNSA-N", 2);
		/* Hydrogen Fluoride */
		TableI.put("KRHYYFGTRYWZRS-UHFFFAOYSA-N", 1);
		/* Hydrogen Chloride */
		TableI.put("VEXZGXHMUGYJMC-NJFSPNSNSA-N", 2);
		TableI.put("VEXZGXHMUGYJMC-UHFFFAOYSA-N", 1);
		/* Hydrogen Bromide */
		/* TODO */
		TableI.put("CPELXLSAUQHCOX-BJUDXGSMSA-N", 0);
		TableI.put("CPELXLSAUQHCOX-OUBTZVSYSA-N", 0);
		TableI.put("CPELXLSAUQHCOX-UHFFFAOYSA-N", 0);
		/* Hydrogen Iodide */
		TableI.put("XMBWDFGMSWQBCA-UHFFFAOYSA-N", 1);
		/* Chlorine Monoxide */
		TableI.put("NHYCGSASNAIGLD-UHFFFAOYSA-N", 1);
		TableI.put("NHYCGSASNAIGLD-NJFSPNSNSA-N", 2);
		/* Carbonyl Sulfide */
		TableI.put("JJWKPURADFRFRB-LBPDFUHNSA-N", 4);
		TableI.put("JJWKPURADFRFRB-HQMMCQRPSA-N", 5);
		TableI.put("JJWKPURADFRFRB-YZRHJBSPSA-N", 2);
		TableI.put("JJWKPURADFRFRB-OUBTZVSYSA-N", 3);
		TableI.put("JJWKPURADFRFRB-UHFFFAOYSA-N", 1);
		/* Formaldehyde */
		TableI.put("WSFSSNUMVMOOMR-HQMMCQRPSA-N", 3);
		TableI.put("WSFSSNUMVMOOMR-UHFFFAOYSA-N", 1);
		TableI.put("WSFSSNUMVMOOMR-OUBTZVSYSA-N", 2);
		/* Hypochlorous Acid */
		TableI.put("QWPPOHNGKGFGJK-NJFSPNSNSA-N", 2);
		TableI.put("QWPPOHNGKGFGJK-UHFFFAOYSA-N", 1);
		/* Molecular Nitrogen */
		TableI.put("IJGRMHOSHXDMSA-UHFFFAOYSA-N", 1);
		/* Hydrogen Cyanide */
		TableI.put("LELOWRISYMNNSU-VQEHIDDOSA-N", 3);
		TableI.put("LELOWRISYMNNSU-OUBTZVSYSA-N", 2);
		TableI.put("LELOWRISYMNNSU-UHFFFAOYSA-N", 1);
		/* Methyl Chloride - Chloromethane */
		TableI.put("NEHMKBQYUWJMIP-UHFFFAOYSA-N", 1);
		/* Hydrogen Peroxide */
		TableI.put("MHAJPDPJQMAIIY-UHFFFAOYSA-N", 1);
		/* Acetylene */
		TableI.put("HSFWRNGVRCDJHI-OUBTZVSYSA-N", 2);
		TableI.put("HSFWRNGVRCDJHI-QDNHWIQGSA-N", 3); // not in HITRAN
		TableI.put("HSFWRNGVRCDJHI-UHFFFAOYSA-N", 1);
		/* Ethane */
		TableI.put("OTMSDBZUPAUEDD-UHFFFAOYSA-N", 1);
		/* Phosphine */
		TableI.put("XYFCBTPGUUZFHI-UHFFFAOYSA-N", 1);
		/* Carbonyl Fluoride */
		TableI.put("IYRWEQXVUNLMAY-UHFFFAOYSA-N", 1);
		/* Sulfur Hexafluoride */
		TableI.put("SFZCNBIFKDRMGX-UHFFFAOYSA-N", 1);
		/* Hydrogen Sulfide */
		TableI.put("RWSOTUBLDIXVET-UHFFFAOYSA-N", 1);
		TableI.put("RWSOTUBLDIXVET-NJFSPNSNSA-N", 2);
		TableI.put("RWSOTUBLDIXVET-OUBTZVSYSA-N", 3);
		/* Formic Acid */
		TableI.put("BDAGIHXWWSANSR-OUBTZVSYSA-N", 1);
		TableI.put("BDAGIHXWWSANSR-UHFFFAOYSA-N", 1);
		/* Hydroperoxyl Radical */
		TableI.put("OUUQCZGPVNCOIJ-UHFFFAOYSA-N", 1);
		/* Oxygen Atom */
		/* TODO */
		/* Chlorine Nitrate */
		TableI.put("XYLGPCWDPLOBGP-UHFFFAOYSA-N", 1);
		/* Nitric Oxide Cation */
		TableI.put("KEJOCWOXCDWNID-UHFFFAOYSA-N", 1);
		/* Hypobromous Acid */
		/** TODO **/
		TableI.put("CUILPNURFADTPE-OUBTZVSYSA-N", 0);
		TableI.put("CUILPNURFADTPE-UHFFFAOYSA-N", 0);
		TableI.put("CUILPNURFADTPE-BJUDXGSMSA-N", 0);
		/* Ethylene */
		TableI.put("VGGSQFUCUMXWEO-UHFFFAOYSA-N", 1);
		/* Methanol */
		TableI.put("OKKJLVBELUTLKV-OUBTZVSYSA-N", 2); // not in HITRAN
		TableI.put("OKKJLVBELUTLKV-UHFFFAOYSA-N", 1);
		/* Methyl Bromide - Bromomethane */
		/** TODO **/
		TableI.put("GZUXJHMPEANEGY-UHFFFAOYSA-N", 0);
		TableI.put("GZUXJHMPEANEGY-UHFFFAOYSA-N", 0);
		TableI.put("GZUXJHMPEANEGY-JVVVGQRLSA-N", 0);
		/* Methyl Cyanide */
		TableI.put("WEVYAHXRMPXWCK-UHFFFAOYSA-N", 1);
		TableI.put("WEVYAHXRMPXWCK-OUBTZVSYSA-N", 0); // not in HITRAN
		TableI.put("WEVYAHXRMPXWCK-VQEHIDDOSA-N", 0); // not in HITRAN
		/* Carbon Tetrafluoride */
		TableI.put("TXEYQDLBPFQVAA-UHFFFAOYSA-N", 1);
		/* Diacetylene */
		/* TODO */
		/* Cyanoacetylene - Propynenitrile */
		TableI.put("LNDJVIYUJOJFSO-UHFFFAOYSA-N", 1);
		/* Molecular Hydrogen */
		TableI.put("UFHFLCQGNIYNRP-UHFFFAOYSA-N", 1);
		/* Carbon Monosulfide */
		TableI.put("DXHPZXWIPWDXHJ-VQEHIDDOSA-N", 4);
		TableI.put("DXHPZXWIPWDXHJ-HQMMCQRPSA-N", 2);
		TableI.put("DXHPZXWIPWDXHJ-UHFFFAOYSA-N", 1);
		TableI.put("DXHPZXWIPWDXHJ-OUBTZVSYSA-N", 3);
		/* Sulfur Trioxide */
		TableI.put("AKEJUJNQAAGONA-UHFFFAOYSA-N", 1);
	}

	/**
	 * Gets the HITRAN isotopologue ID number. Iso = 1 for the most abundant (by
	 * a chosen terrestrial determination) isotopologue, Iso = 2 for the next
	 * most abundant, etc.
	 * 
	 * @param inChIKey
	 * @return the current ID corresponding to the isotopologue, 0 if the
	 *         isotopologue does not exist in HITRAN
	 */

	public int getHitranICode(String inChIKey) {
		if (TableI.containsKey(inChIKey)) {
			return TableI.get(inChIKey);
		}
		return 0;
	}
}
