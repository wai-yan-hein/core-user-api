package cv.user.api.service;

import cv.user.api.common.Util1;
import cv.user.api.entity.Department;
import cv.user.api.repo.DepartmentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo repo;

    @Override
    public Department save(Department dep) {
        if (Util1.isNullOrEmpty(dep.getDeptId())) {
            dep.setDeptId(repo.findMaxId());
        }
        return repo.save(dep);
    }
}
