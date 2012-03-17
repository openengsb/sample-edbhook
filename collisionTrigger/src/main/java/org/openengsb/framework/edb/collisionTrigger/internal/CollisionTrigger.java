package org.openengsb.framework.edb.collisionTrigger.internal;

import java.util.List;

import org.openengsb.core.api.edb.EDBCollisionTrigger;
import org.openengsb.core.api.edb.EDBObject;
import org.openengsb.similarity.Index;

public class CollisionTrigger implements EDBCollisionTrigger {

    private Index index;

    @Override
    public List<List<String>> findCollisions(List<EDBObject> samples) {
        return index.findCollisions(samples);
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}
