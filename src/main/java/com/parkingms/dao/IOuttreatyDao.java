package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.OuttreatyBean;
public interface IOuttreatyDao {
	/**
	 * 此方法用于企业用户资料的数据库上传操作
	 */
	@Insert("INSERT INTO t_outtreaty(outtreaty_addr,outtreaty_formerno,outtreaty_newno,outtreaty_company,outtreaty_contact,"
			+ "outtreaty_tel,outtreaty_begintime,outtreaty_endtime,outtreaty_img,outtreaty_carNum) VALUES"
			+ "(#{addr},#{formerno},#{newno},#{company},#{contact},#{tel},#{begintime},#{endtime},#{img},#{carNum})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="outtreaty_id")
	public int insert(OuttreatyBean bean);
	
	
	@Select("SELECT outtreaty_id id,outtreaty_addr addr,outtreaty_formerno formerno,outtreaty_newno newno,outtreaty_company company,outtreaty_contact contact,"
			+ "outtreaty_tel tel,outtreaty_begintime begintime,outtreaty_endtime endtime,outtreaty_img img,outtreaty_carNum carNum FROM t_outtreaty")
	public List<OuttreatyBean> select();

	/**
	 * 此方法用于修改企业的信息
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_outtreaty SET outtreaty_newno=#{newno},outtreaty_company=#{company}"
			+ ", outtreaty_contact=#{contact},outtreaty_tel=#{tel},"
			+ "outtreaty_begintime=#{begintime},outtreaty_endtime=#{endtime} WHERE outtreaty_id=#{id}")
	public int update(OuttreatyBean bean);

	@Delete("DELETE FROM t_outtreaty WHERE outtreaty_id=#{id}")
	public int delete(Integer id);
}
