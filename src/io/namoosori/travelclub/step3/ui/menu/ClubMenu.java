package io.namoosori.travelclub.step3.ui.menu;

import io.namoosori.travelclub.step1.entity.TravelClub;
import io.namoosori.travelclub.step3.logic.ClubCoordinator;
import io.namoosori.travelclub.step3.ui.console.ClubConsole;
import io.namoosori.travelclub.util.util.Narrator;
import io.namoosori.travelclub.util.util.TalkingAt;

import java.util.Scanner;

public class ClubMenu {
    private ClubConsole clubConsole;
    private MemberMenu memberMenu;

    private TravelClub currentClub;

    private Scanner scanner;
    private Narrator narrator;

    public ClubMenu(){

        ClubCoordinator clubCoordinator = new ClubCoordinator();
        this.memberMenu = new MemberMenu(clubCoordinator);
        this.clubConsole = new ClubConsole(clubCoordinator);

        this.scanner = new Scanner(System.in);
        this.narrator = new Narrator(this, TalkingAt.Left);
    }

    public void show(){

        int inputNumber = 0;

        while (true){
            displayMenu();
            inputNumber = selectMenu();

            switch (inputNumber){
                case 1:
                    currentClub = clubConsole.register();
                    break;
                case 2:
                    currentClub = clubConsole.find();
                    break;
                case 3:
                    currentClub = clubConsole.modify();
                    break;
                case 4:
                    clubConsole.remove();
                    break;
                case 5:
                    memberMenu.show(currentClub);
                    break;
                case 0:
                    this.exitProgram();

                default:
                    narrator.sayln("Choose again!");
            }
        }
    }

    private int selectMenu() {

        System.out.print("Select: ");
        int menuNumber = scanner.nextInt();

        if(menuNumber >= 0 && menuNumber <= 5){
            scanner.nextLine();
            return menuNumber;
        } else {
            narrator.sayln("It's a invalid number --> " + menuNumber);
            return -1;
        }
    }

    private void exitProgram(){

        narrator.sayln("Program exit. Bye...");
        scanner.close();
        System.exit(0);
    }

    private void displayMenu(){

        narrator.sayln("");
        narrator.sayln("..............................");
        narrator.sayln(" Club menu ");
        narrator.sayln("..............................");
        narrator.sayln(" 1. Register");
        narrator.sayln(" 2. Find");
        narrator.sayln(" 3. Modify");
        narrator.sayln(" 4. Remove");
        narrator.sayln("..............................");
        narrator.sayln(" 5. Member menu");
        narrator.sayln("..............................");
        narrator.sayln(" 0. Exit program");
        narrator.sayln("..............................");
    }

}
