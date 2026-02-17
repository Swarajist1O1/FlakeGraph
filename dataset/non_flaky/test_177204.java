class DummyClass_177204 {
    @Test
    public void greetingShouldReturn404() throws Exception {
        assertThat(restTemplate.getForEntity("http://localhost:" +
                                             httpPort +
                                             "/tomcat/api/rest/v1/greet",
                                             Void.class)
                               .getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

}