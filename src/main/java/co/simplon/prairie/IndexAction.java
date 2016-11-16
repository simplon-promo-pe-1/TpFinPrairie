package co.simplon.prairie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;

@MadvocAction
public class IndexAction {

	static String databaseUrl = "jdbc:oracle:thin:SIMPLON/SIMPLON@localhost:1521:XE";

	static String clauseWhereEquipeSF = " WHERE t.city='San Francisco' ";
	
	static String clauseWhereJoueurs = " AND p.position = 'Catcher' AND p.weight >= 220 ";
	
	static String triJoueurs = " ORDER BY p.dob ASC ";

	static String requeteEquipeSF = " SELECT * FROM TEAMS t "
			+ clauseWhereEquipeSF;

	static String requeteListeJoueursEquipeSF = " SELECT * FROM PLAYERS p JOIN TEAMS t ON p.team_id=t.id "
			+ clauseWhereEquipeSF + clauseWhereJoueurs + triJoueurs;

	@Out
	String nomEquipe;
	
	@Out
	List<Joueur> joueurs;

	@Action("/")
	public String view() throws Exception {
		DriverManager.registerDriver(new OracleDriver());

		Connection connexion = DriverManager.getConnection(databaseUrl);
		
		nomEquipe = trouverEquipeSanFrancisco(connexion);
		joueurs = listerJoueursCatcherPoidsLourd(connexion);

		connexion.close();
		return "/index";
	}

	private List<Joueur> listerJoueursCatcherPoidsLourd(Connection connexion) throws Exception {
		List<Joueur> joueurs = new ArrayList<>();
		
		Statement requete = connexion.createStatement();
		ResultSet resultat = requete.executeQuery(requeteListeJoueursEquipeSF);
		while (resultat.next()) {
			Joueur joueur = new Joueur();
			joueur.setPrenom(resultat.getString("fname"));
			joueur.setNom(resultat.getString("lname"));
			joueur.setPoids(resultat.getInt("weight"));
			joueur.setNumeroMaillot(resultat.getInt("jersey"));
			joueur.setDateNaissance(resultat.getDate("dob"));
			joueurs.add(joueur);
		}
		
		return joueurs;
	}

	private String trouverEquipeSanFrancisco(Connection connexion) throws Exception {
		String equipe = null;		
		Statement requete = connexion.createStatement();
		ResultSet resultat = requete.executeQuery(requeteEquipeSF);
		if (resultat.next()) {
			equipe = resultat.getString("NAME");
		}
		resultat.close();
		requete.close();

		return equipe;
	}
}
