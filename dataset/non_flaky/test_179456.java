class DummyClass_179456 {
    @Test
    public void testUpdateIntByPrimaryKeySelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserIntMapper mapper = sqlSession.getMapper(UserIntMapper.class);
            UserInt user = mapper.selectByPrimaryKey(999);
            assertNotNull(user);
            Integer age = user.getAge();
            int count = mapper.updateByPrimaryKeySelective(user);
            assertEquals(1, count);

            user = mapper.selectByPrimaryKey(999);
            assertFalse(age.equals(user.getAge()));
        } finally {
            sqlSession.close();
        }
    }

}