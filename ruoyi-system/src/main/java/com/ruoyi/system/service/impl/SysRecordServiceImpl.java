package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysRecordMapper;
import com.ruoyi.system.domain.SysRecord;
import com.ruoyi.system.service.ISysRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 方量记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-12-08
 */
@Service
public class SysRecordServiceImpl implements ISysRecordService 
{
    @Autowired
    private SysRecordMapper sysRecordMapper;

    /**
     * 查询方量记录
     * 
     * @param recordId 方量记录ID
     * @return 方量记录
     */
    @Override
    public SysRecord selectSysRecordById(Long recordId)
    {
        return sysRecordMapper.selectSysRecordById(recordId);
    }

    /**
     * 查询方量记录列表
     * 
     * @param sysRecord 方量记录
     * @return 方量记录
     */
    @Override
    public List<SysRecord> selectSysRecordList(SysRecord sysRecord)
    {
        return sysRecordMapper.selectSysRecordList(sysRecord);
    }

    /**
     * 新增方量记录
     * 
     * @param sysRecord 方量记录
     * @return 结果
     */
    @Override
    public int insertSysRecord(SysRecord sysRecord)
    {
        return sysRecordMapper.insertSysRecord(sysRecord);
    }

    /**
     * 修改方量记录
     * 
     * @param sysRecord 方量记录
     * @return 结果
     */
    @Override
    public int updateSysRecord(SysRecord sysRecord)
    {
        return sysRecordMapper.updateSysRecord(sysRecord);
    }

    /**
     * 删除方量记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysRecordByIds(String ids)
    {
        return sysRecordMapper.deleteSysRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除方量记录信息
     * 
     * @param recordId 方量记录ID
     * @return 结果
     */
    @Override
    public int deleteSysRecordById(Long recordId)
    {
        return sysRecordMapper.deleteSysRecordById(recordId);
    }
}
