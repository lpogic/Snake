package Alvic.utilities.graphs.models;

import java.util.Collection;

public interface Graph<N,L> {
    void addNode(N node);
    void removeNode(N node);
    Collection<N> getNodes();
    Collection<N> getNodes(N node);
    N getNode();
    N getNode(N node);
    int countNodes();
    int countNodes(N node);
    boolean containsNode(N node);
    void link(N src, N dest, L link);
    void detach(N src, N dest);
    Collection<L> getLinks();
    Collection<L> getLinks(N node);
    L getLink(N src, N dest);
    boolean linked(N src, N dest);
    void merge(Graph<N, L> that);
}
