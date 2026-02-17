class DummyClass_136494 {
    @Test
    public void should_return_snake_case() throws Exception {
        //Given
        String name = "theBigOne__andSmaller_One";

        //When
        final String actual = strategy.apply(name);

        //Then
        assertThat(actual).isEqualTo("the_big_one_and_smaller_one");
    }

}