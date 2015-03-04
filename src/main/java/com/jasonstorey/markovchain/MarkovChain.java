package com.jasonstorey.markovchain;

import java.util.*;

public class MarkovChain {
    private HashMap<String, List<String>> memory;
    private String separator;
    private final int MAX_CHAIN_LENGTH = 100;

    public MarkovChain() {
        this.memory = new HashMap<>();
        this.separator = " ";
    }

    public void learn(String txt) {
        List<String> words = Arrays.asList(txt.toLowerCase().split(separator));

        ListIterator iterator = words.listIterator();

        while(iterator.hasNext()) {
            String key = "";

            if(iterator.hasPrevious()) {
                key = words.get(iterator.previousIndex());
            }

            commitToMemory(key, (String) iterator.next());
        }
    }

    private void commitToMemory(String key, String word) {
        if(!memory.containsKey(key)) {
            memory.put(key, new ArrayList<>());
        }

        memory.get(key).add(word);
    }

    public String ask() {
        return ask("");
    }

    public String ask(String seed) {
        return getChain(seed.toLowerCase(), "", MAX_CHAIN_LENGTH);
    }

    private String getChain(String currentState, String chain, int maxChainLength) {
        List<String> nextStates = memory.getOrDefault(currentState, new ArrayList<>());

        if(nextStates.isEmpty() || maxChainLength == 0) {
            return chain;
        }

        int randomInt = new Random().nextInt(nextStates.size());
        String next = nextStates.get(randomInt);
        chain = chain.concat(next + separator);

        return getChain(next, chain, maxChainLength - 1);
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
