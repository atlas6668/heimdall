/**
 * <p>Title: BaseEntity.java</p>
 * <p>Description: 实体顶级父类，声明id属性，实现序列化</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: xiaoma.com</p>
 * @author damon
 * @date 2014年7月23日
 * @version 1.0
 */
package com.wx.serveplatform.base.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <p>Title: BaseEntity</p>
 * <p>Description: 实体顶级父类，声明id属性，实现序列化</p>
 * <p>Company: xiaoma.com</p> 
 * @author damon
 * @date 2014年7月23日
 */
@MappedSuperclass
public class BaseEntity implements java.io.Serializable {
    /** serialVersionUID */
	private static final long serialVersionUID = 4796878092680372098L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return (this.id == null);
    }
    
}