package com.bmi.service.app;

import java.util.List;
import java.util.Optional;

import com.bmi.app.entity.Utilisateur;

public interface UtiliasateurService {
	
	Utilisateur createUtilisateur(Utilisateur utilisateur);

	Optional<Utilisateur> getUtilisateur(long id);

	Utilisateur editUtilisateur(Utilisateur utilisateur);

	void deleteUtilisateur(Utilisateur utilisateur);

	boolean deleteUtilisateur(long utilisateurId);

	List<Utilisateur> getAllUtilisateur(int pageNumber, int pageSize);

	List<Utilisateur> getAllUtilisateur();

}
