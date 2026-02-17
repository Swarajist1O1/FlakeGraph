class DummyClass_136489 {
    @Test
    public void should_return_blank_name() throws Exception {
        //Given
        String name = "     ";

        //When
        final String actual = strategy.apply(name);

        //Then
        assertThat(actual).isEmpty();
    }

}