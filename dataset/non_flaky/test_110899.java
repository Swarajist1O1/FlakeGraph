class DummyClass_110899 {
    @Test
    public void updateNullFieldToNotNull() {
        final User userForInsert = User.newInstance(null, "user@email.com", null); // phone is null

        putUserBlocking(userForInsert);

        final User userForUpdate = User.newInstance(
                userForInsert.id(),
                userForInsert.email(),
                "1-999-547867"  // phone not null
        );

        updateUserBlocking(userForUpdate);
        checkOnlyOneItemInStorage(userForUpdate);
    }

}