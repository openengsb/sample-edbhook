package org.openengsb.framework.edb.hooks.internal;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.hooks.EDBPostCommitHook;
import org.openengsb.similarity.Index;

public class EDBPostCommitHookImpl implements EDBPostCommitHook {

    private Index plcfunction = null;
    private Index plccombined = null;
    private static final Logger LOGGER = Logger.getLogger(EDBPostCommitHookImpl.class);

    public EDBPostCommitHookImpl() {
        try {
            plcfunction = new PLCFunctionTextOneIndex();
            plccombined = new PLCCombinedIndex();
        } catch (IOException e) {
            LOGGER.error("could not create indices for EDBPostCommitHook");
            LOGGER.debug(e.getStackTrace());
        }
    }

    @Override
    public void onPostCommit(EDBCommit commit) {
        plcfunction.updateIndex(commit);
        plccombined.updateIndex(commit);
    }
}
