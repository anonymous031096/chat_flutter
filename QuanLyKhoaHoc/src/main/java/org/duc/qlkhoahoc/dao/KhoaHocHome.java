package org.duc.qlkhoahoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.duc.qlkhoahoc.entity.KhoaHoc;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class KhoaHocHome {

	private static final Log log = LogFactory.getLog(KhoaHocHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(KhoaHoc transientInstance) {
		log.debug("persisting KhoaHoc instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	public void update(KhoaHoc khoaHoc) {
		// TODO Auto-generated method stub
		KhoaHoc kh = findById(khoaHoc.getIdkhoaHoc());
		kh.setTenKhoaHoc(khoaHoc.getTenKhoaHoc());
		kh.setSinhViens(khoaHoc.getSinhViens());
		entityManager.flush();
	}
	public void remove(KhoaHoc persistentInstance) {
		log.debug("removing KhoaHoc instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public KhoaHoc merge(KhoaHoc detachedInstance) {
		log.debug("merging KhoaHoc instance");
		try {
			KhoaHoc result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public KhoaHoc findById(Integer id) {
		log.debug("getting KhoaHoc instance with id: " + id);
		try {
			KhoaHoc instance = entityManager.find(KhoaHoc.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
