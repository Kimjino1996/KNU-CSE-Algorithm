import java.io.*;
import java.util.*; 

class Solution {
    public int solution(String s) {
        int answer = 100000;
        if(s.length() == 1 || s.length()==2)
            return s.length();
        
        int i, j;
        for(i=1;i< s.length();i++){
            String now = " "; 
            int answer_now = 0; 
            int flag = 0; 
            for(j=0; j+i-1<s.length();j+=i){ 
                if(now.equals(s.substring(j,j+i))){
                    if(flag == 0){
                        answer_now+=1;
                        flag ++;
                    }
                    flag++;
                    if(flag == 10 || flag == 100 || flag == 1000){
                        answer_now++;
                    }
                } else{
                    now = s.substring(j,j+i);
                    answer_now += i;
                    flag = 0;
                }
            }
            answer_now += s.length() - j;
            answer = Math.min(answer, answer_now);
        }
        return answer;
    }
}
