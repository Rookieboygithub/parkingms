package com.parkingms.dao;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.*;
public interface IComplainDao {
	
	//查询所有的投诉的大概信息
	public List<ComplainBean> showAllComplain();
	
	//查询单个投诉记录的详细信息
	@Select("SELECT * FROM v_complainmessage WHERE complain_id = #{id}")
	@Results({
        @Result(property = "id", column = "complain_id"),
        @Result(property = "location", column = "landlordcar_location"),
        @Result(property = "carNumber",column = "landlordcar_num"),
        @Result(property = "begintime",column = "deal_begintime"),
        @Result(property = "endtime", column = "deal_endtime"),
        @Result(property = "alias", column = "user_alias"),
        @Result(property = "name",column = "user_name"),
        @Result(property = "cardno",column = "user_cardno"),
        @Result(property = "career", column = "user_career"),
        @Result(property = "tel", column = "user_tel"),
        @Result(property = "reason",column = "complain_reason"),
        @Result(property = "byalias", column = "t_user_alias"),
        @Result(property = "byname",column = "t_user_name"),
        @Result(property = "bycardno",column = "t_user_cardno"),
        @Result(property = "bycareer", column = "t_user_career"),
        @Result(property = "bytel", column = "t_user_tel"),
        
    })
	public List<ComplainMessageBean> showOneComplain(ComplainBean bean);
	
	//处理投诉>修改记录的状态
	@Update("UPDATE t_complain SET complain_status=#{status}, complain_dealreason = #{dealreason}  WHERE complain_id=#{id}")
	public int dealComplain(ComplainBean bean);
	
	//发起投诉
	@Insert("Insert into t_complain (complain_no,complain_deal_id,complainant_id,defendant_id,complain_reason,complain_time)"
		+ "VALUES(#{no},#{deal.id},#{complaint.id},#{defendant.id},#{reasons},#{date})")
	public int insertComplain(ComplainBean bean);
	
	//通过Id查询用户信息
	@Select("SELECT * FROM t_user WHERE user_id=#{id}")
	@Results({
        @Result(property = "id", column = "user_id"),
        @Result(property = "name", column = "user_name"),
        @Result(property = "alias",column = "user_alias"),
        @Result(property = "addr",column = "user_addr"),
        @Result(property = "tel", column = "user_tel"),
        @Result(property = "cardNo", column = "user_cardno"),
        @Result(property = "career",column = "user_career"),
        @Result(property = "email",column = "user_email"),
        @Result(property = "credit", column = "user_credit"),     
    })
	public UserBean getById(int id);
	
}
