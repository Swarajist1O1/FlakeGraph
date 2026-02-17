class DummyClass_156058 {
    @Test
    public void testTimeSensitiveExpiration() {
        
        RedditToken subject = new RedditToken(jsonToken);
        RedditToken subjectUserProvided = new RedditToken(accessToken, tokenType, expiresIn2, scope);
        
        assertFalse(subjectUserProvided.willExpireIn(expiresIn2 - 60));
        assertTrue(subjectUserProvided.willExpireIn(expiresIn2 + 60));
        assertFalse(subjectUserProvided.isExpired());
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertTrue(subject.isExpired());
        assertTrue(subject.getExpiration() < (System.currentTimeMillis() / 1000));
        
    }

}