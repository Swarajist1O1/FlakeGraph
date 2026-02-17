class DummyClass_136491 {
    @Test
    public void should_return_case_sensitive() throws Exception {
        //Given
        String name = "dcSdIfk_sdf";

        //When
        final String actual = strategy.apply(name);

        //Then
        assertThat(actual).isEqualTo("\"dcSdIfk_sdf\"");
    }

}