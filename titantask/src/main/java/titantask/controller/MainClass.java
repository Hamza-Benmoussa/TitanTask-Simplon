package titantask.controller;

import titantask.AffecterTask;
import titantask.CrudTache;
import titantask.Export_Import;
import titantask.ManagmenetUtilisateur;
import titantask.Utilisateur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        ManagmenetUtilisateur managmenetUtilisateur = new ManagmenetUtilisateur();
        Utilisateur utilisateur = new Utilisateur();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;32mBienvenue dans votre TaskManager ! \033[0m ");
        int i=0;
        while (i!=2) {
            System.out.println("========== Connectez-vous ==========");
            try {
                utilisateur = managmenetUtilisateur.login();
                if (utilisateur != null) {
                    System.out.println("\u001b[32mVous etes connecté avec succès\u001b[0m");
                    //
                    if (utilisateur.isRole()) {
                        if(subMenuAdmin(utilisateur)==1) {
                        	i=1;
                        }
                    } else {
                    	if(subMenuUser(utilisateur)==1) {
                        	i=1;
                        }
                    }
                    //
                } else {
                    System.out.println("\u001b[31mLe mail ou le mot de passe est incorrect || le compte n'existe pas \\u001b[0m");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Les données que vous avez saisie sont incorrecte ");
                scanner.nextLine();
            }
        }
    }


    static int subMenuAdmin(Utilisateur utilisateur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001b[32m============================================================================\u001b[0m");
        System.out.println("\t\t\t\t\t \033[1;34mBienvenue "+utilisateur.getNom()+" dans votre espace personnel\033[0m");
        while (true) {
            System.out.println("\u001b[32m============================================================================\u001b[0m");
            System.out.println("choisis une option :");
            System.out.println("1. Gestion des tâches");
            System.out.println("2. Gestion des utilisateurs");
            System.out.println("3. Deconnection");
            System.out.print("Entrez le numéro de votre choix: ");
            try {
                int inputChoix = scanner.nextInt();
                switch (inputChoix) {
                    case 1:
                    	if(gestionTacheAdmin(utilisateur)==1) {
                    		return 1;
                    	}
                        break;
                    case 2:
                    	if(gestionUserAdmin(utilisateur)==1) {
                    		return 1;
                    	}
                        break;
                    case 3:
                        System.out.println("M3a Salama");
                        //scanner.close();
                        return 1;
                        //System.exit(0);
                        //break;
                    default:
                        System.out.println("\u001b[31mLe nombre incorrect\u001b[0m");
                }
            } catch (NumberFormatException | InputMismatchException exception) {
                System.out.println("\u001b[31mle entree est incorrect\u001b[0m");
                scanner.nextLine();
            }
        }


    }

    static int subMenuUser(Utilisateur utilisateur) {
        CrudTache managmenetTache = new CrudTache();
        Export_Import ei=new Export_Import();
        System.out.println("\u001b[32m============================================================================\u001b[0m");
        System.out.println("\t\t\t\t\t \033[1;34mBienvenue "+utilisateur.getNom()+" dans votre espace personnel\033[0m");
        while (true) {
            System.out.println("\u001b[32m============================================================================\u001b[0m");
            System.out.println("Choisis une option");
            System.out.println("1. Ajouter une tache");
            System.out.println("2. Modifier une tache");
            System.out.println("3. Afficher list des taches");
            System.out.println("4. Supprimer une tache");
            System.out.println("5. Filter les taches par categorie");
            System.out.println("6. Trier les Taches par priorite ou date creation");
            System.out.println("7.Exporter vos taches");
            System.out.println("8.Importer list des taches");
            System.out.println("9. Deconnection");
            System.out.print("Entrez le numéro de votre choix: ");
            try {
                Scanner scanner = new Scanner(System.in);
                int inputChoix = scanner.nextInt();
                switch (inputChoix) {
                    case 1:
                        if (managmenetTache.ajouter(utilisateur) == 1){
                            System.out.println("ajout success");
                        }else
                            System.out.println("ajout refusee");
                        break;
                    case 2:
                        managmenetTache.modifier(utilisateur);
                        break;
                    case 3:
                        managmenetTache.afficher(utilisateur);
                        break;
                    case 4:
                        managmenetTache.supprimer(utilisateur);
                        break;
                    case 5:
                    	managmenetTache.filtrerCategorie(utilisateur);
                    	break;
					case 6:
                    	managmenetTache.tri(utilisateur);
                    	break;
                    case 7:
                    	if (ei.export_Tache(utilisateur.getId())){
                            System.out.println("Export reussi");
                        }else
                            System.out.println("Echec Export");
                        break;
					case 8:
					    
					    if (ei.import_tache(utilisateur.getId())){
					        System.out.println("Import reussi");
					    }else
					        System.out.println("Echec Import");
					    break;
                    case 9:
                    	return 1;
                        //scanner.close();
                        //System.exit(0);
                    default:
                        System.out.println("\u001b[31mLe nombre incorrect\u001b[0m");
                }
            } catch (InputMismatchException inputMismatchExceptioni) {
                System.out.println("\u001b[31mle entree est incorrect\u001b[0m");
            }

        }

    }
    static int gestionTacheAdmin(Utilisateur utilisateur) {
    	CrudTache managmenetTache = new CrudTache();
        AffecterTask at=new AffecterTask();
        Export_Import ei=new Export_Import();
        while (true) {
            System.out.println("\u001b[32m===========================================================================\u001b[0m");
            System.out.println("Choisis une option");
            System.out.println("1. Ajouter une tache");
            System.out.println("2. Modifier une tache");
            System.out.println("3. Afficher list des taches");
            System.out.println("4. Supprimer liste des taches");
            System.out.println("5. Filter les taches par categorie");
            System.out.println("6. Trier les Taches par priorite ou date creation");
            System.out.println("7. Affecter une tache");
            System.out.println("8. Exporter vos taches");
            System.out.println("9. Importer list des taches");
            System.out.println("10. Afficher Historique des operations des utilisateurs");
            System.out.println("11. Deconnection");
            System.out.print("Entrez le numéro de votre choix: ");
            try {
                Scanner scanner = new Scanner(System.in);
                int inputChoix = scanner.nextInt();
                switch (inputChoix) {
                    case 1:
                        if (managmenetTache.ajouter(utilisateur) == 1){
                            System.out.println("ajout success");
                        }else
                            System.out.println("ajout refusee");
                        break;
                    case 2:
                        managmenetTache.modifier(utilisateur);
                        break;
                    case 3:
                        managmenetTache.afficher(utilisateur);
                        break;
                    case 4:
                        managmenetTache.supprimer(utilisateur);
                        break;
                    case 5:
                    	managmenetTache.filtrerCategorie(utilisateur);
                    	break;
					case 6:
                    	managmenetTache.tri(utilisateur);
                    	break;
                    case 7:  
                        if (at.affecterTache() == 1){
                            System.out.println("Tache  Affecter");
                        }else
                            System.out.println("Tache pas affecter");
                        break;
                    case 8:
                        if (ei.export_Tache(utilisateur.getId())){
                            System.out.println("Export reussi");
                        }else
                            System.out.println("Echec Export");
                        break;
					case 9:
					    if (ei.import_tache(inputChoix)){
					        System.out.println("Import reussi");
					    }else
					        System.out.println("Echec Import");
					    break;
					case 10:
                        managmenetTache.afficherHistorique();
                        break;
                    case 11:
                        //scanner.close();
                        //System.exit(0);
                    	return 1;
                    default:
                        System.out.println("\u001b[31mLe nombre incorrect\u001b[0m");
                }
            } catch (InputMismatchException inputMismatchExceptioni) {
                System.out.println("\u001b[31mle entree est incorrect\u001b[0m");
            }

        }
    }
    static int gestionUserAdmin(Utilisateur utilisateur) {
    	ManagmenetUtilisateur manageUser = new ManagmenetUtilisateur();
        
        while (true) {
            System.out.println("\u001b[32m===========================================================================\u001b[0m");
            System.out.println("Choisis une option");
            System.out.println("1. Ajouter un Utilisateur");
            System.out.println("2. Modifier un Utilisateur");
            System.out.println("3. Afficher un Utilisateur");
            System.out.println("4. Supprimer un Utilisateur");
            System.out.println("5. Deconnection");
            System.out.print("Entrez le numéro de votre choix: ");
            try {
                Scanner scanner = new Scanner(System.in);
                int inputChoix = scanner.nextInt();
                switch (inputChoix) {
                    case 1:
                        if (manageUser.ajouter()){
                            System.out.println("ajout success");
                        }else
                            System.out.println("ajout refusee");
                        break;
                    case 2:
                    	if (manageUser.modifier()){
                            System.out.println("Modification reussi");
                        }else
                            System.out.println("Modification refusee");
                        break;
                    case 3:
                    	System.out.println(manageUser.afficher().toString());
                    	
                        break;
                    case 4:
                    	if (manageUser.supprimer()){
                            System.out.println("Suppression reussi");
                        }else
                            System.out.println("Suppression refusee");
                        break;
                    case 5:
                        //scanner.close();
                        //System.exit(0);
                    	return 1;
                    default:
                        System.out.println("\u001b[31mLe nombre incorrect\u001b[0m");
                }
            } catch (InputMismatchException inputMismatchExceptioni) {
                System.out.println("\u001b[31mle entree est incorrect\u001b[0m");
            }

        }

    }
}

