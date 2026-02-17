class DummyClass_179452 {
    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserTimestampMapper mapper = sqlSession.getMapper(UserTimestampMapper.class);
            UserTimestamp user = new UserTimestamp();
            user.setId(1);
            user.setJoinDate(new Timestamp(System.currentTimeMillis()));
            int count = mapper.insert(user);
            assertEquals(1, count);
        } finally {
            sqlSession.close();
        }
    }

}