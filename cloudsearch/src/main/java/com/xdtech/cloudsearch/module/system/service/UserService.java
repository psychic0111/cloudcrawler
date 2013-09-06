package com.xdtech.cloudsearch.module.system.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.system.bean.User;

/**
 * 用户Service
 * 
 * @author yuhao
 */
@Service(value="userService")
@Transactional(rollbackFor=Exception.class)
public class UserService extends BaseService {
    
    /**查询所有用户
     * @author yuhao
     * @return
     */
	@Transactional(readOnly = true) 
	public List<User> findAll(){
		String queryString = "from User ";
		List<User> list = find(queryString);
		return list;
	}
	
	/**用户登陆
	 * 
	 * @param user
     * @author yuhao
     * @return
     */
	public User login(User user) {
		System.out.println("ab"+user.getUsername()+"cd");
		String queryString = "from User u where u.username = '"+user.getUsername()+"'";
		List<User> userlist = getDao().findList(queryString);
		if (userlist.size() > 0) {
			User userbase = userlist.get(0);
			if (userbase != null) {
				if (userbase.getPassword().equals(user.getPassword()) && userbase.getUsername().equals(user.getUsername())) {
					userbase.setLastlogintime(new Date());
					update(userbase);
					user = userbase;
					return user;
				} else {
					return null;
				}
			} else {
				return null;
			}

		} else {
			return null;
		}
	}
	
	/**检查用户是否存在
	 * 
	 * @param user
     * @author yuhao
     * @return
     */
	@Transactional(readOnly = true) 
	public String checkUser(User user) {
		StringBuffer queryString = new StringBuffer("select count(*) from User u where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if (user.getId() != null && !user.getId().equals("")) {
			queryString.append(" and u.id<>?  ");
			list.add(user.getId());
		}
		if (user.getUsername() != null && !user.getUsername().equals("")) {
			queryString.append("and u.username=?  ");
			list.add(user.getUsername());
		}
		List<Object> userlist = getDao().findList(queryString.toString(), list.toArray());
		Long c = (Long) userlist.get(0);
		if (c > 0) {
			return "false";
		}
		return "true";
	}
	
	@Transactional(readOnly = true) 
	public PageResult findByPage(User user, PageResult pageResult) {
		String queryString = "from User";
		List<User> list = null;
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		pageResult.setTotal(getCount(User.class));
		return pageResult;
	}
}
