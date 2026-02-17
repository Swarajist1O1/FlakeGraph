class DummyClass_179497 {
    @Test
    public void testUpdate(){
        SqlSession sqlSession = getSqlSession();
        try {
            User2Mapper userMapper = sqlSession.getMapper(User2Mapper.class);
            User2 user = userMapper.selectByPrimaryKey(1);
            Assert.assertEquals("abel533", user.getName());
            Assert.assertEquals("Hebei", user.getAddress().getProvince());
            Assert.assertEquals("Shijiazhuang", user.getAddress().getCity());
            Assert.assertEquals(StateEnum.enabled, user.getState());

            user.setState(StateEnum.disabled);
            user.getAddress().setCity("Handan");
            Assert.assertEquals(1, userMapper.updateByPrimaryKey(user));

            user = userMapper.selectByPrimaryKey(1);
            Assert.assertEquals("abel533", user.getName());
            Assert.assertEquals("Hebei", user.getAddress().getProvince());
            Assert.assertEquals("Handan", user.getAddress().getCity());
            Assert.assertEquals(StateEnum.disabled, user.getState());
        } finally {
            sqlSession.close();
        }
    }

}