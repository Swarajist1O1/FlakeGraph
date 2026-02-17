class DummyClass_179453 {
    @Test
    public void testUpdate() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserTimestampMapper mapper = sqlSession.getMapper(UserTimestampMapper.class);
            UserTimestamp user = mapper.selectByPrimaryKey(999);
            assertNotNull(user);
            Timestamp joinDate = user.getJoinDate();
            int count = mapper.updateByPrimaryKey(user);
            assertEquals(1, count);

            user = mapper.selectByPrimaryKey(999);
            assertFalse(joinDate.equals(user.getJoinDate()));
        } finally {
            sqlSession.close();
        }
    }

}