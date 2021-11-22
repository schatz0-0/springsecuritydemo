package com.zime.consumerclient.pojo;


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
public class Menu {


    private Integer id;

    private String pattern;

    private List<Role> roles;

    private Route route;




}
