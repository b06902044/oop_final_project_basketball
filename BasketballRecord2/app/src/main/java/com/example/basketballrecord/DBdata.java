package com.example.basketballrecord;

public final class DBdata {
    private static final String mySqlIp = "db4free.net";
    private static final int port = 3306;
    private static final String dbName = "firstschema";
    public static final String dbUser = "b06902006";
    public static final String dbPwd = "b06902006";
    public static final String url;
    static{
        url = "jdbc:mysql://" + mySqlIp + ":" + port + "/"+ dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }
}
