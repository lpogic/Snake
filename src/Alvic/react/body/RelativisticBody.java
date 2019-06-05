package Alvic.react.body;

import java.util.*;

public class RelativisticBody {
    private Body body;
    private Set<Object> dwellDimensions;
    private Set<Object> affectDimensions;

    public RelativisticBody(Body body) {
        this.body = body;
        this.dwellDimensions = new HashSet<>();
        this.affectDimensions = new HashSet<>();
    }

    public Body getBody() {
        return body;
    }

    public Set<Object> getDwellDimensions() {
        return dwellDimensions;
    }

    public Set<Object> getAffectDimensions() {
        return affectDimensions;
    }

    public void addDwell(Collection<Object> dimensions){
        dwellDimensions.addAll(dimensions);
    }

    public void addAffect(Collection<Object> dimensions){
        affectDimensions.addAll(dimensions);
    }

    public static Builder builder(Body body){
        return new Builder(body);
    }

    public static class Builder {
        private RelativisticBody body;

        public Builder(Body body) {
            this.body = new RelativisticBody(body);
        }

        public Builder addDwell(Object ... dimensions){
            body.addDwell(Arrays.asList(dimensions));
            return this;
        }

        public Builder addAffect(Object ... dimensions){
            body.addAffect(Arrays.asList(dimensions));
            return this;
        }

        public Builder addBoth(Object ... dimensions){
            List<Object> dimensionList = Arrays.asList(dimensions);
            body.addDwell(dimensionList);
            body.addAffect(dimensionList);
            return this;
        }

        public RelativisticBody get(){
            return body;
        }
    }
}
