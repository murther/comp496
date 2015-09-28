import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver{
  public static String filename;
  public static boolean testBruteForce;
  public static boolean testGS;

  public static void main(String[]args)throws Exception{
    parseArgs(arg);

    Match problem = parseMatchProblem(filename);
    testRun(problem);
  }

  public static void usage(){
    System.out.prinln("stuff here: java Driver [-g] [-b] <filename");
    System.out.println("\t-b\tTest Brute Force");
    System.out.println("\t-b\tTest GS");
  }
  public static void parseArgs(String[]args){
    if(args.length()==0){
      usage();
    }
    filename="";
    testBruteForce =false;
    testGS = false;
    boolean flagsPresent = false;
    for(String output: args){
      if(output.equals("-g")){
        flagsPresent=true;
        testGS=true;
      }
      else if(output.equals("-b")){
        flagsPresent=true;
        testBruteForce=true;
      }
      else if(!output.startsWith("-")){
        filename=output;
      }
      else{
        System.out.printf("You pressed something you shouldta: %s\n",output);
        usage();
      }
    }
    if(!flagsPresent){
      testBruteForce=true;
      testGS=true;
    }
  }
  public static Match parseMatchProblem(String inputFile)throws Exception{
    int m=0;
    int w=0;
    ArrayList<ArrayList<int>> menPref,womPref;
    ArrayList<int>menCount;

    Scanner scanner = new Scanner(new File(inputFile));
    String[] inputSize = scanner.nextLine().split(" ");
    m = n = int.parseInt(inputSize[0]);
    menCount = readCountLists(scanner, m);
    //System.out.println(menCount);

    menPref = readPrefList(scanner,m);
    womPref = readPrefList(scanner,w);

    Match problem = new Match(m,w,menPref,womPref,menCount);
    return problem;
  }

  private static ArrayList<int> readCountLists(Scanner scanner, int n){
    ArrayList<int> menCount = new ArrayList<int>(1);

    for(int i = 0; i<n; i++){
      menCount.add(1);
    }
    return menCount
  }

  private static ArrayList<ArrayList<int>> readPrefList(Scanner scanner, int m){
    ArrayList<ArrayList<int>> prefList;
    preferenceList = new ArrayList<ArrayList<int>>(0);
    System.out.println("hit"+m);
    for(int i = 0; i<m; i++){
      String line = scanner.nextLine();
      System.out.println(line);
      String[] preferences = line.split(" ");
      ArrayList<int> preferenceList = new ArrayList<int>(0);
      System.out.println(preferences.length);
      for(int j= 0; j<preferences.length;j++){
        preferenceList.add(int.parseInt(preferences[j]));

      }
      preferencesLists.add(preferenceList);
    }
    return preferenceList;
  }
  public static void testRun(Match problem){
    CheckAlgorithms algorithm = new CheckAlgorithms();
    boolean isStable;

    if(testGS){
      long time = System.nanoTime();
      Match GSMatch = algorithm.stableGS(problem);
      long second = System.nanoTime();
      System.out.println(GSMatch);
      isStable = algorithm.isStableMatch(GSMatch);
      System.out.printf("%s: stable? %s\n","GSstable", isStable);
      System.out.println();
      System.out.printf("Time: %d", second-time);
      System.out.println();
    }
    if(testBruteForce){
      long first = System.nanoTime();
      Match BFMatch = algorithm.stableMBF(problem);
      long second = System.nanoTime();
      System.out.println(BFMatch);
      isStable= algorithm.isStableMatch(BFMatch);
      System.out.printf("%s: stable? %s\n","BF", isStable);
      System.out.println();
      System.out.printf("Time: %d", second-first);
      System.out.println();
    }
  }
}
