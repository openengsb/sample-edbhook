package org.openengsb.framework.edb.updateHook.internal;

import java.util.List;

import org.openengsb.core.api.edb.EDBObject;
import org.openengsb.core.api.edb.EDBUpdateHook;
import org.openengsb.similarity.Index;

public class UpdateHook implements EDBUpdateHook {

    private Index index;

    @Override
    public void update(List<EDBObject> inserts, List<EDBObject> updates, List<EDBObject> deletes) {
        index.updateIndex(inserts, updates, deletes);
    }

    public void setIndex(Index index) {
        this.index = index;
    }

}
