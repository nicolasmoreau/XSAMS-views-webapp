package eu.vamdc.hitran;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.vamdc.xsams.schema.MolecularStateType;
import org.vamdc.xsams.schema.MoleculeType;
import org.vamdc.xsams.schema.NamedDataType;
import org.vamdc.xsams.schema.RadiativeTransitionType;
import org.vamdc.xsams.schema.SourceType;
import org.vamdc.xsams.schema.RadiativeTransitionProbabilityType;
import org.vamdc.xsams.schema.BroadeningType;
import org.vamdc.xsams.schema.DataFuncType;
import org.vamdc.xsams.schema.DataType;
import org.vamdc.xsams.schema.EnvironmentType;
import org.vamdc.xsams.schema.FitParametersType;
import org.vamdc.xsams.schema.LineshapeType;
import org.vamdc.xsams.schema.XSAMSData;
import org.vamdc.xsams.io.JAXBContextFactory;

import eu.vamdc.cases.CaseUtil;
import eu.vamdc.util.FormatUtil;
import eu.vamdc.util.Settings;

public class ConvertXsams2Hitran {

	TableMoleculeHitran tableMol = new TableMoleculeHitran();
	TableIsotopologueHitran tableIso = new TableIsotopologueHitran();
	TableGlobalQuantaID tableQ = new TableGlobalQuantaID();
	TableSources tableS = new TableSources();

