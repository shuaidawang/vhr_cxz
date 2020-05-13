package com.chouxiaozi.vhr.controller.system.basic;

import com.chouxiaozi.vhr.model.Menu;
import com.chouxiaozi.vhr.model.Role;
import com.chouxiaozi.vhr.service.MenuService;
import com.chouxiaozi.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限组
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("/listRoles")
    public List<Role> listRoles() {
        return roleService.listRoles();
    }

    @GetMapping("/listMenus")
    public List<Menu> listMenus() {
        return menuService.listMenus();
    }
}
