class DummyClass_179496 {
    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try {
            User2Mapper userMapper = sqlSession.getMapper(User2Mapper.class);

            User2 user = new User2();
            user.setId(3);
            user.setName("liuzh");
            Address address = new Address();
            address.setProvince("Hebei");
            address.setCity("Qinhuangdao");
            user.setAddress(address);
            user.setState(StateEnum.enabled);

            Assert.assertEquals(1, userMapper.insert(user));

            user = userMapper.selectByPrimaryKey(3);
            Assert.assertEquals("liuzh", user.getName());
            Assert.assertEquals("Hebei", user.getAddress().getProvince());
            Assert.assertEquals("Qinhuangdao", user.getAddress().getCity());
            Assert.assertEquals(StateEnum.enabled, user.getState());
        } finally {
            sqlSession.close();
        }
    }

}