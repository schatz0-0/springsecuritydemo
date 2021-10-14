package com.zime.consumerclient.service.impl;

import com.zime.consumerclient.mapper.MenuMapper;
import com.zime.consumerclient.mode.*;
import com.zime.consumerclient.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper){this.menuMapper=menuMapper;}

    @Override
    public Result getMenuListByUserId(int id) {
        List<MenuRoute> menus = menuMapper.getRoutesByUserId(id);
        if (menus == null){
            return new Result<List<Menu>>(
                    ReturnCode.ERROR.getCode(), ReturnCode.ERROR.getType(),
                    "查询用户菜单失败",null
            );
        }
        List<MenuRoute> menuRoute = new ArrayList<MenuRoute>();
        for (MenuRoute menu:menus){
            MenuRoute parent = menuMapper.getParentRouteByRouteId(menu.getId());
            parent = traverseToSetChildren(parent);
            if (parent !=null){
                MenuRoute lastChild=parent;
                while (lastChild.getChildren().size()>0)
                    lastChild=lastChild.getChildren().get(0);
                lastChild.getChildren().add(menu);
                menuRoute.add(parent);
            }
            else
                menuRoute.add(menu);
        }
        MenuRoute baseHome = menuMapper.getBaseHomeRoute();
        menuRoute.add(baseHome);
        List<MenuRoute> forestMenus=new ArrayList<MenuRoute>();
        traverseToInertMenu(forestMenus,menuRoute);
        List<Route> resultRoute = new ArrayList<Route>();
        traverseToCreateMenuList(forestMenus,resultRoute);
        return new Result<List<Route>>(
                ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getType(),
                "ok",resultRoute
        );
    }

    private void insertMenuIntoForest(List<MenuRoute> forestMenus,MenuRoute menu){
        if (menu.getParentid()<=0){
            if (!hasDuplicatedMenu(forestMenus, menu))
                forestMenus.add(menu);
            return;
        }
        for(MenuRoute thisMenu:forestMenus){
            if (thisMenu.getId()== menu.getParentid()){
                boolean hasDuplicateMenu=hasDuplicatedMenu(thisMenu.getChildren(),menu);
                if (!hasDuplicateMenu){
                    thisMenu.getChildren().add(menu);
                    return;
                }
            }
            else {
                insertMenuIntoForest(thisMenu.getChildren(), menu);
            }
        }
        return;
    }

    private void traverseToInertMenu(List<MenuRoute> forestMenus,List<MenuRoute> menus){
        if (menus == null) return;
        for (MenuRoute menu:menus){
            insertMenuIntoForest(forestMenus,menu);
            traverseToInertMenu(forestMenus,menu.getChildren());

        }
    }


    private boolean hasDuplicatedMenu(List<MenuRoute> forsetMenus,MenuRoute menu){
        boolean hasDuplicated=false;
        for (MenuRoute item:forsetMenus){
            if (item.equals(menu)){
                hasDuplicated=true;
                break;
            }
        }
        return hasDuplicated;
    }


    private MenuRoute traverseToSetChildren(MenuRoute currMenu){
        MenuRoute menu = currMenu;
        MenuRoute route = null,childRoute;
        if (menu.getParent() == null) return null;
        menu=menu.getParent();
        route=menu.clone();
        while (menu.getParent() != null){
            menu=menu.getParent();
            childRoute=route;
            route=menu.clone();
            route.getChildren().add(childRoute);
        }
        return route;
    }


    private void traverseToCreateMenuList(List<MenuRoute> routes, List<Route> menuList){
        if (routes == null) return;
        for (MenuRoute route:routes){
            Route menu = new Route();
            menu.setPath(route.getPath());
            menu.setName(route.getName());
            menu.setComponent(route.getComponent());
            menu.setMeta(route.getMeta());
            menu.setRedirect(route.getRedirect());
            menuList.add(menu);
            if (route.getChildren() != null)
                menu.setChildren(new ArrayList<Route>());
            traverseToCreateMenuList(route.getChildren(),menu.getChildren());
        }
    }
}
