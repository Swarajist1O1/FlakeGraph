class DummyClass_177202 {
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" +
                                             httpPort +
                                             "/tomcat/api/rest/v1/greeting",
                                             String.class))
                .contains("Hello, World!");
    }

}