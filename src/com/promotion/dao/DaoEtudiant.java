package com.promotion.dao;

import com.promotion.bean.EtudiantBean;
import com.promotion.bean.MatiereBean;
import com.promotion.bean.NoteBean;
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
			try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
				preparedStatement.setString(2, etudiant.getEmail());
				preparedStatement.setString(3, etudiant.getMotDePasse());
				preparedStatement.setString(4, etudiant.getNom());
				preparedStatement.setBoolean(1, etudiant.getAdmin());
				preparedStatement.setString(5, etudiant.getPrenom());
				preparedStatement.setInt(6, getIdPromotion(etudiant.getNomPromotion()));
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void updateMoyenneGenerale(String email) {
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

		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String updateQuery = "UPDATE Etudiant SET moyenneGenerale=? WHERE email=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
				preparedStatement.setDouble(1, moyenne);
				System.out.println(moyenne);
				System.out.println(email);
				preparedStatement.setString(2, email);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

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
						etudiantRecherche.setMoyenneGenerale(resultSet.getDouble(9));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return etudiantRecherche;
	}

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

	public static Boolean etudiantIsAdmin(EtudiantBean etudiant) {
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {

			String selectQuery = "SELECT * FROM Etudiant WHERE email=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, etudiant.getEmail());
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						return resultSet.getBoolean(2);

					} else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

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
						etudiantNonAdmin.setEmail(resultSet.getString(3));
						etudiantNonAdmin.setMotDePasse(resultSet.getString(4));
						etudiantNonAdmin.setNom(resultSet.getString(5));
						etudiantNonAdmin.setDateDInscription(new Timestamp(resultSet.getDate(7).getTime()));
						etudiantNonAdmin.setAdmin(resultSet.getBoolean(2));
						etudiantNonAdmin.setPrenom(resultSet.getString(6));
						etudiantNonAdmin.setAnnee(resultSet.getInt(8));
						etudiantNonAdmin.setMoyenneGenerale(resultSet.getDouble(9));
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
			try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
				preparedStatement.setString(1, promotion.getNomPromotion());
				preparedStatement.setInt(2, promotion.getAnnee());
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

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

	public static int getIdPromotion(String promotion) {
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

	public static ArrayList<NoteBean> getNotesEtudiant(int etudiantId) {
		ArrayList<NoteBean> ListeNotesEtudiant = new ArrayList<NoteBean>();
		try (Connection con = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
			String selectQuery = "SELECT * FROM etudie WHERE idEtudiant=?";
			try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
				preparedStatement.setInt(1, etudiantId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						NoteBean note = new NoteBean();
						note.setIdNote(resultSet.getInt(3));
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

}
