class DummyClass_112079 {
    @Test
    public void assertTransformWithNull() {
        assertThat(ExceptionUtil.transform(null), is(""));
    }

}