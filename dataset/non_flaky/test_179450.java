class DummyClass_179450 {
    @Test(expected = Exception.class)
    public void testDeleteByEmptyIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.deleteByIdList(new ArrayList<Long>());
        } finally {
            sqlSession.close();
        }
    }

}