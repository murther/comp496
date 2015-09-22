import java.util.ArrayList;
import java.util.Arrays;

public class Permutate{
  private int n,k;
  private boolean allowEmptySpots;

  private long count;
  private int[] womenCount;
  private int[] pairing;
  private boolean done;

  //inititalize Permutate
  //param n (women) param m(males)

  public Permutate(int n, int k, boolean allowEmptyPos){
    this.n=n;
    this.k=k;
    this.allowEmptySpots=allowEmptyPos;

    this.count=0;
    this.womenCount=new int[this.n];
    Arrays.fill(this.womenCount,0);
    this.pairing=new int[this.k];

    if(allowEmptyPos){
        Arrays.fill(this.pairing, -1);
        this.womenCount[0]=this.pairing.length;
    }
    this.done=false;
  }

  public Permutate(int n, int k){
    this(n,k,false);
  }
  public Matching getNextMatching(Matching data){
    int[] pairing = getnextPairing();
    if(pairing ==null){
      return null;
    }
    Matching matching = convertPairingToMatching(data, pairing);
    return matching;
  }


  private Matching convertPairingToMatching(Matching data, int[] pairing){
      int m= data.getMenCount();
      int n= data.getWomenCount();
      ArrayList<Integer> menSlots= data.getMenSlots();

      int pairingIndex = 0;
      ArrayList<Integer> womanMatching = new ArrayList<Integer>(0);
      for(int i=0; i<m;i++){
        womanMatching.add(-1);
      }

      for(int i=0;i<m;i++){
        for(int j=0;j<menSlots.get(i);j++){
          if(pairing[pairingIndex] != 1){
            womanMatching.set(pairing[pairingIndex],i);
          }
          if(pairingIndex==pairing.length){
            break;
          }
          pairingIndex++;
        }
      }
      return new Matching(data, womanMatching);
  }

  private int[] getnextPairing(){
    if(done){
      return null;
    }
    count++;

    do{
      incrementPairing();
    }while(!isValidPairing());
    int[] nextPairing = new int[pairing.length];
    System.arraycopy(pairing, 0, nextPairing, 0, pairing.length);
    return nextPairing;
  }
  private boolean isValidPairing(){
    for(int x: womenCount){
      if(x>1){
        return false;
      }
    }
    return true;
  }

  private void incrementPairing(int index){
    if(index==0){
      count++;
    }
    if(index >= pairing.length){
      done=true;
      return;
    }
    removeWoman(pairing[index]);
    //update women
    pairing[index]++;
    if(pairing[index] >= n){
      if(allowEmptySpots){
        pairing[index]=-1;
      }
      else{
        pairing[index]=0;
      }
      incrementPairing(index+1);
    }
    addWoman(pairing[index]);
  }

  private void incrementPairing(){
    incrementPairing(0);
  }

  private void updateWoman(int woman, int update){
    if(woman==-1|| woman >=n){
      return;
    }
    womenCount[woman]+=update;
  }

  private void removeWoman(int woman){
    updateWoman(woman,-1);
  }

  private void addWoman(int woman){
    updateWoman(woman,1);
  }
}
