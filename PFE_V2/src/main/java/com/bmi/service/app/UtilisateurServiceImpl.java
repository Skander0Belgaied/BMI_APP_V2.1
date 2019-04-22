package com.bmi.service.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtiliasateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void createUtilisateur(Utilisateur utilisateur) {
		final String pwd=utilisateur.getUtilisateurPassword();
		utilisateur.setUtilisateurPassword(bCryptPasswordEncoder.encode(pwd));
		 utilisateurRepository.save(utilisateur);
	}

	@Override
	public Optional<Utilisateur> getUtilisateur(long utilisateurId) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findById(utilisateurId);
	}
	public List<Utilisateur> getUtilisateurByMail(String utilisateurEmail) {
		// TODO Auto-generated method stub
		List<Utilisateur> utilisateurs=null;
		if(utilisateurEmail.equals("ALL")) {
			 utilisateurs=utilisateurRepository.findAll();
		}
		else {
			utilisateurs=utilisateurRepository.findLikeMailOrFirstNameOrLastName(utilisateurEmail);
		}
		return utilisateurs ;
	}

	@Override
	public Utilisateur editUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.delete(utilisateur);
	}

	@Override
	public boolean deleteUtilisateur(long id) {
	Optional<Utilisateur> utiliasteurSerach=utilisateurRepository.findById(id);
	if(utiliasteurSerach.isPresent()) {
	utilisateurRepository.deleteById(id);
	return true;
	}else
		System.out.println("pas trouvééé");
	return false;
	}

	@Override
	public List<Utilisateur> getAllUtilisateur(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<Utilisateur> getAllUtilisateur() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll();
	}
	
}
