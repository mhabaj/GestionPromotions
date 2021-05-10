package com.promotion.dao;

import com.promotion.bean.EtudiantBean;
import com.promotion.bean.MatiereBean;
import com.promotion.bean.NoteBean;
import com.promotion.bean.PromotionBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * classe DATA ACCESS OBJECT qui contiennent les fonctions qui interragissent avec la bdd pour remplir les beans.
 * @author Sean, Juliette & Mahmod
 *
 */
public class DaoEtudiant extends DaoGenerator {

	/**
	 * fonction permettant d'inserer un etudiant dans la base de donnees
	 * @param etudiant
	 */
	public static void creerEtudiant(EtudiantBean etudiant) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String insertQuery = "INSERT INTO Etudiant (admin, email, mot_de_passe, nom, prenom, date_inscription , idPromotion) VALUES (?, ?, ?, ?, ?, NOW() ,?)";
			try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
				preparedStatement.setString(2, etudiant.getEmail());
				preparedStatement.setString(3, etudiant.getMotDePasse());
				preparedStatement.setString(4, etudiant.getNom());
				preparedStatement.setBoolean(1, etudiant.getAdmin());
				preparedStatement.setString(5, etudiant.getPrenom());
				preparedStatement.setInt(6, getIdPromotionFromNomPromotion(etudiant.getNomPromotion()));

				preparedStatement.executeUpdate();
///////////////////////////////////////////////////////
				////////////////////////////////////// pour chaque etudiant cree on lui insere des notes generees au hasard dans toutes les matieres de la promo.

				PromotionBean promo = getPromotionById(getIdPromotionFromNomPromotion(etudiant.getNomPromotion()));

