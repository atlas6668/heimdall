/**
 * <p>Title: QrcodeRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年5月27日
 * @version 1.0
 */
package com.wx.serveplatform.repository.sdj;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wx.serveplatform.base.repository.sdj.BaseRepository;
import com.wx.serveplatform.model.Qrcode;

/**
 * <p>Title: QrcodeRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年5月27日
 */
public interface QrcodeRepository extends BaseRepository<Qrcode, Integer>, JpaSpecificationExecutor<Qrcode>  {

}
