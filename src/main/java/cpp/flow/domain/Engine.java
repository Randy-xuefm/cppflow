package cpp.flow.domain;

import java.util.List;
import java.util.Map;
import cpp.flow.exception.FlowNotExistsException;
import cpp.flow.util.StringUtils;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class Engine {
    private Map<String/**flowId**/,Flow> flowMap;
    private RuntimeFlowManager flowManager;

    public Engine(RuntimeFlowManager manager){
        this.flowManager = manager;
    }

    /**
     * 开始流程
     * @param bussinessId
     * @param flowId
     * @param lineId
     */
    public void startProcess(String bussinessId,String flowId,String lineId){
        if(!flowMap.containsKey(flowId)){
            return;
        }

        List<Flow> flowList= flowManager.getCurrentFlowList(bussinessId);
        if(flowList == null || flowList.size() <= 0){
            startNewProcess(bussinessId,flowId,lineId);
            return;
        }

        //else
        Flow curFlow = this.flowMap.get(flowId);
        Line curLine = curFlow.getLine(lineId);
        if(!curFlow.availableNodeContainsNode(curLine)){
            //路径不可达
            return;
        }
        //验证父流程
        if(!matchParentProcess(curFlow, bussinessId)){
            //总流程不满足
            return;
        }

        this.flowManager.register(bussinessId,curFlow,curLine);
    }

    /**
     * 结束流程
     * @param bussinessId
     * @param flowId
     * @param lineId
     */
    public void endProcess(String bussinessId,String flowId,String lineId){
        if(!flowMap.containsKey(flowId)){
            return;
        }
        Flow flow = this.flowMap.get(flowId);
        this.flowManager.unregister(bussinessId,flow,flow.getLine(lineId));
    }

    /**
     * 根据流程ID,业务主键查询是否有可达路径
     * @param bussinessId
     * @param flowId
     * @return
     */
    public List<Node> findAvailableNode(String bussinessId,String flowId){
        if(StringUtils.isBlank(bussinessId) || StringUtils.isBlank(flowId)){
            return null;
        }
        boolean matched = matchParentProcess(this.flowMap.get(flowId),bussinessId);
        if(!matched){
            return null;
        }
        String currentNodeId = this.flowManager.getCurrentNodeId(flowId,bussinessId);
        return this.flowMap.get(flowId).findAvailableNode(currentNodeId);
    }

    private void startNewProcess(String bussinessId,String flowId,String lineId){
        Flow flow = this.flowMap.get(flowId);
        if(flow == null){
            throw new FlowNotExistsException();
        }
        if(flow.getParentFlow() != null){
            startNewProcess(bussinessId,flow.getParentFlow().getFlowId(),Line.START);
        }else{
            this.flowManager.register(bussinessId,flow,flow.getLine(lineId));
        }

    }

    private boolean matchParentProcess(Flow flow, String bussinessId){
        if(flow.getParentFlow() != null){
            if(!flow.getParentFlow().getFlowNodeId().equals(this.flowManager.getCurrentNodeId(flow.getParentFlow().getFlowId(),bussinessId))){
                return false;
            }
            matchParentProcess(this.flowMap.get(flow.getParentFlow().getFlowId()), bussinessId);
        }
        return true;
    }
}
