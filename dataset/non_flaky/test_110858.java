class DummyClass_110858 {
    @Test
    public void queryWithRawQuery() {
        final List<User> users = TestFactory.newUsers(20);

        int counter = 1;

        for (int i = 0; i < users.size(); i++) {
            char[] chars = new char[counter++];
            Arrays.fill(chars, '*'); // wtf is going on?
            users.set(i, User.newInstance(null, new String(chars)));
        }

        putUsersBlocking(users);

        final List<User> usersWithLongName = new ArrayList<User>(users.size());

        int lengthSum = 0;
        for (User user : users) {
            lengthSum += user.email().length();
        }

        final int avrLength = lengthSum / users.size();

        for (User user : users) {
            if (user.email().length() > avrLength) {
                usersWithLongName.add(user);
            }
        }

        final String query = "Select * from " + UserTableMeta.TABLE
                + " where length(" + UserTableMeta.COLUMN_EMAIL + ") > "
                + "(select avg(length(" + UserTableMeta.COLUMN_EMAIL + ")) from users)";

        final List<User> usersFromQuery = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(RawQuery.builder()
                        .query(query)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQuery).isEqualTo(usersWithLongName);
    }

}