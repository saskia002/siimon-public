package siimon.core.api.module.avaleht.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlPaths {

	// * Domain name
	public static final String AVALEHT_MAPPING = "/v2/avaleht";

	// * Course
	public static final String AVALEHT_COURSE_MAPPING = AVALEHT_MAPPING + "/course";
	public static final String AVALEHT_COURSE_REGISTRATIONS_MAPPING = AVALEHT_COURSE_MAPPING + "/registration";

}
