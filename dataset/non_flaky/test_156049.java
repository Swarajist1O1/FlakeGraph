class DummyClass_156049 {
    @Test
    public void testMatchFailure() {
        // Match a string that most likely will never become a Kind's value
        Assert.assertNull(Kind.match("djkaskjsf7s98f989389589a9f8a998935"));
    }

}