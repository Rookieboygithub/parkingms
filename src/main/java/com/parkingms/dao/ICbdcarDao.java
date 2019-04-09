package com.parkingms.dao;



import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.parkingms.bean.CbdcarBean;
/**
 * 
 * @author lLty
 *李同遥
 */
public interface ICbdcarDao {
	/**
	 * 
	 *添加车位，支持批量批量添加和单个添加，批量添加车位车位编号格式为CF20-40(或者cf40-20),
	 *单个车位添加格式为cf20-20; 
	 *自营车位支持添加
	 */
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int insertCbdcar(@Param("bean") List<CbdcarBean> bean);
	/**
	 * 
	 *查询所有车位，包括已经出租的 
	 */
	public 	List<CbdcarBean> showCbdcar();
	/**
	 * 通过状态查询车位信息
	 * @param status 1:已租赁，0：未租赁
	 * @return 
	 */
	public List<CbdcarBean> findCbdcarByStatus(@Param("start")int start, @Param("count")int count, @Param("status")int status);
	/**
	 * 
	 *修改车位信息(包括车位出租状态status，车位flag，车位，租户合同id)
	 *支持批量更新
	 */
	public  int updateCbdcar(@Param("bean")List<CbdcarBean> bean);
	/**
	 * 
	 *修改车位状态
	 */
	public  int updateCbdcarByCcid(@Param("ccid")int ccid,@Param("status")int status);
	/**
	 * 软删除车位，可批量删除
	 */

	public int updateFlag(@Param("bean") List<CbdcarBean> bean);
	
	/**
	 * 获取未租赁车位记录总条数
	 * @return
	 */
	public int findTotalRecords();
	/**
	 *	根据编号和地址和状态查询
	 * @param bean
	 * @return
	 */
	public List<String> findCbdcarByAddr(@Param("bean")CbdcarBean bean,@Param("str")String str);
	/**
	 * 查找车位的最长可租时间中最小的时间
	 * @param bean
	 * @param min
	 * @param max
	 * @return
	 */
	public Date findMaxtimeByAddr(@Param("bean") CbdcarBean bean,@Param("str")String str);
	/**
	 *通过页码查询
	 */
	public List<CbdcarBean> showCbdcarByPage(@Param(value = "page") Integer page, 
			@Param(value = "limit") Integer limit,@Param("bean") CbdcarBean bean);
	/**
	 * 
	 * 查询自营车位的的条数
	 * 
	 * 
	 */
	public int findCbdcarNumber(@Param("bean") CbdcarBean bean);
	/**
	 * 
	 * @param bean
	 * @return
	 * 通过车位id删除车位
	 */
	public void deleteByFlag(int id);
}
