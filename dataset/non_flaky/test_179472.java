class DummyClass_179472 {
    @Test
    public void testSelect(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.selectAll();
            Assert.assertNotNull(users);
            Assert.assertEquals(2, users.size());

            Assert.assertEquals("abel533", users.get(0).getName());
            Assert.assertEquals(LockDictEnum.unlocked, users.get(0).getLock());
            Assert.assertEquals(StateDictEnum.enabled, users.get(0).getState());

            Assert.assertEquals("isea533", users.get(1).getName());
            Assert.assertEquals(LockDictEnum.locked, users.get(1).getLock());
            Assert.assertEquals(StateDictEnum.disabled, users.get(1).getState());

            User user = userMapper.selectByPrimaryKey(1);
            Assert.assertEquals("abel533", user.getName());
            Assert.assertEquals(LockDictEnum.unlocked, users.get(0).getLock());
            Assert.assertEquals(StateDictEnum.enabled, user.getState());
        } finally {
            sqlSession.close();
        }
    }

}