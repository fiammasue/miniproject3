package project.utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import project.dto.Member;

public class MemberDB {
   
    public static void main(String[] args) throws Exception {

           String fileName = "C:\\temp\\member.db";
           File file =new File(fileName);
           ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
           Map<String,Member> memberMap = new HashMap<>();
          // memberMap.put("kkk",new Member("root","관리자","1234","N","N","N","N"));
           

           oos.writeObject(memberMap);
           oos.close();
       }
   }