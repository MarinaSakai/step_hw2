import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dictionary {
  public static final int M = 75000;
  Scanner scan;

  public Dictionary(File source) {
    try {
      this.scan = new Scanner(source);
    } catch(FileNotFoundException fnex) {
      fnex.printStackTrace();
    }
  }

  public String[] read() {
    String[] dicList = new String[M];
    synchronized(this.scan) {
      for(int i = 0; this.scan.hasNext(); i ++) {
        dicList[i] = this.scan.nextLine().toLowerCase();
//        System.out.println(dicList[i]);
      }
      return dicList;
    }
  }

  public void close() {
    this.scan.close();
  }
}