	/**
	 * read XSAMS file content
	 * 
	 * @param path
	 */
	public String convertXSAMS(String path) {
		String result = "";
		try {

			XSAMSData d = (XSAMSData) JAXBContextFactory.getUnmarshaller().unmarshal(new File(path));

			result = extractData(d, path);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Converts all XSAMSData d into HITRAN format and printing them into a file in
	 * path.
	 * 
	 * @param d
	 * @param path
	 */

	public String extractData(XSAMSData d, String path) {
		StringBuilder result = new StringBuilder();
		try {

			tableMol.doMapping(); // initialize table of molecules
			tableIso.doMapping(); // initialize table of isotopologues
			tableQ.doMapping(); // initialize table of global quanta
			Integer radiativeSourceID = 0;

			/* Build sources for referencing them */
			tableS.buildSourcesIndex(d.getSources().getSources());

			for (RadiativeTransitionType radiative : d.getProcesses().getRadiative().getRadiativeTransitions()) {
				List<DataType> Type = radiative.getEnergyWavelength().getWavenumbers();
				/* if no wavenumbers check for frequencies */
				if (Type.isEmpty())
					Type = radiative.getEnergyWavelength().getFrequencies();

				/*
				 * Case where Sources are global for all radiative transitions Case with Wiadis
				 * database
				 */
				for (SourceType source : radiative.getSources()) {
					radiativeSourceID = tableS.getSourcesIndex(source.getSourceID());
				}

				for (DataType energy : Type) {
					double S, transition, accuracy = 0.0, nrj;
					Integer gUp, gLow;
					int vCode;
					String units, inChIKey;
					MoleculeType molecule;
					MolecularStateType ELowRef, EUpRef;
					HitranData data = new HitranData();

					/* Vacuum wavenumber */
					transition = energy.getValue().getValue();
					units = energy.getValue().getUnits();
					transition = FormatUtil.getWavenumberHitran(transition, units);
					data.setVacWn(transition);
					/* Sources */
					if (energy.getSources().isEmpty())
						data.setVacWnRef(radiativeSourceID);
					else {
						for (SourceType source : energy.getSources()) {
							Integer sourceID = tableS.getSourcesIndex(source.getSourceID());
							data.setVacWnRef(sourceID);
						}
					}
					// only one accuracy is relevant ?
					if (!energy.getAccuracies().isEmpty()) {
						accuracy = energy.getAccuracies().get(0).getValue();
						accuracy = FormatUtil.getWavenumberHitran(accuracy, units);
						data.setVacWnErr(accuracy);
					}
					/* get lower and Upper state references */
					ELowRef = (MolecularStateType) radiative.getLowerStateRef();
					EUpRef = (MolecularStateType) radiative.getUpperStateRef();
					if (ELowRef == null || EUpRef == null)
						continue;
					molecule = (MoleculeType) EUpRef.getParent();
					inChIKey = molecule.getMolecularChemicalSpecies().getInChIKey();

					/* Molecule and Isotopologue number */
					data.setM(tableMol.getHitranMCode(inChIKey));
					data.setI(tableIso.getHitranICode(inChIKey));

					/* Lower-state energy */
					if (ELowRef.getMolecularStateCharacterisation().getStateEnergy() != null) {
						nrj = ELowRef.getMolecularStateCharacterisation().getStateEnergy().getValue().getValue();
						units = ELowRef.getMolecularStateCharacterisation().getStateEnergy().getValue().getUnits();
						//data.getWavenumberHitran(nrj, units); => useless because no affectation of result
						data.setELow(nrj);
					}

					/* Statistical weight of lower and upper state */
					gLow = ELowRef.getMolecularStateCharacterisation().getTotalStatisticalWeight();
					gUp = EUpRef.getMolecularStateCharacterisation().getTotalStatisticalWeight();
					if (gLow != null)
						data.setgLow(gLow);
					if (gUp != null)
						data.setgUp(gUp);

					/* only one item is possible here for HITRAN conversion */
					RadiativeTransitionProbabilityType probability;
					try {
						probability = radiative.getProbabilities().get(0);
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Something went wrong with probability items. Size is: "
								+ radiative.getProbabilities().size());
						return "";
					}
					if (probability.getLineStrength() != null) {
						/* Intensity */
						S = probability.getLineStrength().getValue().getValue();
						units = probability.getLineStrength().getValue().getUnits();
						/* Convert into HITRAN units */
						S = data.getIntensityHitran(S, units);
						data.setS(S);
						if (!probability.getLineStrength().getAccuracies().isEmpty()) {
							accuracy = probability.getLineStrength().getAccuracies().get(0).getValue();
							data.setSErr(accuracy);
						}
						if (probability.getLineStrength().getSources().isEmpty())
							data.setSRef(radiativeSourceID);
						else {
							for (SourceType source : probability.getLineStrength().getSources()) {
								Integer sourceID = tableS.getSourcesIndex(source.getSourceID());
								data.setSRef(sourceID);
							}
						}
					}
					/* Einstein A-coefficient */
					if (probability.getTransitionProbabilityA() != null)
						data.setA(probability.getTransitionProbabilityA().getValue().getValue());

					vCode = tableQ.getHitranGlobalQCode(inChIKey);
					/* Upper-state "local" quanta */
					String qUp = data.getLocalQuanta(EUpRef, CaseUtil.UPPER_LEVEL);
					data.setQUp(qUp);
					/* Upper-state "global" quanta */
					String vUp = data.getGlobalQuanta(EUpRef, vCode);
					data.setVUp(vUp);
					/* Lower-state "local" quanta */
					String qLow = data.getLocalQuanta(ELowRef, CaseUtil.LOWER_LEVEL);
					data.setQLow(qLow);
					/* Lower-state "global" quanta */
					String vLow = data.getGlobalQuanta(ELowRef, vCode);
					data.setVLow(vLow);

					/* Get gamma, n and delta values */
					if (!radiative.getBroadenings().isEmpty()) {
						// more than one broadening can exist.
						for (BroadeningType broadening : radiative.getBroadenings()) {
							LineshapeType lineshapes;
							DataFuncType funcType;
							EnvironmentType env;
							try {
								lineshapes = broadening.getLineshapes().get(0);
								funcType = lineshapes.getLineshapeParameters().get(0);
								env = (EnvironmentType) broadening.getEnvRef();
							} catch (IndexOutOfBoundsException e) {
								System.out.println(
										"Something went wrong with lineshapes, lineshapeparameters or EnvRef.");
								return "";
							}

							/* We test if gamma list is empty first */
							if (funcType.getFitParameters().isEmpty()) {
								double val = funcType.getValue().getValue();
								if (!funcType.getAccuracies().isEmpty())
									accuracy = funcType.getAccuracies().get(0).getValue();
								Integer sourceID = 0;

								for (SourceType source : funcType.getSources()) {
									sourceID = tableS.getSourcesIndex(source.getSourceID());
								}
								/*
								 * FIXME: should we also test here for delta and n ? Need EXAMPLES, because only
								 * ICB data come here for now
								 */
								if (funcType.getName().startsWith("gammaL")) {
									if (env.getEnvID().endsWith("-self")) {
										data.setGammaSelf(val);
										data.setGammaSelfErr(accuracy);
										data.setGammaSelfRef(sourceID);
									} else if (env.getEnvID().endsWith("-air")) {
										data.setGammaAir(val);
										data.setGammaAirErr(accuracy);
										data.setGammaAirRef(sourceID);
									}
								}
							}
							/* if the gamma list is not empty */
							else {
								for (FitParametersType fitParam : funcType.getFitParameters()) {
									for (NamedDataType value : fitParam.getFitParameters()) {
										double val = value.getValue().getValue();
										if (!value.getAccuracies().isEmpty())
											accuracy = value.getAccuracies().get(0).getValue();
										Integer sourceID = 0;

										for (SourceType source : value.getSources()) {
											sourceID = tableS.getSourcesIndex(source.getSourceID());
										}
										if (value.getName().startsWith("gammaL")) {
											if (env.getEnvID().endsWith("-self")) {
												data.setGammaSelf(val);
												data.setGammaSelfErr(accuracy);
												data.setGammaSelfRef(sourceID);
											} else if (env.getEnvID().endsWith("-air")) {
												data.setGammaAir(val);
												data.setGammaAirErr(accuracy);
												data.setGammaAirRef(sourceID);
											}
										} else if (value.getName().equals("n")) {
											if (env.getEnvID().endsWith("-air")) {
												data.setnAir(val);
												data.setnAirErr(accuracy);
												data.setnAirRef(sourceID);
											}
										} else if (value.getName().startsWith("delta")) {
											if (env.getEnvID().endsWith("-air")) {
												data.setDeltaAir(val);
												data.setDeltaAirErr(accuracy);
												data.setDeltaAirRef(sourceID);
											}
										}
									}
								}
							}
						}
					}
					result.append(WriteOutput.getHitranAsString(data) + "\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new StringBuilder();
			result.append("An exception occurred : "+ e);
			result.append("Please contact administrator : "+ Settings.CONTACT_ADDRESS);
		} 
		
		return result.toString();

	}
}