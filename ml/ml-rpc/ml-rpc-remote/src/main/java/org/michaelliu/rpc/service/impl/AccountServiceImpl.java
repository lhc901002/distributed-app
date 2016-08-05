package org.michaelliu.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.dao.AccountMapper;
import org.michaelliu.entity.Account;
import org.michaelliu.rpc.service.AccountService;
import org.michaelliu.vo.AccountVo;

import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 8/4/16.
 *
 * RMI: rmi://localhost:1199/rmi/AccountService
 * Hessian: http://localhost:8080/hessian/AccountService
 */
public class AccountServiceImpl implements AccountService {

    private static final Log log = LogFactory.getLog(AccountService.class);

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

    @Override
    public Long saveOrUpdateSelective(AccountVo accountVo) throws Exception {
        log.info("saveOrUpdateSelective receives: " + accountVo);
        Account account = new Account();
        account.setId(accountVo.getId());
        account.setName(accountVo.getName());
        account.setBalance(accountVo.getBalance());
        int result;
        if (null == accountVo.getId()) {
            result = accountMapper.insertSelective(account);
        } else {
            account.setUpdateTime(new Date());
            result = accountMapper.updateByPrimaryKeySelective(account);
        }
        log.info("saveOrUpdateSelective entity: " + account);
        if (result > 0) {
            return account.getId();
        } else {
            throw new IllegalStateException("Insertion fail to create/update new record in `tb_account`!");
        }
    }

}
