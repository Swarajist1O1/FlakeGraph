class DummyClass_159532 {
    @Test
    public void compileWithInterfaceForObject() {
        ASSERT.about(javaSources())
                .that(Arrays.asList(JavaFileObjects.forResource("some/test/InterfaceObjectReference.java"), customInterface))
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}