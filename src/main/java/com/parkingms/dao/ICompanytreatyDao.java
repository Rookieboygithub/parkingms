package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.CompanytreatyBean;

public interface ICompanytreatyDao {
	/**
	 * 企业租赁合同增加
	 * @param CompanytreatyBean bean
	 * @return int
	 */
	
	@Insert("insert into t_companytreaty(companytreaty_formerno,companytreaty_newno,companytreaty_company,"
			+ "companytreaty_contact,companytreaty_tel,companytreaty_begintime,"
			+ "companytreaty_endtime,companytreaty_img,companytreaty_c_id) "
			+ "values(#{formerno},#{newno},#{companyname},#{contact},#{tel},#{begintime},#{endtime},"
			+ "#{img},#{company.id})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="companytreaty_id")
	public int insert(CompanytreatyBean bean);
	/**
	 * 企业租赁合同修改
	 * @param bean
	 * @return int
	 */
	@Update("UPDATE t_companytreaty SET companytreaty_begintime = #{begintime},companytreaty_contact = #{contact},companytreaty_endtime = #{endtime},"
	+" companytreaty_tel = #{tel} WHERE companytreaty_id = #{id} AND flag = 0;")
	public int update(CompanytreatyBean bean);
	/**
	 * 企业租赁合同删除
	 * @param cid
	 * @return int
	 */
	@Update("update t_companytreaty set flag = 1 where companytreaty_id = #{cid} and flag = 0")
	public int delete(@Param("cid")int id);
	/**
	 * 根据id查询企业合同的所有数据
	 * @param id
	 * @return CompanytreatyBean
	 */
	public List<CompanytreatyBean> findCompanytreaty(@Param(value="bean")CompanytreatyBean bean,@Param(value="page")int page,@Param(value="num") int num);
	
	public int findTotalNum(@Param(value="bean")CompanytreatyBean bean);
}
