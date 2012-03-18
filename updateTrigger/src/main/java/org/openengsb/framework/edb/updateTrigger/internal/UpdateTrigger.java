package org.openengsb.framework.edb.updateTrigger.internal;

import java.io.IOException;

import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBUpdateTrigger;
import org.openengsb.similarity.Index;

public class UpdateTrigger implements EDBUpdateTrigger {

    private Index standard = null;
    private Index complex = null;

    public UpdateTrigger() {
        try {
            standard = new StandardIndex();
            complex = new ComplexIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(EDBCommit commit) {
        standard.updateIndex(commit);
        complex.updateIndex(commit);
    }

}
