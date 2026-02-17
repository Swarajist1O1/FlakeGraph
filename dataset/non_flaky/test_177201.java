class DummyClass_177201 {
    @Test
    public void verifySingleConnector() {
        // Relevant to Tomcat 9.0
        assertThat(applicationContext).isInstanceOf(WebServerApplicationContext.class);
        final WebServer webServer = ((WebServerApplicationContext) applicationContext).getWebServer();
        assertThat(webServer).isInstanceOf(TomcatWebServer.class);
        assertThat(((TomcatWebServer) webServer).getTomcat()
                                                .getEngine()
                                                .getService()
                                                .findConnectors()).hasSize(1);
    }

}