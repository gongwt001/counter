package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysRole;

/**
 * 项目管理 服务层
 * 
 * @author ruoyi
 */
public interface ISysProjectService
{
    /**
     * 查询项目管理数据
     * 
     * @param project 项目信息
     * @return 项目信息集合
     */
    public List<SysProject> selectProjectList(SysProject project);

    /**
     * 查询项目管理树
     * 
     * @param project 项目信息
     * @return 所有项目信息
     */
    public List<Ztree> selectProjectTree(SysProject project);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Ztree> roleProjectTreeData(SysRole role);

    /**
     * 查询项目人数
     * 
     * @param parentId 父项目ID
     * @return 结果
     */
    public int selectProjectCount(Long parentId);

    /**
     * 查询项目是否存在用户
     * 
     * @param projectId 项目ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkProjectExistUser(Long projectId);

    /**
     * 删除项目管理信息
     * 
     * @param projectId 项目ID
     * @return 结果
     */
    public int deleteProjectById(Long projectId);

    /**
     * 新增保存项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(SysProject project);

    /**
     * 修改保存项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(SysProject project);

    /**
     * 根据项目ID查询信息
     * 
     * @param projectId 项目ID
     * @return 项目信息
     */
    public SysProject selectProjectById(Long projectId);

    /**
     * 校验项目名称是否唯一
     * 
     * @param project 项目信息
     * @return 结果
     */
    public String checkProjectNameUnique(SysProject project);
}
