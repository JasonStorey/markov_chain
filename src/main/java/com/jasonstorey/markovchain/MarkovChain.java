package com.jasonstorey.markovchain;

import java.util.*;

public class MarkovChain {
    public HashMap<String, List<String>> memory;

    public MarkovChain() {
        this.memory = new HashMap<>();
    }

    public void learn(String txt) {
        List<String> words = Arrays.asList(txt.split(" "));

        ListIterator iterator = words.listIterator();

        while(iterator.hasNext()) {
            String key = null;

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
        return ask(null);
    }

    public String ask(String seed) {
        return getChain(seed, "");
    }

    private String getChain(String currentState, String chain) {
        List<String> nextStates = memory.getOrDefault(currentState, new ArrayList<>());

        if(nextStates.isEmpty()) {
            return chain;
        }

        int randomInt = new Random().nextInt(nextStates.size());
        String next = nextStates.get(randomInt);
        chain = chain.concat(next + " ");

        return getChain(next, chain);
    }
}
