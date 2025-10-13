package IteratorLab;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that allows iteration through an array of Strings while skipping to every N'th element.
 * 
 * For example, iterating through the array {"A", "B", "C", "D", "E"} with a skip value of 2, would
 * return "A", then "C" then "E". Iterating with a skip value of 3 would return "A", then "D".
 * 
 *
 */
public class SkipScanner implements Iterable<String> {

  private String[] elements;
  private int skip;

  /**
   * Construct a SkipScanner.
   * 
   * @param elements The array to iterate over
   * @param skip The scanner will visit every skip'th element
   */
  public SkipScanner(String[] elements, int skip) {
    this.elements = elements;
    this.skip = skip;
  }

  /**
   * @return An appropriate iterator object.
   */
  public Iterator<String> iterator() {
    return null;
  }

}
