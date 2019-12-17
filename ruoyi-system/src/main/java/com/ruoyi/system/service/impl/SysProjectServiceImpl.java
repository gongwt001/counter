package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.service.ISysProjectService;

/**
 * 项目管理 服务实现
 * 
 * @author ruoyi
 */
@Service
public class SysProjectServiceImpl implements ISysProjectService
{
    @Autowired
    private SysProjectMapper projectMapper;

    /**
     * 查询项目管理数据
     * 
     * @param project 项目信息
     * @return 项目信息集合
     */
    @Override
    @DataScope(projectAlias = "d")
    public List<SysProject> selectProjectList(SysProject project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 查询项目管理树
     * 
     * @param project 项目信息
     * @return 所有项目信息
     */
    @Override
    @DataScope(projectAlias = "d")
    public List<Ztree> selectProjectTree(SysProject project)
    {
        List<SysProject> projectList = projectMapper.selectProjectList(project);
        List<Ztree> ztrees = initZtree(projectList);
        return ztrees;
    }

    /**
     * 根据角色ID查询项目（数据权限）
     *
     * @param role 角色对象
     * @return 项目列表（数据权限）
     */
    @Override
    public List<Ztree> roleProjectTreeData(SysRole role)
    {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysProject> projectList = selectProjectList(new SysProject());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleProjectList = projectMapper.selectRoleProjectTree(roleId);
            ztrees = initZtree(projectList, roleProjectList);
        }
        else
        {
            ztrees = initZtree(projectList);
        }
        return ztrees;
    }

    /**
     * 对象转项目树
     *
     * @param projectList 项目列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysProject> projectList)
    {
        return initZtree(projectList, null);
    }

    /**
     * 对象转项目树
     *
     * @param projectList 项目列表
     * @param roleProjectList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysProject> projectList, List<String> roleProjectList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleProjectList);
        for (SysProject project : projectList)
        {
            if (UserConstants.PROJECT_NORMAL.equals(project.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(project.getProjectId());
                ztree.setpId(project.getParentId());
                ztree.setName(project.getProjectName());
                ztree.setTitle(project.getProjectName());
                if (isCheck)
                {
                    ztree.setChecked(roleProjectList.contains(project.getProjectId() + project.getProjectName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询项目人数
     * 
     * @param parentId 项目ID
     * @return 结果
     */
    @Override
    public int selectProjectCount(Long parentId)
    {
        SysProject project = new SysProject();
        project.setParentId(parentId);
        return projectMapper.selectProjectCount(project);
    }

    /**
     * 查询项目是否存在用户
     * 
     * @param projectId 项目ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkProjectExistUser(Long projectId)
    {
        int result = projectMapper.checkProjectExistUser(projectId);
        return result > 0 ? true : false;
    }

    /**
     * 删除项目管理信息
     * 
     * @param projectId 项目ID
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long projectId)
    {
        return projectMapper.deleteProjectById(projectId);
    }

    /**
     * 新增保存项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public int insertProject(SysProject project)
    {
        SysProject info = projectMapper.selectProjectById(project.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.PROJECT_NORMAL.equals(info.getStatus()))
        {
            throw new BusinessException("项目停用，不允许新增");
        }
        project.setAncestors(info.getAncestors() + "," + project.getParentId());
        return projectMapper.insertProject(project);
    }

    /**
     * 修改保存项目信息
     * 
     * @param project 项目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateProject(SysProject project)
    {
        SysProject newParentProject = projectMapper.selectProjectById(project.getParentId());
        SysProject oldProject = selectProjectById(project.getProjectId());
        if (StringUtils.isNotNull(newParentProject) && StringUtils.isNotNull(oldProject))
        {
            String newAncestors = newParentProject.getAncestors() + "," + newParentProject.getProjectId();
            String oldAncestors = oldProject.getAncestors();
            project.setAncestors(newAncestors);
            updateProjectChildren(project.getProjectId(), newAncestors, oldAncestors);
        }
        int result = projectMapper.updateProject(project);
        if (UserConstants.PROJECT_NORMAL.equals(project.getStatus()))
        {
            // 如果该项目是启用状态，则启用该项目的所有上级项目
            updateParentProjectStatus(project);
        }
        return result;
    }

    /**
     * 修改该项目的父级项目状态
     * 
     * @param project 当前项目
     */
    private void updateParentProjectStatus(SysProject project)
    {
        String updateBy = project.getUpdateBy();
        project = projectMapper.selectProjectById(project.getProjectId());
        project.setUpdateBy(updateBy);
        projectMapper.updateProjectStatus(project);
    }

    /**
     * 修改子元素关系
     * 
     * @param projectId 被修改的项目ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateProjectChildren(Long projectId, String newAncestors, String oldAncestors)
    {
        List<SysProject> children = projectMapper.selectChildrenProjectById(projectId);
        for (SysProject child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            projectMapper.updateProjectChildren(children);
        }
    }

    /**
     * 根据项目ID查询信息
     * 
     * @param projectId 项目ID
     * @return 项目信息
     */
    @Override
    public SysProject selectProjectById(Long projectId)
    {
        return projectMapper.selectProjectById(projectId);
    }

    /**
     * 校验项目名称是否唯一
     * 
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public String checkProjectNameUnique(SysProject project)
    {
        Long projectId = StringUtils.isNull(project.getProjectId()) ? -1L : project.getProjectId();
        SysProject info = projectMapper.checkProjectNameUnique(project.getProjectName(), project.getParentId());
        if (StringUtils.isNotNull(info) && info.getProjectId().longValue() != projectId.longValue())
        {
            return UserConstants.PROJECT_NAME_NOT_UNIQUE;
        }
        return UserConstants.PROJECT_NAME_UNIQUE;
    }
}
