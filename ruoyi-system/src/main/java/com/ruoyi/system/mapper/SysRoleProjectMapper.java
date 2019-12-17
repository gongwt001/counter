package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysRoleProject;

/**
 * 角色与项目关联表 数据层
 * 
 * @author ruoyi
 */
public interface SysRoleProjectMapper
{
    /**
     * 通过角色ID删除角色和项目关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleProjectByRoleId(Long roleId);

    /**
     * 批量删除角色项目关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleProject(Long[] ids);

    /**
     * 查询项目使用数量
     * 
     * @param projectId 项目ID
     * @return 结果
     */
    public int selectCountRoleProjectByProjectId(Long projectId);

    /**
     * 批量新增角色项目信息
     * 
     * @param roleProjectList 角色项目列表
     * @return 结果
     */
    public int batchRoleProject(List<SysRoleProject> roleProjectList);
}
