class DummyClass_136560 {
    @Test
    public void should_get_first_value() throws Exception {
        //When
        final String actual = OverridingOptional
                .from(Optional.ofNullable("first"))
                .andThen(Optional.<String>empty())
                .andThen(Optional.ofNullable("value"))
                .defaultValue("default")
                .get();

        //Then
        assertThat(actual).isEqualTo("first");
    }

}