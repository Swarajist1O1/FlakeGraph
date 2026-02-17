class DummyClass_110847 {
    @Test
    public void queryAll() {
        final List<User> users = putUsersBlocking(3);
        final List<User> usersFromQuery = getAllUsersBlocking();
        assertThat(users.equals(usersFromQuery)).isTrue();
    }

}