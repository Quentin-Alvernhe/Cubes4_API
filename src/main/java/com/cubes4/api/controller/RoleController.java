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

import com.cubes4.api.dao.RoleDao;
import com.cubes4.api.model.Role;

@CrossOrigin()
@RestController
public class RoleController {

    @Autowired
    private RoleDao roleDao;

    // Obtenir un produit
    @GetMapping("/role/{id}")
    public ResponseEntity<?> getRole(@PathVariable int id) {
        try {
            Optional<Role> roleOptional = roleDao.findById(id);
            if (roleOptional.isEmpty()) {
                // Retourne une erreur 404 si fournisseur pas trouvé
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(roleOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération du fournisseur : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir la liste des produits
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        try {
            List<Role> listRoles = roleDao.findAll();
            return new ResponseEntity<>(listRoles, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération des fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ajouter un fournisseur et modifier
    @PostMapping("/role")
    public ResponseEntity<?> updateOrCreateRole(@RequestBody Role role) {
        try {
            // Si c'est une mise à jour (update)
            if (role.getId() != null) {
                Optional<Role> roleOptional = roleDao.findById(role.getId());
                // si le fournisseur n'existe pas, il s'agit d'une action non autorisée
                // cad : affecté directement un id sans suivre l'auto-incrémentation
                if (roleOptional.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                roleDao.save(role);
                return new ResponseEntity<>(roleOptional.get(), HttpStatus.OK);
            }

            // si c'est une création
            roleDao.save(role);
            return new ResponseEntity<>(role, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de l'ajout des fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un fournisseur
    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        try {
            Optional<Role> roleOptional = roleDao.findById(id);
            if (roleOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            roleDao.deleteById(id);
            return new ResponseEntity<>(roleOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la supprression des fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
