import java.io.*;
import java.util.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> st = new Stack<>();
        int answer = -1;
        int cnt = 0;
        while(cnt < s.length()){
            if(st.isEmpty()){
                st.push(s.charAt(cnt));
            }
            else{
                if(st.peek() == s.charAt(cnt)){
                    st.pop();
                }
                else{
                    st.push(s.charAt(cnt));
                }
            }
            cnt++;
            
        }

        if(st.isEmpty()){
            return 1;
        }
        else
            return 0;
    }
}
