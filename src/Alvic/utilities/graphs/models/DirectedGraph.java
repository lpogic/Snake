package Alvic.utilities.graphs.models;

import java.util.Collection;

public interface DirectedGraph<N,L> extends Graph<N,L> {
    Collection<N> getAgainstNodes(N node);
    N getAgainstNode(N node);
    int countAgainstNodes(N node);
    Collection<L> getAgainstLinks(N node);
}
