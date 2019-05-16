package zyxhj.course.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CollageClass;
import zyxhj.course.repository.CollageRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton; 

/**
 * @author 16436
 *
 */
public class CollageService {
	private static Logger log = LoggerFactory.getLogger(CollageService.class); 
	 
	private  CollageRepository collageRepository;
	
	public CollageService() { 
		try { 
			collageRepository = Singleton.ins(CollageRepository.class); 
		} catch (Exception e) { 
			log.error(e.getMessage(), e); 
		} 
	}
	/**
	 * 添加教程大纲
	 * @param conn
	 * @param collageName
	 * @param knowledge
	 * @param electId
	 * 还要继承一个来自实验室的信息，教师的信息
	 * 实验室机构里面应分好从属关系，可暂时把实验室看成一个机构，为高层管理机构。
	 * 下属有 课程 教师 大纲 实验教室
	 * 
	 * @return
	 */
	public CollageClass addCollage(DruidPooledConnection conn,String collageName,Float knowledge,Byte electId,Byte examId) throws Exception{
		CollageClass c = new CollageClass();
		
		c.Id = IDUtils.getSimpleId(); // 课程大纲Id,可能要随机生成的改id格式
	    c.institutionId = IDUtils.getSimpleId(); // 课程所属机构ID 这里要改成类似于1对应大数据学院，而不是随机生成Id
	     
	    
		c.collageName = collageName; // 课程大纲名称
		c.knowledge = knowledge;  // 总学时
		c.elect = CollageClass.ELECT.OBLIGATORY.v(); // 课程性质
		c.exam = CollageClass.EXAM.ROLLTEST.v();//考核方式ID 这里要弄成 考核方式对应不同的ID 比如1 代表卷面考试。
		
		collageRepository.insert(conn, c);
		return c;
	}
	
	/**
	 * 刪除课程大纲
	 * @param conn
	 * @param collageId
	 */
	public void delCollage(DruidPooledConnection conn,Long Id) throws Exception{
		collageRepository.deleteByKey(conn, "id", Id);
	}
	/**
	 * 修改课程大纲
	 * @param collage
	 */
	public CollageClass upCollageClass(DruidPooledConnection conn,Long Id, String CollageName,Float knowledge,Byte electId,Byte examId) throws Exception{
		CollageClass c = new CollageClass(); 
		
		c.Id = Id;
		c.collageName = CollageName;
		c.knowledge = knowledge;  // 总学时
		c.elect = CollageClass.ELECT.OBLIGATORY.v(); // 课程性质
		c.exam = CollageClass.EXAM.ROLLTEST.v(); // 考核方式
		
		collageRepository.updateByKey(conn, "collage_id",Id ,c,true);
		
		return c;
	}
	
	/**
	 * 根据id查询大纲
	 * @param conn
	 * @param collageId
	 * @return
	 * @throws Exception
	 */
	public CollageClass queryCollageById(DruidPooledConnection conn, Long Id) throws Exception{
		
		return collageRepository.getByKey(conn, "collage_id", Id);
	}
}
