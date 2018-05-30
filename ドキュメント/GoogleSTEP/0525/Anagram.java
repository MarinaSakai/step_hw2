import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Anagram {
  public static void main(String[] args) {

    // 入力した文字列を配列lettersArrayにsortした状態で格納
    System.out.printf("Input letters: ");
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String letters;
    String[] lettersArray = null;
    String[] words = new String[10000];
    int n = 0;
  try {
      letters = br.readLine();
      lettersArray = letters.split("");
    } catch(Exception e) {
      System.out.println(e);
    }
    Arrays.sort(lettersArray); // sort


    // Dictionaryの内容を読み込んでsortした状態でdicDataListSortに格納
    String file_name = "Dictionary.txt";
    Dictionary dicScan = new Dictionary(new File(file_name));
    String[] dicData = dicScan.read(); // Dictionaryの内容
    int count = 0;
    for(int i = 0; i < dicData.length; i ++) {
      if(dicData[i] == null) break;
      count ++;
    }

    String[] dicDataArray = new String[16]; // Dictionaryの単語自体をsortするために一度配列に変換
    for(int i = 0; i < count; i ++) {
//      System.out.println(dicData[i]);
      if(dicData[i] != null) dicDataArray = dicData[i].split(""); // 一度配列に
      Arrays.sort(dicDataArray); // SortArray

      // Search
      int c = 0;
      int p = 0;
      for(int j = 0; j < dicDataArray.length; j ++) {
        if(dicDataArray.length > lettersArray.length) break;
        for(int k = p; k < lettersArray.length; k ++) {
          if(lettersArray[k].equals(dicDataArray[j])) {
//            System.out.println("l: " + lettersArray[k] + " d: " + dicDataArray[j]);
            c ++;
            p = k+1;
            break;
          }
        }
      }
      if(c == dicDataArray.length) {
        words[n] = dicData[i];
        n ++;
        System.out.printf(dicData[i] + " ");
      }
    }
    System.out.printf("\n");

    // BEST SCORE
    System.out.println(n);
    if(n > 0) {
      int num = -1;
      int max = 0;
      for(int i = 0; i < n; i ++) {
        int score = 0;
        String[] str = words[i].split("");
        for(int j = 0; j < str.length; j ++) {
          if(str[j].equals("a") || str[j].equals("b") || str[j].equals("d") || str[j].equals("e") || str[j].equals("g") || str[j].equals("i") || str[j].equals("n") || str[j].equals("o") || str[j].equals("r") || str[j].equals("s") || str[j].equals("t") || str[j].equals("u")) {
            score ++;
          } else if(str[j].equals("c") || str[j].equals("f") || str[j].equals("h") || str[j].equals("l") || str[j].equals("m") || str[j].equals("p") || str[j].equals("v") || str[j].equals("w") || str[j].equals("y")) {
            score += 2;
          } else if(str[j].equals("j") || str[j].equals("k") || str[j].equals("q") || str[j].equals("x") || str[j].equals("z")) {
            score += 3;
          }
        }
        if(score > max) {
          max = score;
          num = i;
        }
      }
      System.out.println("THE BEST: " + words[num] + max);
    }

    dicScan.close();

  }
}
