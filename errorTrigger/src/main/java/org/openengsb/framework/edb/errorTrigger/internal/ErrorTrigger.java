package org.openengsb.framework.edb.errorTrigger.internal;

import org.openengsb.core.api.edb.EDBErrorTrigger;
import org.openengsb.similarity.Index;

public class ErrorTrigger implements EDBErrorTrigger {

    private Index index;

    @Override
    public void recover() {
        index.buildIndex();
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}
