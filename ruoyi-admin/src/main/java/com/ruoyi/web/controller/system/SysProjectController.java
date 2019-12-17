package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.service.ISysProjectService;

/**
 * 项目信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/project")
public class SysProjectController extends BaseController
{
    private String prefix = "system/project";

    @Autowired
    private ISysProjectService projectService;

    @RequiresPermissions("system:project:view")
    @GetMapping()
    public String project()
    {
        return prefix + "/project";
    }

    @RequiresPermissions("system:project:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysProject> list(SysProject project)
    {
        List<SysProject> projectList = projectService.selectProjectList(project);
        return projectList;
    }

    /**
     * 新增项目
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        mmap.put("project", projectService.selectProjectById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存项目
     */
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:project:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysProject project)
    {
        if (UserConstants.PROJECT_NAME_NOT_UNIQUE.equals(projectService.checkProjectNameUnique(project)))
        {
            return error("新增项目'" + project.getProjectName() + "'失败，项目名称已存在");
        }
        project.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{projectId}")
    public String edit(@PathVariable("projectId") Long projectId, ModelMap mmap)
    {
        SysProject project = projectService.selectProjectById(projectId);
        if (StringUtils.isNotNull(project) && 100L == projectId)
        {
            project.setParentName("无");
        }
        mmap.put("project", project);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:project:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysProject project)
    {
        if (UserConstants.PROJECT_NAME_NOT_UNIQUE.equals(projectService.checkProjectNameUnique(project)))
        {
            return error("修改项目'" + project.getProjectName() + "'失败，项目名称已存在");
        }
        else if (project.getParentId().equals(project.getProjectId()))
        {
            return error("修改项目'" + project.getProjectName() + "'失败，上级项目不能是自己");
        }
        project.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除
     */
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:project:remove")
    @GetMapping("/remove/{projectId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("projectId") Long projectId)
    {
        if (projectService.selectProjectCount(projectId) > 0)
        {
            return AjaxResult.warn("存在下级项目,不允许删除");
        }
        if (projectService.checkProjectExistUser(projectId))
        {
            return AjaxResult.warn("项目存在用户,不允许删除");
        }
        return toAjax(projectService.deleteProjectById(projectId));
    }

    /**
     * 校验项目名称
     */
    @PostMapping("/checkProjectNameUnique")
    @ResponseBody
    public String checkProjectNameUnique(SysProject project)
    {
        return projectService.checkProjectNameUnique(project);
    }

    /**
     * 选择项目树
     */
    @GetMapping("/selectProjectTree/{projectId}")
    public String selectProjectTree(@PathVariable("projectId") Long projectId, ModelMap mmap)
    {
        mmap.put("project", projectService.selectProjectById(projectId));
        return prefix + "/tree";
    }

    /**
     * 加载项目列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = projectService.selectProjectTree(new SysProject());
        return ztrees;
    }

    /**
     * 加载角色项目（数据权限）列表树
     */
    @GetMapping("/roleProjectTreeData")
    @ResponseBody
    public List<Ztree> projectTreeData(SysRole role)
    {
        List<Ztree> ztrees = projectService.roleProjectTreeData(role);
        return ztrees;
    }
}
