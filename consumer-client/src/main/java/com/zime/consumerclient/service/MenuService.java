package com.zime.consumerclient.service;

import com.zime.consumerclient.vo.ResultVo;

public interface MenuService {
    ResultVo getMenuListByUserId(int id);
}
