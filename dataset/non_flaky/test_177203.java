class DummyClass_177203 {
    @Test
    public void greetingShouldReturnUsersMessage() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" +
                                             httpPort +
                                             "/tomcat/api/rest/v1/greeting?name=Armeria",
                                             String.class))
                .contains("Hello, Armeria!");
    }

}