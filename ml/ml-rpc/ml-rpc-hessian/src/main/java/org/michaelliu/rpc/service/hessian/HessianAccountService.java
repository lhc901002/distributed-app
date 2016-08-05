package org.michaelliu.rpc.service.hessian;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.dao.AccountMapper;
import org.michaelliu.rpc.service.AccountService;
import org.michaelliu.vo.AccountVo;

import java.util.List;

/**
 * Created by michael on 2016/8/4.
 *
 * Hessian: http://localhost:8080/hessian/AccountService
 */
public class HessianAccountService implements AccountService {

    private static final Log log = LogFactory.getLog(HessianAccountService.class);

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

    @Override
    public List<AccountVo> findByName(String name) {
        log.info("findByName receives name: " + name);
        List<AccountVo> accountList = accountMapper.selectByName(name);
        log.info("findByName responses: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
