import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GuessTheMovies {

    public static void main(String []args) throws Exception {
        //creation de l'objet file
        File file = new File("movies.txt");
        //lire son contenu
        Scanner scanner = new Scanner(file);
        StringBuilder hiddenTitle = new StringBuilder(); // titre caché
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
        }

        GuessTheMovies MovieListReader = null;
        List<String> movieList = readMovieList();
        System.out.println(movieList);
        String movieTitle = getRandomMovieTitle();
        System.out.println(movieTitle);
        for (int i = 0; i < movieTitle.length(); i++) {
            if (movieTitle.charAt(i) == ' ') { // si le caractère est un espace
                hiddenTitle.append(" "); // ajouter un espace au titre caché
            } else {
                hiddenTitle.append("_"); // sinon, ajouter un trait de soulignement
            }
        }

        Scanner scanner1 = new Scanner(System.in);
        String hiddenTitle1 = movieTitle.replaceAll("[a-zA-Z]", "_"); // cacher le titre
        int numGuesses = 0;

        while (numGuesses < 10) {
            System.out.println("Guess The Movie!");
            System.out.println(hiddenTitle1);
            System.out.println("Enter a letter:");

            String letter = scanner1.nextLine(); // lire l'entrée de l'utilisateur
            char c = letter.charAt(0);
            List<String> wrongGuesses = new ArrayList<>(); // create a list to store wrong guesses


            if (movieTitle.contains(Character.toString(c))) {
                System.out.println("La lettre " + c + " est présente dans le titre du film." );
                wrongGuesses.add(letter);
                // mettre à jour l'affichage pour montrer la lettre correctement devinée
                // incrémenter le nombre de lettres correctement devinées
            } else {
                numGuesses++;
                System.out.println("La lettre " + c + " n'est pas présente dans le titre du film. Il te reste : "  + (10 - numGuesses) +  " essaies.");
                wrongGuesses.add(letter);
                // mettre à jour l'affichage pour montrer la lettre incorrectement devinée
                // ajouter la lettre à la liste des lettres incorrectement devinées
                // décrémenter le nombre de tentatives
            }

            for (int i = 0; i < movieTitle.length(); i++) {
                if (movieTitle.charAt(i) == letter.charAt(0)) {
                    hiddenTitle1 = hiddenTitle1.substring(0, i) + letter + hiddenTitle1.substring(i + 1);
                }
            }



            if (hiddenTitle1.equals(movieTitle)) {
                System.out.println("Congratulations, tu as gagné!");
                break;
            }
        }

        if (numGuesses == 10) {
            System.out.println("Game over. Le film était " + movieTitle);
        }

        scanner1.close();


    }


    public static List<String> readMovieList() throws FileNotFoundException {
        List<String> movieList = new ArrayList<>();
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String movieTitle = scanner.nextLine();
            movieList.add(movieTitle);
        }
        scanner.close();
        return movieList;
    }
    public static String getRandomMovieTitle() throws FileNotFoundException {
        List<String> movieList1 = new ArrayList<>();
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String movieTitle1 = scanner.nextLine();
            movieList1.add(movieTitle1);
        }
        scanner.close();
        Random random = new Random();
        int randomIndex = random.nextInt(movieList1.size());
        return movieList1.get(randomIndex);
    }

}
