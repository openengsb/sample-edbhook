package org.openengsb.framework.edb.updateTrigger.internal;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBUpdateTrigger;
import org.openengsb.similarity.Index;

public class UpdateTrigger implements EDBUpdateTrigger {

    private Index index;

    @Override
    public void update(EDBCommit commit) {
        index.updateIndex(commit);
    }

    public void setIndex(Index index) {
        this.index = index;
    }

}
