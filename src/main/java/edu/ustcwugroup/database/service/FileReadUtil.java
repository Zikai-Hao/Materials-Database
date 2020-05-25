package edu.ustcwugroup.database.service;

import edu.ustcwugroup.database.model.ViewObject;
import edu.ustcwugroup.database.util.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Haozk on 2019/12/17
 */
@Component
public class FileReadUtil {

    public ViewObject CIFRead(String url) throws Exception{
        FileInputStream fis = new FileInputStream(url);
        StringBuilder cif = new StringBuilder();
        byte[] bytes=new byte[4096];
        while (true){
            int temp = fis.read(bytes);
            if(temp==-1) break;
            cif.append(new String(bytes,0,temp));
        }
        fis.close();
        ViewObject vo = getValidInfo(new String(cif).split("\n"));
        vo.set("text",new String(cif).replace("\r\n","\\n"));
        vo.set("url",url);
        return vo;
    }

    /**
     * 获取CIF有效信息
     * @param strs
     * @return
     */
    private ViewObject getValidInfo(String[] strs){
        ViewObject vo =new ViewObject();
        ArrayList<String[]> wordsList = new ArrayList<>();
        HashSet<String> keyWords = getKeywords();
        HashSet<String> elements = new HashSet<>();
        for(String str:strs){
            wordsList.add(str.split("\\s+"));
        }
        ArrayList<String[]> occupancy = new ArrayList<>();
        int status = 0;
        for(String[] words:wordsList){
            if (keyWords.contains(words[0])) vo.set(words[0].length()>28?words[0].substring(0,28):words[0],words);
            else if (words[0].equals("_atom_site_occupancy")) status=1;
            //判断是否进入原子位置模块
            else if (status==1)
                if (words[0].equals("loop_"))status=0;
                else occupancy.add(words);
        }
        for(String[] words:occupancy) if(!elements.contains(words[1])) elements.add(words[1]);
        vo.set("elements",elements);
        vo.set("occupancy",occupancy);
        return vo;

    }

    private HashSet<String> getKeywords(){
        HashSet<String> keyWords = new HashSet<>();
        keyWords.add("_cell_length_a");
        keyWords.add("_cell_length_b");
        keyWords.add("_cell_length_c");
        keyWords.add("_cell_angle_alpha");
        keyWords.add("_cell_angle_beta");
        keyWords.add("_cell_angle_gamma");
        keyWords.add("_symmetry_space_group_name_H-M");
        return keyWords;
    }

    /**
     * 能带读取
     * @param url
     * @return
     */
    public static List<List<String>> bandsRead(String url) {
        try(FileReader fis = new FileReader(url);
            BufferedReader bf = new BufferedReader(fis)){
            String lineString;
            List<String> strings ;
            List<List<String>> listStrings=new ArrayList<>();
            while((lineString=bf.readLine())!=null){
                String[] stringArray=lineString.split("\\s+");
                int lineNumbers = stringArray.length-1;
                int i=0;
                for(String str:stringArray){
                    if(str.equals("")) continue;
                    if(listStrings.size()<lineNumbers){
                        strings=new LinkedList<>();
                        strings.add(str);
                        listStrings.add(strings);
                    }
                    else listStrings.get(i++).add(str);
                }


            }
            return listStrings;


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 测试用
     * @param args
     **/

    public static void main(String[] args) {
        try{
            ViewObject vo = new ViewObject();
            vo.set("bands",FileReadUtil.bandsRead("E://0caculation//Si//bands.dat"));
            String value = JsonUtil.getJSONString(0,vo.getObjs());
            System.out.println(value);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
