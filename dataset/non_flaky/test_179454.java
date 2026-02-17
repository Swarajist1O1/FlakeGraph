class DummyClass_179454 {
    @Test
    public void testUpdateByPrimaryKeySelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserTimestampMapper mapper = sqlSession.getMapper(UserTimestampMapper.class);
            UserTimestamp user = mapper.selectByPrimaryKey(999);
            assertNotNull(user);
            Timestamp joinDate = user.getJoinDate();
            int count = mapper.updateByPrimaryKeySelective(user);
            assertEquals(1, count);

            user = mapper.selectByPrimaryKey(999);
            assertFalse(joinDate.equals(user.getJoinDate()));
        } finally {
            sqlSession.close();
        }
    }

}