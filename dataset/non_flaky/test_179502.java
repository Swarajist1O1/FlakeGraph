class DummyClass_179502 {
    @Test
    public void testDelete(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Assert.assertEquals(1, userMapper.deleteByPrimaryKey(1));

            User user = new User();
            Address address = new Address();
            address.setProvince("Hebei");
            address.setCity("Handan");
            user.setAddress(address);
            user.setState(StateEnum.enabled);
            Assert.assertEquals(0, userMapper.delete(user));

            user.setState(StateEnum.disabled);
            Assert.assertEquals(1, userMapper.delete(user));
        } finally {
            sqlSession.close();
        }
    }

}