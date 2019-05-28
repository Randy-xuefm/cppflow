package cpp.flow.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class Engine {
    private Map<String/**flowId**/,Flow> flowMap;
    private FlowManager flowManager;

    public void startProcess(String bussinessId,String flowId,String lineId){
        if(!flowMap.containsKey(flowId)){
            return;
        }

        String currentNodeId = flowManager.getCurrentNodeId(bussinessId);
        Flow flow = this.flowMap.get(flowId);
        if(flow == null){
            //error
            return;
        }
        Line line = flow.getLine(lineId);
        if(line == null){
            //error
            return;
        }
        if(!line.getPreNode().getId().equals(currentNodeId)){
            //error
            return;
        }

        if(!flow.availableNodeContainsNode(line)){
            //error
            return;
        }
        this.flowManager.register(bussinessId,flow,line);

    }

    public void endProcess(String bussinessId,String flowId,String lineId){
        if(!flowMap.containsKey(flowId)){
            return;
        }

        String currentNodeId = flowManager.getCurrentNodeId(bussinessId);
        Flow flow = this.flowMap.get(flowId);
        if(flow == null){
            //error
            return;
        }
        Line line = flow.getLine(lineId);
        if(line == null){
            //error
            return;
        }
        if(!line.getPreNode().getId().equals(currentNodeId)){
            //error
            return;
        }
        this.flowManager.unregister(bussinessId,flow,line);
    }

    public List<Node> findAvailableNode(String bussinessId){
        if(bussinessId == null || bussinessId.isEmpty()){
            return null;
        }
        String currentNodeId = this.flowManager.getCurrentNodeId(bussinessId);
        return this.flowMap.get(currentNodeId).findAvailableNode(currentNodeId);
    }

}
