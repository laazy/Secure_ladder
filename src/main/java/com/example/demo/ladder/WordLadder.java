package com.example.demo.ladder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class WordLadder {
    private String[] tot_word;
    public void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/Users/61640/Documents/java-wordladder/dictionary.txt"));
        Vector<String> tmp_words = new Vector<String>();
        while (sc.hasNext()){
            String tmp = sc.next().toLowerCase();
            tmp_words.add(tmp);
        }
        tot_word = new String[tmp_words.size()];
        tot_word = tmp_words.toArray(tot_word);
        Arrays.sort(tot_word);
    }

    private boolean checkWord(String word){
        int i = Arrays.binarySearch(tot_word, word);
        return i > 0;
    }

    private String transString(char[] chars){
        StringBuffer ans = new StringBuffer();
        for (char i : chars)
            if (i != ' ')
                ans.append(i);
        return ans.toString();
    }

    private Vector<String> alterWords(String now){
        char[] vec = new char[now.length()*2 + 1];
        Vector<String> ans = new Vector<String>();
        for (int i = 0; i< vec.length; i++){
            if (i % 2 == 1)
                vec[i] = now.charAt((i-1)/2);
            else
                vec[i] = ' ';
        }
        char pointer = ' ';
        for (int i = 0; i < vec.length; i++){
            pointer = vec[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == pointer)
                    continue;
                vec[i] = j;
                String tmp = transString(vec);
                if (checkWord(tmp))
                    ans.add(tmp);
            }
            if (pointer != ' '){
                vec[i] = ' ';
                String tmp = transString(vec);
                if (checkWord(tmp))
                    ans.add(tmp);
            }
            vec[i] = pointer;
        }
        return ans;
    }

    private Vector<Vector<String>> nextLayer(Vector<String> now){
        Vector<Vector<String>> ans = new Vector<Vector<String>>();
        Vector<String> alternative = alterWords(now.lastElement());
        for (String i : alternative) {
            Vector<String> tmp = (Vector<String>) now.clone();
            tmp.add(i);
            ans.add(tmp);
        }
        return ans;
    }

    public Vector<String> findLadder(String begin, String end){
        Queue<Vector<String>> ladders = new LinkedList<Vector<String>>();
        Vector<String> head = new Vector<String>();
        head.add(begin);
        ladders.offer(head);
        while (!ladders.isEmpty()){
            Vector<String> now = ladders.poll();
            if (now.lastElement().equalsIgnoreCase(end))
                return now;
            Vector<Vector<String>> layer = nextLayer(now);
            for (Vector<String> i : layer)
                ladders.offer(i);
        }
        throw new NoSuchElementException();
    }

}