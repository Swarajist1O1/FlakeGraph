class DummyClass_179462 {
    @Test
    public void testUserAutoIncrement() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserAutoIncrementMapper mapper = sqlSession.getMapper(UserAutoIncrementMapper.class);

            UserAutoIncrement user = new UserAutoIncrement();
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