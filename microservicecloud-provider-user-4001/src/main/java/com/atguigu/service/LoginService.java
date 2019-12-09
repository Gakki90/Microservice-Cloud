package com.atguigu.service;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.dao.RoleMenuDAO;
import com.atguigu.dao.UserDAO;
import com.atguigu.springcloud.bean.Menu;
import com.atguigu.springcloud.bean.RoleMenu;
import com.atguigu.springcloud.bean.User;
import com.atguigu.springcloud.utils.JwtUtil;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    MenuService menuService;
    @Autowired
    RoleMenuDAO roleMenuDao;
    @Autowired
    JwtUtil jwtUtil;

    public JSONObject Login(String name, String password) throws Exception {
        User user=userDAO.findByName(name);
        JSONObject result=new JSONObject();
        if(user==null){
            result.put("msg","用戶名不存在");
            result.put("success","false");

        }
        else if(encoder.matches(password,user.getPwd())){
            result.put("msg","登錄成功");
            result.put("success","true");
            result.put("roleId",user.getRole().getId());
            result.put("user",user);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pId", -1);
            List<Menu> menuList = menuService.list(map , 0, 100);
            List<JSONObject>  list =  new ArrayList<JSONObject>();
            for (Menu menu : menuList) {
                //当前下当前用户的角色  有没有这个菜单
                RoleMenu roleMenu=	roleMenuDao.findByRoleIdAndMenuId(user.getRole().getId(), menu.getId());
                if(roleMenu!=null) {
                    JSONObject node = new JSONObject();
                    node.put("id", menu.getId());
                    node.put("text", menu.getName());
                    node.put("url", menu.getUrl());
                    node.put("type", menu.getType());
                    node.put("icon", menu.getIcon());
                    node.put("divId", menu.getDivId());
                    node.put("children", getChildren(menu.getId(),user.getRole().getId()));
                    list.add(node);
                }
            }
            result.put("treeList", list);
            //生成令牌
            String token=jwtUtil.createJWT(String.valueOf(user.getId()),user.getName(),user.getRole().getName());
            result.put("token",token);
        }
        else {
            result.put("msg","密碼錯誤");
            result.put("success","false");

        }
        return result;
    }
    /**
     * 辅助方法  辅助 上面的 admin_main（getCheckedTreeMenu）
     * @param pId
     * @param roleId
     * @return
     */
    public List<JSONObject> getChildren(Integer pId, Integer roleId)  throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pId", pId);
        List<Menu> menuList = menuService.list(map , 0, 100);

        List<JSONObject>  list =  new ArrayList<JSONObject>();
        for (Menu menu : menuList) {
            RoleMenu roleMenu=	roleMenuDao.findByRoleIdAndMenuId(roleId, menu.getId());
            if(roleMenu!=null){
                JSONObject node = new JSONObject();
                node.put("id", menu.getId());
                node.put("text", menu.getName());
                node.put("url", menu.getUrl());
                node.put("type", menu.getType());
                node.put("icon", menu.getIcon());
                node.put("divId", menu.getDivId());
                list.add(node);
            }
        }
        return list;
    }

}
