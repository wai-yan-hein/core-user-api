package cv.user.api.dao;

import org.springframework.stereotype.Repository;

@Repository
public class NativeSqlDaoImpl extends AbstractDao<String,Object> implements NativeSqlDao{
    @Override
    public void executeSql(String... sql) {
        execSql(sql);
    }
}
