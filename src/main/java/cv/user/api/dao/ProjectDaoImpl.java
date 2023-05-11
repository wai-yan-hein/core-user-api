package cv.user.api.dao;

import cv.user.api.common.Util1;
import cv.user.api.entity.Project;
import cv.user.api.entity.ProjectKey;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectDaoImpl extends AbstractDao<ProjectKey, Project> implements ProjectDao {
    @Override
    public Project save(Project obj) {
        saveOrUpdate(obj, obj.getKey());
        return obj;
    }

    @Override
    public Project find(ProjectKey key) {
        return getByKey(key);
    }

    @Override
    public List<Project> search(String compCode) {
        String hsql = "select o from Project o where o.key.compCode ='" + compCode + "'";
        return findHSQL(hsql);
    }

    @Override
    public List<Project> search(String str, String compCode) {
        String sql = "select project_no,project_name,comp_code\n" +
                "from project\n" +
                "where comp_code ='" + compCode + "'\n" +
                "and project_status='PROGRESS'\n" +
                "and (project_no like '" + str + "%' or project_name like '" + str + "%')\n" +
                "order by project_no,project_name";
        List<Project> list = new ArrayList<>();
        List<Map<String, Object>> hm = getList(sql);
        hm.forEach(obj -> {
            Project p = new Project();
            ProjectKey key = new ProjectKey();
            key.setProjectNo(Util1.getString(obj.get("project_no")));
            key.setCompCode(Util1.getString(obj.get("comp_code")));
            p.setKey(key);
            p.setProjectName(Util1.getString(obj.get("project_name")));
            list.add(p);
        });
        return list;
    }
}
