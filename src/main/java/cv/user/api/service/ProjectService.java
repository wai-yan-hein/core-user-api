package cv.user.api.service;

import cv.user.api.entity.Project;
import cv.user.api.entity.ProjectKey;

import java.util.List;

public interface ProjectService {
    Project save(Project obj);

    Project find(ProjectKey key);

    List<Project> search(String compCode);

    List<Project> search(String str, String compCode);
}
