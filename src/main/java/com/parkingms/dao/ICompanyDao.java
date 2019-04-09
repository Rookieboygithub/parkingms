package com.parkingms.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;
import com.parkingms.bean.CompanyBean;

/**
 * 企业用户dao接口
 * @author BHH
 *
 */
@Repository
public interface ICompanyDao {
	/**
	 * 添加企业
	 * @param map：使用map，获取存储过程返回值
	 * @return
	 */
	@Select(value = "call add_company("
			+ "#{bean.name,mode=IN,jdbcType=VARCHAR},"
			+ "#{bean.addr,mode=IN,jdbcType=VARCHAR},"
			+ "#{bean.contact,mode=IN,jdbcType=VARCHAR},"
			+ "#{bean.tel,mode=IN,jdbcType=VARCHAR},"
			+ "#{bean.account,mode=IN,jdbcType=VARCHAR},"
			+ "#{bean.pwd,mode=IN,jdbcType=VARCHAR},"
			+ "#{bean.character,mode=IN,jdbcType=TINYINT},"
			+ "#{result,mode=OUT,jdbcType=TINYINT})")
	@Options(statementType = StatementType.CALLABLE)
	public Boolean insertCompanyBean(Map<String, Object> map);

	/**
	 * 删除企业用户
	 * @param bean
	 * @return
	 */
	@Select(value = "call delete_company("
			+ "#{bean.id,mode=IN,jdbcType=TINYINT},"
			+ "#{result,mode=OUT,jdbcType=TINYINT})")
	@Options(statementType = StatementType.CALLABLE)
	public Boolean deleteCompanyBean(Map<String, Object> map);

	/**
	 * 查询企业信息
	 * @param bean 查询企业
	 * @param page 当前页码
	 * @param limit 当前每页显示条数
	 * @return
	 */
	public List<CompanyBean> selectCompanyBean(@Param(value = "bean") CompanyBean bean,
			@Param(value = "page") Integer page, @Param(value = "limit") Integer limit);

	/**
	 * 查询企业用户数量
	 * @param bean
	 * @return
	 */
	public int selectCompanyNumber(@Param(value = "bean") CompanyBean bean);

	/**
	 * 通过电话查公司登陆名
	 * 
	 * @param tel
	 * @return
	 */
	@Select(value = {
			"SELECT login_account FROM t_login where login_id IN (SELECT company_login_id FROM t_company WHERE company_tel = #{tel} AND flag = 0) AND flag = 0;" })
	public String getByTel(String tel);

	/**
	 * 查询企业登录名是否存在
	 * @param loginName
	 * @return
	 */
	@Select(value = "SELECT login_id FROM t_login WHERE login_account = #{loginName} AND login_character =4")
	public Integer selectCompanyLoginName(String loginName);
}
