/**
 * Created by LPO on 08.09.2018
 */

package Alvic.utilities.graphs.entities;

import Alvic.utilities.graphs.models.CompleteLexicon;
import Alvic.utilities.graphs.models.DirectedGraph;
import Alvic.utilities.graphs.models.Graph;

import java.util.Collection;

public class DirectedExtendedHashGraph<N,L> implements DirectedGraph<N,L> {
    private CompleteLexicon<N,N,L> lexicon;

    public DirectedExtendedHashGraph(){
        lexicon = new CompleteHashLexicon<>();
    }

    public DirectedExtendedHashGraph(Graph<N,L> that){
        this();
        if(that != null) {
            for (N it : that.getNodes()) {
                for(N itt : that.getNodes(it)){
                    link(it,itt,that.getLink(it,itt));
                }
            }
        }
    }

    @Override
    public Collection<N> getAgainstNodes(N node) {
        return lexicon.getBeings(node);
    }

    @Override
    public N getAgainstNode(N node) {
        return lexicon.getBeing(node);
    }

    @Override
    public int countAgainstNodes(N node) {
        return lexicon.countBeings(node);
    }

    @Override
    public Collection<L> getAgainstLinks(N node) {
        return lexicon.getBeingGrades(node);
    }

    @Override
    public void addNode(N node) {
        lexicon.addBeing(node);
    }

    @Override
    public void removeNode(N node) {
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
        return lexicon.getTrait();
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
        lexicon.gradate(src,dest,link,link);
    }

    @Override
    public void detach(N src, N dest) {
        lexicon.removeTrait(src,dest);
    }

    @Override
    public Collection<L> getLinks() {
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
}
