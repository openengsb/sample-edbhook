package org.openengsb.framework.edb.updateHook.internal;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBUpdateHook;
import org.openengsb.similarity.Index;

public class UpdateHook implements EDBUpdateHook {

    private Index index;

    @Override
    public void update(EDBCommit commit) {
        index.updateIndex(commit);
    }

    public void setIndex(Index index) {
        this.index = index;
    }

}
