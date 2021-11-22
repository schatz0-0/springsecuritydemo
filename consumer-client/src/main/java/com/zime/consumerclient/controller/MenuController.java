package com.zime.consumerclient.controller;

import com.zime.consumerclient.service.MenuService;
import com.zime.consumerclient.vo.ResultGenerator;
import com.zime.consumerclient.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
public class MenuController {

    MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService){this.menuService=menuService;}

    @GetMapping("/getMenuListById")
    public ResultVo getMenuListByUserId(@RequestParam int id){
        log.info("访问到菜单接口");
        return ResultGenerator.genSuccessResult(menuService.getMenuListByUserId(id));
    }

}
