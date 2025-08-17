/**
 * The GameManager class is responsible for managing the main game loop and controlling the flow of the game.
 * It initializes the game components, handles user input, and manages the state transitions of the game.
 * The game involves researchers performing experiments in a lake puzzle environment.
 * 
 * The main states of the game are:
 * - "ResearcherBeforeLake": Researchers select equipment before entering the lake.
 * - "Entrance": Researchers choose equipment or proceed to the lake.
 * - "InLake": Researchers perform actions within the lake, such as moving and using equipment.
 * - "Finished": The game ends.
 * 
 * The GameManager class also handles the initialization of the lake puzzle, equipment bag, researcher queue,
 * and the generation of random experiments. It manages the researchers' actions, equipment selection, and
 * experiment completion.
 * 
 * The class contains several private helper methods to manage the game state, check equipment availability,
 * and handle researcher actions.
 * 
 * The GameMenu and ResearcherQueue inner classes provide additional functionality for displaying game menus
 * and managing the queue of researchers, respectively.
 */
package utility;

import Components.General.MapSquare;
import Components.Interfaces.Equipment;
import Components.ResearchEquipmentItems.*;
import exceptions.*;

import java.util.*;

import inputHandler.InputHandler;
import Components.General.EquipmentBag;
import Components.General.LakePuzzle;
import Components.General.Researcher;
import Components.HazardEquipments.ClimbingEquipment;
import Components.HazardEquipments.LargeWoodenBoard;
import Components.HazardEquipments.ProtectiveHelmet;

public class GameManager {

    private LakePuzzle lakePuzzle;
    private ResearcherQueue researcherQueue;
    private EquipmentBag<Equipment> equipmentBag;
    private InputHandler inputHandler;
    private Set<ResearchEquipmentItem> goals;
    private Set<ResearchEquipmentItem> finishedGoals;
    private Researcher currentResearcher;
    private boolean isGameContinue = true;
    private String state = "ResearcherBeforeLake";
    private GameMenu gameMenu;
    private boolean bypassTheProcessList = true;

    public GameManager() {
        this.inputHandler = new InputHandler();
        this.gameMenu = new GameMenu();
        initilazeGame();
        Play();
    }


