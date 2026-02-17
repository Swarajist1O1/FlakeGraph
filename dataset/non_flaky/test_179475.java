class DummyClass_179475 {
    @Test
    public void testDelete(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Assert.assertEquals(1, userMapper.deleteByPrimaryKey(1));

            User user = new User();
            user.setState(StateDictEnum.enabled);
            Assert.assertEquals(0, userMapper.delete(user));

            user = new User();
            user.setLock(LockDictEnum.unlocked);
            Assert.assertEquals(0, userMapper.delete(user));

            user = new User();
            user.setLock(LockDictEnum.locked);
            user.setState(StateDictEnum.disabled);
            Assert.assertEquals(1, userMapper.delete(user));
        } finally {
            sqlSession.close();
        }
    }

}