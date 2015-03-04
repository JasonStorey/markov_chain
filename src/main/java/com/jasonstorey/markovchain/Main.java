package com.jasonstorey.markovchain;

public class Main {
    public static void main(String[] args) {
        MarkovChain markovChain = new MarkovChain();
        markovChain.learn("Some text to learn");
        markovChain.learn("Some new text to learn");
        markovChain.learn("A text is hard to learn");

        markovChain.setSeparator(",");
        markovChain.learn("A,B,C,D,E,F,G");
        markovChain.setSeparator(" ");
        System.out.println(markovChain.ask());
    }
}