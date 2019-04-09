package com.parkingms.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkingms.bean.DealBean;
import com.parkingms.bean.LandlordCarBean;
import com.parkingms.bean.UserBean;
import com.parkingms.dao.IDealDao;
import com.parkingms.service.IDealService;

public class DealTest {
	/**
	 * 测试新增出租信息订单
	 */
	@Test
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IDealService service = context.getBean(IDealService.class);
		DealBean bean = new DealBean();
		bean.setNo("D2018080601");
		bean.setPrice(2800);
		UserBean landlord = new UserBean();
		UserBean tenement = new UserBean();
		landlord.setId(1);
		tenement.setId(2);
		bean.setLandlord(landlord);
		bean.setTenement(tenement);
		LandlordCarBean landlordCar = new LandlordCarBean();
		landlordCar.setId(2);
		landlordCar.setStatus(3);
		bean.setLandlordCar(landlordCar);
		bean.setMessage("能便宜点吗？");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			bean.setBeginTime(df.parse("2018-08-06 10:00"));
			bean.setEndTime(df.parse("2018-08-06 16:00"));
			bean.setOrderTime(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(service.insertDeal(bean));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	/**
	 * 测试包租婆同意出租订单
	 */
//	@Test
//	public void test1(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		IDealService service = context.getBean(IDealService.class);
//		DealBean bean = new DealBean();
//		bean.setId(1);
//		bean.setStatus(2);
//		bean.setDoneTime(new Date());
//		LandlordCarBean landlordCar = new LandlordCarBean();
//		landlordCar.setId(2);
//		landlordCar.setStatus(4);
//		bean.setLandlordCar(landlordCar);
//		String result="";
//		try {
//			if(service.dealwithDeal(bean)){
//				result = "success";
//			}
//		} catch (Exception e) {
//			result = e.getMessage();
//		}
//		System.out.println(result);
//	}
	@Test
	public void test1(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IDealService service = context.getBean(IDealService.class);
		DealBean bean = new DealBean();
		bean.setId(1);
		bean.setStatus(2);
		bean.setDoneTime(new Date());
		LandlordCarBean landlordCar = new LandlordCarBean();
		landlordCar.setId(2);
		landlordCar.setStatus(4);
		bean.setLandlordCar(landlordCar);
		String result="";
		try {
			/*if(service.dealwithDeal(bean)){
				result = "success";
			}*/
		} catch (Exception e) {
			result = e.getMessage();
		}
		System.out.println(result);
	}
	/**
	 * 测试包租婆拒绝出租订单
	 */
//	@Test
//	public void test2(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		IDealService service = context.getBean(IDealService.class);
//		DealBean bean = new DealBean();
//		bean.setId(2);
//		bean.setStatus(3);
//		LandlordCarBean landlordCar = new LandlordCarBean();
//		landlordCar.setId(2);
//		landlordCar.setStatus(2);
//		bean.setLandlordCar(landlordCar);
//		String result="";
//		try {
//			if(service.dealwithDeal(bean)){
//				result = "success";
//			}
//		} catch (Exception e) {
//			result = e.getMessage();
//		}
//		System.out.println(result);
//	}
	@Test
	public void test2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IDealService service = context.getBean(IDealService.class);
		DealBean bean = new DealBean();
		bean.setId(2);
		bean.setStatus(3);
		LandlordCarBean landlordCar = new LandlordCarBean();
		landlordCar.setId(2);
		landlordCar.setStatus(2);
		bean.setLandlordCar(landlordCar);
		String result="";
		try {
			/*if(service.dealwithDeal(bean)){
				result = "success";
			}*/
		} catch (Exception e) {
			result = e.getMessage();
		}
		System.out.println(result);
	}
	/**
	 * 测试查询所有订单信息
	 */
//	@Test
//	public void test3(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		IDealService service = context.getBean(IDealService.class);
//		DealBean bean = new DealBean();
//		List<DealBean> list = service.findDeal(bean);
//		for (DealBean dealBean : list) {
//			System.out.println("交易编号"+dealBean.getNo()+",出租开始时间"+dealBean.getBeginTime()
//			+",结束时间"+dealBean.getEndTime()+",费用"+dealBean.getPrice()+",车位ID"+dealBean.getLandlordCar().getId()
//			+",车位编号"+dealBean.getLandlordCar().getCarNo()+",车位位置"+dealBean.getLandlordCar().getLocation()+",包租婆id"+dealBean.getLandlord().getId()
//			+",包租婆"+dealBean.getLandlord().getName()+",招租客"+dealBean.getTenement().getName());
//		}
//	}
	/**
	 * 测试根据包租婆id查订单
	 */
//	@Test
//	public void test4(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		IDealService service = context.getBean(IDealService.class);
//		DealBean bean = new DealBean();
//		UserBean landlord = new UserBean();
//		landlord.setId(1);
//		bean.setLandlord(landlord);
//		List<DealBean> list = service.findDeal(bean);
//		System.out.println(list.isEmpty());
//		for (DealBean dealBean : list) {
//			System.out.println("交易编号"+dealBean.getNo()+",出租开始时间"+dealBean.getBeginTime()
//			+",结束时间"+dealBean.getEndTime()+",费用"+dealBean.getPrice()+",车位ID"+dealBean.getLandlordCar().getId()
//			+",车位编号"+dealBean.getLandlordCar().getCarNo()+",车位位置"+dealBean.getLandlordCar().getLocation()+",包租婆id"+dealBean.getLandlord().getId()
//			+",包租婆"+dealBean.getLandlord().getName()+",招租客"+dealBean.getTenement().getName());
//		}
//	}
	/**
	 * 测试根据招租客id查订单
	 */
//	@Test
//	public void test5(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		IDealService service = context.getBean(IDealService.class);
//		DealBean bean = new DealBean();
//		UserBean tenement = new UserBean();
//		tenement.setId(2);
//		bean.setTenement(tenement);
//		List<DealBean> list = service.findDeal(bean);
//		System.out.println(list.isEmpty());
//		for (DealBean dealBean : list) {
//			System.out.println("交易编号"+dealBean.getNo()+",出租开始时间"+dealBean.getBeginTime()
//			+",结束时间"+dealBean.getEndTime()+",费用"+dealBean.getPrice()+",车位ID"+dealBean.getLandlordCar().getId()
//			+",车位编号"+dealBean.getLandlordCar().getCarNo()+",车位位置"+dealBean.getLandlordCar().getLocation()+",包租婆id"+dealBean.getLandlord().getId()
//			+",包租婆"+dealBean.getLandlord().getName()+",招租客"+dealBean.getTenement().getName());
//		}
//	}
	/**
	 * 测试查询一条订单
	 */
	@Test
	public void test6(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		IDealDao dao = context.getBean(IDealDao.class);
		/**
		 * 通过ID获取订单金额或者订单编号
		 */
		//传入ID，封装到一个DealBean里面
		DealBean bean = new DealBean();
		bean.setId(3);
		//结果封装到DealBean里面
		DealBean resultBean = dao.get(bean);
		//获取订单金额
		if(resultBean != null){
			System.out.println(resultBean.getPrice());
		}
		//获取订单编号
		if(resultBean != null){
			System.out.println(resultBean.getNo());
		}
		/**
		 * 通过订单编号查ID
		 */
		//传入订单编号，封装到一个DealBean里面
		DealBean bean1 = new DealBean();
		bean1.setNo("D201808084562");
		//结果封装到DealBean里面
		DealBean resultBean1 = dao.get(bean);
		//获取订单ID
		if(resultBean1 != null){
			System.out.println(resultBean1.getId());
		}
	}
	/**
	 * 测试退款
	 */
	@Test
	public void test7(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//		IDealService service = context.getBean(IDealService.class);
//		DealBean bean = new DealBean();
//		bean.setId(15);
//		bean.setStatus(3);
//		service.dealwithDeal(bean);
		for(int i1=1;i1<=100;i1++){
//			String code=String.format("%06d",((int)(Math.random()*1000000)));
			String code = "";
			for(int i=0;i<6;i++){
				code += (int)(Math.random()*10);
			}
			System.out.println(code);
		}
		
	}
}
