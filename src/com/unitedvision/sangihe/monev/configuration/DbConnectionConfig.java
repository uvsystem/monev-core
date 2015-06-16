package com.unitedvision.sangihe.monev.configuration;

public class DbConnectionConfig {
	public static final String HOST = "mysql31535-core-unitedvision.whelastic.net";
	public static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    public static final String PROPERTY_NAME_DATABASE_URL = String.format("jdbc:mysql://%s:3306/sangihe_monev", HOST);
    public static final String PROPERTY_NAME_DATABASE_USERNAME = "sangihe";
    public static final String PROPERTY_NAME_DATABASE_PASSWORD = "somahekaikehage";
    public static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.unitedvision.sangihe.monev.entity";
}
