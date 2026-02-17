class DummyClass_159552 {
    @Test
    public void inMemoryRealmWithDifferentNames() {
        testRealm.beginTransaction();
        Dog dog = testRealm.createObject(Dog.class);
        dog.setName("DinoDog");
        testRealm.commitTransaction();

        // Creates the 2nd in-memory Realm with a different name. To make sure they are not affecting each other.
        RealmConfiguration inMemConf2 = configFactory.createConfigurationBuilder()
                .name(IDENTIFIER + "2")
                .inMemory()
                .build();
        Realm testRealm2 = Realm.getInstance(inMemConf2);
        testRealm2.beginTransaction();
        Dog dog2 = testRealm2.createObject(Dog.class);
        dog2.setName("UFODog");
        testRealm2.commitTransaction();

        assertEquals(testRealm.where(Dog.class).count(), 1);
        //noinspection ConstantConditions
        assertEquals(testRealm.where(Dog.class).findFirst().getName(), "DinoDog");
        assertEquals(testRealm2.where(Dog.class).count(), 1);
        //noinspection ConstantConditions
        assertEquals(testRealm2.where(Dog.class).findFirst().getName(), "UFODog");

        testRealm2.close();
    }

}