class DummyClass_112070 {
    @Test
    public void assertJobInit() throws IOException {
        ScriptElasticJobUtil.buildScriptCommandLine();
        WaitingUtils.waitingShortTime();
        String scriptCommandLine = ((ScriptJobConfiguration) getLiteJobConfig().getTypeConfig()).getScriptCommandLine();
        LiteJobConfiguration liteJobConfig = LiteJobConfigurationGsonFactory.fromJson(getRegCenter().get("/" + getJobName() + "/config"));
        assertThat(((ScriptJobConfiguration) liteJobConfig.getTypeConfig()).getScriptCommandLine(), is(scriptCommandLine));
    }

}