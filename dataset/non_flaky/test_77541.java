class DummyClass_77541 {
    @Test
    public void testGetPerson() {
        assertThat(resourceTestRule.target("/person/blah").request()
                .get(Person.class))
                .isEqualTo(person);
        verify(peopleStore).fetchPerson("blah");
    }

}