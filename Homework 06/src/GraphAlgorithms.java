import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * Your implementation of various graph algorithms.
 *
 * @author Andrew Friedman
 * @version 1.0
 * @userid afriedman38
 * @GTID 903506792
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        assert (!isNull(start) || !isNull(graph))
                && graph.getAdjList().containsKey(start) : "args are null...";

        HashSet<Vertex<T>> visited = new HashSet<>();
        ArrayList<Vertex<T>> result = new ArrayList<>();

        dfs(start, graph, visited, result);
        return result;
    }

    /**
     * dfs recursive helper method
     *
     * @param <T> the generic typing of the data
     * @param vertex the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param vs the set of visited vertexs
     * @param result the list returned
     */
    private static <T> void dfs(Vertex<T> vertex, Graph<T> graph,
                                Set<Vertex<T>> vs, List<Vertex<T>> result) {
        result.add(vertex);
        vs.add(vertex);

        var getV = graph.getAdjList().get(vertex);
        for (int i = 0, getSize = getV.size(); i < getSize; i++) {
            var tempV = getV.get(i);
            if (!vs.contains(tempV.getVertex())) {
                dfs(tempV.getVertex(), graph, vs, result);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        assert !isNull(graph) : "graph is null...";
        assert graph.getVertices().contains(start) : "graph has no start vertex...";

        HashMap<Vertex<T>, Integer> mapH = new HashMap<>();
        var adjL = graph.getAdjList();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        ArrayList<Vertex<T>> vList = new ArrayList<>();
        adjL.keySet().forEach(vertex -> {
            mapH.put(vertex, vertex.equals(start) ? 0 : Integer.MAX_VALUE);
        });

        pq.add(new VertexDistance<>(start, 0));

        while (vList.size() < adjL.size() && !(pq.isEmpty())) {
            var temp = pq.remove();
            vList.add(temp.getVertex());
            List<VertexDistance<T>> get = adjL.get(temp.getVertex());
            for (int i = 0, getSize = get.size(); i < getSize; i++) {
                var tempV = get.get(i);
                int newD;
                newD = temp.getDistance() + tempV.getDistance();
                if (!vList.contains(tempV.getVertex()) && mapH.get(tempV.getVertex()) > newD) {
                    mapH.put(tempV.getVertex(), newD);
                    pq.add(new VertexDistance<>(tempV.getVertex(),
                            newD));
                }
            }
        }
        return mapH;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        assert !isNull(start) && !isNull(graph)
                && graph.getVertices().contains(start)
                : "args are null or graph has no start...";

        HashSet<Vertex<T>> tempVS = new HashSet<>();
        HashSet<Edge<T>> tempM = new HashSet<>();
        var priQ = new PriorityQueue<Edge<T>>();
        var edges = graph.getEdges();
        var itr = edges.iterator();

        while (itr.hasNext()) {
            Edge<T> edge;
            edge = itr.next();
            if (edge.getU().equals(start)) {
                priQ.add(edge);
            }
        }

        tempVS.add(start);

        while (!priQ.isEmpty() && tempVS.size() != graph.getVertices().size()) {
            Edge<T> edge = priQ.remove();
            if (!tempVS.contains(edge.getV())) {
                tempVS.add(edge.getV());
                tempM.add(edge);
                tempM.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
                edges.stream().filter(ed -> ed.getU().equals(edge.getV())
                        && !tempVS.contains(ed.getV())).forEachOrdered(priQ::add);
            }
        }

        return tempM;
    }
}
