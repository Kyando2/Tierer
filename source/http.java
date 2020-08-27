package tier;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class Http {
	static final String SEARCHURL = "https://vsbattles.fandom.com/wiki/Special:Search?query=";

	public static String getPage(String name) throws Exception {
		URL url = new URL(Http.getUrl(name));
		StringBuffer data = Http.request(url);
		return data.toString();
	}

	private static StringBuffer request(URL url) throws Exception {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		int statusCode = con.getResponseCode();
		StringBuffer returned = new StringBuffer("");
		if (statusCode == 200) {
			try {
				returned = Http.reader(con);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		con.disconnect();
		return returned;
	}

	private static StringBuffer reader(HttpURLConnection con) throws Exception {
		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		return content;
	}

	private static String getUrl(String rsrc) throws Exception {
		URL searchUrl = new URL(SEARCHURL + rsrc);
		StringBuffer data = Http.request(searchUrl);
		return Data.findUrl(data);
	}
}