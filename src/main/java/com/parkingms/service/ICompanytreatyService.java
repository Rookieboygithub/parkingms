package com.parkingms.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanytreatyBean;
/**
 * 租户合同的service接口
 * @author 李超
 *
 */
@Service
public interface ICompanytreatyService {
	/**
	 * 企业签订租赁合同(增加)
	 * @param CompanytreatyBean bean
	 * @return String
	 */
	public String Companysigned(CompanytreatyBean bean,CbdcarBean cbdcar);
	/**
	 * 企业续租车位合同(修改)
	 * @param bean
	 * @return String
	 */
	public String Companyrenewal(CompanytreatyBean bean,CbdcarBean cbdcar);
	/**
	 * 企业解除租赁合同(删除)
	 * @param cid
	 * @return String
	 */
	public String Companyunwind(int cid);
	/**
	 * 查询所有企业合同(查询All)
	 * @param id
	 * @return List
	 */
	public Map<String,Object> getCompanytreaty(CompanytreatyBean bean,int page,int num);
	/**
	 * 通过id查询企业合同(查询一个)
	 * @param id
	 * @return CompanytreatyBean
	 */
	public CompanytreatyBean getOldCompanytreaty(CompanytreatyBean bean);
	/**
	 * 修改合同
	 * @param bean
	 * @return
	 */
	public String Companyupdata(CompanytreatyBean bean,CbdcarBean newcbdcar,CbdcarBean oldcbdcar);
}
