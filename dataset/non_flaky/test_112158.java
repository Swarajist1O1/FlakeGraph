class DummyClass_112158 {
    @Test
    public void assertToDataflowJobJson() {
        assertThat(GsonFactory.getGson().toJson(new TestJobRootConfiguration(new TestDataflowJobConfiguration(true).getTypeConfig())),
                is(APIJsonConstants.getDataflowJobJson(IgnoreJobExceptionHandler.class.getCanonicalName())));
    }

}