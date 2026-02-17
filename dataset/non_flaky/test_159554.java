class DummyClass_159554 {
    @Test
    public void writeCopyTo() {
        byte[] key = TestHelper.getRandomKey();
        String fileName = IDENTIFIER + ".realm";
        String encFileName = IDENTIFIER + ".enc.realm";
        RealmConfiguration conf = configFactory.createConfigurationBuilder()
                .name(fileName)
                .build();
        RealmConfiguration encConf = configFactory.createConfigurationBuilder()
                .name(encFileName)
                .encryptionKey(key)
                .build();

        Realm.deleteRealm(conf);
        Realm.deleteRealm(encConf);

        testRealm.beginTransaction();
        Dog dog = testRealm.createObject(Dog.class);
        dog.setName("DinoDog");
        testRealm.commitTransaction();

        // Tests a normal Realm file.
        testRealm.writeCopyTo(new File(configFactory.getRoot(), fileName));
        Realm onDiskRealm = Realm.getInstance(conf);
        assertEquals(onDiskRealm.where(Dog.class).count(), 1);
        onDiskRealm.close();

        // Tests a encrypted Realm file.
        testRealm.writeEncryptedCopyTo(new File(configFactory.getRoot(), encFileName), key);
        onDiskRealm = Realm.getInstance(encConf);
        assertEquals(onDiskRealm.where(Dog.class).count(), 1);
        onDiskRealm.close();
        // Tests with a wrong key to see if it fails as expected.
        try {
            RealmConfiguration wrongKeyConf = configFactory.createConfigurationBuilder()
                    .name(encFileName)
                    .encryptionKey(TestHelper.getRandomKey(42))
                    .build();
            Realm.getInstance(wrongKeyConf);
            fail("Realm.getInstance should fail with RealmFileException");
        } catch (RealmFileException expected) {
            assertEquals(expected.getKind(), RealmFileException.Kind.ACCESS_ERROR);
        }
    }

}