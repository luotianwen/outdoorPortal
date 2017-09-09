package cn.com.visitbeijing.e.api;


import org.apache.commons.codec.binary.Base64;

public class pack {


	public static byte[] str2pack(String str) {
		int nibbleshift = 4;
		int position = 0;
		int len = str.length()/2 + str.length()%2;
		
		byte[] output = new byte[len];
		for (char v : str.toCharArray()) {
			byte n = (byte) v;
			if (n >= '0' && n <= '9') {
				n -= '0';
			} else if (n >= 'A' && n <= 'F') {
				n -= ('A' - 10);
			} else if (n >= 'a' && n <= 'f') {
				n -= ('a' - 10);
			} else {
				continue;
			}
			
			output[position] |= (n << nibbleshift);

			if (nibbleshift == 0) {
				position++;
			}
			nibbleshift = (nibbleshift + 4) & 7;
		}
		
		return output;
	}
}
