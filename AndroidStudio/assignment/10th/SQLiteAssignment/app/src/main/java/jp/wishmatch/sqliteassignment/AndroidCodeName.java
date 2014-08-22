package jp.wishmatch.sqliteassignment;

public class AndroidCodeName {
	public static final String TABLE_NAME = "android_code_name";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_VERSION = "version";

	private String name;
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}