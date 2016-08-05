package org.michaelliu.dao;

import org.michaelliu.entity.Account;
import org.michaelliu.vo.AccountVo;

import java.util.List;

/**
 * Created by Michael on 8/4/16.
 */
public interface AccountMapper {

    AccountVo selectByPrimaryKey(Long id);

    List<AccountVo> selectAll();

    List<AccountVo> selectByName(String name);

    List<AccountVo> selectByIdList(List<Long> idList);

    int insertSelective(Account account);

    int updateByPrimaryKeySelective(Account account);

}
