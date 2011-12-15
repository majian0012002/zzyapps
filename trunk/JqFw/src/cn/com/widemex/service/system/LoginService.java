package cn.com.widemex.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.com.widemex.dao.system.SmFunctionDao;
import cn.com.widemex.dao.system.SmUserDao;
import cn.com.widemex.domain.system.SmFunction;
import cn.com.widemex.domain.system.SmRole;
import cn.com.widemex.domain.system.SmUser;
/**
 * 登录service
 *
 * @author 张中原
 *
 */
@Service("loginService") 
public class LoginService {
	/**用户dao*/
	private SmUserDao smUserDao;
	/**功能dao*/
	private SmFunctionDao smFunctionDao;

	/**
	 * 获取用户，根据账号、密码
	 * @param acc 账号
	 * @param pwd 密码
	 * @return
	 */
	public SmUser getUser(String acc, String pwd){
		List<SmUser> list = this.smUserDao.find("select  distinct a from SmUser a " +
            				"left join fetch a.smRoles b " +
            				"left join fetch a.smOrg c " +
	                       	"where a.acc=? and a.pwd=? and b.status=1 and c.status=1", acc, pwd );
		
		if(list.size() == 0){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	/**
	 * 根据用户获取相关功能(List集合)
	 * @param user 用户 
	 * @return
	 */
	public List<SmFunction> getUserFunList(SmUser user){
		// 用户所有权限
		List<SmFunction> userFunList = new ArrayList<SmFunction>();
		
		
		// 用户所有有权限功能菜单
		Map<String, SmFunction> userFunMap = this.getMapFun(user);
		// 一级菜单
		List<SmFunction> firstFunList = this.getFirstFun();
		
		for (SmFunction fun : firstFunList) {
			if(fun.getStatus()==1 && (userFunMap.get(fun.getId()) != null || "admin".equals(user.getAcc())) ){
				this.fetchUserFun(fun, userFunMap, user.getAcc());
				userFunList.add(fun);
			}
		}
		
		return userFunList;
	}
	
	/**
	 * 抓取当前菜单的相关叶子节点
	 * @param fun 要抓取的菜单
	 * @param userFunMap 用户所有的菜单map
	 * @param acc 账号, 如果是admin则抓取全部有效记录
	 */
	private void fetchUserFun(SmFunction fun, Map<String, SmFunction> userFunMap, String acc){
		Set<SmFunction> childrenFunSet = fun.getChildrenFuns();
		if(childrenFunSet.isEmpty()){
			return;
		}else{
			for (SmFunction childFun : childrenFunSet) {
				if("admin".equals(acc)){ // 如果是admin
					if(childFun.getStatus() == 0){
						childrenFunSet.remove(childFun);
					}else{
						this.fetchUserFun(childFun, userFunMap, acc);
					}
				}else{
					if(userFunMap.get(childFun.getId()) == null || childFun.getStatus()==0){
						childrenFunSet.remove(childFun);
					}else{
						this.fetchUserFun(childFun, userFunMap, acc);
					}	
				}
				
			}
		}
	}
	
	/**
	 * 获取一级菜单
	 * @return
	 */
	private List<SmFunction> getFirstFun(){
		return this.smFunctionDao.find("select a from SmFunction a where a.status=1 and a.parentFun is null order by a.orderInd");
	}
	
	/**
	 * 根据用户获取相关功能(Map集合)
	 * @param user 用户 
	 * @return
	 */
	public Map<String, SmFunction> getMapFun(SmUser user){
		Map<String, SmFunction> funMap = new HashMap<String, SmFunction>();
		Set<SmRole> roleSet = user.getSmRoles();
		for (SmRole smRole : roleSet) {
			Set<SmFunction> funSet = smRole.getSmFuns();
			for (SmFunction smFunction : funSet) {
				if(smFunction.getStatus() == 1 && funMap.get(smFunction.getId())!=null){
					funMap.put(smFunction.getId(), smFunction);
				}
			}
		}
		return funMap;
	}	
	
	
	public SmUserDao getSmUserDao() {
		return smUserDao;
	}

	public void setSmUserDao(SmUserDao smUserDao) {
		this.smUserDao = smUserDao;
	}

	public SmFunctionDao getSmFunctionDao() {
		return smFunctionDao;
	}

	public void setSmFunctionDao(SmFunctionDao smFunctionDao) {
		this.smFunctionDao = smFunctionDao;
	}
	
	
	
	
}
