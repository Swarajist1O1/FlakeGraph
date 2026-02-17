class DummyClass_156061 {
    @Test
    public void testGenerateImplicitFlowURI() {
        RedditScopeBuilder builder = new RedditScopeBuilder();
        builder.addScope(RedditScope.FLAIR);
        String url = subject.generateImplicitFlowURI(builder);
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid(url));
    }

}