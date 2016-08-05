package org.michaelliu.rpc.service;

import org.michaelliu.vo.AccountVo;

import java.util.List;

/**
 * Created by Michael on 8/4/16.
 */
public interface AccountService {

    AccountVo findById(Long id);

    List<AccountVo> findAll();

    List<AccountVo> findByName(String name);

    Long saveOrUpdateSelective(AccountVo account) throws Exception;

}
