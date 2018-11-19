import java.util.*;

public class RollDice{
  private static RollDice instance;
  Dice d=Dice.getInstance();
  int[] r=new int[5];
  private String dstr="";
  private String vstr="";

  public RollDice(){}

  public static RollDice getInstance(){
    if(instance==null){return new RollDice();}
    return instance;
  }

  public void setRoll(int[] i){this.r=i;}
  public int[] getRoll(){return this.r;}

  public int[] newRoll(){
    for(int i=0;i<r.length;i++){
      d.setValue();
      r[i]=d.getValue();
    }
    this.setRoll(r);
    return r;
  }

  public void secondRoll(int i){
    this.getRoll();
    r[i]=this.newValue();
    this.setRoll(r);
  }

  public int newValue(){
    d.setValue();
    return d.getValue();
  }

  public void setString(int[] il){
    dstr="Dice:  ";
    vstr="Value: ";
    for(int i=0;i<il.length;i++){
      dstr=dstr+"["+(i+1)+"] ";
      vstr=vstr+" "+il[i]+"  ";
    }
  }

  public String getD(){return dstr;}
  public String getV(){return vstr;}

  public void printRoll(){
    this.setString(this.getRoll());
    System.out.println(this.getD());
    System.out.println(this.getV());
  }

}
