import java.util.ArrayList;
/* Matching may or may not be stable */

public class Match{
  //Number of men and women
  private int m;
  private int w;

  //list containing man's preference list
  private ArrayList<ArrayList<int>> menPref;
  //list containing women's preferene list
  private ArrayList<ArrayList<int>> womPref;
  //number of men
  private ArrayList<int> menCount;
  // matching info

  private ArrayList<int> manMatch;

  public Match(int m, int w, ArrayList<ArrayList<int>> menPref, ArrayList<ArrayList<int>> womPref, ArrayList<int> menCount){
    this.m=m;
    this.w=w;
    this.menPref = menPref;
    this.womPref = womPref;
    this.menCount = menCount;
    this.manMatch = null;

  }
  //recursive call
  public Match(int m, int w, ArrayList<ArrayList<int>> menPref, ArrayList<ArrayList<int>> womPref, ArrayList<int> menCount, ArrayList<int> manMatch){
    this.m=m;
    this.w=w;
    this.menPref = menPref;
    this.womPref = womPref;
    this.menCount = menCount;
    this.manMatch = manMatch;
  }
  //constructs solution to SMP given problem is matching
  public Match(Match data, ArrayList<int> manMatch){
    this(data.m, data.n, data.menPref,data.manMatch,data.menCount,manMatch);
  }
  public void setmanMatch(ArrayList<int> manMatch){
    this.manMatch = manMatch;
  }
  public int getMenCount(){
    return m;
  }
  public int getWomanCount(){
    return w;
  }
  public ArrayList<ArrayList<int>> getMenPref(){
    return menPref;
  }
  public ArrayList<ArrayList<int>> getWomanPref(){
    return womPref;
  }
  public ArrayList<int> getMenCount(){
    return menCount;
  }
  public ArrayList<int> getManMatch(){
    return manMatch;
  }
  public int totalMen(){
    int count=0;
    for(int i=0; i<m;i++){
      count += menCount.get(i);
    }
    return count;
  }
  public string getInputSizeString(){
    return String.format("men=%d women=%d \n",m,n);
  }
  public String getOutput(){
    if(manMatch == null){
      return "";
    }
    StringBuilder output= new StringBuilder();
    for(int i=0; i<manMatch.size();i++){
      String str= String.format("{(%d,%d)}",i,manMatch.get(i));
      output.append(str);
      if(i != manMatch.size()-1){
        output.append("\n")
      }
    }
    return output.toString();
  }
public String toString(){
  return getInputSizeString()+ getOutput();
}
  
}
