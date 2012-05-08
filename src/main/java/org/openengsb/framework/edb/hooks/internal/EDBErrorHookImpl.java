package org.openengsb.framework.edb.hooks.internal;

import org.openengsb.core.api.edb.EDBCheckException;
import org.openengsb.core.api.edb.EDBCommit;
import org.openengsb.core.api.edb.EDBException;
import org.openengsb.core.api.edb.hooks.EDBErrorHook;

public class EDBErrorHookImpl implements EDBErrorHook {

    public EDBErrorHookImpl() {
    }

    @Override
    public EDBCommit onError(EDBCommit commit, Exception exception) throws EDBException {
        if (exception instanceof EDBCheckException) {
            // alternatively do some magic and try to repair the commit, based on some rules
            throw new EDBException(exception.getMessage());
        }
        return null;
    }

}
