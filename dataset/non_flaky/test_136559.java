class DummyClass_136559 {
    @Test
    public void should_get_first_present_value() throws Exception {
        //When
        final String actual = OverridingOptional
                .from(Optional.<String>empty())
                .andThen(Optional.<String>empty())
                .andThen(Optional.ofNullable("value"))
                .defaultValue("default")
                .get();

        //Then
        assertThat(actual).isEqualTo("value");
    }

}