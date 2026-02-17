class DummyClass_179498 {
    @Test
    public void testDelete(){
        SqlSession sqlSession = getSqlSession();
        try {
            User2Mapper userMapper = sqlSession.getMapper(User2Mapper.class);
            Assert.assertEquals(1, userMapper.deleteByPrimaryKey(1));

            User2 user = new User2();
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