package IteratorLab;
import java.util.Iterator;

public class IteratorDriver {

  public static void main(String[] args) {
    String[] words = {"I", "love", "computer", "science"};

    BackScanner sc = new BackScanner(words);

    System.out.println("First use an enhanced for loop:\n");
    for (String current : sc) {
      System.out.println(current);
    }

    System.out.println("\nNow try the iterator object directly:\n");
    Iterator<String> it = sc.iterator();

    while (it.hasNext()) {
      String current = it.next();
      System.out.println(current);
    }


  }

}
