package com.pigasuo.side.webapp.s2.action.user;

import com.pigasuo.side.domain.Acctinfo;
import com.pigasuo.side.service.AcctinfoService;
import com.pigasuo.side.webapp.s2.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by linxuhong on 2017/8/18.
 */
public class UserAction extends BaseAction {

    @Autowired
    AcctinfoService acctinfoService;

    public String getList() {

        System.out.println(acctinfoService);
        Acctinfo good = acctinfoService.getAcctinfo(1);
        generateJsonpResult(1, MSG_FAILURE, good);
        return NONE;
    }


}
