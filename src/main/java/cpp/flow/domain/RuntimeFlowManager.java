package cpp.flow.domain;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import cpp.flow.exception.BussinessIdBlankException;
import cpp.flow.util.StringUtils;

/**
 * Created by fenming.xue on 2019/5/27.
 * 流程管理类.管理流程在运行时的状态
 */
public class RuntimeFlowManager {

    private ConcurrentMap<String/**flowId**/,Flow> runtimeFlowMap = new ConcurrentHashMap<>(32);

    public void register(String bussinessId,Flow flow,Line line){
        this.runtimeFlowMap.putIfAbsent(flow.getId(),flow);

    }

    public void unregister(String bussinessId,Flow flow,Line line){
        this.runtimeFlowMap.remove(flow.getId());

    }

    /**
     * 根据业务ID获取处于运行中的状态
     * @param bussinessId
     * @return
     */
    public List<Flow> getCurrentFlowList(String bussinessId){
        if(StringUtils.isBlank(bussinessId)){
            throw new BussinessIdBlankException();
        }

        return null;
    }

    public String getCurrentNodeId(String flowId,String bussinessId){
        if(StringUtils.isBlank(flowId) || StringUtils.isBlank(bussinessId)){
            throw new RuntimeException("");
        }
        return null;
    }

}
