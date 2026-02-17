class DummyClass_162345 {
    @Test
    public void testUnionSource() {
        DatasetAccessor accessor = source.getAccessor();
        accessor.add(TEST + "g1", createGraph("graph1"));
        accessor.add(TEST + "g2", createGraph("graph2"));
        TestUtil.testArray(checkGraphs(), new String[]{"graph1", "graph2"});
        
        accessor.putModel(TEST + "g1", createGraph("graph1-b"));
        TestUtil.testArray(checkGraphs(), new String[]{"graph1-b", "graph2"});
        
        accessor.deleteModel(TEST + "g2");
        TestUtil.testArray(checkGraphs(), new String[]{"graph1-b"});
    }

}