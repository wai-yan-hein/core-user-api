package cv.user.api.service;

import cv.user.api.dao.ProjectDao;
import cv.user.api.entity.Project;
import cv.user.api.entity.ProjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao dao;

    @Override
    public Project save(Project obj) {
        return dao.save(obj);
    }

    @Override
    public Project find(ProjectKey key) {
        return dao.find(key);
    }

    @Override
    public List<Project> search(String compCode) {
        return dao.search(compCode);
    }

    @Override
    public List<Project> search(String str, String compCode) {
        return dao.search(str,compCode);
    }
}
