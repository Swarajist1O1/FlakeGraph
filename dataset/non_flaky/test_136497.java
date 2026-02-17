class DummyClass_136497 {
    @Test
    public void should_return_lower_case() throws Exception {
        //Given
        String name = "ZfrEr_rfR";

        //When
        final String actual = strategy.apply(name);

        //Then
        assertThat(actual).isEqualTo("zfrer_rfr");
    }

}