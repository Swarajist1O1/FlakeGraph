class DummyClass_91584 {
    @Test
    public void test() {
        RandomSampler<String> s = new RandomSampler<String>();
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            data.add(String.valueOf(i));
        }

        List<String> result = s.sample(data, 50);
        System.out.println(result);
        assertEquals(50, result.size());
    }

}