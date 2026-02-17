class DummyClass_110898 {
    @Test
    public void updateOne() {
        final User userForInsert = putUserBlocking();

        final User userForUpdate = User.newInstance(
                userForInsert.id(), // using id of inserted user
                "new@email.com" // new value
        );

        updateUserBlocking(userForUpdate);
        checkOnlyOneItemInStorage(userForUpdate);  // update should not add new rows!
    }

}