package org.openengsb.framework.edb.hooks.internal;

import java.io.IOException;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.hooks.EDBPostCommitHook;
import org.openengsb.similarity.Index;

public class EDBPostCommitHookImpl implements EDBPostCommitHook {

    private Index plcfunction = null;
    private Index plccombined = null;

    public EDBPostCommitHookImpl() {
        try {
            plcfunction = new PLCFunctionTextOneIndex();
            plccombined = new PLCCombinedIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPostCommit(EDBCommit commit) {
        plcfunction.updateIndex(commit);
        plccombined.updateIndex(commit);
    }
}
