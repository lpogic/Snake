package Alvic.print;

import Alvic.react.body.RigidBody;

public abstract class RigidBodyOutfit {
    private RigidBody body;

    public RigidBodyOutfit(RigidBody body) {
        this.body = body;
    }

    public RigidBody getBody() {
        return body;
    }

    public void setBody(RigidBody body) {
        this.body = body;
    }

    public abstract void print();
}
