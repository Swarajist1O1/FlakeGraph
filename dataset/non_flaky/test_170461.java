class DummyClass_170461 {
    @Test
    public void testMethodNameMining()
    {
        assertEquals("fullName", MetaData.toAttributeName("getFullName"));
        assertEquals("fullName", MetaData.toAttributeName("getfullName"));
        assertEquals("fullName", MetaData.toAttributeName("isFullName"));
        assertEquals("fullName", MetaData.toAttributeName("isfullName"));
        assertEquals("fullName", MetaData.toAttributeName("setFullName"));
        assertEquals("fullName", MetaData.toAttributeName("setfullName"));
        assertEquals("fullName", MetaData.toAttributeName("FullName"));
        assertEquals("fullName", MetaData.toAttributeName("fullName"));
    }

}