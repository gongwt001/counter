package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysRecord;
import com.ruoyi.system.service.ISysRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 方量记录Controller
 * 
 * @author ruoyi
 * @date 2019-12-08
 */
@Controller
@RequestMapping("/system/record")
public class SysRecordController extends BaseController
{
    private String prefix = "system/record";

    @Autowired
    private ISysRecordService sysRecordService;

    @RequiresPermissions("system:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询方量记录列表
     */
    @RequiresPermissions("system:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRecord sysRecord)
    {
        startPage();
        List<SysRecord> list = sysRecordService.selectSysRecordList(sysRecord);
        return getDataTable(list);
    }

    /**
     * 导出方量记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "方量记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysRecord sysRecord)
    {
        List<SysRecord> list = sysRecordService.selectSysRecordList(sysRecord);
        ExcelUtil<SysRecord> util = new ExcelUtil<SysRecord>(SysRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增方量记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存方量记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "方量记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysRecord sysRecord)
    {
        return toAjax(sysRecordService.insertSysRecord(sysRecord));
    }

    /**
     * 修改方量记录
     */
    @GetMapping("/edit/{recordId}")
    public String edit(@PathVariable("recordId") Long recordId, ModelMap mmap)
    {
        SysRecord sysRecord = sysRecordService.selectSysRecordById(recordId);
        mmap.put("sysRecord", sysRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存方量记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "方量记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysRecord sysRecord)
    {
        return toAjax(sysRecordService.updateSysRecord(sysRecord));
    }

    /**
     * 删除方量记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "方量记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysRecordService.deleteSysRecordByIds(ids));
    }
}
