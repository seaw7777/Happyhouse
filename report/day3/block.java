import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.LinkedList;

public class block {
	static LinkedList<Node> hashtable = new LinkedList<Node>();
	static Node dummy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println(dummy.toString());
		dummy = new Node("Genisis Block","","",0);
		hashcasll(dummy);
		hashtable.add(dummy);
		while (true) {
			System.out.println("데이터를 입력해주세요(exit 입력시 종료)");
			String data = br.readLine();
			if ("exit".equals(data)) break;
			dummy = new Node(data, hashtable.get(hashtable.size()-1).hash, "", 0);
			hashcasll(dummy);
			hashtable.add(dummy);
		}
		for (int i = 0; i < hashtable.size(); i++) {
			System.out.println(hashtable.get(i));
		}
	}

	private static void hashcasll(Node dummy) {
		try {
			boolean flag = true;
			while (true) {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
//			System.out.println(dummy);
				byte[] hash = digest.digest(dummy.hashString().getBytes("UTF-8"));
				StringBuffer hexString = new StringBuffer();

				for (int i = 0; i < hash.length; i++) {
					String hex = Integer.toHexString(0xff & hash[i]);
					if (hex.length() == 1)
						hexString.append('0');
					hexString.append(hex);
				}

				// 출력
//				System.out.println(hexString.toString());
				if (hashtable.size() == 0) {
					dummy.hash = hexString.toString();
					break;
				}
				for (int i = 0; i < 5; i++) {
					if (hexString.charAt(i) != '0') {
						flag = false;
						dummy.nonce++;
//						System.out.println(dummy.nonce);
						break;
					}
					flag = true;
				}
				if (flag) {
					dummy.hash = hexString.toString();
					break;
					}
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	public static class Node {
		String data;
		String previoushash;
		String hash;
		int nonce;

		public Node(String data, String previoushash, String hash, int nonce) {
			super();
			this.data = data;
			this.previoushash = previoushash;
			this.hash = hash;
			this.nonce = nonce;
		}

		public Node() {
			super();
		}

		public String hashString() {
			return "/" + data + "/" + previoushash + "/" + nonce;
		}

		@Override
		public String toString() {
			return "nonce : " + nonce+"\ndata : "+data+"\nprevhash : "+previoushash+"\nhash : "+hash;
		}
		

	}
}