    public void Play() {
        while (isGameContinue) {
            if (isFinished()) {
                break;  // Exit the game loop if the game is finished
            }
            switch (state) {
                case "ResearcherBeforeLake": {
                    isThereEnoughItemForGoals();
                    this.gameMenu.equipmentList();
                    state = "Entrance";
                    break;

                }
                case "Entrance": {
                    String str = inputHandler.getEquipmentCode();
                    if (str.equals("no")) {
                        if (currentResearcher.getEquipmentSet().isEmpty()) {
                            try {
                                currentResearcher.isBagEmpty();
                            } catch (IncorrectBagContentsException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                        } else {
                            state = "InLake";
                            gameMenu.resarcherBagText(1);
                        }
                    } else if (this.equipmentBag.isContain(str)) {
                        Equipment newEquipment = this.equipmentBag.getEquipment(str);
                        int status = addEquipmentToReseacher(newEquipment, currentResearcher);
                        gameMenu.resarcherBagText(0);
                        if (status == 200) {
                            equipmentBag.removeEquipment(newEquipment);
                        }
                    } else {
                        System.out.println("*** There no more " + str + " left in the Equipment Storage.");
                    }
                    break;
                }
                case "InLake": {

                    String choice;
                    MapSquare square = lakePuzzle.findResearcherSquare(currentResearcher);
                    int[] position = square.getPosition();
                    if(bypassTheProcessList == false){

                        if (position[0] == 6 && position[1] == 1) {
                            gameMenu.processList(0);
                        } else {
                            gameMenu.processList(1);
                        }
                        choice = inputHandler.processAnswer(currentResearcher.getId());

                    }else {
                        choice = "1";
                        bypassTheProcessList = false;
                    }




                    switch (choice) {
                        case "1": {
                            System.out.println();
                            while (true) {
                                String direction = inputHandler.getDirectionInput();
                                try {
                                    currentResearcher.move(direction, lakePuzzle);
                                    isFinished();
                                    lakePuzzle.printMap();
                                    break;
                                } catch (UnavailableDirectionException e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            }
                            break;
                        }
                        case "2": {
                            try {
                                Equipment currentEquipment = null;
                                if (!currentResearcher.isHaveReseachItem()) {
                                    throw new UnavailableEquipmentException("Researcher has no Experiment Equipment");
                                }

                                while (true) {
                                    String code = inputHandler.getEquipmentCode();
                                    try {
                                        currentEquipment = currentResearcher.getItemWithCode(code, lakePuzzle);
                                        ((ResearchEquipmentItem) currentEquipment).finishTask();
                                        ((ResearchEquipmentItem) currentEquipment).use(lakePuzzle, currentResearcher);
                                        accomplishResearch((ResearchEquipmentItem) currentEquipment);
                                        break;
                                    } catch (UnavailableEquipmentException e) {
                                        System.out.println(e.getMessage());
                                        continue;
                                    }
                                }

                            } catch (UnavailableEquipmentException e) {
                                System.out.println(e.getMessage());
                                continue;
                            } catch (IncompatibleResearchEquipmentLocationException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                            break;
                        }
                        case "3": {
                            if (position[0] == 6 && position[1] == 1) {
                                researcherToTheEntrance(currentResearcher);
                            } else {
                                this.nextReseacher();
                            }
                            break;
                        }
                    }
                    break;
                }
                case "Finished": {
                    isGameContinue = false;
                    break;
                }
            }
        }
    }

    private void initilazeGame(){
        this.lakePuzzle = new LakePuzzle();
        goals = new HashSet<ResearchEquipmentItem>();
        finishedGoals = new HashSet<ResearchEquipmentItem>();
        this.researcherQueue = new ResearcherQueue();
        Random random = new Random();
        int[] options = {2, 3, 4};
        int randomChoice = options[random.nextInt(options.length)];

        for (int i = 1; i <= randomChoice; i++) {
            Researcher researcher = new Researcher(i);
            researcherQueue.addResearcher(researcher);
        }
        this.equipmentBag = new EquipmentBag<>();
        equipmentBag.addEquipment(new TemperatureDetectorItem());
        equipmentBag.addEquipment(new TemperatureDetectorItem());
        equipmentBag.addEquipment(new ChiselingEquipmentItem());
        equipmentBag.addEquipment(new ChiselingEquipmentItem());
        equipmentBag.addEquipment(new ClimbingEquipment());
        equipmentBag.addEquipment(new ClimbingEquipment());
        equipmentBag.addEquipment(new ProtectiveHelmet());
        equipmentBag.addEquipment(new ProtectiveHelmet());
        equipmentBag.addEquipment(new LargeWoodenBoard());
        equipmentBag.addEquipment(new LargeWoodenBoard());
        equipmentBag.addEquipment(new CameraItem());
        equipmentBag.addEquipment(new CameraItem());
        equipmentBag.addEquipment(new WindSpeedMeasurement());
        equipmentBag.addEquipment(new WindSpeedMeasurement());

        gameMenu.enterOfGame();

        currentResearcher = researcherQueue.pop();

        lakePuzzle.addResearcherToMap(currentResearcher);

        lakePuzzle.printMap();
    }

    private void generateRandomExperiment( int count ) {
        int number = count;
        ArrayList<ResearchEquipmentItem> a = new ArrayList<>(4);
        a.add(new CameraItem());
        a.add(new ChiselingEquipmentItem());
        a.add(new TemperatureDetectorItem());
        a.add(new WindSpeedMeasurement());

        Random random = new Random();

        for (int i = 0; i < number && !a.isEmpty(); i++) {
            int randomIndex = random.nextInt(a.size());
            ResearchEquipmentItem selectedItem = a.remove(randomIndex);
            goals.add(selectedItem);
            System.out.println(selectedItem.textOfGoal());


        }

    }

    private int randomExperimentCount( ){
        int researcherCount = researcherQueue.size();
        Random random = new Random();
        int choice = random.nextInt(2);
        int selectedValue = (choice == 0) ? researcherCount : (researcherCount - 1);
        return selectedValue;
    }


    private void researcherToTheEntrance(Researcher researcher){

        Set<Equipment> equipments = researcher.getEquipmentSet();
        for(Equipment currentEquipment : equipments){
            this.equipmentBag.addEquipment(currentEquipment);
        }
        this.lakePuzzle.findResearcherSquare(researcher).removeResearcher();
        nextReseacher();
    }

    private void nextReseacher(){ 
        if(researcherQueue.isEmpty()){
            gameMenu.FinishedGame(3);
        }else{
            currentResearcher = researcherQueue.pop();
            lakePuzzle.addResearcherToMap(currentResearcher);
            if (!isThereEnoughItemForGoals()){
                gameMenu.FinishedGame(2);
                state = "Finished";
            }else {
                state = "ResearcherBeforeLake";
                bypassTheProcessList = true;
            }

            lakePuzzle.printMap();
        }
    }


    private boolean isThereEnoughItemForGoals() {
        for (ResearchEquipmentItem goal : goals) {
            boolean isFound = false;
            // Her ekipmanı bir kez kontrol et
            for (Equipment equipment : equipmentBag.getEquipmentSet()) {
                if (equipment instanceof ResearchEquipmentItem &&
                        equipment.getClass() == goal.getClass()) {
                    isFound = true;
                    break;  // Eşleşme bulundu, iç döngüden çık
                }
            }
            // Eğer bu hedef için ekipman bulunamadıysa
            if (!isFound) {
                return false;
            }
        }
        return true;
    }

    private void accomplishResearch(ResearchEquipmentItem item){
        for(ResearchEquipmentItem currentItem: goals){
            if(item.toString().equals(currentItem.toString()) && item.isAccomplish()){
                finishedGoals.add(item);
                goals.remove(currentItem);
                break;
            }
        }
    }

    private int addEquipmentToReseacher(Equipment equipmentItem , Researcher rs){
        try{
            rs.addEquipment(equipmentItem);
            return 200;
        }catch (IncorrectBagContentsException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public boolean isFinished() {
        if (!currentResearcher.researcherAlive()) {
            gameMenu.FinishedGame(0);
            state = "Finished";  // Add this line
            return true;
        } else if (goals.isEmpty()) {
            gameMenu.FinishedGame(1);
            state = "Finished";  // Add this line
            return true;
        }
        return false;
    }

    private class GameMenu{


        private void enterOfGame(){
            int count = randomExperimentCount();
            System.out.println();
            System.out.println("Welcome to Frozen Lake Puzzle App. There are "+ researcherQueue.size() +" researchers waiting at the lake entrance.");
            System.out.println("There are "+ count +" experiment(s) that must be completed: " );
            System.out.println();
            generateRandomExperiment(count);
            System.out.println();
            System.out.println("The initial map of the frozen lake: ");
        }



        private void equipmentList() {
            System.out.println("=====> Researcher " + currentResearcher.getId() +
                    " starts waiting at the entrance and can select up to 3 pieces of equipment of the same type. Here are the shorter notations of the equipments:");

            ArrayList<Equipment> tempory = new ArrayList<>();
            for (Equipment e : equipmentBag.getEquipmentSet()) {
                boolean alreadyAdded = false;

                // Check if the equipment type already exists in the temporary list
                for (Equipment temp : tempory) {
                    if (temp.getClass() == e.getClass()) {
                        alreadyAdded = true;
                        break;
                    }
                }

                // Add only if not already present
                if (!alreadyAdded) {
                    tempory.add(e);
                }
            }

            // Print the filtered list
            for (Equipment e : tempory) {
                System.out.println("[ " + e.toString() + " ] " + e.nameOfEquipment());
            }
            System.out.println("[ no ] Stop taking equipment and head out to the lake ");
        }


        private void processList(int value) {
            System.out.println("=====> Researcher "+currentResearcher.getId() + " manages to stop safely.\n" +
                    "[1] Continue moving on the ice.\n" +
                    "[2] Choose experiment equipment and perform an experiment.");
            if (value ==1){
                System.out.println("[3] Sit on the ground and let the other researchers head out to the lake. ");}
            else{
                System.out.println("[3] Exit of the map. ");
            }
        }

        private void resarcherBagText(int count){
            String str ="";
            if (count == 0){
                str = ("- Contents of the bag of Researcher "+currentResearcher.getId() +": ");

            }else{
                str ="- The final contents of the bag of Researcher "+currentResearcher.getId() +": ";}

            for (Equipment e : currentResearcher.getEquipmentSet()){
                str+= e.toString() + " ";
            }
            System.out.println(str);
        }

        /**
         * Ends the game and prints the appropriate message based on the given value.
         *
         * @param value an integer representing the reason for the game ending:
         *              1 - Research goals have been accomplished.
         *              0 - The player was injured.
         *              2 - No equipment left to finish the game.
         *              3 - Given experiments didn't finish.
         */
        private void FinishedGame(int value) {
            isGameContinue = false;
            if(value == 1){
                System.out.println();
                System.out.println("-----------> Research goal(s) have been accomplished. Here are their results: ");
                System.out.println();
                for(ResearchEquipmentItem item: finishedGoals){
                    System.out.println(item.getMessage());
                }
                System.out.println();
                System.out.println("-----------> SUCCESSFUL");
                System.out.println();
            }else if(value == 0){
                System.out.println();
                System.out.println("----------->  The Player was injured and that's why the game is over!!!");
                System.out.println();
            }
            else if(value == 2) {
                System.out.println();
                System.out.println("----------->   There is no equipment left to finish the game, the game is over!!!");
                System.out.println();
            } else if (value ==3) {
                System.out.println();
                System.out.println("----------->   The given experiments didn't finished so, the game is over!!!");
                System.out.println();
            }
            state = "Finished";
        }


    }

    private class ResearcherQueue{

        private final LinkedList<Researcher> queue;
        private int currentIndex;

        public ResearcherQueue() {
            this.queue = new LinkedList<>();
            this.currentIndex = 0;
        }

        public void addResearcher(Researcher researcher) {
            queue.add(researcher);
        }
        public Researcher removeResearcher() {
            if (isEmpty()) {
                return null;
            }
            Researcher removed = queue.remove(currentIndex);
            if (currentIndex >= queue.size()) {
                currentIndex = 0;
            }
            return removed;
        }

        public Researcher getCurrentResearcher() {
            if (isEmpty()) {
                return null;
            }
            return queue.get(currentIndex);
        }

        public Researcher pop(){
            if (isEmpty()) {
                return null;
            }
            Researcher removed = queue.remove(currentIndex);
            if (currentIndex >= queue.size()) {
                currentIndex = 0;
            }
            return removed;
        }

        public void nextResearcher() {
            if (!isEmpty()) {
                currentIndex = (currentIndex + 1) % queue.size();
            }
        }
        public int size() {
            return queue.size();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

}


