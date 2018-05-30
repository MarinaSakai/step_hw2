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
    String lettersSort;
  try {
      letters = br.readLine();
      lettersArray = letters.split("");
    } catch(Exception e) {
      System.out.println(e);
    }
    Arrays.sort(lettersArray); // sort
    lettersSort = String.join("", lettersArray);

    // Dictionaryの内容を読み込んでsortした状態でdicDataListSortに格納
    String file_name = "Dictionary.txt";
    Dictionary dicScan = new Dictionary(new File(file_name));
//    ArrayList<String[]> dicData = dicScan.read();
    String[] dicData = dicScan.read(); // Dictionaryの内容(String)
    int count = 0;
    for(int i = 0; i < dicData.length; i ++) {
      if(dicData[i] == null) break;
      count ++;
    }
    System.out.println("count: " + count); //72412
    String[] dicDataArray = new String[16]; // Dictionaryの単語自体をsortするために一度配列に変換
    String[] dicDataList = new String[count]; // Dictionaryの単語自体のみがsortされたリスト
    String[] dicDataListSort = new String[count]; // Dictionaryの単語同士もsortされたリスト
    for(int i = 0; i < count; i ++) {
      if(dicData[i] != null) dicDataArray = dicData[i].split("");
      Arrays.sort(dicDataArray);
      dicDataList[i] = String.join("", dicDataArray);
      dicDataListSort[i] = dicDataList[i];
    }
    Arrays.sort(dicDataListSort);

    dicScan.close();

    System.out.println(lettersSort);
    int num;
    int none = 0;
    System.out.println(dicDataList.length);
    for(num = 0; num < dicDataList.length; num ++) {
      if(lettersSort.compareToIgnoreCase(dicDataList[num]) == 0) {
        break;
      }
      if(num == dicDataList.length-1) {
        System.out.println("none");
        none = 1;
        break;
      }
    }
    if(none == 0) {
      System.out.println(dicDataList[num]+" num: " + num);
      System.out.println(dicData[num]);
    }

/*    // 比較(二分探索)
    int L = 0;
    int R = dicDataListSort.length;
    int M = -1;
    String str;
    while(L < R) {
      M = (L+R)/2;
      str = dicDataListSort[M];
      System.out.println(str);
      if(lettersSort.compareToIgnoreCase(str) < 0) { // targetの方が小さい
        R = M;
      } else if(lettersSort.compareToIgnoreCase(str) > 0) { // targetの方が大きい
        L = M + 1;
      } else { // targetと等しい
        break;
      }
    }
    System.out.println("The word is " + dicDataListSort[M]);
    */
  }
}
