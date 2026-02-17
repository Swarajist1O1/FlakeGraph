class DummyClass_176819 {
    @Test
    public void snapshotNumberShouldBeLessThan10Digits() {
        FilterableRequestSpecification requestSpec = mock(FilterableRequestSpecification.class);
        when(requestSpec.getURI()).thenReturn("http://www.google.com");
        when(requestSpec.getHeaders()).thenReturn(new Headers());

        WebCustomRequest request = mapper.map(requestSpec);
        assertThat(request, hasProperty("snapshot", CustomMatchers.stringWithLength(lessThanOrEqualTo(15)))); //10 digits + t + .inf
    }

}