package org.openengsb.framework.edb.edbHook.internal;

import java.io.IOException;
import java.util.List;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBHook;
import org.openengsb.core.api.edb.EDBObject;
import org.openengsb.similarity.Index;

public class EDBHookImpl implements EDBHook {

    private Index plcfunction = null;
    private Index plccombined = null;

    public EDBHookImpl() {
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

    @Override
    public List<List<String>> findCollisions(List<EDBObject> samples) {
        List<List<String>> result1 = plcfunction.findCollisions(samples);
        List<List<String>> result2 = plccombined.findCollisions(samples);

        result1.addAll(result2);
        return result1;
    }

    @Override
    public void recover() {
        plcfunction.buildIndex();
        plccombined.buildIndex();
    }

}
