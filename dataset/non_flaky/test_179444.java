class DummyClass_179444 {
    @Test
    public void testMax() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            AggregateCondition aggregateCondition = AggregateCondition.builder().
                    aggregateBy("id").aliasName("aggregation").aggregateType(AggregateType.MAX).groupBy("role");
            Example example = new Example(User.class);
            example.setOrderByClause("role desc");
            List<User> m = mapper.selectAggregationByExample(example, aggregateCondition);
            Assert.assertEquals(2, m.size());
            Assert.assertEquals(new Long(6), m.get(0).getAggregation());
            Assert.assertEquals(new Long(3), m.get(1).getAggregation());
        } finally {
            sqlSession.close();
        }
    }

}