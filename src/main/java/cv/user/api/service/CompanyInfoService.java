package cv.user.api.service;

import cv.user.api.common.YearEnd;
import cv.user.api.entity.CompanyInfo;

public interface CompanyInfoService {
    CompanyInfo save(CompanyInfo info);

    YearEnd yearEnd(YearEnd end);
}
