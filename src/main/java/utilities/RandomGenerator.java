package utilities;

import org.apache.commons.lang.RandomStringUtils;

public class RandomGenerator {
	public static String getFirstName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Rahul"+generatedString);
	}
	public static String getLastName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
        return("Sharma"+generatedString);
	}
	public static String getJobName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Leader"+generatedString);
	}

}
