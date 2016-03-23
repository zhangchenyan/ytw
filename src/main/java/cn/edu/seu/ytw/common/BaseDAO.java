package cn.edu.seu.ytw.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;



public interface BaseDAO<T, ID extends Serializable> {

	/**
	 * <����ʵ��> <��������ʵ��>
	 * 
	 * @param t
	 *            ʵ�����
	 */
	public abstract void save(T t);

	/**
	 * <������߸���ʵ��>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public abstract void saveOrUpdate(T t);

	/**
	 * <load> <����ʵ���load����>
	 * 
	 * @param id
	 *            ʵ���id
	 * @return ��ѯ������ʵ��
	 */
	public abstract T load(ID id);

	/**
	 * <get> <���ҵ�get����>
	 * 
	 * @param id
	 *            ʵ���id
	 * @return ��ѯ������ʵ��
	 */
	public abstract T get(ID id);

	/**
	 * <contains>
	 * 
	 * @param t
	 *            ʵ��
	 * @return �Ƿ����
	 */
	public abstract boolean contains(T t);

	/**
	 * <delete> <ɾ�����е�t����>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public abstract void delete(T t);

	/**
	 * <����IDɾ������>
	 * 
	 * @param Id
	 *            ʵ��id
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public abstract boolean deleteById(ID Id);

	/**
	 * <ɾ������>
	 * 
	 * @param entities
	 *            ʵ���Collection����
	 */
	public abstract void deleteAll(Collection<T> entities);

	/**
	 * <ִ��Hql���>
	 * 
	 * @param hqlString
	 *            hql
	 * @param values
	 *            ������������
	 */
	public abstract void executeHql(String hqlString, Object... values);

	/**
	 * <ִ��Sql���>
	 * 
	 * @param sqlString
	 *            sql
	 * @param values
	 *            ������������
	 */
	public abstract void executeSql(String sqlString, Object... values);

	/**
	 * <����HQL������Ψһʵ��>
	 * 
	 * @param hqlString
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯʵ��
	 */
	public abstract T getByHQL(String hqlString, Object... values);

	/**
	 * <����SQL������Ψһʵ��>
	 * 
	 * @param sqlString
	 *            SQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯʵ��
	 */
	public abstract T getBySQL(String sqlString, Object... values);

	/**
	 * <����HQL��䣬�õ���Ӧ��list>
	 * 
	 * @param hqlString
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯ���ʵ���List����
	 */
	public abstract List<T> getListByHQL(String hqlString, Object... values);

	/**
	 * <����SQL��䣬�õ���Ӧ��list>
	 * 
	 * @param sqlString
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��ѯ���ʵ���List����
	 */
	public abstract List<T> getListBySQL(String sqlString, Object... values);

	/**
	 * ��sql���õ�List
	 * 
	 * @param sql
	 * @param map
	 * @param values
	 * @return List
	 */
	public List findListBySql(final String sql, final RowMapper map, final Object... values);

	/**
	 * <refresh>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public abstract void refresh(T t);

	/**
	 * <update>
	 * 
	 * @param t
	 *            ʵ��
	 */
	public abstract void update(T t);

	/**
	 * <����HQL�õ���¼��>
	 * 
	 * @param hql
	 *            HQL���
	 * @param values
	 *            ����������Object����
	 * @return ��¼����
	 */
	public abstract Long countByHql(String hql, Object... values);

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
	public abstract PageResults<T> findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize,
			Object... values);

}