class DummyClass_179446 {
    @Test
    public void testSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            DemoCountryMapper mapper = sqlSession.getMapper(DemoCountryMapper.class);
            List<DemoCountry> countries = mapper.selectAll();
            System.out.println(countries.size());
        } finally {
            sqlSession.close();
        }
    }

}