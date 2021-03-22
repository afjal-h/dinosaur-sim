import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        printIntro();// prints the intro details of the game
        setDinosaurs(); // create dinosaurs

    }

    public static Dinosaur createDinosaurs(String Species, int Annoyance, // method initialisation for ADT
                                           int Hunger) {
        Dinosaur dinosaur = new Dinosaur();
        dinosaur = setSpecies(dinosaur, Species); //sets values to attribute variables from parameter data
        dinosaur = setAnnoyance(dinosaur, Annoyance);
        dinosaur = setHunger(dinosaur, Hunger);
        dinosaur = setDangerLvl(dinosaur, 0);
        return dinosaur; //returns record with updated data
    }

    public static Dinosaur setSpecies(Dinosaur dinosaur, String input) {
        dinosaur.Species = input; //sets the value of species to the parameter input
        return dinosaur;
    }

    public static Dinosaur setAnnoyance(Dinosaur dinosaur, int input) {
        dinosaur.Annoyance = input; ////sets the value of species to the parameter input
        return dinosaur;
    }

    public static Dinosaur setHunger(Dinosaur dinosaur, int input) {
        dinosaur.Hunger = input; ////sets the value of species to the parameter input
        return dinosaur;
    }

    public static Dinosaur setDangerLvl(Dinosaur dinosaur, int input) {
        dinosaur.Dangerlvl = input; ////sets the value of species to the parameter input
        return dinosaur;
    }

    public static String getSpecies(Dinosaur dinosaur) {
        return dinosaur.Species; //returns species value
    }

    public static int getAnnoyance(Dinosaur dinosaur) {
        return dinosaur.Annoyance; //returns Annoyance value
    }

    public static int getHunger(Dinosaur dinosaur) {
        return dinosaur.Hunger; //returns hunger value
    }

    public static int getDangerLvl(Dinosaur dinosaur) {
        return dinosaur.Dangerlvl; //returns dinosaur value
    }

    public static void setDinosaurs() throws IOException { // method for creating dinosaurs and storing them
        System.out.println("Do you want to load a save file?");
        String choice = inputString();
        if (choice.equals("y")){
            Dinosaur[] DinoArray= loadSave();
            Task(DinoArray, DinoArray.length);

        }
        System.out.println("How many dinosaurs do you want to take care of?");
        final int DinoTotal = inputInt(); // final variable. Constant value for amount of dinosaurs.
        Dinosaur[] DinoArray = createDinoArray(DinoTotal); // array for dinosaur storage
        for (int i = 0; i <= (DinoTotal - 1); i++) { // looped based on how many dinosaurs the user wants
            String species = inputSpecies();
            DinoArray[i] = createDinosaurs(species, 6, 6); // stores data in array position i to add one dinosaur.
        }
        Task(DinoArray, DinoTotal); // calling method for the game to begin
    }

    public static void printIntro() { // prints game name and instructions.
        System.out.println("Welcome to Jurassic World!");
        System.out.println("****Create Dinosaur****");
    }

    public static Dinosaur[] createDinoArray(int amount) {
        Dinosaur[] DinoArray = new Dinosaur[amount]; // array for dinosaur storage
        return DinoArray;
    }

    public static int[] createIntArray(int length) {
        int[] Array = new int[length]; // array for dinosaur storage
        return Array;
    }

    public static String inputString() { // regular method for string input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input; // returns local variable containing input to the variable it was called in
    }

    public static int inputInt() { // regular method for integer inputs
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());
        return input; //returns input to variable it was called in
    }

    public static String inputSpecies() { // Asks user what species the dinosaur is.
        System.out.println("What species is your dinosaur?:");
        String species = inputString();
        System.out.println(species + " are brutal");
        return species; // returns input to variable called in.
    }

    public static void Task(Dinosaur[] dinosaurArray, int dinoTotal) {
        for (int i = 1; i <= 20; i++) { // defined set of rounds
            int dinoselection = RandomIntGenerator(dinoTotal - 1); //random dinosaur selection for round
            dinosaurArray[dinoselection] = randomCalmHunger(dinosaurArray[dinoselection]); // randomly changes the value for hunger and annoyance
            dinosaurArray[dinoselection].Dangerlvl = dinosaurArray[dinoselection].Hunger + dinosaurArray[dinoselection].Annoyance;// calculation for danger level
            DinoStatus(dinosaurArray[dinoselection], i);// method call for dino status
            Options(dinosaurArray[dinoselection], i); // prints options to care for the dinosaur
            int choiceSelect = inputInt();
            dinosaurArray[dinoselection] = Choiceprocessing(choiceSelect, dinosaurArray[dinoselection], dinosaurArray); // processes input
        }
    }

    private static Dinosaur randomCalmHunger(Dinosaur dinosaur) { // randomly chooses if either annoyance or hunger should be adjusted
        Random random = new Random();
        boolean add = AddOrSubtract();// method call to decide whether to add or subtract value for annoyance.
        boolean add2 = AddOrSubtract(); // decides whether to add or subtract value for hunger
        int randomCalm = random.nextInt(3);
        int randomHunger = random.nextInt(3); //random int from 1 to 3
        if (add) {
            dinosaur.Annoyance += randomCalm; // adds value if add== true
        } else {
            dinosaur.Annoyance -= randomCalm;// else subtract random value from calmness
        }
        if (add2) {
            dinosaur.Hunger += randomHunger;
        } else {
            dinosaur.Hunger -= randomHunger;
        }
        dinosaur.Hunger = inRange((dinosaur.Hunger)); // checks and adjusts the value of the record so it's not negative
        dinosaur.Annoyance = inRange((dinosaur.Annoyance));
        return dinosaur; //updates the variable it was called in.
    }

    public static void DinoStatus(Dinosaur dinosaur, int rounds) { // if statement to print status of dinosaur based on danger level.
        if (dinosaur.Dangerlvl >= 20) {
            System.out.println("The " + dinosaur.Species + " is thirsty for blood... RUN!"); // prints if danger level is greater or equal to 20
            Gameover(rounds); // exits game
        } else if (dinosaur.Dangerlvl >= 15 & dinosaur.Dangerlvl < 20) {
            System.out.println("The " + dinosaur.Species + " is looking dangerous");
        } else if (dinosaur.Dangerlvl >= 10 & dinosaur.Dangerlvl < 15) {
            System.out.println("The " + dinosaur.Species + " is looking rebellious");
        } else if (dinosaur.Dangerlvl >= 5 & dinosaur.Dangerlvl < 10) {
            System.out.println("The " + dinosaur.Species + " is aroused");
        } else if (dinosaur.Dangerlvl >= 0 & dinosaur.Dangerlvl < 5) {
            System.out.println("The " + dinosaur.Species + " is calm");
        }

    }

    public static void Gameover(int rounds) { // prints game over and quits game
        System.out.println();
        System.out.println("***GAME OVER***");
        System.out.println("You survived " + rounds + " rounds!");
        System.exit(1); //exits program
    }

    public static int RandomIntGenerator(int bound) { //generates random integer in range of inputted parameter
        Random random = new Random();
        int randomNum = random.nextInt(bound); // random integer within range of inputted parameter
        return randomNum; // returns to variable
    }

    public static int inRange(int number) {
        if (number > 10) { // if number is greater than 10 then subtract 10 to put it back in range
            number = number - (number - 10);

        } else if (number < 0) { // checks to see if the number is negative, then add 10.
            number = number + (number + 10);

        }
        if (number == 0) { //hunger and annoyance can't be 0.
            number += 1; // adds 1 to counteract.
        }
        return number;// returns updated value
    }

    public static void Options(Dinosaur dinosaur, int round) { // prints dino status values and options the user can do.
        System.out.println();
        System.out.println("**Round " + round + "**");
        System.out.println("Hunger: " + dinosaur.Hunger);
        System.out.println("Annoyance: " + dinosaur.Annoyance);
        System.out.println("What action do you want to perform? " +
                "1)Feed " +
                "2)Sooth " +
                "3)Check Danger Level" +
                "4) END and SAVE");
    }

    public static boolean AddOrSubtract() {
        Random random = new Random();
        boolean add;
        int randomInt = random.nextInt(2); // random between 1 and 2 to decide whether to add or subtract
        if (randomInt == 1) {
            add = true; // the value will be added and not subtracted
        } else {
            add = false; // the value will be subtracted and not added.
        }
        return add;
    }

    public static Dinosaur Choiceprocessing(int choiceSelect, Dinosaur dinosaur, Dinosaur[] dinosaurArray) {
        if (choiceSelect == 1) {
            reduceHunger(dinosaur); // method call for ADT operation
        } else if (choiceSelect == 2) {
            reduceAnnoyance(dinosaur);
        } else if (choiceSelect == 3) {
            printDangerLvl(dinosaurArray);
        } else if (choiceSelect == 4) {
            save(dinosaurArray);
        }
        return dinosaur;
    }

    public static Dinosaur reduceHunger(Dinosaur dinosaur) {
        int randomNumHunger = RandomIntGenerator(5);// generates random number to adjust the attributes of dinosaur
        int randomNumCalm = RandomIntGenerator(10);
        int Hunger = getHunger(dinosaur); //getter method call
        int Annoyance = getAnnoyance(dinosaur);
        dinosaur = setHunger(dinosaur, (Hunger -= randomNumHunger)); // reduces hunger by random int
        dinosaur = setAnnoyance(dinosaur, (Annoyance += randomNumCalm));// increases annoyance by random int

        return dinosaur;
    }

    public static Dinosaur reduceAnnoyance(Dinosaur dinosaur) {
        int randomNumHunger = RandomIntGenerator(10);// generates random number to adjust the attributes of dinosaur
        int randomNumCalm = RandomIntGenerator(5);
        int Hunger = getHunger(dinosaur); //getter method call
        int Annoyance = getAnnoyance(dinosaur);
        dinosaur = setHunger(dinosaur, (Hunger += randomNumHunger)); // reduces hunger by random int
        dinosaur = setAnnoyance(dinosaur, (Annoyance -= randomNumCalm));// increases annoyance by random int

        return dinosaur;
    }

    public static void printDangerLvl(Dinosaur[] dinosaurs) {
        int[] Dangerlvl = createIntArray(dinosaurs.length);
        for (int i = 0; i < dinosaurs.length; i++) {
            Dangerlvl[i] = dinosaurs[i].Dangerlvl;
        }
        dinosaurs = bubbleSort(Dangerlvl, dinosaurs);
        for (int i = 0; i < dinosaurs.length; i++) {
            System.out.println(dinosaurs[i].Species);
            System.out.println(dinosaurs[i].Dangerlvl);

        }

    }

    public static Dinosaur[] bubbleSort(int[] Dangerlvl, Dinosaur[] Array) //sorts array
    {
        int n = Dangerlvl.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (Dangerlvl[j] < Dangerlvl[j + 1]) // sorts danger level
                {
                    // swapping values of position j and j+1
                    Dinosaur tempvalue = Array[j]; //swaps the record the danger level is in
                    Array[j] = Array[j + 1];
                    Array[j + 1] = tempvalue;
                }
        return Array;
    }

    public static void save(Dinosaur[] dinosaurs) {
        storeDinosaur(dinosaurs);
        System.out.println("SAVED!");
        System.exit(1);
    }


    public static void storeDinosaur(Dinosaur[] dinosaurs) {
        try {
            FileWriter writer = new FileWriter("Savefile.txt");
            Writer output = new BufferedWriter(writer);
            output.write(dinosaurs.length + "\n");
            for (int i = 0; i < dinosaurs.length; i++) {
                output.write(dinosaurs[i].Species + "\n" + dinosaurs[i].Annoyance + "\n" + dinosaurs[i].Hunger + "\n" + dinosaurs[i].Dangerlvl + "\n");
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save failed!");
            System.exit(1);
        }
    }

    public static Dinosaur[] loadSave() throws IOException {
        File file= new File ("Savefile.txt");
        Scanner scanner= new Scanner(file);
        int Arraylength= (Integer.parseInt(scanner.nextLine())*4);
        String[] data= new String[Arraylength];
        int position=0;
        while (scanner.hasNextLine()){
            data[position]= scanner.nextLine();
            position+=1;
        }
        for (int i =0; i< data.length;i++){
            System.out.println(data[i]);
        }

        Dinosaur[] dinosaurs= new Dinosaur[Arraylength/4];
        int arrayTransferStepper=0;
        for (int i =0; i< (Arraylength/4);i++){
            String Species= data[arrayTransferStepper];
            int Annoyance= Integer.parseInt(data[arrayTransferStepper+1]);
            int Hunger= Integer.parseInt(data[arrayTransferStepper+2]);
            int DangerLvl = Integer.parseInt(data[arrayTransferStepper+3]);
            dinosaurs[i]= createDinosaurs(Species,Annoyance,Hunger);
            arrayTransferStepper+=4;// cuts off the first 4 array elements after transfer complete
        }
        return dinosaurs;
    }

    static class Dinosaur {        // new data type called dinosaur with attributes
        String Species;
        int Annoyance;
        int Hunger;
        int Dangerlvl;
    }
}
