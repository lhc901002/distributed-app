package org.michaelliu.rpc.service.cxf;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.dao.AccountMapper;
import org.michaelliu.rpc.webservice.AccountService;
import org.michaelliu.vo.AccountVo;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by michael on 2016/8/5.
 *
 * WS: http://localhost:8080/webservice/AccountService?wsdl
 */
@WebService(endpointInterface = "org.michaelliu.rpc.webservice.AccountService")
public class CxfAccountService implements AccountService {

    private static final Log log = LogFactory.getLog(CxfAccountService.class);

    private AccountMapper accountMapper;

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountVo findById(Long id) {
        log.info("findById receives id: " + id);
        AccountVo accountVo = accountMapper.selectByPrimaryKey(id);
        log.info("findById responses: " + JSON.toJSONString(accountVo));
        return accountVo;
    }

    @Override
    public List<AccountVo> findAll() {
        List<AccountVo> accountList = accountMapper.selectAll();
        log.info("findAll responses: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
