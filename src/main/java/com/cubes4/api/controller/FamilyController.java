package com.cubes4.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.cubes4.api.dao.FamilyDao;
import com.cubes4.api.model.Family;

@CrossOrigin()
@RestController
public class FamilyController {

    @Autowired
    private FamilyDao familyDao;

    // Obtenir une famille
    @GetMapping("/family/{id}")
    public ResponseEntity<?> getFamily(@PathVariable int id) {
        try {
            Optional<Family> familyOptional = familyDao.findById(id);
            if (familyOptional.isEmpty()) {
                // Retourne une erreur 404 si famille pas trouvée
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(familyOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération de la famille : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir la liste des familles
    @GetMapping("/families")
    public ResponseEntity<?> getFamilies() {
        try {
            List<Family> listFamilies = familyDao.findByDeletedFalse();
            return new ResponseEntity<>(listFamilies, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération des familles : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ajouter une famille et modifier
    @PostMapping("/family")
    public ResponseEntity<?> updateOrCreateFamily(@RequestBody Family family) {
        try {
            // Si c'est une mise à jour (update)
            if (family.getId() != null) {
                Optional<Family> familyOptional = familyDao.findById(family.getId());

                // si la famille n'existe pas, il s'agit d'une action non autorisée
                // cad : affecté directement un id sans suivre l'auto-incrémentation
                if (familyOptional.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                familyDao.save(family);
                return new ResponseEntity<>(familyOptional.get(), HttpStatus.OK);
            }

            // si c'est une création
            familyDao.save(family);
            return new ResponseEntity<>(family, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de l'ajout des familles : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un famille
    @DeleteMapping("/family/{id}")
    public ResponseEntity<?> deleteFamily(@PathVariable int id) {
        try {
            Optional<Family> familyOptional = familyDao.findById(id);
            if (familyOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            familyDao.deleteById(id);
            return new ResponseEntity<>(familyOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la supprression des familles : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
