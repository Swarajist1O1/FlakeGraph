class DummyClass_112099 {
    @Test
    public void assertGetIp() {
        assertThat(new JobInstance().getIp(), Is.is(IpUtils.getIp()));
    }

}