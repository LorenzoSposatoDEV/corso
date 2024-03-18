package com.sistemi.informativi.controller;

import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.exception.CustomException;
import com.sistemi.informativi.service.AcademyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
TEST CONSUMER
    GET http://localhost:8080/rest/api/academies
    GET http://localhost:8080/rest/api/academies/code/{code}
    POST http://localhost:8080/rest/api/academies
    PUT http://localhost:8080/rest/api/academies
    DELETE http://localhost:8080/rest/api/academies/code/{code}
 */

/*
L'annotation @RestController fa si che la classe diventi un Restful Web Service Provider
in grado di ricevere file JSON e di restituire file JSON
 */
@CrossOrigin
@RestController
@RequestMapping("/rest/api/academies")
public class AcademyController {

    private AcademyService academyService;

    public AcademyController(AcademyService academyService) {
        this.academyService = academyService;
    }

    /*
    Il Consumer per consumare l'operazione dovrà fare la seguente chiamata:
    GET http://localhost:8080/rest/api/academies
     */
    @GetMapping
    public List<Academy> findAllAcadamies() throws CustomException {

        /*
        Nel caso positivo il metodo del Service ritorna una List<Academy> che,
        essendo un Oggetto Java, viene automaticamente convertito dall'Object
        Mapper in un file JSON
         */
        return academyService.checkFindAllAcadamies();

    }

    /*
    Il Consumer per consumare l'operazione dovrà fare la seguente chiamata:
    GET http://localhost:8080/rest/api/academies/code/{code}

    L'annotation @PathVariable va utlizzata per chiedere a Spring Restful di
    convertire la variabile di path inserita nella url del Browser da parte del
    Consumer in una variabile Java
     */
    @GetMapping("/code/{code}")
    public Academy findAcademyByCode(@PathVariable String code) throws CustomException {

        return academyService.checkFindAcademyByCode(code);

    }

    /*
   Il Consumer per consumare l'operazione dovrà fare la seguente chiamata:
   POST http://localhost:8080/rest/api/academies
    */
    @PostMapping
    public Academy saveAcademy(@Valid @RequestBody Academy academy) {

        return academyService.checkSaveOrUpdate(academy);

    }

    /*
   Il Consumer per consumare l'operazione dovrà fare la seguente chiamata:
   PUT http://localhost:8080/rest/api/academies
    */
    @PutMapping
    public Academy updateAcademy(@Valid @RequestBody Academy academy) {

        return academyService.checkSaveOrUpdate(academy);

    }

    /*
   Il Consumer per consumare l'operazione dovrà fare la seguente chiamata:
   DELETE http://localhost:8080/rest/api/academies/code/{code}
    */
    @DeleteMapping("/code/{code}")
    public Map<String, Boolean> deleteAcademy(@PathVariable String code) {

        return academyService.checkDeleteAcademy(code);

    }


}
