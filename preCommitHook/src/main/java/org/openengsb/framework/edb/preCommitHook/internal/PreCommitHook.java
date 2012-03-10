package org.openengsb.framework.edb.preCommitHook.internal;

import java.util.List;

import org.openengsb.core.api.edb.EDBHook;
import org.openengsb.core.api.edb.EDBObject;
import org.openengsb.similarity.Searcher;

public class PreCommitHook implements EDBHook {

    private Searcher searcher;

    @Override
    public Object execute(List<EDBObject> inserts, List<EDBObject> updates, List<EDBObject> deletes,
            List<EDBObject> samples) {
        return searcher.findCollisions(samples);
    }

}
