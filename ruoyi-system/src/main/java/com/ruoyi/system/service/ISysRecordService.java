package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysRecord;
import java.util.List;

/**
 * 方量记录Service接口
 * 
 * @author ruoyi
 * @date 2019-12-08
 */
public interface ISysRecordService 
{
    /**
     * 查询方量记录
     * 
     * @param recordId 方量记录ID
     * @return 方量记录
     */
    public SysRecord selectSysRecordById(Long recordId);

    /**
     * 查询方量记录列表
     * 
     * @param sysRecord 方量记录
     * @return 方量记录集合
     */
    public List<SysRecord> selectSysRecordList(SysRecord sysRecord);

    /**
     * 新增方量记录
     * 
     * @param sysRecord 方量记录
     * @return 结果
     */
    public int insertSysRecord(SysRecord sysRecord);

    /**
     * 修改方量记录
     * 
     * @param sysRecord 方量记录
     * @return 结果
     */
    public int updateSysRecord(SysRecord sysRecord);

    /**
     * 批量删除方量记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysRecordByIds(String ids);

    /**
     * 删除方量记录信息
     * 
     * @param recordId 方量记录ID
     * @return 结果
     */
    public int deleteSysRecordById(Long recordId);
}
