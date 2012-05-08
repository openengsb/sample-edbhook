package org.openengsb.framework.edb.hooks.internal;

import java.io.IOException;
import java.util.List;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBException;
import org.openengsb.core.api.edb.EDBObject;
import org.openengsb.core.api.edb.hooks.EDBPreCommitHook;
import org.openengsb.similarity.Index;

public class EDBPreCommitHookImpl implements EDBPreCommitHook {

    private Index plcfunction = null;
    private Index plccombined = null;

    public EDBPreCommitHookImpl() {
        try {
            plcfunction = new PLCFunctionTextOneIndex();
            plccombined = new PLCCombinedIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles only inserted EDBObjects
     */
    @Override
    public void onPreCommit(EDBCommit commit) throws EDBException {
        String exceptionmessage = "";
        Boolean faulty = false;

        List<EDBObject> inserts = commit.getInserts();

        List<List<String>> insert1Analyzed = plcfunction.findCollisions(inserts);
        exceptionmessage += "The plc-function-index found following problems: \n";
        createErrorMessage(exceptionmessage, inserts, insert1Analyzed, faulty);

        List<List<String>> insert2Analyzed = plccombined.findCollisions(inserts);
        exceptionmessage += "The plc-combined-index found following problems: \n";
        createErrorMessage(exceptionmessage, inserts, insert2Analyzed, faulty);

        if (faulty) {
            throw new EDBException(exceptionmessage);
        }
    }

    private void createErrorMessage(String exceptionmessage, List<EDBObject> EDBObjects,
            List<List<String>> analyzedEDBObjects, Boolean faulty) {
        int counter = 0;
        for (List<String> sublist : analyzedEDBObjects) {
            if (sublist.size() > 0) {
                faulty = true;
                exceptionmessage +=
                    "Object " + EDBObjects.get(counter).getOID() + " collides with following existing elements: ";
                for (String oid : sublist) {
                    exceptionmessage += oid + " ";
                }
                exceptionmessage += "\n";
            }
            counter++;
        }
    }
}
