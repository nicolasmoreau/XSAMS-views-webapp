package eu.vamdc.hitran;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vamdc.xsams.schema.SourceType;

public class TableSources {
	private Map<String, Integer> TableS = new HashMap<String, Integer>();

	private void doMapping(String id, int index) {
		TableS.put(id, index);
	}

	/**
	 * Do the mapping for sources. For each sourceID an unique key is assigned.
	 * 
	 * @param sources
	 */

	public void buildSourcesIndex(List<SourceType> sources) {
		int index = 0;
		for (SourceType source : sources) {
			++index;
			doMapping(source.getSourceID(), index);
			// System.out.println(source.getSourceID() + ": " + index);
		}
		System.out.println("Sources: " + index);
	}

	/**
	 * Gets the ID of the current source used.
	 * 
	 * @param sourceID
	 * @return the ID or 0 if no sources are found.
	 */

	public int getSourcesIndex(String sourceID) {
		if (TableS.containsKey(sourceID)) {
			return TableS.get(sourceID);
		}
		return 0;
	}
}
