package cv.user.api.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;
    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;


    public T getByKey(PK key) {
        return entityManager.find(persistentClass, key);
    }


    public void saveOrUpdate(T entity,PK pk) {
        T t = entityManager.find(persistentClass, pk);
        if (t == null) entityManager.persist(entity);
        else entityManager.merge(entity);

    }

    public void update(T entity) {
        entityManager.merge(entity);
    }


    public List<T> findHSQL(String hsql) {
        return entityManager.createQuery(hsql, persistentClass).getResultList();
    }

    public void execSql(String... sql) {
        for (String s : sql) {
            jdbcTemplate.execute(s);
        }
    }

    public void remove(PK pk) {
        T byKey = getByKey(pk);
        if (byKey != null) {
            entityManager.remove(byKey);
        }
    }
    public List<Map<String, Object>> getList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    public ResultSet getResult(String sql) {
        return jdbcTemplate.execute((ConnectionCallback<ResultSet>) con -> {
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql);
        });
    }
    @Transactional
    public ResultSet getResult(String sql, Object... params) {
        return jdbcTemplate.execute((ConnectionCallback<ResultSet>) con -> {
            PreparedStatement stmt = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            String formattedSql = formatSqlWithParams(sql, params);
            //log.info("Executing SQL query: {}", formattedSql);
            return stmt.executeQuery();
        });
    }
    private String formatSqlWithParams(String sql, Object[] params) {
        for (Object param : params) {
            sql = sql.replaceFirst("\\?", param.toString());
        }
        return sql;
    }
}
