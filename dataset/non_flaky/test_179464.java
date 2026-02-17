class DummyClass_179464 {
    @Test
    public void testUserSqlAfter() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserSqlAfterMapper mapper = sqlSession.getMapper(UserSqlAfterMapper.class);

            UserSqlAfter user = new UserSqlAfter();
            user.setName("liuzh");
            Assert.assertEquals(1, mapper.insert(user));
            Assert.assertNotNull(user.getId());

            user = mapper.selectByPrimaryKey(user.getId());
            Assert.assertEquals("liuzh", user.getName());
        } finally {
            sqlSession.close();
        }
    }

}