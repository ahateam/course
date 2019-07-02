package zyxhj.course.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.service.TermService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;

public class TermController extends Controller {
	
	private static Logger log = LoggerFactory.getLogger(TermController.class);
	private DruidDataSource dds;
	private TermService termService;
	
	public TermController(String node){
		super(node);
		
		try {
			dds = DataSource.getDruidDataSource("rdsDefault.prop");
			termService = Singleton.ins(TermService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "createTerm",
			des = "创建学期",
			ret = "返回学期表"
	)
	public APIResponse createTerm(
			@P(t = "学期名称") String termName,
			@P(t = "有多少周") Integer weekCount,
			@P(t = "开始时间") Date startDate,
			@P(t = "结束时间") Date endDate,
			@P(t = "创建时间") Date createTime,
			@P(t = "备注")  String remark
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			termService.createTerm(conn, termName, weekCount, startDate, endDate, createTime, remark);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "editTerm",
			des = "编辑学期",
			ret = "返回修改后的信息"
	)
	public APIResponse editTerm(
			@P(t = "学期ID") Long termId,
			@P(t = "学期名称") String termName,
			@P(t = "有多少周") Integer weekCount,
			@P(t = "开始时间") Date startDate,
			@P(t = "结束时间") Date endDate,
			@P(t = "创建时间") Date createTime,
			@P(t = "备注")  String remark
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			termService.editTerm(conn, termId, termName, weekCount, startDate, endDate, createTime, remark);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "getTerm",
			des = "通过学期ID，获取学期",
			ret = "返回特定学院Id"
	)
	public APIResponse getTerm(
			@P(t = "学期ID") Long termId
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			termService.getTerm(conn, termId);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "queryTerm",
			des = "分页查询学期",
			ret = "返回分页学院信息"
	)
	public APIResponse queryTerm(
			@P(t = "每页显示记录数") Integer count, //
			@P(t = "从第几条记录开始") Integer offset //
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			termService.queryTerm(conn, count, offset);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "delTerm",
			des = "通过学期ID，获取学期"
	)
	public APIResponse delTerm(
			@P(t = "学期ID") Long termId
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			termService.delTerm(conn, termId);
			return APIResponse.getNewSuccessResp();
		}
	}

}
