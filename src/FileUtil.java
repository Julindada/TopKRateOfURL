import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by julin on 2019-04-01 09:37
 */
public class FileUtil {

    public static int divideFile(String path, long size) {
        try {
            File srcFile = new File(path);
            if (!srcFile.exists() || srcFile.isDirectory()) {
                return 0;
            }
            File parentFile = srcFile.getParentFile();
            int num = Long.valueOf(srcFile.length() / size).intValue() + (srcFile.length() % size == 0 ? 0 : 1);
            List<BufferedWriter> writers = new ArrayList<BufferedWriter>(num);
            List<Long> fileLength = new ArrayList<Long>(num);
            List<Integer> fileIndex = new ArrayList<Integer>(num);
            for (int i = 0; i < num; i++) {
                fileLength.add(0L);
                fileIndex.add(0);
                File file = new File(parentFile, i + "0");
                writers.add(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))));
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));
            String lineData;
            while ((lineData = reader.readLine()) != null) {
                if (lineData.getBytes("UTF-8").length + 1 > size) return 0;
                int n = lineData.hashCode() % num;
                if (n < 0) n *= -1;
                if (fileLength.get(n) + lineData.getBytes("UTF-8").length + 1 > size) {
                    fileIndex.set(n, fileIndex.get(n) + 1);
                    File file = new File(parentFile, String.valueOf(n) + String.valueOf(fileIndex.get(n)));
                    fileLength.set(n, 0L);
                    writers.get(n).close();
                    writers.set(n, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))));
                }
                fileLength.set(n, fileLength.get(n) + lineData.getBytes("UTF-8").length + 1);
                writers.get(n).write(lineData);
                writers.get(n).write("\n");
            }
            for (int i = 0; i < num; i++)
                writers.get(i).close();
            reader.close();
            return num;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
