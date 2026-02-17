class DummyClass_77542 {
    @Test
    public void testGetImmutableListOfPersons() {
        assertThat(resourceTestRule.target("/person/blah/list").request()
                .get(new GenericType<List<Person>>() {
                })).isEqualTo(Collections.singletonList(person));
    }

}