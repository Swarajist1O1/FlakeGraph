class DummyClass_179465 {
    @Test
    public void testUserSqlBefore() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserSqlBeforeMapper mapper = sqlSession.getMapper(UserSqlBeforeMapper.class);

            UserSqlBefore user = new UserSqlBefore();
            user.setName("liuzh");
            Assert.assertEquals(1, mapper.insert(user));
            Assert.assertEquals(new Integer(12345), user.getId());

            user = mapper.selectByPrimaryKey(12345);
            Assert.assertEquals("liuzh", user.getName());
        } finally {
            sqlSession.close();
        }
    }

}