class DummyClass_136490 {
    @Test
    public void should_return_blank_name_on_null() throws Exception {
        //Given
        String name = null;

        //When
        final String actual = strategy.apply(name);

        //Then
        assertThat(actual).isEmpty();
    }

}