package cpp.flow.domain;

import java.util.List;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class Line {
    private Node preNode;

    private Node nextNode;

    private boolean isCircle;

    private List<Filter> filterList;

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

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
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
}