				for (MatiereBean currentMatiere : promo.getMatieres()) {
					Random r = new Random();
					double randomValue = Math.round(0 + (20 - 0) * r.nextDouble() * 100.0) / 100.0;
					String insertQuery2 = "INSERT INTO `etudie` (`idNote`, `idMatiere`, `idEtudiant`, `note`) VALUES (NULL, ?, ?, ?);";

					try (PreparedStatement preparedStatement2 = con.prepareStatement(insertQuery2)) {

						preparedStatement2.setInt(1, currentMatiere.getId());
						preparedStatement2.setInt(2, getEtudiant(etudiant.getEmail()).getId());
						preparedStatement2.setDouble(3, randomValue);

						preparedStatement2.executeUpdate();

					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * fonction permettant d'obtenir une promotion en fonction de son id
	 * @param promotionId
	 * @return promotionBean
	 */
	public static PromotionBean getPromotionById(int promotionId) {
		PromotionBean promo = new PromotionBean();

		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM promotion WHERE idPromotion = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setInt(1, promotionId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						promo.setNomPromotion(resultSet.getString(2));
						promo.setAnnee(resultSet.getInt(3));
						promo.setMatieres(getMatieresFromPromotionId(promotionId));
						promo.setEtudiants(getEtudiantsFromPromotion(promotionId));
					}
					return promo;

				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * obtenir la liste de MatiereBean en fonction de l'id de la promotion
	 * @param promotionId
	 * @return matieres
	 */
	public static ArrayList<MatiereBean> getMatieresFromPromotionId(int promotionId) {
///////////////////////////
		//////////////////////////
		ArrayList<MatiereBean> matieres = new ArrayList<MatiereBean>();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM comprend natural join matiere WHERE idPromotion = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setInt(1, promotionId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						MatiereBean matiere = new MatiereBean();
						matiere.setId(resultSet.getInt(1));
						matiere.setNomMatiere(resultSet.getString(3));
						
						matiere.setCoefficientMatiere(resultSet.getDouble(4));
						matieres.add(matiere);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return matieres;
	}

	/**
	 * obtenir la liste des EtudiantBean en fonction de l'id de leur promotion
	 * @param idPromotion
	 * @return listeDesEtudiants
	 */
	public static ArrayList<EtudiantBean> getEtudiantsFromPromotion(int idPromotion) {
		ArrayList<EtudiantBean> listeDesEtudiants = new ArrayList<EtudiantBean>();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Etudiant WHERE admin = 0 AND idPromotion = ? ";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setInt(1, idPromotion);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						EtudiantBean etudiant = new EtudiantBean();
						etudiant.setId(resultSet.getInt(1));
						etudiant.setAdmin(resultSet.getBoolean(2));
						etudiant.setEmail(resultSet.getString(3));
						etudiant.setMotDePasse(resultSet.getString(4));
						etudiant.setNom(resultSet.getString(5));
						etudiant.setPrenom(resultSet.getString(6));
						etudiant.setDateDInscription(new Timestamp(resultSet.getDate(7).getTime()));
						etudiant.setAnnee(resultSet.getInt(8));
						etudiant.setMoyenneGenerale(calculeMoyenneGeneraleEtudiant(resultSet.getString(3)));
						listeDesEtudiants.add(etudiant);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listeDesEtudiants;
	}

	/**
	 * permet de calculer la moyenne generale de l'etudiant identifie par son adresse mail.
	 * @param email
	 * @return moyenne
	 */
	public static double calculeMoyenneGeneraleEtudiant(String email) {
		EtudiantBean etudiant = getEtudiant(email);
		ArrayList<NoteBean> ListeNotesEtudiant = getNotesEtudiant((int) etudiant.getId());
		Double moyenne = 0.0;
		Double sommeCoeff = 0.0;
		if (!ListeNotesEtudiant.isEmpty()) {
			for (NoteBean note : ListeNotesEtudiant) {
				moyenne += note.getNote() * note.getMatiere().getCoefficientMatiere();
				sommeCoeff += note.getMatiere().getCoefficientMatiere();
			}
			moyenne = moyenne / sommeCoeff;
		}

		return Math.round(moyenne * 100.0) / 100.0;
	}

	/**
	 * verifier si le login (l'email) existe dans la base de donnees
	 * @param login
	 * @return true || false
	 */
	public static boolean emailExistsInDatabase(String login) {
		try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {

			String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
			try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
				statement.setString(1, login);
				try (ResultSet resultSet = statement.executeQuery()) {
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

	/**
	 * obtenir un etudiant en fonction de son adresse mail
	 * @param email
	 * @return etudiantRecherche
	 */
	public static EtudiantBean getEtudiant(String email) {
		EtudiantBean etudiantRecherche = new EtudiantBean();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
			try (PreparedStatement statement = con.prepareStatement(selectQuery)) {
				statement.setString(1, email);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						etudiantRecherche.setId(resultSet.getInt(1));
						etudiantRecherche.setEmail(resultSet.getString(3));
						etudiantRecherche.setMotDePasse(resultSet.getString(4));
						etudiantRecherche.setNom(resultSet.getString(5));
						etudiantRecherche.setDateDInscription(new Timestamp(resultSet.getDate(7).getTime()));
						etudiantRecherche.setAdmin(resultSet.getBoolean(2));
						etudiantRecherche.setPrenom(resultSet.getString(6));
						try {
							etudiantRecherche.setMoyenneGenerale(resultSet.getDouble(9));
						} catch (Exception e) {
							etudiantRecherche.setMoyenneGenerale(0);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return etudiantRecherche;
	}

	/**
	 * verifier que le login (email) et le mot de passe existent dans la base de donnees
	 * @param login
	 * @param motDePasse
	 * @return true || false
	 */
	public static boolean loginExistsInDatabase(String login, String motDePasse) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Etudiant WHERE email=? AND mot_de_passe=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, login);
				preparedStatement.setString(2, motDePasse);
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

	/**
	 * renvoie true si un etudiant est admin
	 * @param etudiant
	 * @return true || false
	 */
	public static Boolean etudiantIsAdmin(EtudiantBean etudiant) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {

			String selectQuery = "SELECT admin FROM etudiant WHERE email=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, etudiant.getEmail());
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						return resultSet.getBoolean(1);

					} else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * fonction permettant d'obtenir la liste des etudiants non admins
	 * @return listeDesEtudiantsNonAdmins
	 */
	public static List<EtudiantBean> getAllNonAdminEtudiants() {
		List<EtudiantBean> listeDesEtudiantsNonAdmins = new ArrayList<EtudiantBean>();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Etudiant WHERE admin=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setBoolean(1, false);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						EtudiantBean etudiantNonAdmin = new EtudiantBean();
						etudiantNonAdmin.setId(resultSet.getInt(1));
						etudiantNonAdmin.setAdmin(resultSet.getBoolean(2));
						etudiantNonAdmin.setEmail(resultSet.getString(3));
						etudiantNonAdmin.setMotDePasse(resultSet.getString(4));
						etudiantNonAdmin.setNom(resultSet.getString(5));
						etudiantNonAdmin.setPrenom(resultSet.getString(6));
						etudiantNonAdmin.setDateDInscription(new Timestamp(resultSet.getDate(7).getTime()));
						etudiantNonAdmin.setAnnee(resultSet.getInt(8));
						etudiantNonAdmin.setMoyenneGenerale(calculeMoyenneGeneraleEtudiant(resultSet.getString(3)));
						listeDesEtudiantsNonAdmins.add(etudiantNonAdmin);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listeDesEtudiantsNonAdmins;
	}

	/**
	 * fonction qui permet de calculer la moyenne generale d'une promotion
	 * @param promo
	 * @return moyenne
	 */
	public static double calculeMoyenneGeneralePromo(PromotionBean promo) {
		Double moyenne = 0.0;
		Double sommeCoeff = 0.0;
		for (EtudiantBean currentEtudiant : promo.getEtudiants()) {
			moyenne += currentEtudiant.getMoyenneGenerale();
			sommeCoeff++;
		}
		
		return Math.round((moyenne / sommeCoeff) * 100.0) / 100.0;
	}
	
	/**
	 * fonction qui permet de retourner une liste contenant toutes les promotions.
	 * @return listPromotions
	 */
	public static ArrayList<PromotionBean> getAllPromotions() {
		ArrayList<PromotionBean> listPromotions = new ArrayList<PromotionBean>();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM promotion";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						PromotionBean promo = new PromotionBean();
						promo.setNomPromotion(resultSet.getString(2));
						promo.setAnnee(resultSet.getInt(3));
						promo.setEtudiants(getEtudiantsFromPromotion(resultSet.getInt(1)));
						promo.setMoyenneGeneralePromo(calculeMoyenneGeneralePromo(promo));
						listPromotions.add(promo);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listPromotions;
	}

	/**
	 * fonction permettant d'insérer une promotion dans la base de données à partir d'un PromotionBean
	 * @param promotion
	 */
	public static void creerPromotion(PromotionBean promotion) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String insertQuery = "INSERT INTO Promotion ( nomPromotion, annee) VALUES (?, ?)";
			try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
				preparedStatement.setString(1, promotion.getNomPromotion());
				preparedStatement.setInt(2, promotion.getAnnee());
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * retourne true si une promotion portant le nom de promo passé en paramètre existe dans la base de données
	 * @param nomPromotion
	 * @return true || false
	 */
	public static boolean promotionExists(String nomPromotion) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {

			String selectQuery = "SELECT * FROM Promotion WHERE nomPromotion=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, nomPromotion);
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

	/**
	 * fonction permettant de supprimer un etudiant de la base de donnees
	 * @param etudiantId
	 */
	public static void deleteEtudiant(int etudiantId) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String deleteQuery = "DELETE FROM Etudiant WHERE idEtudiant=?;";
			try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
				preparedStatement.setInt(1, etudiantId);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * obtenir l'annee d'obtention de diplôme d'un etudiant
	 * @param promotion
	 * @return anneeDeLaPromotion
	 */
	public static int getAnneeEtudiant(String promotion) {
		int anneeDeLaPromotion = 0;
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Promotion WHERE nomPromotion=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, promotion);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						anneeDeLaPromotion = resultSet.getInt(3);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return anneeDeLaPromotion;
	}

	/**
	 * obtenir l'id de la promotion en fonction du nom de la promotion
	 * @param promotion
	 * @return idPromotion
	 */
	public static int getIdPromotionFromNomPromotion(String promotion) {
		int idPromotion = 0;
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Promotion WHERE nomPromotion=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, promotion);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						idPromotion = resultSet.getInt(1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return idPromotion;
	}

	/**
	 * obtenir la liste de notes de l'étudiant en fonction de son idEtudiant
	 * @param etudiantId
	 * @return ListeNotesEtudiant
	 */
	public static ArrayList<NoteBean> getNotesEtudiant(int etudiantId) {
		ArrayList<NoteBean> ListeNotesEtudiant = new ArrayList<NoteBean>();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM etudie WHERE idEtudiant=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setInt(1, etudiantId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						NoteBean note = new NoteBean();
						note.setIdNote(resultSet.getInt(1));
						note.setMatiere(getMatiereFromIdMatiere(resultSet.getInt(2)));
						note.setNote(resultSet.getDouble(4));
						ListeNotesEtudiant.add(note);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ListeNotesEtudiant;
	}

	/**
	 * obtenir la matiere en fonction de son id
	 * @param idMatiere
	 * @return matiere
	 */
	public static MatiereBean getMatiereFromIdMatiere(int idMatiere) {
		MatiereBean matiere = new MatiereBean();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Matiere WHERE idMatiere=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setInt(1, idMatiere);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						matiere.setId(resultSet.getInt(1));
						matiere.setNomMatiere(resultSet.getString(2));
						matiere.setCoefficientMatiere(resultSet.getDouble(3));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return matiere;
	}

	/**
	 * obtenir l'id de l'étudiant en fonction de son adresse mail
	 * @param email
	 * @return idEtudiant
	 */
	public static int getIdEtudiant(String email) {
		int idEtudiant = 0;
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, email);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						idEtudiant = resultSet.getInt(1);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return idEtudiant;
	}

	/**
	 * obtenir l'id de la promotion de l'étudiant en fonction de l'adresse mail de l'étudiant
	 * @param email
	 * @return
	 */
	public static int getIdPromotionFromEmailEtudiant(String email) {
		int idPromotion = 0;
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, email);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						idPromotion = resultSet.getInt(8);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return idPromotion;
	}

	/**
	 * obtenir le nom de la promotion en fonction de son id
	 * @param idPromotion
	 * @return nomPromotion
	 */
	public static String getNomPromotionFromIdPromotion(int idPromotion) {
		String nomPromotion = "";
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Promotion WHERE idPromotion=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setInt(1, idPromotion);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						nomPromotion = resultSet.getString(2);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return nomPromotion;
	}

	/**
	 * obtenir l'annee d'obtention de diplôme de la promotion en fonction de son nom de promotion.
	 * @param nomPromotion
	 * @return
	 */
	public static int getAnneePromotionFromNomPromotion(String nomPromotion) {
		int anneePromotion = 0;
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM Promotion WHERE nomPromotion=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, nomPromotion);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						anneePromotion = resultSet.getInt(3);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return anneePromotion;
	}

	/**
	 * ajouter une matiere dans la base de données
	 * @param matiere
	 * @param nomPromotion
	 */
	public static void creerMatiere(MatiereBean matiere, String nomPromotion) {
		int idPromotion = getIdPromotionFromNomPromotion(nomPromotion);
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			
			String insertQuery = "INSERT INTO `matiere` (`idMatiere`, `nomMatiere`, `coefficientMatiere`) VALUES (NULL, ?, ?);";
			try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, matiere.getNomMatiere());
				preparedStatement.setDouble(2, matiere.getCoefficientMatiere());
				int affectedRows = preparedStatement.executeUpdate();

				if(affectedRows == 0) {
					throw new SQLException("Création matière non réussie.");
				}
				try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if(generatedKeys.next()) {
						int lastInsertedId = (int) generatedKeys.getLong(affectedRows);
						linkMatiereToPromotion(lastInsertedId, idPromotion);
					}else {
						throw new SQLException("idMatiere invalide.");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * permet de lier la matiere à la promotion après l'avoir ajoutée dans la base de données.
	 * @param idMatiere
	 * @param idPromotion
	 */
	public static void linkMatiereToPromotion(int idMatiere, int idPromotion) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String insertQuery = "INSERT INTO Comprend ( idMatiere, idPromotion) VALUES (?, ?)";
			try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
				preparedStatement.setInt(1, idMatiere);
				preparedStatement.setInt(2, idPromotion);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
