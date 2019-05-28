package cpp.flow.domain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class FlowManager {

    private ConcurrentMap<String/**flowId**/,Flow> runtimeFlowMap = new ConcurrentHashMap<>(32);

    public void register(String bussinessId,Flow flow,Line line){
        this.runtimeFlowMap.putIfAbsent(flow.getId(),flow);

    }

    public void unregister(String bussinessId,Flow flow,Line line){
        this.runtimeFlowMap.remove(flow.getId());

    }

    public String getCurrentNodeId(String bussinessId){
        return null;
    }
}
