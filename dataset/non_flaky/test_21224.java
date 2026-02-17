class DummyClass_21224 {
    @Test
    public void testSharedPrefNotNullIfNoDSMgr() {
        assertNotNull(mManager.getSharedPreferences());
    }

}