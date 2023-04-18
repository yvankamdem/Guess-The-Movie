import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wrongLetters = new ArrayList<Character>();

        List<String> movieList = readMovieList();
        System.out.println("Liste des films :" + movieList);
        String movieTitle = getRandomMovieTitle();
        String hiddenTitle = movieTitle.replaceAll("[a-zA-Z]", "_");
        System.out.println(movieTitle);



        int maxWrongGuesses = 10;
        int wrongGuesses = 0;

        while (wrongGuesses < maxWrongGuesses && hiddenTitle.contains("_")) {
            System.out.println("Devinez une lettre :");
            char letter = scanner.next().charAt(0);

            if (movieTitle.indexOf(letter) == -1) {
                if (wrongLetters.contains(letter)) {
                    System.out.println("Vous avez déjà deviné cette lettre et vous vous êtes trompé.");
                } else {
                    System.out.println("Mauvaise supposition.");
                    wrongLetters.add(letter);
                    wrongGuesses++;
                }
            } else {
                for (int i = 0; i < movieTitle.length(); i++) {
                    if (movieTitle.charAt(i) == letter) {
                        hiddenTitle = hiddenTitle.substring(0, i) + letter + hiddenTitle.substring(i + 1);

                    }
                }
                System.out.println("La lettre " + letter + " est présente dans le titre du film." );
            }

            System.out.println("Titre caché : " + hiddenTitle);
            System.out.println("Mauvaises lettres: " + wrongLetters);
            System.out.println("il vous reste: " + (10 - wrongGuesses));
        }

        if (wrongGuesses == maxWrongGuesses) {
            System.out.println("Vous avez perdu. Le titre du film était: " + movieTitle);
        } else {
            System.out.println("Vous avez gagné ! Le titre du film était : " + movieTitle);
        }
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
}