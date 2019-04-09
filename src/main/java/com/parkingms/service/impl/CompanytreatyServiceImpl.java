package com.parkingms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanytreatyBean;
import com.parkingms.dao.ICbdcarDao;
import com.parkingms.dao.ICompanytreatyDao;
import com.parkingms.service.ICompanytreatyService;
import com.parkingms.util.ConvertUtil;

/**
 * 操作租户合同
 * 
 * @author LC
 *
 */
@Service
public class CompanytreatyServiceImpl implements ICompanytreatyService {
	@Autowired
	private ICompanytreatyDao dao;
	@Autowired
	private ICbdcarDao cardao;

	@ParkingmsLog(module = "企业合同模块", method = "租户合同增加", plantform = 1, type = 1)
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public String Companysigned(CompanytreatyBean bean, CbdcarBean cbdcar) {
		if (bean == null) {
			return "该合同没有数据!";
		}
		// 插入合同
		int flag2 = dao.insert(bean);
		cbdcar.setCompanytreaty(bean);
		cbdcar.setStatus(1);
		// 判断是否有车位是出租状态
		List<CbdcarBean> carlist = ConvertUtil.conv(cbdcar);
		// 获取车位编号范围
		String str = "'";
		for (int i = 0; i < carlist.size(); i++) {
			str += carlist.get(i).getNo();
			if (i < carlist.size() - 1) {
				str += "','";
			}
		}
		str += "'";
		List<String> findCbdcar = cardao.findCbdcarByAddr(cbdcar, str);
		boolean flag3 = false;
		if (findCbdcar.size() == carlist.size()) {
			flag3 = true;
		}
		// 查找签合同的最长可租时间中最小的时间
		Date mintime = cardao.findMaxtimeByAddr(cbdcar, str);
		boolean flag4 = false;
		boolean flag5 = false;
		if (mintime != null) {
			// 比较时间相等则返回0，大于返回1，否则返回-1
			int compareTo = bean.getEndtime().compareTo(mintime);
			System.out.println(compareTo);
			if (compareTo == 0 || compareTo == -1) {
				flag4 = true;
			}
		} else {
			flag5 = true;
		}
		// 更新车位
		int flag1 = cardao.updateCbdcar(carlist);
		// 如果添加或者跟新失败，抛出一个异常
		if (flag2 < 1) {
			throw new RuntimeException("租户合同添加失败!");
		} else if (flag5) {
			throw new RuntimeException("车位信息有误!");
		} else if (!flag3) {
			throw new RuntimeException("输入的车位中，有已出租的车位!");
		} else if (!flag4) {
			throw new RuntimeException("输入的车位中，有的车位可租时间小于租赁时间!");
		} else if (flag1 < 1) {
			throw new RuntimeException("车位信息有误!");
		} else {
			return "租户合同添加成功!";
		}
	}

	@ParkingmsLog(module = "企业合同模块", method = "租户合同续约", plantform = 1, type = 1)
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public String Companyrenewal(CompanytreatyBean bean, CbdcarBean cbdcar) {
		if (bean == null) {
			return "该合同没有数据!";
		}
		// 根据id软删除旧合同
		int flag1 = dao.delete(bean.getId());
		// 把车位变为未租状态
		int flag6 = cardao.updateCbdcarByCcid(bean.getId(), 0);
		// 新增续约合同
		int flag2 = dao.insert(bean);
		// 把合约与车位联系起来
		cbdcar.setCompanytreaty(bean);
		List<CbdcarBean> carlist = ConvertUtil.conv(cbdcar);
		// 获取车位编号范围
		String str = "'";
		for (int i = 0; i < carlist.size(); i++) {
			str += carlist.get(i).getNo();
			if (i < carlist.size() - 1) {
				str += "','";
			}
		}
		str += "'";
		// 查找签合同的最长可租时间中最小的时间
		Date mintime = cardao.findMaxtimeByAddr(cbdcar, str);
		boolean flag3 = false;
		boolean flag5 = false;
		// 比较时间相等则返回0，大于返回1，否则返回-1
		if (mintime != null) {
			int compareTo = bean.getEndtime().compareTo(mintime);
			if (compareTo == 0 || compareTo == -1) {
				flag3 = true;
			}
		} else {
			flag5 = true;
		}
		// 跟新车位
		int flag4 = cardao.updateCbdcar(carlist);
		// 如果添加或者跟新失败，抛出一个异常
		if (flag1 < 1 || flag2 < 1) {
			throw new RuntimeException("合约续约失败!");
		} else if (flag5) {
			throw new RuntimeException("该合同所有车位已经被删除!");
		} else if (flag6 != carlist.size()) {
			throw new RuntimeException("车位有已删除的!");
		} else if (!flag3) {
			throw new RuntimeException("输入的车位中，有的车位可租时间小于租赁时间!");
		} else if (flag4 < 1) {
			throw new RuntimeException("车位更新失败!");
		} else {
			return "租户合同续约成功!";
		}
	}

