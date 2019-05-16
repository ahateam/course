package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * @author 16436
 *这里类似于实验室机构
 *下属有 教师 课程 大纲 实验教室
 *暂时把这里定为最高层
 *作用为管理下层。具体怎么做暂时不知。先放一些教室进来
 *
 *实体类可暂定层级管理
 */
public class Organ {
	
	/**
	 * 机构Id 例：大数据学院教务科
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public long organId;     
	
	/**
	 * 机构 实验室名称
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public long organId1;
	
}
