package cpp.flow.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class Engine {
    private Map<String/**nodeId**/,Flow> flowMap;

    public void startProcess(String nodeId){
        if(!flowMap.containsKey(nodeId)){
            return;
        }

    }

    public void endProcess(String nodeId){
        if(!flowMap.containsKey(nodeId)){
            return;
        }
    }

    public List<Node> findAvailableNode(String currentNodeId){
        if(!containsNode(currentNodeId)){
            return null;
        }
        return this.flowMap.get(currentNodeId).findAvailableNode(currentNodeId);
    }

    private boolean containsNode(String nodeId){
        return this.flowMap != null && this.flowMap.containsKey(nodeId);
    }
}
