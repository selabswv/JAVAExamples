

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CopyFile {

	public static void main(String[] args) throws IOException {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		int c;
		long start=0,end=0;

		Scanner sc = new Scanner(System.in);
		String cmd = sc.next();
		String target = sc.next();
		String targetTo = sc.next();

		if(cmd.toUpperCase().equals("COPY")){
			try {
				in = new BufferedInputStream(new FileInputStream(target));
				out = new BufferedOutputStream(new FileOutputStream(targetTo)); 

				start = System.currentTimeMillis();
				while ((c = in.read()) != -1) {
					System.out.println((char)c);
					out.write(c);
				}
				end = System.currentTimeMillis();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					in.close(); 

				if (out != null)
					out.close();
			}
		}
		System.out.println(target + "을 " + targetTo + "으로 복사완료.");
		System.out.println("동작 시간: "+(end-start)/1000+"."+(end-start)%1000+"(s)");
	}
}

