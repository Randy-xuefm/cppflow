package cpp.flow.domain;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class Node {
    private String id;

    private String name;

    private String parentFlowId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentFlowId() {
        return parentFlowId;
    }

    public void setParentFlowId(String parentFlowId) {
        this.parentFlowId = parentFlowId;
    }
}
