package co.simplon.prairie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;

@MadvocAction
public class IndexAction {

    static String databaseUrl = "jdbc:oracle:thin:SIMPLON/SIMPLON@localhost:1521:XE";

    static String requeteEquipeSF = "SELECT * FROM TEAMS WHERE city='San Francisco' ";
	
    @Out
    String nomEquipe;

    @Action("/")
    public String view() throws Exception {
        nomEquipe = trouverEquipeSanFrancisco();
        return "/index";
    }
    
    private String trouverEquipeSanFrancisco() throws Exception {
    	String equipe = null;
    	
        DriverManager.registerDriver(new OracleDriver());

        Connection connexion = DriverManager.getConnection(databaseUrl);
        Statement requete = connexion.createStatement();
        ResultSet resultat = requete.executeQuery(requeteEquipeSF);
        if (resultat.next()) {
            equipe = resultat.getString("NAME");
        }
        resultat.close();
        requete.close();
        connexion.close();
        
    	return equipe;
    }
}
