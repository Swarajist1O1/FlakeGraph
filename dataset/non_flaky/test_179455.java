class DummyClass_179455 {
    @Test
    public void testUpdateInt() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserIntMapper mapper = sqlSession.getMapper(UserIntMapper.class);
            UserInt user = mapper.selectByPrimaryKey(999);
            assertNotNull(user);
            Integer age = user.getAge();
            int count = mapper.updateByPrimaryKey(user);
            assertEquals(1, count);

            user = mapper.selectByPrimaryKey(999);
            assertFalse(age.equals(user.getAge()));
        } finally {
            sqlSession.close();
        }
    }

}