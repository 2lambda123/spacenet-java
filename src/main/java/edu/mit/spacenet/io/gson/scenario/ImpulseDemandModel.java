package edu.mit.spacenet.io.gson.scenario;

import java.util.List;

public class ImpulseDemandModel extends DemandModel {
	public List<Resource> demands;

	public static ImpulseDemandModel createFrom(edu.mit.spacenet.domain.model.TimedImpulseDemandModel demandModel, Context context) {
		ImpulseDemandModel m = new ImpulseDemandModel();
		m.id = context.getUUID(demandModel);
		m.name = demandModel.getName();
		m.description = demandModel.getDescription();
		m.demands = Resource.createFrom(demandModel.getDemands(), context);
		return m;
	}
	
	@Override
	public edu.mit.spacenet.domain.model.TimedImpulseDemandModel toSpaceNet(Context context) {
		edu.mit.spacenet.domain.model.TimedImpulseDemandModel m = new edu.mit.spacenet.domain.model.TimedImpulseDemandModel();
		m.setTid(context.getId(id));
		m.setName(name);
		m.setDescription(description);
		m.setDemands(Resource.toSpaceNetSet(demands, context));
		return m;
	}
}
