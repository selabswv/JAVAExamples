

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Prac3_2 {
	public static void main(String args[]) throws IOException {
		String tmp, inst;
		FileInputStream in = null;
		FileOutputStream out = null;
		BufferedOutputStream ob = null;
		int c;
		long start = 0, end = 0;
		ByteBuffer buf = ByteBuffer.allocate(1024*10);

		Scanner s = new Scanner(System.in);
		tmp = s.nextLine();

		inst = tmp.split(" ")[0].toUpperCase();
		if (inst.equals("COPY")) {
			try {
				in = new FileInputStream(tmp.split(" ")[1]);
				out = new FileOutputStream(tmp.split(" ")[2]);
				ob = new BufferedOutputStream(out);

				buf.clear();
				start = System.currentTimeMillis();
				while ((c = in.read()) != -1) {
					if (buf.hasRemaining())
						buf.put((byte) c);
					
					else {
						ob.write(buf.array());
						buf.clear();
					}
				}

				buf = (ByteBuffer) buf.flip();
				
				if (buf.limit() != 0) {
					ob.write(buf.array(), 0, buf.limit());
					buf.clear();
					}
				end = System.currentTimeMillis();
				System.out.println("동작 시간: " + (end - start) / 1000 + "."
						+ (end - start) % 1000 + "(s)");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (ob != null)
					ob.close();
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			}
		}
	}
}
