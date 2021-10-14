package com.zime.consumerclient.mode;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Route{

    private String path;

    private String name;

    private String component;

    private Meta meta;

    private String redirect;

    private List<Route> children;


}
