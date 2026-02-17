class DummyClass_179474 {
    @Test
    public void testUpdate(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectByPrimaryKey(1);
            Assert.assertEquals("abel533", user.getName());
            Assert.assertEquals(LockDictEnum.unlocked, user.getLock());
            Assert.assertEquals(StateDictEnum.enabled, user.getState());

            user.setLock(LockDictEnum.locked);
            user.setState(StateDictEnum.disabled);
            Assert.assertEquals(1, userMapper.updateByPrimaryKey(user));

            user = userMapper.selectByPrimaryKey(1);
            Assert.assertEquals("abel533", user.getName());
            Assert.assertEquals(LockDictEnum.locked, user.getLock());
            Assert.assertEquals(StateDictEnum.disabled, user.getState());
        } finally {
            sqlSession.close();
        }
    }

}