package com.xdtech.cloudsearch.module.task.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.cloudsearch.module.base.page.PageResult;
import com.xdtech.cloudsearch.module.base.service.BaseService;
import com.xdtech.cloudsearch.module.task.bean.Task;
/**
 * 任务管理service
 * @author LZF
 *
 */
@Service(value="taskService")
@Transactional(rollbackFor=Exception.class)
public class TaskService extends BaseService{
	
	/**
	 * 根据任务名称查询
	 * @param task
	 * @param pageResult
	 * @return
	 */
	public PageResult findByPage(Task task,PageResult pageResult){
		String queryString  = "from Task where 1= 1";
		if(null!=task){
			if(null!=task.getTaskName() &&!"".equals(task.getTaskName())){
				queryString =queryString +" and taskName like '%"+task.getTaskName()+"%'";
			}
		}
		List<Task> list=null;
		queryString = queryString +" order by operateTime desc ";
		try {
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		pageResult.setTotal(getCount(queryString));
		return pageResult;
	}
	
	/**
	 * 根据任务实例查询
	 * @param task
	 * @param pageResult
	 * @return
	 */
	public PageResult findTasksByExample(Task task,PageResult pageResult){
		List<Task> list=null;
		try {
			int pageNo = pageResult.getPageNo();
			int start = (pageNo - 1) * pageResult.getPageSize();
			list = findByExample(task, start, pageResult.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		return pageResult;
	}
	
	/**
	 * 查询运行的任务
	 * @param task
	 * @param pageResult
	 * @return
	 */
	public PageResult findRunningTasks(Date date, PageResult pageResult){
		List<Task> list=null;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int h = c.get(Calendar.HOUR_OF_DAY);
			int mi = c.get(Calendar.MINUTE);
			String queryString = "from Task where isUse=1 and startHour<=" + h + " and startMinute<=" + mi + " and endHour>=" + h + " and startMinute<=" + mi;
			list = findByPage(queryString, pageResult.getPageNo(), pageResult.getPageSize());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult.setRows(list);
		return pageResult;
	}
	
	/**
	 * 检查任务名称
	 * @param task
	 * @return
	 */
	public String checkTask(Task task){
		StringBuffer queryString =new StringBuffer("select count(*) from Task t where 1=1 ");
		List<Object> list =new ArrayList<Object>();
		if(task.getId()!=null&&!task.getId().equals("")){
			queryString.append(" and t.id<>?  ");
			list.add(task.getId());
		}
		if(task.getTaskName() !=null&&!task.getTaskName().equals("")){
			queryString.append("and t.taskName=?  ");
			list.add(task.getTaskName());
		}
		List<Object> tasklist = getDao().findList(queryString.toString(),list.toArray());
		Long c= (Long) tasklist.get(0);
		if(c>0){
			return "false";
		}
		return "true";
	}
}
