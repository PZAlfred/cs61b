import static org.junit.Assert.*;

import org.junit.Test;
public class test {
  
  @Test
  public void testLLDeque() {
    LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
    L1.addFirst(99);
    System.out.println(L1.size());

  }
}
