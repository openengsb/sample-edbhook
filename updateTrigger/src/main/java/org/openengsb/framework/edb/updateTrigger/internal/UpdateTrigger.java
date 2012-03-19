package org.openengsb.framework.edb.updateTrigger.internal;

import java.io.IOException;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBUpdateTrigger;
import org.openengsb.similarity.Index;

public class UpdateTrigger implements EDBUpdateTrigger {

    private Index plcfunction = null;
    private Index plccombined = null;

    public UpdateTrigger() {
        try {
            plcfunction = new PLCFunctionTextOneIndex();
            plccombined = new PLCCombinedIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(EDBCommit commit) {
        plcfunction.updateIndex(commit);
        plccombined.updateIndex(commit);
    }

}
