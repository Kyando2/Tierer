package tier;

class Main {
	public static void main(String[] args) {
		String r = "";
		try {
			r = Http.getPage(args[0]);
			Main.print(Data.findTier(r));
		} catch (Exception e) {
			System.out.println (e.getMessage());		
		}
	}

	private static void print(String arg) {
		System.out.println(arg); // Display the string.
	}
}
