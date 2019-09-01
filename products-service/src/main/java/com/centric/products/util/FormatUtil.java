package com.centric.products.util;

import java.util.UUID;

public final class FormatUtil {
	public static final String HREF_TEMPLATE = "https://%s/v%d/%s/%s";

	private FormatUtil() {

	}

	/**
	 * Create a link for an externally identified object.
	 *
	 * @param host    the host to retrieve the object from.
	 * @param path    the API path between host and version.
	 * @param version the API version to call.
	 * @param id      the ID path parameter.
	 * @return a link for the object with the given ID.
	 */
	public static String makeHref(String host, int version, String path, UUID id) {
		return String.format(HREF_TEMPLATE, host, version, path, id);
	}
}