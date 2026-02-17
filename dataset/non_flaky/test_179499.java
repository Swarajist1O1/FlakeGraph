class DummyClass_179499 {
    @Test
    public void testSelect(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.selectAll();
            Assert.assertNotNull(users);
            Assert.assertEquals(2, users.size());

            Assert.assertEquals("abel533", users.get(0).getName());
            Assert.assertEquals("Hebei", users.get(0).getAddress().getProvince());
            Assert.assertEquals("Shijiazhuang", users.get(0).getAddress().getCity());
            Assert.assertEquals(StateEnum.enabled, users.get(0).getState());

            Assert.assertEquals("isea533", users.get(1).getName());
            Assert.assertEquals("Hebei/Handan", users.get(1).getAddress().toString());
            Assert.assertEquals(StateEnum.disabled, users.get(1).getState());

            User user = userMapper.selectByPrimaryKey(1);
            Assert.assertEquals("abel533", user.getName());
            Assert.assertEquals("Hebei", user.getAddress().getProvince());
            Assert.assertEquals("Shijiazhuang", user.getAddress().getCity());
            Assert.assertEquals(StateEnum.enabled, user.getState());
        } finally {
            sqlSession.close();
        }
    }

}