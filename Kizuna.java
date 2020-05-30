package com.example.myapplication_ver5;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Kizuna extends Thread {
    CallbackInstance callbackInstance;



    @Override
    public void run() {
        Map<String,Map<String,Integer>> resultMap = new TreeMap<>();
        try {
            Document document = Jsoup.connect(" https://papimo.jp/h/00041817/hit/index_sort/220010002/1-20-266966").timeout(10000).get();
            Elements elements = document.select(".unit_no");


            for (Element element : elements) {
                String serialNO = element.text();
                Document oneDocument = Jsoup.connect("https://papimo.jp/h/00041817/hit/view/"+ serialNO).timeout(10000).get();


                Elements historyTR = oneDocument.select(".history tr");
                if (historyTR.size() != 0) {
                    historyTR.remove(0);
                    Collections.reverse(historyTR);
                    List<List<String>> historyList = new ArrayList<>();
                    for (Element TRElement : historyTR) {
                        List<String> list = new ArrayList<>();
                        list.add(TRElement.select(".cnt").text());
                        list.add(TRElement.select(".time").text());
                        list.add(TRElement.select(".start").text().replace(",", ""));
                        list.add(TRElement.select(".out").text().replace(",", ""));
                        list.add(TRElement.select(".sts").text());
                        historyList.add(list);
                    }
                    int last = Integer.parseInt(oneDocument.select("#tab-data-some tbody tr").eq(0).select("td").eq(6).text());
                    resultMap.put(serialNO,creatDetailData(historyList, last));
                }
           }

        } catch (IOException e) {
            System.out.println(e);
        }
        callbackInstance.callbackMethod(resultMap);

    }

    public Map<String, Integer> creatDetailData(List<List<String>> list1, int last){
        int bc = 0;
        int bt = 0;
        int hamari[] = {0,0};
        int total = last;
        boolean bttyuu = false;
        for (List<String> list:list1) {
            if(list.get(4).equals("BIG") && Integer.parseInt(list.get(2)) > 1){
                total += Integer.parseInt(list.get(2));
                bc +=1;
                hamari[0] += Integer.parseInt(list.get(2));
                hamari[1] += 1;
                bttyuu = false;
            }else{
                if(bttyuu ==false){
                    bttyuu = true;
                    hamari[0]=0;
                    hamari[1]=0;
                    bt +=1;
                }
            }
        }
        Map<String,Integer> map = new TreeMap<>();
        map.put("BC",bc);
        map.put("BT",bt);
        map.put("HG",hamari[0] + last);
        map.put("HBC",hamari[1]);
        return map;
    }

    interface CallbackInstance{
        void callbackMethod(Map<String,Map<String,Integer>> resultMap);
    }

    public Kizuna setCallbackInstance(CallbackInstance callbackInstance) {
        this.callbackInstance = callbackInstance;
        return this;
    }

}

