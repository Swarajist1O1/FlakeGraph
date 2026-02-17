class DummyClass_176778 {
    @Test(expected = CukesRuntimeException.class)
    public void byInvalidPattern() throws Exception {
        generator.byPattern("b");
    }

}