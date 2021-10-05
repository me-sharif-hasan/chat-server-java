package com.iishanto.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class JsonStreamReader {
    InputStream is;
    public JsonStreamReader(InputStream inputStream) {
        is=inputStream;
    }

    Stack <Character> braceSeq;
    public String nextObject() throws IOException {
        braceSeq=new Stack<Character>();
        String json="";
        byte []buff=new byte[1];
        int br=0;

        while (true){
            int r=is.read(buff);
            if(r<=0) continue;
            char c=new String(buff).charAt(0);

            if(c=='{'||c=='}'){
                br++;
                if(braceSeq.empty()&&br==1){
                    braceSeq.push(c);
                }else if(braceSeq.peek()=='{'&&c=='}'){
                    braceSeq.pop();
                }else{
                    braceSeq.push(c);
                }
            }
            if(br>=1){
                json+=c;
            }
            if(braceSeq.empty()&&br>=1) break;
        }
        json=json.trim();
        return json;
    }
}
