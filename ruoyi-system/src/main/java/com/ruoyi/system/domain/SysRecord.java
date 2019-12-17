package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 方量记录对象 sys_record
 * 
 * @author ruoyi
 * @date 2019-12-08
 */
public class SysRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录id */
    private Long recordId;

    /** 项目名 */
    @Excel(name = "项目名")
    private String projectName;

    /** 用户Id */
    @Excel(name = "用户Id")
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String loginName;

    /** 方量公式 */
    @Excel(name = "方量公式")
    private String expression;

    /** 方量结果 */
    @Excel(name = "方量结果")
    private String result;

    /** 单价 */
    @Excel(name = "单价")
    private String price;

    /** 所得 */
    @Excel(name = "所得")
    private String gains;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 创建日期 */
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdate;

    /** 更新日期 */
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedate;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setExpression(String expression) 
    {
        this.expression = expression;
    }

    public String getExpression() 
    {
        return expression;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setGains(String gains) 
    {
        this.gains = gains;
    }

    public String getGains() 
    {
        return gains;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCreatedate(Date createdate) 
    {
        this.createdate = createdate;
    }

    public Date getCreatedate() 
    {
        return createdate;
    }
    public void setUpdatedate(Date updatedate) 
    {
        this.updatedate = updatedate;
    }

    public Date getUpdatedate() 
    {
        return updatedate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("projectName", getProjectName())
            .append("userId", getUserId())
            .append("loginName", getLoginName())
            .append("expression", getExpression())
            .append("result", getResult())
            .append("price", getPrice())
            .append("gains", getGains())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createdate", getCreatedate())
            .append("updateBy", getUpdateBy())
            .append("updatedate", getUpdatedate())
            .append("remark", getRemark())
            .toString();
    }
}
