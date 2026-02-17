class DummyClass_179473 {
    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = new User();
            user.setId(3);
            user.setName("liuzh");
            user.setLock(LockDictEnum.unlocked);
            user.setState(StateDictEnum.enabled);

            Assert.assertEquals(1, userMapper.insert(user));

            user = userMapper.selectByPrimaryKey(3);
            Assert.assertEquals("liuzh", user.getName());
            Assert.assertEquals(LockDictEnum.unlocked, user.getLock());
            Assert.assertEquals(StateDictEnum.enabled, user.getState());
        } finally {
            sqlSession.close();
        }
    }

}