package com.sistemi.informativi.service;

import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.exception.CustomException;
import com.sistemi.informativi.repository.AcademyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcademyServiceImpl implements AcademyService{

    @Value("${no.acadamies}")
    private String noAcademies;
    @Value("${no.acadamy}")
    private String noAcademy;

    private AcademyRepository academyRepository;

    public AcademyServiceImpl(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    /*
        All'interno di una Applicazione Spring Boot in cui si implementa un
        Restful Web Service Provider è pratica consolidata far restituire ai
        metodi dello strato Service degli Oggetti Java in maniera tale che il
        RestController che invocherà i metodi del Service avrà già a disposizione
        tali Oggetti che verranno successivamente convertiti dall'Object Mapper di
        Spring Bott in formato JSON
         */
    @Override
    public Academy checkSaveOrUpdate(Academy academy) {

        Academy savedOrUpdate = null;

        try {
            savedOrUpdate = academyRepository.save(academy);
        }
        catch (IllegalArgumentException | OptimisticLockingFailureException ex) {
            ex.printStackTrace();
        }

        return savedOrUpdate;

    }

    @Override
    public List<Academy> checkFindAllAcadamies() throws CustomException {

        List<Academy> academies = academyRepository.findAll();

        if (academies.isEmpty()) {
            throw new CustomException(noAcademies);
        }

        return academies;

    }

    @Override
    public Academy checkFindAcademyByCode(String code) throws CustomException{

        /*
        Il metodo findById viene fornito da Spring Data Jpa e consente in generale di restuire
        un Oggetto Java che rappresenta il contenuto informativo relativo ad un record del
        Database con una specifica chiave primaria passata in input al metodo

        findById a partire dalla versione 2.7 di Spring Boot è un metodo funzionale, possiamo
        infatti invocare con l'operatore . una funzione di nome orElseThrow alla quale
        possiamo passare in input una Eccezzione

        Nel caso in cui l'operazione non vada in Eccezzione il metodo findById restituisce un
        Oggetto, altrimenti viene cattura l'Eccezione che richiamiamo nella funzione orElseThrow
         */
        return academyRepository.findById(code).orElseThrow(() -> new CustomException(noAcademy));

    }

    @Override
    public Map<String, Boolean> checkDeleteAcademy(String code) {

        Map<String, Boolean> deleteMap = new HashMap<>();

        try {
            academyRepository.deleteById(code);
            deleteMap.put("Deletion",true);
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        return deleteMap;

    }


}
