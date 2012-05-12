package org.openengsb.framework.edb.hooks.internal;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBException;
import org.openengsb.core.api.edb.EDBObject;
import org.openengsb.core.api.edb.hooks.EDBPreCommitHook;
import org.openengsb.similarity.Index;

public class EDBPreCommitHookImpl implements EDBPreCommitHook {

    private Index plcfunction = null;
    private Index plccombined = null;
    private static final Logger LOGGER = Logger.getLogger(EDBPreCommitHookImpl.class);

    public EDBPreCommitHookImpl() {
        try {
            plcfunction = new PLCFunctionTextOneIndex();
            plccombined = new PLCCombinedIndex();
        } catch (IOException e) {
            LOGGER.error("could not create indices for EDBPostCommitHook");
            LOGGER.debug(e.getStackTrace());
        }
    }

    /**
     * handles only inserted EDBObjects
     */
    @Override
    public void onPreCommit(EDBCommit commit) throws EDBException {
        StringBuilder exceptionmessage = new StringBuilder();
        Boolean faulty = false;

        List<EDBObject> inserts = commit.getInserts();

        List<List<String>> insert1Analyzed = plcfunction.findCollisions(inserts);
        exceptionmessage.append("The plc-function-index found following problems: \n");
        exceptionmessage.append(createErrorMessage(inserts, insert1Analyzed, faulty));

        List<List<String>> insert2Analyzed = plccombined.findCollisions(inserts);
        exceptionmessage.append("The plc-combined-index found following problems: \n");
        exceptionmessage.append(createErrorMessage(inserts, insert2Analyzed, faulty));

        if (faulty) {
            LOGGER.info("exception occured with content" + exceptionmessage.toString());
            throw new EDBException(exceptionmessage.toString());
        }
    }

    private String createErrorMessage(List<EDBObject> EDBObjects,
            List<List<String>> analyzedEDBObjects, Boolean faulty) {
        StringBuilder exceptionmessage = new StringBuilder();
        int counter = 0;
        for (List<String> collisionCandidates : analyzedEDBObjects) {
            if (!collisionCandidates.isEmpty()) {
                faulty = true;
                exceptionmessage.append(
                    "Object " + EDBObjects.get(counter).getOID() + " collides with following existing elements: ");
                for (String oid : collisionCandidates) {
                    exceptionmessage.append(oid + " ");
                }
                exceptionmessage.append("\n");
            }
            counter++;
        }
        return exceptionmessage.toString();
    }
}
