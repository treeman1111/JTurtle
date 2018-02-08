package graphics;

import lsystem.LSystem;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestLSystem {
    @Test
    public void testLSystemSpeed() {
        final Set<Character> valid_chars = new HashSet<>();

        valid_chars.add('F');
        valid_chars.add('+');
        valid_chars.add('-');

        final LSystem l_sys = new LSystem(valid_chars, "F-F-F-F");
        double time_sum = 0;

        l_sys.addProduction("F", "FF-F+F-F-FF");

        for (int i = 0; i < 100; i++) {
            long start_time = System.nanoTime();
            l_sys.generateNthProduction(8);
            time_sum += (System.nanoTime() - start_time);
        }

        System.out.println(time_sum / 100);
    }
}
