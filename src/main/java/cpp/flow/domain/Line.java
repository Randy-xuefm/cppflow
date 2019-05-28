package cpp.flow.domain;

import java.util.List;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class Line {

    private String id;

    private Node preNode;

    private Node nextNode;

    private List<Filter> filterList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getPreNode() {
        return preNode;
    }

    public void setPreNode(Node preNode) {
        this.preNode = preNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public boolean isAvailable(){
        if(this.filterList == null || this.filterList.size() <= 0){
            return true;
        }

        return this.filterList.stream().allMatch(Filter::match);
    }

    public boolean containsNode(String nodeId){
        return this.preNode.getId().equals(nodeId);
    }

}
