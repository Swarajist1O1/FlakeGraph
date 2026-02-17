class DummyClass_156059 {
    @Test
    public void testDefaultConstructor() {
        RedditApp app = new RedditWebApp(clientID, clientSecret, redirectURI);
        new RedditOAuthAgent(userAgent, app);
        app = new RedditScriptApp(clientID, clientSecret, redirectURI);
        new RedditOAuthAgent(userAgent, app);
        app = new RedditInstalledApp(clientID, redirectURI);
        new RedditOAuthAgent(userAgent, app);
    }

}