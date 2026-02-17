class DummyClass_91592 {
    @Test
    public void testHiveCLI() {
        System.setProperty("kylin.source.hive.client", "cli");

        Map<String, String> hiveProps = new HashMap<>();
        hiveProps.put("hive.execution.engine", "mr");
        Map<String, String> hivePropsOverwrite = new HashMap<>();
        hivePropsOverwrite.put("hive.execution.engine", "tez");
        HiveCmdBuilder hiveCmdBuilder = new HiveCmdBuilder("test HiveCLI");
        hiveCmdBuilder.addStatement("USE default;");
        hiveCmdBuilder.addStatement("DROP TABLE `test`;");
        hiveCmdBuilder.addStatement("SHOW\n TABLES;");
        hiveCmdBuilder.setHiveConfProps(hiveProps);
        hiveCmdBuilder.overwriteHiveProps(hivePropsOverwrite);
        assertEquals(
                "hive -e \"set mapred.job.name='test HiveCLI';\nUSE default;\nDROP TABLE \\`test\\`;\nSHOW\n TABLES;\n\" --hiveconf hive.execution.engine=tez",
                hiveCmdBuilder.build());
    }

}