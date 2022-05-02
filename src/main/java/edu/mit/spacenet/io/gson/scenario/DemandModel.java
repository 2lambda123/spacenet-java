package edu.mit.spacenet.io.gson.scenario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import edu.mit.spacenet.domain.model.DemandModelType;
import edu.mit.spacenet.domain.model.I_DemandModel;

public abstract class DemandModel implements Cloneable {
	public static final BiMap<String, DemandModelType> TYPE_MAP = new ImmutableBiMap.Builder<String, DemandModelType>()
			.put("Crew Consumables Demand Model", DemandModelType.CREW_CONSUMABLES)
			.put("Timed Impulse Demand Model", DemandModelType.TIMED_IMPULSE)
			.put("Rated Demand Model", DemandModelType.RATED)
			.put("Sparing by Mass Demand Model", DemandModelType.SPARING_BY_MASS)
			.build();
	
	public UUID id;
	public String name;
	public String description;

	public static DemandModel createFrom(I_DemandModel demandModel, Context context) {
		if(demandModel.getDemandModelType() == DemandModelType.TIMED_IMPULSE) {
			return ImpulseDemandModel.createFrom((edu.mit.spacenet.domain.model.TimedImpulseDemandModel) demandModel, context);
		} else if(demandModel.getDemandModelType() == DemandModelType.RATED) {
			return RatedDemandModel.createFrom((edu.mit.spacenet.domain.model.RatedDemandModel) demandModel, context);
		} else if(demandModel.getDemandModelType() == DemandModelType.SPARING_BY_MASS) {
			return SparingByMassDemandModel.createFrom((edu.mit.spacenet.domain.model.SparingByMassDemandModel) demandModel, context);
		} else if(demandModel.getDemandModelType() == DemandModelType.CREW_CONSUMABLES) {
			return ConsumablesDemandModel.createFrom((edu.mit.spacenet.domain.model.CrewConsumablesDemandModel) demandModel, context);
		} else {
			throw new UnsupportedOperationException("unknown demand model type: " + demandModel.getDemandModelType());
		}
	}
	
	public static List<DemandModel> createFrom(Collection<I_DemandModel> demandModels, Context context) {
		List<DemandModel> ds = new ArrayList<DemandModel>();
		for(I_DemandModel d : demandModels) {
			ds.add(DemandModel.createFrom(d, context));
		}
		return ds;
	}
	
	public abstract I_DemandModel toSpaceNet(UUID source, Context context);
	
	public static SortedSet<I_DemandModel> toSpaceNet(UUID source, Collection<DemandModel> demandModels, Context context) {
		SortedSet<I_DemandModel> ds = new TreeSet<I_DemandModel>();
		for(DemandModel d : demandModels) {
			ds.add(d.toSpaceNet(source, context));
		}
		return ds;
	}
	
	@Override
	public abstract DemandModel clone();
	
	public static List<DemandModel> clone(Collection<? extends DemandModel> models) {
		List<DemandModel> ms = new ArrayList<DemandModel>();
		for(DemandModel m : models) {
			ms.add(m.clone());
		}
		return ms;
	}
}
