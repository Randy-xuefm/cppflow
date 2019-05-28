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

    public List<Node> findAvailableNode(String nodeId){
        List<Node> nodeList = new ArrayList<>();
        List<Line> allLineList = lineList.stream()
                .filter(line-> line.getPreNode().getId().equals(nodeId)||line.getNextNode().getId().equals(nodeId))
                .collect(Collectors.toList());

        if(allLineList == null || allLineList.size() <= 0){
            return null;
        }

        List<Line> resultList = allLineList.stream().filter(Line::isAvailable).collect(Collectors.toList());

        if(resultList == null || resultList.size() <= 0){
            return null;
        }
        resultList.forEach(line -> {
            nodeList.add(line.getPreNode().getId().equals(nodeId) ? line.getNextNode() : line.getPreNode());
        });
        return nodeList;
    }
}
