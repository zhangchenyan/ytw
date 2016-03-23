package cn.edu.seu.ytw.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAOImpl<T, ID extends Serializable> implements BaseDAO<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;

	public BaseDAOImpl() {

	}

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return entityClass;
	}

	/**
	 * <����ʵ��> <��������ʵ��>
	 * 
	 * @param t
	 *            ʵ�����
	 */
	public void save(T t) {
		this.getSession().save(t);
	}

	/**
	 * <������߸���ʵ��>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public void saveOrUpdate(T t) {
		this.getSession().saveOrUpdate(t);
	}

	/**
	 * <load> <����ʵ���load����>
	 * 
	 * @param id
	 *            ʵ���id
	 * @return ��ѯ������ʵ��
	 */
	public T load(ID id) {
		T load = (T) this.getSession().load(getEntityClass(), id);
		return load;
	}

	/**
	 * <get> <���ҵ�get����>
	 * 
	 * @param id
	 *            ʵ���id
	 * @return ��ѯ������ʵ��
	 */
	public T get(ID id) {
		T load = (T) this.getSession().get(getEntityClass(), id);
		return load;
	}

	/**
	 * <contains>
	 * 
	 * @param t
	 *            ʵ��
	 * @return �Ƿ����
	 */
	public boolean contains(T t) {
		return this.getSession().contains(t);
	}

	/**
	 * <delete> <ɾ�����е�t����>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public void delete(T t) {
		this.getSession().delete(t);
	}

	/**
	 * <����IDɾ������>
	 * 
	 * @param Id
	 *            ʵ��id
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteById(ID Id) {
		T t = get(Id);
		if (t == null) {
			return false;
		}
		delete(t);
		return true;
	}

	/**
	 * <ɾ������>
	 * 
	 * @param entities
	 *            ʵ���Collection����
	 */
	public void deleteAll(Collection<T> entities) {
		for (Object entity : entities) {
			this.getSession().delete(entity);
		}
	}

	/**
	 * <ִ��Hql���>
	 * 
	 * @param hqlString
	 *            hql
	 * @param values
	 *            ������������
	 */
	public void executeHql(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.executeUpdate();
	}

	/**
	 * <ִ��Sql���>
	 * 
	 * @param sqlString
	 *            sql
	 * @param values
	 *            ������������
	 */
	public void executeSql(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.executeUpdate();
	}

	/**
	 * <����HQL������Ψһʵ��>
	 * 
	 * @param hqlString
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯʵ��
	 */
	public T getByHQL(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	/**
	 * <����SQL������Ψһʵ��>
	 * 
	 * @param sqlString
	 *            SQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯʵ��
	 */
	public T getBySQL(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	/**
	 * <����HQL��䣬�õ���Ӧ��list>
	 * 
	 * @param hqlString
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯ���ʵ���List����
	 */
	public List<T> getListByHQL(String hqlString, Object... values) {
		Query query = this.getSession().createQuery(hqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	/**
	 * <����SQL��䣬�õ���Ӧ��list>
	 * 
	 * @param sqlString
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯ���ʵ���List����
	 */
	public List<T> getListBySQL(String sqlString, Object... values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	/**
	 * ��sql���õ�List
	 * 
	 * @param sql
	 * @param map
	 * @param values
	 * @return List
	 */
	public List findListBySql(final String sql, final RowMapper map, final Object... values) {
		final List list = new ArrayList();
		// ִ��JDBC��������������
		Work jdbcWork = new Work() {
			public void execute(Connection connection) throws SQLException {

				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					ps = connection.prepareStatement(sql);
					for (int i = 0; i < values.length; i++) {
						setParameter(ps, i, values[i]);

					}

					rs = ps.executeQuery();
					int index = 0;
					while (rs.next()) {
						Object obj = map.mapRow(rs, index++);
						list.add(obj);

					}
				} finally {
					if (rs != null) {
						rs.close();

					}
					if (ps != null) {
						ps.close();
					}
				}
			}
		};
		this.getSession().doWork(jdbcWork);
		return list;
	}

	/**
	 * <refresh>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public void refresh(T t) {
		this.getSession().refresh(t);
	}

	/**
	 * <update>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public void update(T t) {
		this.getSession().update(t);
	}

	/**
	 * <����HQL�õ���¼��>
	 * 
	 * @param hql
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��¼����
	 */
	public Long countByHql(String hql, Object... values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (Long) query.uniqueResult();
	}

	/**
	 * <HQL��ҳ��ѯ>
	 * 
	 * @param hql
	 *            HQL���
	 * @param countHql
	 *            ��ѯ��¼������HQL���
	 * @param pageNo
	 *            ��һҳ
	 * @param pageSize
	 *            һҳ������
	 * @param values
	 *            ����Object�������
	 * @return PageResults�ķ�װ�࣬���������ҳ�����Ϣ�Լ���ѯ������List����
	 */
	public PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize,
			Object... values) {
		PageResults<T> retValue = new PageResults<T>();
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		int currentPage = pageNo > 1 ? pageNo : 1;
		retValue.setCurrentPage(currentPage);
		retValue.setPageSize(pageSize);
		if (countHql == null) {
			ScrollableResults results = query.scroll();
			results.last();
			retValue.setTotalCount(results.getRowNumber() + 1);// �����ܼ�¼��
		} else {
			Long count = countByHql(countHql, values);
			retValue.setTotalCount(count.intValue());
		}
		retValue.resetPageNo();
		List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
		if (itemList == null) {
			itemList = new ArrayList<T>();
		}
		retValue.setResults(itemList);

		return retValue;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @return session
	 */
	public Session getSession() {
		// ��Ҫ����������ܵõ�CurrentSession
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 
	 * ����ÿ�����������
	 * 
	 * @param ps
	 * @param pos
	 *            ?ռλ����������0��ʼ
	 * @param data
	 * @throws SQLException
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private void setParameter(PreparedStatement ps, int pos, Object data) throws SQLException {
		if (data == null) {
			ps.setNull(pos + 1, Types.VARCHAR);
			return;
		}
		Class dataCls = data.getClass();
		if (String.class.equals(dataCls)) {
			ps.setString(pos + 1, (String) data);
		} else if (boolean.class.equals(dataCls)) {
			ps.setBoolean(pos + 1, ((Boolean) data));
		} else if (int.class.equals(dataCls)) {
			ps.setInt(pos + 1, (Integer) data);
		} else if (double.class.equals(dataCls)) {
			ps.setDouble(pos + 1, (Double) data);
		} else if (Date.class.equals(dataCls)) {
			Date val = (Date) data;
			ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
		} else if (BigDecimal.class.equals(dataCls)) {
			ps.setBigDecimal(pos + 1, (BigDecimal) data);
		} else {
			// δ֪����
			ps.setObject(pos + 1, data);
		}

	}

}