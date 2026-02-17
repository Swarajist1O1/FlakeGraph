class DummyClass_91593 {
    @Test
    public void testBeeline() throws IOException {
        String lineSeparator = java.security.AccessController
                .doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));
        System.setProperty("kylin.source.hive.client", "beeline");
        System.setProperty("kylin.source.hive.beeline-shell", "/spark-client/bin/beeline");
        System.setProperty("kylin.source.hive.beeline-params", "-u jdbc_url");

        HiveCmdBuilder hiveCmdBuilder = new HiveCmdBuilder();
        hiveCmdBuilder.addStatement("USE default;");
        hiveCmdBuilder.addStatement("DROP TABLE `test`;");
        hiveCmdBuilder.addStatement("SHOW TABLES;");

        String cmd = hiveCmdBuilder.build();
        String hqlFile = cmd.substring(cmd.lastIndexOf("-f ") + 3).trim();
        hqlFile = hqlFile.substring(0, hqlFile.length() - ";exit $ret_code".length());
        String createFileCmd = cmd.substring(0, cmd.indexOf("EOL\n", cmd.indexOf("EOL\n") + 1) + 3);
        CliCommandExecutor cliCommandExecutor = new CliCommandExecutor();
        Pair<Integer, String> execute = cliCommandExecutor.execute(createFileCmd);
        String hqlStatement = FileUtils.readFileToString(new File(hqlFile), Charset.defaultCharset());
        assertEquals(
                "USE default;" + lineSeparator + "DROP TABLE `test`;" + lineSeparator + "SHOW TABLES;" + lineSeparator,
                hqlStatement);
        assertBeelineCmd(cmd);
        FileUtils.forceDelete(new File(hqlFile));
    }

}