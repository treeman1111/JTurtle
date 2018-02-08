package lsystem;

import java.util.*;

public class StochasticLSystem implements ILSystem {
    private final Map<String, List<StochasticProduction>> productions;
    private final Set<Character> valid_chars;
    private final String axiom;

    public StochasticLSystem(Set<Character> valid_chars, String axiom) {
        this.valid_chars = valid_chars;
        this.axiom = axiom;
        productions = new HashMap<>();
    }

    public void addProduction(String predecessor, String successor, float p) {
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("p: (0, 1]");
        } else if (parseString(predecessor) && parseString(successor)) {
            List<StochasticProduction> s_prods = productions.get(predecessor);

            if (s_prods == null) {
                s_prods = new LinkedList<>();
            }

            s_prods.add(new StochasticProduction(p, successor));

            productions.put(predecessor, s_prods);
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
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            String c = "" + input.charAt(i);

            if (productions.containsKey(c)) {
                result.append(chooseStochasticProduction(productions.get(c)));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private String chooseStochasticProduction(List<StochasticProduction> l) {
        // TODO
        return "";
    }

    private boolean parseString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!valid_chars.contains(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}

class StochasticProduction {
    private final float prob;
    private final String production;

    StochasticProduction(float prob, String production) {
        this.prob = prob;
        this.production = production;
    }

    float getProb() {
        return prob;
    }

    String getProduction() {
        return production;
    }
}
