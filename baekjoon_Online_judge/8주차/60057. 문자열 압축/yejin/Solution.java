import java.io.*;
import java.util.*;

class Solution {
public static int Replace(String str) throws IOException {
        if(str.length() == 1){
            return 1;
        }
        int tokenLen = 1;
        int minLength = 1001;
        for (int len = 1; len <= str.length()/2 ; len++) { //몇 개씩 나눌지 1, 2, 3, ...
            String nowToken = "";
            String nowStr = "";
            for (int i = 0; i+len <= str.length(); i += len) {
                nowToken = str.substring(i, i+len);
                int repeat = 0;
                String compareToken = "";
                int j = i;
                while(true){
                    if(j+len > str.length()){
                        break;
                    }
                    compareToken = str.substring(j, j+len);
                    if(nowToken.equals(compareToken)){
                        repeat++;
                        j += len;
                    }
                    else{
                        break;
                    }
                }
                i = j-len;
                if(repeat != 1) {
                    nowStr += repeat;
                }
                nowStr += nowToken;
            }
            if(str.length() % len != 0)
                nowStr += str.substring(str.length()-str.length()%len);

            if(nowStr.length() < minLength){
                minLength = nowStr.length();
                tokenLen = len;
            }
        }
        return minLength;
    }
    
    public int solution(String s) throws IOException {
        int answer = 0;
        answer = Replace(s);
        return answer;
    }
}