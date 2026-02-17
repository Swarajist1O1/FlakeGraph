class DummyClass_179451 {
    @Test
    public void testSelectByEmptyIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Assert.assertEquals(183, mapper.selectByIdList(new ArrayList<Long>()).size());
        } finally {
            sqlSession.close();
        }
    }

}