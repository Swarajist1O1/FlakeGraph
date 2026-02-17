class DummyClass_110900 {
    @Test
    public void updateNotNullFieldToNull() {
        final User userForInsert = User.newInstance(null, "user@email.com", "1-999-547867"); // phone not null

        putUserBlocking(userForInsert);

        final User userForUpdate = User.newInstance(
                userForInsert.id(),
                userForInsert.email(),
                null    // phone is null
        );

        updateUserBlocking(userForUpdate);
        checkOnlyOneItemInStorage(userForUpdate);
    }

}