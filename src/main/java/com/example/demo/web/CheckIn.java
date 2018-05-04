package com.example.demo.web;

import com.example.demo.ladder.WordLadder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.Vector;

@RestController
public class CheckIn {
    private boolean flag = false;
    private WordLadder ladder = new WordLadder();

    @RequestMapping("/test")
    public String[] test(){
        String[] ret = new String[2];
        ret[0] = "test1";
        ret[1] = "test2";
        return ret;
    }

    @RequestMapping("/ladder")
    public Vector<String> getLadder (@RequestParam(value = "begin", defaultValue = "") String begin,
                                     @RequestParam(value = "end", defaultValue = "") String end){
        if (!flag){
            try {
                ladder.readFile();
            }
            catch (FileNotFoundException e) {
            }
        }
        return ladder.findLadder(begin,end);
        //return ans;
    }
}
