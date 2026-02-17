class DummyClass_159508 {
    @Test
    public void compileBooleanFile() {
        ASSERT.about(javaSource())
                .that(booleansModel)
                .compilesWithoutError();
    }

}