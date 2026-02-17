class DummyClass_77543 {
    @Test
    public void testGetPersonWithQueryParam() {
        // Test to ensure that the dropwizard validator is registered so that
        // it can validate the "ind" IntParam.
        assertThat(resourceTestRule.target("/person/blah/index")
                .queryParam("ind", 0).request()
                .get(Person.class))
                .isEqualTo(person);
        verify(peopleStore).fetchPerson("blah");
    }

}