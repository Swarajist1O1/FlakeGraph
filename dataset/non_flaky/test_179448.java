class DummyClass_179448 {
    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = new ArrayList<User>(countries.length);
            for (int i = 0; i < countries.length; i++) {
                userList.add(new User(countries[i][0], countries[i][1]));
            }
            Assert.assertEquals(countries.length, mapper.insertList(userList));
            for (User user : userList) {
                Assert.assertNotNull(user.getId());
                System.out.println(user.getId());
            }
        } finally {
            sqlSession.close();
        }
    }

}