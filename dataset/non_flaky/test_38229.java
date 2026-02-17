class DummyClass_38229 {
    @Test
    public void testHashString() throws Exception {
        String testStr = null;
        long hash = TextUtils.hashString(testStr);
        assertEquals(0, hash);

        testStr = "Allen cheats at Race for the Galaxy.";
        hash = TextUtils.hashString(testStr);
        assertEquals(1133932183, hash);
    }

}