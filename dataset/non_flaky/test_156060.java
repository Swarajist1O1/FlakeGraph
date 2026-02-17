class DummyClass_156060 {
    @Test
    public void testGenerateCodeFlowURI() {
        RedditScopeBuilder builder = new RedditScopeBuilder();
        builder.addScope(RedditScope.EDIT);
        String url = subject.generateCodeFlowURI(builder, RedditDuration.PERMANENT);
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid(url));
    }

}