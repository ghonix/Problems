package graph;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        System.out.println(new Dijkstra().calculateShortestPath());
    }


    private int calculateShortestPath() {
        Map<String, Set<NodeData>> graph = new HashMap<>();
        graph.put("1", Set.of(new NodeData("2", 2),
                new NodeData("4", 10),
                new NodeData("8", 1)));
        graph.put("2", Set.of(new NodeData("3", 3)));
        graph.put("3", Set.of(new NodeData("4", 4)));
        graph.put("4", Set.of(new NodeData("6", 6)));
        graph.put("5", Set.of(new NodeData("6", 6)));
        graph.put("6", Set.of(new NodeData("4", 4),
                new NodeData("5", 5),
                new NodeData("7", 7),
                new NodeData("8", 8)));
        graph.put("7", Set.of(new NodeData("6", 6)));
        graph.put("8", Set.of(new NodeData("6", 6),
                new NodeData("4", 1)));

        int shortestPath = calculateShortestPath("1", "4", graph);
        return shortestPath;
    }

    private int calculateShortestPath(String src, String dst, Map<String, Set<NodeData>> graph) {
        PriorityQueue<PathData> queue = new PriorityQueue<>(
                Comparator.comparingInt(PathData::getCost));
        Map<String, PathData> costMap = new HashMap<>();

        Set<String> visitedSet = new HashSet<>();

        PathData srcData = new PathData(src, 0, src);
        queue.add(srcData);
        costMap.put(src, srcData);

        while (!queue.isEmpty()) {
            PathData current = queue.poll();
            if (dst.equals(current.node)) {
                return current.cost;
            } else if (!visitedSet.contains(current.node)) {
                visitedSet.add(current.node);
                for (NodeData neighbor : graph.get(current.node)) {
                    if (costMap.containsKey(neighbor.node)) {
                        PathData neighborData = costMap.get(neighbor.node);
                        if (neighborData.cost > neighbor.weight + current.cost) {
                            queue.remove(neighborData);

                            neighborData.setCost(neighbor.weight + current.cost);
                            neighborData.setFrom(current.from);

                            queue.add(neighborData);
                        }
                    } else {
                        PathData neighborData = new PathData(neighbor.node, neighbor.weight + current.cost, current.from);
                        costMap.put(neighbor.node, neighborData);
                        queue.add(neighborData);
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    class PathData {
        final String node;
        String from;

        Integer cost;

        PathData(String node, int cost, String from) {
            this.node = node;
            this.cost = cost;
            this.from = from;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public Integer getCost() {
            return cost;
        }

        public void setCost(Integer cost) {
            this.cost = cost;
        }
    }

    class NodeData {
        final String node;
        final int weight;

        NodeData(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }


}
