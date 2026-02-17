class DummyClass_159553 {
    @Test
    public void delete() {
        RealmConfiguration configuration = testRealm.getConfiguration();
        try {
            Realm.deleteRealm(configuration);
            fail("Realm.deleteRealm should fail with illegal state");
        } catch (IllegalStateException ignored) {
        }

        // Nothing should happen when delete a closed in-mem-realm.
        testRealm.close();
        testRealm = null;
        assertTrue(Realm.deleteRealm(configuration));
    }

}