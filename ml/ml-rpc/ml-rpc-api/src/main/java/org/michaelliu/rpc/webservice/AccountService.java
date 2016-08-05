package org.michaelliu.rpc.webservice;

import org.michaelliu.vo.AccountVo;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by michael on 2016/8/5.
 */
@WebService
public interface AccountService {

    AccountVo findById(Long id);

    List<AccountVo> findAll();

}
