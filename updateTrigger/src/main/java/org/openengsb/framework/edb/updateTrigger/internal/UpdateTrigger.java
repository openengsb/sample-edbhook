package org.openengsb.framework.edb.updateTrigger.internal;

import java.io.IOException;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBUpdateTrigger;
import org.openengsb.similarity.Index;

public class UpdateTrigger implements EDBUpdateTrigger {

    private Index index = null;

    public UpdateTrigger() {
        try {
            index = new StandardIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(EDBCommit commit) {
        index.updateIndex(commit);
    }

}
