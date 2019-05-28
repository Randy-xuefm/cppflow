package cpp.flow.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fenming.xue on 2019/5/27.
 */
public class Flow {
    private String id;
    private List<Line> lineList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> findAvailableNode(String nodeId){
        List<Node> nodeList = new ArrayList<>();
        List<Line> allLineList = lineList.stream()
                .filter(line-> line.getPreNode().getId().equals(nodeId))
                .collect(Collectors.toList());

        if(allLineList.size() <= 0){
            return null;
        }

        List<Line> resultList = allLineList.stream().filter(Line::isAvailable).collect(Collectors.toList());

        if(resultList.size() <= 0){
            return null;
        }
        resultList.forEach(line -> nodeList.add(line.getNextNode()));
        return nodeList;
    }

    public Line getLine(String lineId){
        return this.lineList.stream()
                .filter(line -> line.getId().equals(lineId)).findFirst()
                .orElse(null);
    }

    public boolean availableNodeContainsNode(Line line){
        List<Node> availableNodeList = this.findAvailableNode(line.getPreNode().getId());

        return availableNodeList.stream()
                .filter(node -> node.getId().equals(line.getNextNode().getId()))
                .findFirst().orElse(null) != null;
    }
}
