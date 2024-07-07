import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test1() {
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        int loopN = 10;
        String messageReturn = "";
        for (int t = 0; t < loopN; t++) {
            int addFirstN = StdRandom.uniform(8);
            for (int i = 0; i < addFirstN; i++) {
                student.addFirst(i);
                solution.addFirst(i);
                messageReturn += "addFirst(" + i + ")\n";
            }
            int addLastN = StdRandom.uniform(8);
            for (int j = 0; j < addLastN; j++) {
                student.addLast(j);
                solution.addLast(j);
                messageReturn += "addLast(" + j + ")\n";
            }
            int removeFirstN = StdRandom.uniform(addFirstN + addLastN);
            for (int removeFirstInd = 0; removeFirstInd < removeFirstN; removeFirstInd++) {
                Integer actural1 = student.removeFirst();
                Integer expect1 = solution.removeFirst();
                messageReturn += "removeFirst()\n";
                assertEquals(messageReturn, expect1, actural1);
            }
            int removeLastN = StdRandom.uniform(addFirstN + addLastN - removeFirstN);
            for (int removeLastInd = 0; removeLastInd < removeLastN; removeLastInd++) {
                Integer actural2 = student.removeLast();
                Integer expect2 = solution.removeLast();
                messageReturn += "removeLast()\n";
                assertEquals(messageReturn, expect2, actural2);
            }
        }
    }
}
