import java.util.*;
public class Test {
    public static void main(String[] args) {
     Scanner s=new Scanner(System.in);
      int cases=s.nextInt();
      for (int i=0;i<cases;i++){
        String str=s.next();
        if (isPalindrome(str)){
          System.out.println(0);
          System.out.println();
        }else{
          String string="";
          int total=0;
          for (int j=0;j<str.length();j++){
            if (str.substring(j,j+1).equals("1")){
              string+=(j+1)+" ";
              total++;
            }
          }
          System.out.println(total);
          System.out.println(string);
        }
      }
  }
  public static boolean isPalindrome(String str){
    if(str.length()<2){
      return true;
    }

    for (int i=0;i<str.length();i++){
      if (!(str.substring(i,i+1).equals(str.substring(str.length()-i-1,str.length()-i)))){
        return false;
      }
    }
    return true;
  }
}
