package com.zime.consumerclient.mode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-05-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRoute {


    private Integer id;

    private String path;

    private String name;

    private String component;

    private Meta meta;

    private String redirect;

    private Integer parentid;

    private MenuRoute parent;

    private List<MenuRoute> children;


    public MenuRoute clone(){
        MenuRoute route = new MenuRoute();
        route.id = this.id;
        route.name=this.name;
        route.path=this.path;
        route.meta= new Meta(this.meta.getTitle(),this.meta.isAffix(),this.meta.getIcon());
        route.component=this.component;
        route.parentid=this.parentid;
        route.children=new ArrayList<MenuRoute>();
        return route;
    }

    public boolean equals(MenuRoute route){return this.id == route.id;}

}