	/**
	 * 企业解除合约，软删除合约，把车位设置为待租状态
	 */
	@ParkingmsLog(module = "企业合同模块", method = "租户合同解约", plantform = 1, type = 1)
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public String Companyunwind(int cid) {
		if (cid <= 0) {
			return "该合同不存在!";
		}
		// 软删除合约
		int flag1 = dao.delete(cid);
		// 根据合同id把车位状态设置为 待租状态
		int flag2 = cardao.updateCbdcarByCcid(cid, 0);
		// 如果出错抛出异常
		if (flag1 > 0 && flag2 > 0) {
			return "合同解约成功!";
		} else {
			throw new RuntimeException("解约合同失败!");
		}
	}

	/**
	 * 根据id获取已存在租户合同
	 */
	@Override
	public CompanytreatyBean getOldCompanytreaty(CompanytreatyBean bean) {
		if (bean == null || bean.getId() == 0) {
			return new CompanytreatyBean();
		}
		List<CompanytreatyBean> list = dao.findCompanytreaty(bean, 1, 1);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new CompanytreatyBean();
		}
	}

	/**
	 * 传入参数为0，查询所有合同
	 */
	@Override
	public Map<String, Object> getCompanytreaty(CompanytreatyBean bean, int page, int num) {
		List<CompanytreatyBean> list = dao.findCompanytreaty(bean, page, num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		// 数据总数
		int total = dao.findTotalNum(bean);
		map.put("totalpage", total);
		return map;
	}

	@ParkingmsLog(module = "企业合同模块", method = "租户合同更新", plantform = 1, type = 1)
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public String Companyupdata(CompanytreatyBean bean, CbdcarBean newcbdcar, CbdcarBean oldcbdcar) {
		if (bean == null) {
			return "该合同不存在!";
		}
		// 把以前的车位状态设为可租，并跟新
		oldcbdcar.setStatus(0);
		List<CbdcarBean> carlist1 = ConvertUtil.conv(oldcbdcar);
		int flag1 = cardao.updateCbdcar(carlist1);
		// 跟新合同
		int flag2 = dao.update(bean);
		// 更新新车位的状态
		newcbdcar.setStatus(1);
		newcbdcar.setCompanytreaty(bean);
		List<CbdcarBean> carlist2 = ConvertUtil.conv(newcbdcar);
		// 获取车位编号的范围
		String str = "'";
		for (int i = 0; i < carlist2.size(); i++) {
			str += carlist2.get(i).getNo();
			if (i < carlist2.size() - 1) {
				str += "','";
			}
		}
		str += "'";
		// 判断是否有车位是出租状态
		List<String> findCbdcar = cardao.findCbdcarByAddr(newcbdcar, str);
		boolean flag3 = false;
		if (findCbdcar.size() == carlist2.size()) {
			flag3 = true;
		}
		// 查找签合同的最长可租时间中最小的时间
		Date mintime = cardao.findMaxtimeByAddr(newcbdcar, str);
		boolean flag5 = false;
		// 比较时间相等则返回0，大于返回1，否则返回-1
		int compareTo = bean.getEndtime().compareTo(mintime);
		if (compareTo == 0 || compareTo == -1) {
			flag5 = true;
		}
		int flag4 = cardao.updateCbdcar(carlist2);
		if (flag2 < 1) {
			throw new RuntimeException("租户合同更新失败!");
		} else if (flag4 < 1) {
			throw new RuntimeException("车位信息有误!");
		} else if (flag1 < 1) {
			throw new RuntimeException("租户合同更新失败，因车位信息无法更新!");
		} else if (!flag3) {
			throw new RuntimeException("输入的车位中，有已出租的车位!");
		} else if (!flag5) {
			throw new RuntimeException("输入的车位中，有的车位可租时间小于租赁时间!");
		} else {
			return "租户合同更新成功!";
		}
	}

	public ICompanytreatyDao getDao() {
		return dao;
	}

	public void setDao(ICompanytreatyDao dao) {
		this.dao = dao;
	}

	public ICbdcarDao getCardao() {
		return cardao;
	}

	public void setCardao(ICbdcarDao cardao) {
		this.cardao = cardao;
	}

}
