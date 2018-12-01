import java.util.*;

public class Game{
  private Scorecard sc=Scorecard.getInstance();
  private RollDice rd=RollDice.getInstance();
  private ScoreFactory sf=new ScoreFactory();
  Scanner c=new Scanner(System.in);
  int x=0;

  public Game(){}

  public void setName(String s){sc.setName(s);}
  public String getName(){return sc.getName();}

  public void clearScreen() {
   System.out.print("\033[H\033[2J");
   System.out.flush();
  }

  public void firstRoll(){
    x=0;
    rd.newRoll();
    this.viewUI();
    x++;System.out.println("x="+x);
    this.rollAgain();
  }

  public void rollAgain(){
    if(x!=3){
      System.out.println("Do you want to roll again?");
      if(c.nextLine().equals("y")){
        x++;
        this.pickDice();
      }
      else{this.pickScore();}
    }
  }

  public void viewUI(){
    this.clearScreen();
    this.viewSC();
    this.viewDice();
  }

  public void pickDice(){
    System.out.println("Which dice do you want to reroll? (pick the dice number)");
    int num=c.nextInt();
    this.newRoll(num-1);
    c.nextLine();
    System.out.println("Do you want to change another dice?");
    if(c.nextLine().equals("y")){
      this.pickDice();
    }
    else{
      this.viewUI();
      if(x<3){this.rollAgain();}
      else{this.pickScore();}
    }
  }

  public void newRoll(int i){
    rd.secondRoll(i);
  }

  public void viewDice(){
    System.out.println("YOUR CURRENT DICE ROLL:");
    rd.printRoll();
  }

  public void viewSC(){
    System.out.println(sc.toString());
  }

  public void pickScore(){
    this.viewUI();
    System.out.println("Choose the row number that matches where you want to enter your score");
    System.out.println("***to enter a score of 0 choose a field that doesn't match your roll");
    System.out.println("Your choice: ");
    ScoreProcessor sp = sf.getScore(c.nextInt());
    c.nextLine();
    this.enterScore(sp);
  }

  public void enterScore(ScoreProcessor sp){
    sp.setScore(rd.getRoll(),sc);
    this.clearScreen();
    System.out.println(sc.toString());
    System.out.println("Next turn...");
    try{Thread.sleep(5000);}
    catch (InterruptedException e){e.printStackTrace();}
    this.viewUI();
    this.firstRoll();
  }
}
