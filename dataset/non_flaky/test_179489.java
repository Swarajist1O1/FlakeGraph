class DummyClass_179489 {
    @Test(expected = PersistenceException.class)
    public void testSafeDeleteByExampleNull() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.deleteByExample(null);
        } finally {
            sqlSession.close();
        }
    }

}