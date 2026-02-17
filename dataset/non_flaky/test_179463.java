class DummyClass_179463 {
    @Test
    public void testUserAutoIncrementIdentity() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserAutoIncrementIdentityMapper mapper = sqlSession.getMapper(UserAutoIncrementIdentityMapper.class);

            UserAutoIncrementIdentity user = new UserAutoIncrementIdentity();
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