/**
 * Created by LPO on 08.09.2018
 */

package Alvic.utilities.graphs.entities;

import Alvic.utilities.graphs.models.DirectedGraph;
import Alvic.utilities.graphs.models.Graph;
import Alvic.utilities.graphs.models.Lexicon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirectedHashGraph<N,L> implements DirectedGraph<N,L> {
    private Lexicon<N,N,L> lexicon;

    public DirectedHashGraph(){
        lexicon = new HashLexicon<>();
    }

    public DirectedHashGraph(Graph<N,L> that){
        this();
        if(that != null) {
            for (N it : that.getNodes()) {
                for(N itt : that.getNodes(it)){
                    link(it,itt,that.getLink(it,itt));
                }
            }
        }
    }

    protected final void set(DirectedHashGraph<N,L> that){
        this.lexicon = that.lexicon;
    }

    protected Lexicon<N,N,L> getLexicon(){
        return lexicon;
    }

    @Override
    public void addNode(N node) {
        lexicon.addBeing(node);
    }

    @Override
    public void removeNode(N node) {
        for(N it : getNodes()){
            detach(it,node);
        }
        lexicon.removeBeing(node);
    }

    @Override
    public Collection<N> getNodes() {
        return lexicon.getBeings();
    }

    @Override
    public Collection<N> getNodes(N node) {
        return lexicon.getTraits(node);
    }

    @Override
    public N getNode() {
        return lexicon.getBeing();
    }

    @Override
    public N getNode(N node) {
        return lexicon.getTrait(node);
    }

    @Override
    public int countNodes() {
        return lexicon.countBeings();
    }

    @Override
    public int countNodes(N node) {
        return lexicon.countTraits(node);
    }

    @Override
    public boolean containsNode(N node) {
        return lexicon.containsBeing(node);
    }

    @Override
    public void link(N src, N dest, L link) {
        lexicon.addBeing(dest);
        lexicon.gradate(src,dest,link);
    }

    @Override
    public void detach(N src, N dest) {
        lexicon.removeTrait(src,dest);
    }

    @Override
    public List<L> getLinks() {
        return lexicon.getGrades();
    }

    @Override
    public Collection<L> getLinks(N node) {
        return lexicon.getGrades(node);
    }

    @Override
    public L getLink(N src, N dest) {
        return lexicon.getGrade(src,dest);
    }

    @Override
    public boolean linked(N src, N dest) {
        return lexicon.hasTrait(src,dest);
    }

    @Override
    public void merge(Graph<N, L> that) {
        for(N it : that.getNodes()){
            for(N itt : that.getNodes(it)){
                link(it,itt,that.getLink(it,itt));
            }
        }
    }

    @Override
    public Collection<N> getAgainstNodes(N node) {
        List<N> nodes = new ArrayList<>();
        for(N it : getNodes()){
            if(linked(it,node))
                nodes.add(it);
        }
        return nodes;
    }

    @Override
    public N getAgainstNode(N node) {
        for(N it : getNodes()){
            if(linked(it,node))
                return it;
        }
        return null;
    }

    @Override
    public int countAgainstNodes(N node) {
        int counter = 0;
        for(N it : getNodes()){
            if(linked(it,node))
                ++counter;
        }
        return counter;
    }

    @Override
    public Collection<L> getAgainstLinks(N node) {
        List<L> links = new ArrayList<>();
        L link;
        for(N it : getNodes()){
            link = getLink(it,node);
            if(link != null)
                links.add(link);
        }
        return links;
    }
}
