package org.duc.qlkhoahoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.duc.qlkhoahoc.entity.SinhVien;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class SinhVienHome {

	private static final Log log = LogFactory.getLog(SinhVienHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SinhVien transientInstance) {
		log.debug("persisting SinhVien instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	public void remove(SinhVien persistentInstance) {
		log.debug("removing SinhVien instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SinhVien merge(SinhVien detachedInstance) {
		log.debug("merging SinhVien instance");
		try {
			SinhVien result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SinhVien findById(Integer id) {
		log.debug("getting SinhVien instance with id: " + id);
		try {
			SinhVien instance = entityManager.find(SinhVien.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
