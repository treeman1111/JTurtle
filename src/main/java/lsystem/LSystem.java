package lsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LSystem implements ILSystem {
    private final Map<String, String> productions;
    private final Map<String, String> result_cache;
    private final String axiom;
    private final Set<Character> valid_chars;

    public LSystem(Set<Character> valid_chars, String axiom) {
        this.axiom = axiom;
        this.valid_chars = valid_chars;
        productions = new HashMap<>();
        result_cache = new HashMap<>();
    }

    public void addProduction(String predecessor, String successor) {
        if (parseString(predecessor) && parseString(successor)) {
            productions.put(predecessor, successor);
            result_cache.put(predecessor, successor);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String generateNthProduction(int n) {
        String production = applyProductions(axiom);

        for (int i = 1; i < n; i++) {
            production = applyProductions(production);
        }

        return production;
    }

    private String applyProductions(String input) {
        /* base case. */
        if (productions.containsKey(input)) {
            return productions.get(input);
        }

        /* another base case. */
        if (result_cache.containsKey(input)) {
            return result_cache.get(input);
        } else { /* recursive bit. */
            final int length = input.length();

            /* for chars with no production. */
            if (length == 1) {
                return input;
            }

            final String first = input.substring(0, length / 2);
            final String last = input.substring(length / 2);

            result_cache.put(input, applyProductions(first) + applyProductions(last));

            return result_cache.get(input);
        }
    }

    /**
     * Ensures that the given string uses the valid encoding.
     *
     * @param s The string to be tested for valid encoding.
     */
    private boolean parseString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!valid_chars.contains(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
