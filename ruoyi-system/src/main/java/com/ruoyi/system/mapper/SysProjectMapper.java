package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SysProject;

/**
 * 项目管理 数据层
 * 
 * @author ruoyi
 */
public interface SysProjectMapper
{
    /**
     * 查询项目人数
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int selectProjectCount(SysProject project);

    /**
     * 查询项目是否存在用户
     * 
     * @param projectId 项目ID
     * @return 结果
     */
    public int checkProjectExistUser(Long projectId);

    /**
     * 查询项目管理数据
     * 
     * @param project 项目信息
     * @return 项目信息集合
     */
    public List<SysProject> selectProjectList(SysProject project);

    /**
     * 删除项目管理信息
     * 
     * @param projectId 项目ID
     * @return 结果
     */
    public int deleteProjectById(Long projectId);

    /**
     * 新增项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(SysProject project);

    /**
     * 修改项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(SysProject project);

    /**
     * 修改子元素关系
     * 
     * @param projects 子元素
     * @return 结果
     */
    public int updateProjectChildren(@Param("projects") List<SysProject> projects);

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
     * @param projectName 项目名称
     * @param parentId 父项目ID
     * @return 结果
     */
    public SysProject checkProjectNameUnique(@Param("projectName") String projectName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询项目
     *
     * @param roleId 角色ID
     * @return 项目列表
     */
    public List<String> selectRoleProjectTree(Long roleId);

    /**
     * 修改所在项目的父级项目状态
     * 
     * @param project 项目
     */
    public void updateProjectStatus(SysProject project);

    /**
     * 根据ID查询所有子项目
     * @param projectId 项目ID
     * @return 项目列表
     */
    public List<SysProject> selectChildrenProjectById(Long projectId);
}
