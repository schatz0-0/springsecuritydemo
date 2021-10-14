package com.zime.consumerclient.mapper;


import com.zime.consumerclient.mode.Menu;
import com.zime.consumerclient.mode.MenuRoute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> getAllMenus();
    List<Menu> getMenuListByUserId(int id);
    List<MenuRoute> getRoutesByUserId(int id);
    MenuRoute getBaseHomeRoute();
    MenuRoute getParentRouteByRouteId(int id);
}
