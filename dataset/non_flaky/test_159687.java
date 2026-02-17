class DummyClass_159687 {
    @Test
    public void generateSql_update() throws Exception {
        this.statementUnderTest = new MarkChangeSetRanStatement(new ChangeSet("a", "b", false, false, "c", "e", "f",
                null), ChangeSet.ExecType.RERAN);
        assertCorrect("update [databasechangelog] set [dateexecuted] = getdate(), [deployment_id] = null, [exectype] " +
                        "= 'reran', [md5sum] = '8:d41d8cd98f00b204e9800998ecf8427e', [orderexecuted] = 2 where [id] =" +
                        " 'a' and" +
                        " [author] = 'b' and [filename] = 'c'",
                MSSQLDatabase.class);
        assertCorrect("update databasechangelog set dateexecuted = systimestamp, deployment_id = null, exectype = " +
                        "'reran', md5sum = '8:d41d8cd98f00b204e9800998ecf8427e', orderexecuted = 2 where id = 'a' and" +
                        " author " +
                        "= 'b' and filename = 'c'",
                OracleDatabase.class);
        assertCorrect("update [databasechangelog] set [dateexecuted] = getdate(), [deployment_id] = null, [exectype] " +
                "= 'reran', [md5sum] = '8:d41d8cd98f00b204e9800998ecf8427e', [orderexecuted] = 2 where [id] = 'a' and" +
                " [author] = 'b' and [filename] = 'c'", SybaseDatabase.class);
        assertCorrect("update [databasechangelog] set [dateexecuted] = current year to fraction(5), deployment_id = " +
                "null, exectype = 'reran', md5sum = '8:d41d8cd98f00b204e9800998ecf8427e', orderexecuted = 2 where id " +
                "= 'a' and author = 'b' and filename = 'c'", InformixDatabase.class);
        assertCorrect("update [databasechangelog] set [dateexecuted] = current timestamp, deployment_id = null, " +
                        "exectype = 'reran', md5sum = '8:d41d8cd98f00b204e9800998ecf8427e', orderexecuted = 2 where " +
                        "id = 'a' and author = 'b' and filename = 'c'",
                DB2Database.class);
        assertCorrect("update [databasechangelog] set [dateexecuted] = current_timestamp, deployment_id = null, " +
                        "exectype = 'reran', md5sum = '8:d41d8cd98f00b204e9800998ecf8427e', orderexecuted = 2 where " +
                        "id = 'a' and author = 'b' and filename = 'c'",
                FirebirdDatabase.class,
                DerbyDatabase.class);
        assertCorrect("update [databasechangelog] set [dateexecuted] = NOW(), deployment_id = null, exectype = " +
                        "'reran', md5sum = '8:d41d8cd98f00b204e9800998ecf8427e', orderexecuted = 2 where id = 'a' and" +
                        " author = 'b' and filename = 'c'",
                SybaseASADatabase.class);
        assertCorrect("update [databasechangelog] set [dateexecuted] = NOW(), deployment_id = null, exectype = " +
                        "'reran', md5sum = '8:d41d8cd98f00b204e9800998ecf8427e', orderexecuted = 2 where id = 'a' and" +
                        " author = 'b' and filename = 'c'",
                MySQLDatabase.class, MariaDBDatabase.class, HsqlDatabase.class, PostgresDatabase.class, H2Database
                        .class);
        assertCorrectOnRest("update [databasechangelog] set [dateexecuted] = NOW(), [deployment_id] = null, [exectype] = 'reran', [md5sum] = " +
                "'8:d41d8cd98f00b204e9800998ecf8427e', [orderexecuted] = 2 where id = 'a' and author = 'b' and filename = 'c'");
    }

}