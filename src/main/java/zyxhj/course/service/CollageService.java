package zyxhj.course.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Collage;
import zyxhj.course.repository.CollageRepository;
import zyxhj.utils.Singleton;

public class CollageService {
	private static Logger log = LoggerFactory.getLogger(CollageService.class);

	private CollageRepository collageRepository;

	public CollageService() {
		try {
			collageRepository = Singleton.ins(CollageRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加院校
	 * 
	 * @param conn
	 * @param collageId
	 * @param collageName
	 * @return
	 */
	public Collage addCollage(DruidPooledConnection conn, Long collageId, String collageName) throws Exception {
		Collage c = new Collage();
		c.Id = collageId;
		c.collageName = collageName;
		collageRepository.insert(conn, c);
		return c;
	}

	/**
	 * 修改院校
	 * 
	 * @param conn
	 * @param collageId
	 * @param collageName
	 * @return
	 */
	public Collage upCollage(DruidPooledConnection conn, Long collageId, String collageName) throws Exception {
		Collage c = new Collage();
		c.Id = collageId;
		c.collageName = collageName;
		collageRepository.updateByKey(conn, "id", collageId, c, true);
		return c;
	}

	/**
	 * 删除院校
	 * @param conn
	 * @param collageId
	 */
	public void deleteCollage(DruidPooledConnection conn, Long collageId) throws Exception {
		collageRepository.deleteByKey(conn, "id", collageId);
	}

	/**
	 * 根据id查询院校
	 * 
	 * @param conn
	 * @param collageId
	 * @return
	 */
	public Collage queryCollageById(DruidPooledConnection conn, Long collageId) throws Exception {
		return collageRepository.getByKey(conn, "id", collageId);
	}
}
