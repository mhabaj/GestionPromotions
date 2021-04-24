package com.promotion.dao;

import com.promotion.bean.EtudiantBean;
import com.promotion.bean.PromotionBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DaoEtudiant extends DaoGenerator {

	public static void creerEtudiant(EtudiantBean etudiant) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
        	String insertQuery = "INSERT INTO Etudiant (admin, email, mot_de_passe, nom, prenom, idPromotion) VALUES (?, ?, ?, ?, ?, ?)";
        	try(PreparedStatement preparedStatement = con.prepareStatement(insertQuery)){
        		preparedStatement.setString(2, etudiant.getEmail());
        		preparedStatement.setString(3, etudiant.getMotDePasse());
        		preparedStatement.setString(4, etudiant.getNom());
        		preparedStatement.setBoolean(1, etudiant.getAdmin());
        		preparedStatement.setString(5, etudiant.getPrenom());
        		preparedStatement.setInt(6, getIdPromotion(etudiant.getNomPromotion()));
        		preparedStatement.executeUpdate();
        	}
    	} catch(Exception e) {
    	throw new RuntimeException(e);
    	}
}

	public static boolean emailExistsInDatabase(String login) {
		try ( Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {

            String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
            try ( PreparedStatement statement = connection.prepareStatement(selectQuery)) {
                statement.setString(1, login);
                try ( ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public static EtudiantBean getEtudiant(String email) {
		EtudiantBean etudiantRecherche = new EtudiantBean();
		try ( Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
        	String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
        	try (PreparedStatement statement = con.prepareStatement(selectQuery)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                    	etudiantRecherche.setEmail(resultSet.getString(3));
                    	etudiantRecherche.setMotDePasse(resultSet.getString(4));
                    	etudiantRecherche.setNom(resultSet.getString(5));
                    	etudiantRecherche.setDateDInscription(new Timestamp(resultSet.getDate(7).getTime()));
                    	etudiantRecherche.setAdmin(resultSet.getBoolean(2));
                    	etudiantRecherche.setPrenom(resultSet.getString(6));
                    	etudiantRecherche.setAnnee(resultSet.getInt(8));
                    }
                }
        	}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return etudiantRecherche;
	}

	public static boolean loginExistsInDatabase(String login, String motDePasse) {
		try ( Connection con = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            String selectQuery = "SELECT * FROM Etudiant WHERE email=? AND mot_de_passe=?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                preparedStatement.setString( 1, login );
                preparedStatement.setString( 2, motDePasse );
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public static Boolean etudiantIsAdmin(EtudiantBean etudiant) {
		try (Connection con = DriverManager.getConnection( dbURL, dbLogin, dbPassword)) {

            String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
            try ( PreparedStatement preparedStatement = con.prepareStatement( selectQuery ) ) {
                preparedStatement.setString( 1, etudiant.getEmail() );
                try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
                    if ( resultSet.next() ) {
                  	 return  resultSet.getBoolean(2);
                        
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
	}


	public static List<EtudiantBean> getAllNonAdminEtudiants() {
		List<EtudiantBean> listeDesEtudiantsNonAdmins = new ArrayList<EtudiantBean>();
    	try ( Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            String selectQuery = "SELECT * FROM Etudiant WHERE admin=?";
            try ( PreparedStatement preparedStatement = con.prepareStatement( selectQuery ) ) {
                preparedStatement.setBoolean( 1, false );
                try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
                    while ( resultSet.next() ) {
                    	EtudiantBean etudiantNonAdmin = new EtudiantBean();
                    	etudiantNonAdmin.setId(resultSet.getInt(1));
                    	etudiantNonAdmin.setEmail(resultSet.getString(3));
                    	etudiantNonAdmin.setMotDePasse(resultSet.getString(4));
                    	etudiantNonAdmin.setNom(resultSet.getString(5));
                    	etudiantNonAdmin.setDateDInscription(new Timestamp(resultSet.getDate(7).getTime()));
                    	etudiantNonAdmin.setAdmin(resultSet.getBoolean(2));
                    	etudiantNonAdmin.setPrenom(resultSet.getString(6));
                    	etudiantNonAdmin.setAnnee(resultSet.getInt(8));
                        listeDesEtudiantsNonAdmins.add(etudiantNonAdmin);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    	return listeDesEtudiantsNonAdmins;
	}
	
	public static void creerPromotion(PromotionBean promotion) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
        	String insertQuery = "INSERT INTO Promotion ( nomPromotion, annee) VALUES (?, ?)";
        	try(PreparedStatement preparedStatement = con.prepareStatement(insertQuery)){
        		preparedStatement.setString(1, promotion.getNomPromotion());
        		preparedStatement.setInt(2, promotion.getAnnee());
        		preparedStatement.executeUpdate();
        	}
    	} catch(Exception e) {
    	throw new RuntimeException(e);
    	}
	}
	
	public static boolean promotionExists(String nomPromotion) {
		try (Connection con = DriverManager.getConnection( dbURL, dbLogin, dbPassword)) {

            String selectQuery = "SELECT * FROM Promotion WHERE nomPromotion=?";
            try ( PreparedStatement preparedStatement = con.prepareStatement( selectQuery ) ) {
                preparedStatement.setString(1, nomPromotion);
                try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
                    if ( resultSet.next()) {
                    	return true;  
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
	}

	public static void deleteEtudiant(int etudiantId) {
		try(Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
			String deleteQuery = "DELETE FROM Etudiant WHERE id=?;";
			try(PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
				preparedStatement.setInt(1, etudiantId);
				preparedStatement.executeUpdate();
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public static int getAnneeEtudiant(String promotion) {
		int anneeDeLaPromotion = 0;
		try ( Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            String selectQuery = "SELECT * FROM Promotion WHERE nomPromotion=?";
            try ( PreparedStatement preparedStatement = con.prepareStatement( selectQuery ) ) {
                preparedStatement.setString( 1, promotion );
                try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
                    while ( resultSet.next() ) {
                    	anneeDeLaPromotion = resultSet.getInt(3);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    	return anneeDeLaPromotion;
	}
	
	public static int getIdPromotion(String promotion) {
		int idPromotion = 0;
		try ( Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            String selectQuery = "SELECT * FROM Promotion WHERE nomPromotion=?";
            try ( PreparedStatement preparedStatement = con.prepareStatement( selectQuery ) ) {
                preparedStatement.setString( 1, promotion );
                try ( ResultSet resultSet = preparedStatement.executeQuery() ) {
                    while ( resultSet.next() ) {
                    	idPromotion = resultSet.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    	return idPromotion;
	}

}
