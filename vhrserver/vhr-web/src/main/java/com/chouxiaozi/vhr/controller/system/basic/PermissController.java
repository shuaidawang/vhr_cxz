package com.chouxiaozi.vhr.controller.system.basic;

import com.chouxiaozi.model.Menu;
import com.chouxiaozi.model.Role;
import com.chouxiaozi.service.MenuService;
import com.chouxiaozi.service.RoleService;
import com.chouxiaozi.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Cacheable
    public List<Menu> listMenus() {
        return menuService.listMenus();
    }

    /**
     * 获取角色拥有的菜单
     *
     * @return
     */
    @GetMapping("/listMenus/{roleId}")
    public List<Integer> listMenusByRoleId(@PathVariable("roleId") Integer roleId) {
        return menuService.listMenusByRoleId(roleId);
    }

    /**
     * 更新菜单权限
     */
    @PutMapping("/updateRoleMenus")
    public RespBean updateRoleMenus(Integer rid, Integer[] menuIds) {
        if (menuService.updateRoleMenus(rid, menuIds)) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @DeleteMapping("/{id}")
    public RespBean delRole(@PathVariable("id") Integer id){
        return roleService.delRole(id);
    }
}
