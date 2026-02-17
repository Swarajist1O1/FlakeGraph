class DummyClass_159531 {
    @Test
    public void compileWithInterfaceForList() {
        ASSERT.about(javaSources())
                .that(Arrays.asList(JavaFileObjects.forResource("some/test/InterfaceList.java"), customInterface))
                .processedWith(new RealmProcessor())
                .failsToCompile();
    }

}