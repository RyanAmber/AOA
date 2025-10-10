public class Test {
    public static void main(String[] args) {
    // just instantiate one object and call the methods.
    // I'm sure you can do it better than this code
    LinkedList sll = new LinkedList();

    sll.insert(1);
    sll.insert(2);
    sll.insert(3);
    sll.insert(4);
    sll.insert(5);

    sll.print();
    sll.print(0);
    sll.print(1);
    sll.print(2);
    sll.print(3);
    sll.print(4);

    System.out.println(sll.extract());
    System.out.println(sll.extract());
    System.out.println(sll.extract());
    System.out.println(sll.extract());
    System.out.println(sll.extract());

    sll.print();
  }
}
