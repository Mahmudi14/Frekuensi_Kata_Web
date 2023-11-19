package parsingWeb;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    public static void main(String[] args) {
        Document doc;
        try {
            doc = Jsoup.connect("https://informatika.unsulbar.ac.id/").get();
            String textDoc = doc.text();
            String text01 = textDoc.replaceAll("\u00A9|,|&|\\(|\\)|\\.|/|[0-9]|\\?|@|!|;"," ");
            String text = text01.replaceAll("\\s+"," ");
            String[] kata = text.split(" ");
            ArrayList<String> data = new ArrayList<>();
            ArrayList<Integer> frekuensi   = new ArrayList<>();
            
            for (int i = 0; i < kata.length; i++) {
                String kt = kata[i].toLowerCase();
                if(kt.equals("\u002d") || kt.equals("\u2013")){
                    continue;
                }
                boolean add = false;
                if(!data.contains(kt)){
                    add = true;
                }
                
                if(add){
                    data.add(kt);
                    frekuensi.add(1);
                }else{
                    int index = data.indexOf(kt);
                    int n = frekuensi.get(index);
                    n++;
                    frekuensi.set(index, n);
                }
            }
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i)+" = "+frekuensi.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}