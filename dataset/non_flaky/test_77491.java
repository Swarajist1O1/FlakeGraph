class DummyClass_77491 {
    @ParameterizedTest
    public void isCollectionOfStringsShouldWork(String name, boolean isCollectionOfStrings) {
        final ConfigurationMetadata metadata = new ConfigurationMetadata(
                Jackson.newObjectMapper(), ExampleConfiguration.class);

        assertThat(metadata.isCollectionOfStrings(name)).isEqualTo(isCollectionOfStrings);
    }

}