class DummyClass_176815 {
    @Test
    public void matchesLocationUrl() throws Exception {
        assertThat("http://company.com:80/webapp/orx/rest/index/types/CLIENT/nodes/6f1155df-644b-4228-89af" +
                "-7d24b8fe1a8d", EndsWithRegexp.endsWithRegexp("/index/types/CLIENT/nodes/.+"));
    }

}