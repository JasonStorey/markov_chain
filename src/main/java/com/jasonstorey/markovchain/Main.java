package com.jasonstorey.markovchain;

public class Main {
    public static void main(String[] args) {
        MarkovChain markovChain = new MarkovChain();
        markovChain.learn("Some text to learn");
    }
}