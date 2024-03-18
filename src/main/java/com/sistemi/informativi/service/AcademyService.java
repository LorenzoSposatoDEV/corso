package com.sistemi.informativi.service;

import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.exception.CustomException;

import java.util.List;
import java.util.Map;

public interface AcademyService {

    public Academy checkSaveOrUpdate(Academy academy);
    public List<Academy> checkFindAllAcadamies() throws CustomException;
    public Academy checkFindAcademyByCode(String code) throws CustomException;
    public Map<String,Boolean> checkDeleteAcademy(String code);

}
