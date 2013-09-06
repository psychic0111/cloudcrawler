package com.xdtech.project.modules.webdb.action;

import com.opensymphony.xwork2.Action;
import com.xdtech.project.modules.webdb.service.DBService;
import com.xdtech.project.web.action.BaseAction;

public class InfoAction extends BaseAction {
	private String[] tables;

	public String info() {
		DBService service = new DBService();
		tables = service.findTables();
		return Action.SUCCESS;
	}

	public String[] getTables() {
		return tables;
	}

	public void setTables(String[] tables) {
		this.tables = tables;
	}
}
