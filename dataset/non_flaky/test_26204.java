class DummyClass_26204 {
    @Test
    public void testEqualsContract()
    {
        EqualsVerifier.forClass(LockCache.LockKey.class).usingGetClass().verify();
    }

}