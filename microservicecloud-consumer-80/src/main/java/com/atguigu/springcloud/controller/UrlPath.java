package com.atguigu.springcloud.controller;

import java.net.URI;
/**
 *
 * url path that RestTemplate invoke
 *
 */
public class UrlPath {
    /**
    * user service
     */
    static String prefix="http://myzuul.com:9527/user";
    public final static String GET_USER = prefix +"/admin/user/getOne";
    public final static String GET_USER_LIST = prefix +"/admin/user/list";
    public final static String ADD_USER = prefix +"/admin/user/add";
    public final static String UPDATE_USER = prefix +"/admin/user/update";
    public final static String UPDATE_USER_PWD = prefix +"/admin/user/set_new_pwd";
    /**
     * role service
     */
    public final static String GET_ROLE_LIST = prefix +"/admin/role/list";
    public final static String GET_ROLE_MENU = prefix +"/admin/login";
    public final static String GET_ROLE = prefix +"/admin/role/getOne";
    public final static String ADD_ROLE = prefix +"/admin/role/add";
    public final static String UPDATE_ROLE = prefix +"/admin/role/update";
    public final static String DELETE_ROLE = prefix +"/admin/role/delete";
    public final static String GET_CHECKED_MENU = prefix +"/admin/role/getCheckedMenuData";
    public final static String UPDATE_ROLE_MENU = prefix +"/admin/role/updateMenu";
    /**
    * menu service
     */
    public final static String GET_MENU = prefix +"/admin/menu/getOne";
    public final static String ADD_MENU = prefix +"/admin/menu/add";
    public final static String UPDATE_MENU = prefix +"/admin/menu/update";
    public final static String GET_MENU_LIST = prefix +"/admin/menu/list";
    public final static String DELETE_MENU = prefix +"/admin/menu/delete";
    /**
     * order service
     */
    static String prefix_order="http://myzuul.com:9527/order";

    public final static String GET_ORDER =  prefix_order +"/admin/order/getOne";
    public final static String GET_ORDER_LIST =  prefix_order +"/admin/order/list";
    public final static String DELETE_ORDER =  prefix_order +"/admin/order/delete";
    /**
     * park service
     */
    static String prefix_park="http://myzuul.com:9527/park";

    public final static String GET_PARK =  prefix_park +"/admin/park/getOne";
    public final static String ADD_PARK =  prefix_park +"/admin/park/add";
    public final static String UPDATE_PARK =  prefix_park +"/admin/park/update";
    public final static String GET_PARK_LIST =  prefix_park +"/admin/park/list";
    public final static String DELETE_PARK =  prefix_park +"/admin/park/delete";
}
