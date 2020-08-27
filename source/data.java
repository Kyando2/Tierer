package tier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

class Data {
	static final Pattern tierPattern = Pattern.compile("(?<=\\ |\\>)(\\d\\-[A-C])(?=\\<|\\ )");
	static final Pattern urlPattern = Pattern.compile("(?<=a\\ href\\=\")(https\\:\\/\\/vsbattles\\.fandom\\.com\\/wiki\\/.*?)(?=\"\\ class\\=\"result\\-link\"\\ data\\-pos\\=\"1\")");

	public static String findTier(String data) throws Exception {
		Matcher matcher = tierPattern.matcher(data);
		List<String> matches = new ArrayList<String>();
		while (matcher.find()) {
			matches.add(matcher.group());
		}
		return Data.findLowest(matches);
	}

	private static String findLowest(List<String> data) {
		int lowest = 113;
		String returned = "11-C";
		for (int x = 0; x<data.size(); x++) {
			int value = Data.toValue(data.get(x));
			if (value<lowest) {
				lowest = value;
				returned = data.get(x);
			}
		}
		return returned;
	}

	private static int toValue(String tier) {
		int value = Integer.parseInt(tier
			.replace("-", "")
			.replace("C", "3")
			.replace("B", "2")
			.replace("A", "1"));
		return value;
	}

	public static String findUrl(StringBuffer data) {
		Matcher matcher = urlPattern.matcher(data);
		String returned = "404";
		if (matcher.find()) {
			returned = matcher.group();
		}
		return returned;
	}
}