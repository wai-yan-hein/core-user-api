package cv.user.api.dao;

import cv.user.api.entity.Project;
import cv.user.api.entity.ProjectKey;

import java.util.List;

public interface ProjectDao {
    Project save(Project obj);
    Project find(ProjectKey key);
    List<Project> search(String compCode);
    List<Project> search(String str, String compCode);
}
