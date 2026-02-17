class DummyClass_159686 {
    @Test
    public void generateSql_insert() throws Exception {
        this.statementUnderTest = new MarkChangeSetRanStatement(new ChangeSet("a", "b", false, false, "c", "e", "f",
                null), ChangeSet.ExecType.EXECUTED);
        String version = LiquibaseUtil.getBuildVersion().replaceAll("SNAPSHOT", "SNP");
        assertCorrect("insert into [databasechangelog] ([id], [author], [filename], [dateexecuted], " +
                        "[orderexecuted], [md5sum], [description], [comments], [exectype], [contexts], [labels], " +
                        "[liquibase], [deployment_id]) values ('a', 'b', 'c', getdate(), 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                MSSQLDatabase.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', systimestamp, 1, '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', " +
                        "'executed', 'e', null, '" + version + "', null)",
                OracleDatabase.class);
        assertCorrect("insert into [databasechangelog] ([id], [author], [filename], [dateexecuted], " +
                        "[orderexecuted], [md5sum], [description], [comments], [exectype], [contexts], [labels], " +
                        "[liquibase], [deployment_id]) values ('a', 'b', 'c', getdate(), 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                SybaseDatabase.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', " +
                        "current year to fraction(5), 1, '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', " +
                        "'executed', " +
                        "'e', null, '" + version + "', null)",
                InformixDatabase.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', current timestamp, 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                DB2Database.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', current_timestamp, 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                FirebirdDatabase.class, DerbyDatabase.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', now, 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                HsqlDatabase.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', now(), 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                SybaseASADatabase.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, `description`, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', now(), 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                MySQLDatabase.class, MariaDBDatabase.class);
        assertCorrect("insert into databasechangelog (id, author, filename, dateexecuted, orderexecuted, " +
                        "md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) values " +
                        "('a', 'b', 'c', now(), 1, " +
                        "'8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, '" + version + "'," +
                        " null)",
                PostgresDatabase.class, H2Database.class);
        assertCorrectOnRest("insert into databasechangelog (id, author, filename, dateexecuted, " +
                "orderexecuted, md5sum, description, comments, exectype, contexts, labels, liquibase, deployment_id) " +
                "values ('a', 'b', 'c', " +
                "current timestamp, 1, '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', 'executed', 'e', null, " +
                "'" + version + "', null)");
    }

}