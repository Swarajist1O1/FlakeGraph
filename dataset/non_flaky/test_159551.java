class DummyClass_159551 {
    @Test
    public void inMemoryRealm() {
        testRealm.beginTransaction();
        Dog dog = testRealm.createObject(Dog.class);
        dog.setName("DinoDog");
        testRealm.commitTransaction();

        assertEquals(testRealm.where(Dog.class).count(), 1);
        assertEquals(testRealm.where(Dog.class).findFirst().getName(), "DinoDog");

        testRealm.close();
        // After all references to the in-mem-realm closed,
        // in-mem-realm with same identifier should create a fresh new instance.
        testRealm = Realm.getInstance(inMemConf);
        assertEquals(testRealm.where(Dog.class).count(), 0);
    }

}