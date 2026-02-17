class DummyClass_159510 {
    @Test
    public void compileBooleansProxyFile() throws Exception {
        ASSERT.about(javaSource())
                .that(booleansModel)
                .compilesWithoutError();
    }

}