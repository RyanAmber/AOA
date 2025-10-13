package IteratorLab;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class allows us to scan through the "n-grams" that are represented by an array of Strings.
 * An n-gram is a sequence of n words appearing in order. For example, the sentence:
 * 
 * {"I", "love", "computer", "science"}
 * 
 * Contains the 2-grams (also called bigrams): "I love", "love computer", and "computer science".
 * 
 * It contains the 3-grams (also called trigrams): "I love computer", and "love computer science".
 *
 */
public class NGramScanner implements Iterable<String> {
  private String[] elements;
  private int nValue;

  /**
   * Construct an NGramScanner.
   * 
   * @param elements The array of words
   * @param nValue The value of n to use in constructing n-grams.
   */
  public NGramScanner(String[] elements, int nValue) {
    this.elements = elements;
    this.nValue = nValue;
  }

  @Override
  public Iterator<String> iterator() {
    return null;
  }


}
